package e0148811.duke;

public class Ui {
    TaskList tasks;

    static final String FORMAT_CORRECTION = "Invalid format for the instruction you gave.\n"
            + "The correct format should be ";

    public void linkToTaskList(TaskList list) {
        tasks = list;
    }

    public void throwInvalidIndexException() throws DukeException {
        throw new DukeException("Invalid index.\n" + tasks.getTotalNumOfTasks()
                + "Please note that the index is one-based (begins with 1 instead of 0).");
    }

    public String returnLoadingError() {
        return "File not found, or cannot be read properly. "
                + "Start with an empty task list.\n";
    }

    public String returnFoundEmptyFile() {
        return "File found but empty. Start with an empty task list.\n";
    }

    public String returnLoadingSuccess() {
        return "File found. Load saved task list.\n";
    }

    public static String greet() {
        return "Hello, this is NUS-Duke. "
                + "Please give me an instruction followed by relevant description.\n";
    }

    public String sayGoodbye() {
        return "Goodbye. See you next time!";
    }

    public String returnErrorMessage(Exception e) {
        return e.getMessage();
    }

    public void throwWrongFormatException(String format) throws DukeException {
        throw new DukeException(FORMAT_CORRECTION + format + ".");
    }

    public void throwIOException() throws DukeException {
        throw new DukeException("IOException occurs.");
    }

    public void throwOtherException(String s) throws DukeException {
        throw new DukeException(s);
    }

    public void throwEmptyLineException() throws DukeException {
        throw new DukeException("Empty line input. "
                + "Please specify an instruction followed by relevant description.\n"
                + "The valid instructions include: todo, deadline, event, list, done, bye.");
    }

    public void throwUnknownCommandException() throws DukeException {
        throw new DukeException("I don't understand this instruction.\n"
                + "The valid instructions include: b/bye, c/clear, d/deadline, " +
                "done, e/vent, f/find, l/list, p/priority, r/remove, t/todo");
    }

    public static String showValidInstructions() {
        String output = "\nThe valid instructions are as follows:\n";
        output += "b/bye: exit the app\n";
        output += "c/clear: clear all tasks or only completed tasks\n";
        output += "d/deadline: add a deadline task into task list\n";
        output += "done: mark a task as done\n";
        output += "e/event: add an event task into task list\n";
        output += "f/find: find tasks by some key word\n";
        output += "l/list: list all tasks or tasks with some priority level\n";
        output += "p/priority: change the priority level of a task\n";
        output += "r/remove: remove a task from task list\n";
        output += "t/todo: add a todo task into task list\n";
        return output;
    }
}
