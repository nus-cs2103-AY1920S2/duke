public class Controller {
    public static String readInput(String input) {
        String[] parsedInput = input.split(" ", 2);
        try {
            switch (parsedInput[0]) {
                case "bye":
                    return Output.BYE;
                case "list":
                    if (TaskManager.isEmpty()) {
                        return Output.EMPTY_LIST;
                    }
                    return Output.LIST + TaskManager.printList();
                case "delete":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The task number cannot be empty.");
                    }
                    int deletedTaskNumber = Integer.parseInt(parsedInput[1]);
                    Task deletedTask = TaskManager.deleteTask(deletedTaskNumber);
                    return Output.REMOVE + deletedTask;
                case "clear":
                    TaskManager.clearAll();
                    return Output.CLEAR;
                case "done":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The task number cannot be empty.");
                    }
                    int doneTaskNumber = Integer.parseInt(parsedInput[1]);
                    Task taskDone = TaskManager.markAsDone(doneTaskNumber);
                    return Output.DONE + taskDone;
                case "todo":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    TaskManager.addTask(new Todo(parsedInput[1]));
                    return TaskManager.printTotalTasks();
                case "deadline":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] by = parsedInput[1].split("/");
                    if (by.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The date of a deadline cannot be empty.");
                    }
                    TaskManager.addTask(new Deadline(by[0], by[1]));
                    return TaskManager.printTotalTasks();
                case "event":
                    if (parsedInput.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    String[] at = parsedInput[1].split("/");
                    if (at.length < 2) {
                        throw new DukeException("\t☹ OOPS!!! The date of a event cannot be empty.");
                    }
                    TaskManager.addTask(new Event(at[0], at[1]));
                    return TaskManager.printTotalTasks();
                default:
                    throw new DukeException("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.err.println(e);
        }
        return "Please try again.";
    }
}
