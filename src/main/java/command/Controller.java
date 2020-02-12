package command;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

import java.time.LocalDate;

/**
 * Parses user input and instruct corresponding classes to perform specified tasks.
 */
public class Controller {
    /**
     * Parses user input and determine specified instructions to execute.
     *
     * @param input input received from user.
     * @return output to be displayed to user.
     */
    public static String readInput(String input, TaskList taskList) throws DukeException {
        String[] parsedInput = input.split(" ", 2);
        try {
            switch (parsedInput[0]) {
                case "bye":
                    return UI.BYE;
                case "list":
                    if (taskList.isEmpty()) {
                        return UI.EMPTY_LIST;
                    }
                    return UI.LIST + taskList.printList();
                case "delete":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The task number cannot be empty.");
                    }
                    assert parsedInput.length == 2 : "Invalid arguments";
                    int deletedTaskNumber = Integer.parseInt(parsedInput[1]);
                    Task deletedTask = taskList.getTask(deletedTaskNumber);
                    taskList.deleteTask(deletedTaskNumber);
                    return UI.REMOVE + deletedTask;
                case "clear":
                    taskList.clearAll();
                    return UI.CLEAR;
                case "done":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The task number cannot be empty.");
                    }
                    int doneTaskNumber = Integer.parseInt(parsedInput[1]);
                    Task taskDone = taskList.getTask(doneTaskNumber);
                    taskList.markAsDone(doneTaskNumber);
                    return UI.DONE + taskDone;
                case "find":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\tPlease indicate a keyword.");
                    }
                    assert parsedInput.length == 2 : "Invalid arguments";
                    String keyword = parsedInput[1];
                    return UI.LIST + taskList.findTaskContainingKeyword(keyword);
                case "todo":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    taskList.addTask(new Todo(parsedInput[1]));
                    return taskList.printTotalTasks();
                case "deadline":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    assert parsedInput.length == 2 : "Invalid arguments";
                    String[] by = parsedInput[1].split("/");
                    if (by.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    taskList.addTask(new Deadline(by[0], LocalDate.parse(by[1])));
                    return taskList.printTotalTasks();
                case "event":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] at = parsedInput[1].split("/");
                    if (at.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The date of a event cannot be empty.");
                    }
                    taskList.addTask(new Event(at[0], LocalDate.parse(at[1])));
                    return taskList.printTotalTasks();
                default:
                    throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.err.println(e);
        }
        return "Please try again.";
    }
}
