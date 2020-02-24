package duke.commands;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.enums.ErrorCodes;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.Tag;
import duke.tags.TagList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Represents a Command object specifically for adding a task to the TaskList.
 *
 * @author Firzan Armani
 */
public class AddCommand extends Command {
    private TaskTypes taskType;
    private String taskName;
    private LocalDate taskDate;
    private ArrayList<Tag> tags;

    /**
     * AddCommand constructor.
     *
     * @param taskType TaskTypes enum representing the type of task to be added
     * @param inputArgs The combined arguments of the input task
     */
    public AddCommand(TaskTypes taskType, String taskName, ArrayList<Tag> tags) throws DukeException {
        this.taskType = taskType;
        this.taskName = taskName;
        this.tags = tags;
    }

    public AddCommand(TaskTypes taskType, String taskName, ArrayList<Tag> tags, LocalDate taskDate) throws DukeException {
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.tags = tags;
    }

    @Override
    public String execute(TaskList tasks, TagList tagList, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        String taskPrompt = "";

        switch (taskType) {
        case DEADLINE:
            newTask = new Deadline(taskName, taskDate, tags);
            taskPrompt = "Oooh, important deadline eh, boss? Don't worry, I got it";
            break;
        case TODO:
            newTask = new ToDo(taskName, tags);
            taskPrompt = "Got it, boss. I'll write this one down";
            break;
        case EVENT:
            newTask = new Event(taskName, taskDate, tags);
            taskPrompt = "A special event I see. Don't worry boss, I'll remind you";
            break;
        default:
            throw new DukeException(ErrorCodes.UNKNOWN_COMMAND);
        }
        tasks.addTask(newTask);
        for (Tag tag : newTask.getTags()) {
            tagList.addTag(tag);
        }
        ui.dukePrompt(new String[]{taskPrompt,
            "\n",
            newTask.toString(),
            "\n",
            tasks.printTasksTotal()});
        storage.save(tasks);
        return taskPrompt
            + "\n"
            + newTask.toString()
            + "\n"
            + tasks.printTasksTotal();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}