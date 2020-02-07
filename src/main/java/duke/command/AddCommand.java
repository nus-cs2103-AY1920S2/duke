package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.Ui;

public class AddCommand extends Command {
    private String taskDescriptor;
    private String taskName;
    private String timePeriod;

    public AddCommand(String descriptor, String taskName) {
        this.taskDescriptor = descriptor;
        this.taskName = taskName;
    }

    public AddCommand(String descriptor, String eventName, String timePeriod) {
        this.taskDescriptor = descriptor;
        this.taskName = eventName;
        this.timePeriod = timePeriod;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String result = "";
        switch (taskDescriptor) {
            case "todo":
                Task newTodo = new Todo(taskName);
                taskList.addTask(newTodo);
                result = ui.printAddTaskMessage(newTodo);
                break;

            case "event":
                Event newEvent = new Event(taskName, timePeriod);
                taskList.addTask(newEvent);
                result = ui.printAddTaskMessage(newEvent);
                break;

            case "deadline":
                Deadline newDeadline = new Deadline(taskName, timePeriod);
                taskList.addTask(newDeadline);
                result = ui.printAddTaskMessage(newDeadline);
                break;
        }
        return result;
    }
}