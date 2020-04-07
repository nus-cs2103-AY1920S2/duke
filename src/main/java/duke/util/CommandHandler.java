package duke.util;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

import java.io.IOException;

/**
 * The class that handles all commands entered.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
 * 11 Feb 2020
 *
 * @author Jel
 */
public class CommandHandler {
    private TaskList tasks;
    
    public CommandHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    protected String handleCommand(String[] parsedInput, int taskNum, String keyword) {
        String cmd = parsedInput[0];
        String descAndDate = parsedInput[1];

        try {
            switch (cmd) {
            case "list":
                return tasks.listTasks();
            case "todo":
                return tasks.addTodo(descAndDate);
            case "deadline":
                return tasks.addDeadlineOrEvt(" /by ", descAndDate);
            case "event":
                return tasks.addDeadlineOrEvt(" /at ", descAndDate);
            case "done":
                return tasks.markTaskAsDone(taskNum);
            case "unmark":
                return tasks.markTaskNotDone(taskNum);
            case "delete":
                return tasks.deleteTask(taskNum);
            case "find":
                return tasks.findTask(keyword);
            case "help":
                return this.provideHelp();
            default:
                throw new InvalidCommandException();
            }
        } catch (IOException | DukeException e) {
            StringBuilder sb = new StringBuilder("____________________________________________________________\n");
            sb.append(e).append("\n").append("____________________________________________________________");
            return sb.toString();
        }
    }

    private String provideHelp() {
        return "Below is the list of commands you can use.\n"
                + "\tlist - lists all tasks in the list.\n"
                + "\ttodo {description} - creates todo task.\n"
                + "\tevent {description} /at {YYYY-MM-DD} - creates event task.\n"
                + "\tdeadline {description} /by {YYYY-MM-DD} - creates deadline task.\n"
                + "\tfind {search phrase} - finds tasks with matching description.\n"
                + "\tdone {task index} - marks task as done.\n"
                + "\tdelete {task index} - deletes task from task list permanently.\n"
                + "\tunmark {task index} - marks a previously done task as not done.\n"
                + "\tbye - closes the application.\n"
                + "\thelp - gets this list of help information.";
    }
}
