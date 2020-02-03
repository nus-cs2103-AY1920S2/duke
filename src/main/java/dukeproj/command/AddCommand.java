package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.CommandType;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.tasks.Deadline;
import dukeproj.tasks.Event;
import dukeproj.tasks.Task;
import dukeproj.tasks.Todo;

public class AddCommand extends Command {
    private String description;
    private CommandType type;

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender)
            throws BadDescriptionException, DukeDescriptionException, BadDateException {
        String output = "";
        switch (type) {
        case TODO:
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }

            Task taskToDo = new Todo(description);
            taskList.addTask(taskToDo);
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.ADD) + "\n" + taskToDo;
            break;
        case EVENT:
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int eventDate = description.indexOf("/");
            if (eventDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskEvent = new Event(description.substring(0, eventDate),
                    description.substring(eventDate + 4));
            taskList.addTask(taskEvent);
            calender.addDate(taskEvent);
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.ADD) + "\n" + taskEvent;
            break;
        case DEADLINE:
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int dLineDate = description.indexOf("/");
            if (dLineDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskDLine = new Deadline(description.substring(0, dLineDate),
                    description.substring(dLineDate + 4));
            taskList.addTask(taskDLine);
            calender.addDate(taskDLine);
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.ADD) + "\n" + taskDLine;
            break;
        default:
            break;
        }
        return output;
    }

    public AddCommand(String description, CommandType type) {
        this.description = description;
        this.type = type;
    }
}
