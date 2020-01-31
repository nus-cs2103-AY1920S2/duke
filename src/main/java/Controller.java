import java.time.LocalDate;

public class Controller {
    public static String readInput(String input) {
        String[] parsedInput = input.split(" ", 2);
        try {
            switch (parsedInput[0]) {
                case "bye":
                    return UI.BYE;
                case "list":
                    if (TaskList.isEmpty()) {
                        return UI.EMPTY_LIST;
                    }
                    return UI.LIST + TaskList.printList();
                case "delete":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The task number cannot be empty.");
                    }
                    int deletedTaskNumber = Integer.parseInt(parsedInput[1]);
                    Task deletedTask = TaskList.deleteTask(deletedTaskNumber);
                    return UI.REMOVE + deletedTask;
                case "clear":
                    TaskList.clearAll();
                    return UI.CLEAR;
                case "done":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The task number cannot be empty.");
                    }
                    int doneTaskNumber = Integer.parseInt(parsedInput[1]);
                    Task taskDone = TaskList.markAsDone(doneTaskNumber);
                    return UI.DONE + taskDone;
                case "todo":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    TaskList.addTask(new Todo(parsedInput[1]));
                    return TaskList.printTotalTasks();
                case "deadline":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] by = parsedInput[1].split("/");
                    if (by.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    TaskList.addTask(new Deadline(by[0], LocalDate.parse(by[1])));
                    return TaskList.printTotalTasks();
                case "event":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] at = parsedInput[1].split("/");
                    if (at.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The date of a event cannot be empty.");
                    }
                    TaskList.addTask(new Event(at[0], LocalDate.parse(at[1])));
                    return TaskList.printTotalTasks();
                default:
                    throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.err.println(e);
        }
        return "Please try again.";
    }
}
