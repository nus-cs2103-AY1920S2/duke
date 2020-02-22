package liaomeng.duke;

public class Ui {
    TaskList tasks;

    static final String FORMAT_CORRECTION = "Invalid format for the instruction you gave.\n"
            + "The correct format should be ";

    public void linkToTaskList(TaskList list) {
        tasks = list;
    }

    public void throwInvalidIndexException() throws DukeException {
        throw new DukeException("Invalid index.\n" + tasks.getTotalNumOfTasks()
                + "Please note that the index is one-based (index begins with 1 instead of 0).");
    }

    public String returnLoadingError() {
        return "File not found. Start with an empty task list.\n";
    }

    public String returnFoundEmptyFile() {
        return "File found but empty. Start with an empty task list.\n";
    }

    public String returnLoadingSuccess() {
        return "File found. Load saved task list.\n";
    }

    public static String greet() {
        return "Hello, this is NUS-Duke."
                + " Please give me an instruction followed by relevant description.\n";
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

    public void throwIoException() throws DukeException {
        throw new DukeException("IOException occurs.\nYou may have placed the application in a folder "
                + "that requires administrator permission to read and write files. "
                + "Please move the application to another folder and try again.");
    }

    public void throwOtherException(String s) throws DukeException {
        throw new DukeException(s);
    }

    public void throwEmptyLineException() throws DukeException {
        throw new DukeException("Empty line input. "
                + "Please specify an instruction followed by relevant description.\n"
                + "Valid instructions include: b/bye, c/clear, d/deadline, "
                + "done, e/event, f/find, hi/hello, l/list, p/priority, r/remove, t/todo.");
    }

    public void throwUnknownCommandException() throws DukeException {
        throw new DukeException("I don't understand this instruction.\n"
                + "Valid instructions include: b/bye, c/clear, d/deadline, "
                + "done, e/event, f/find, hi/hello, l/list, p/priority, r/remove, t/todo.");
    }

    public static String showValidInstructions() {
        String output = "The valid instructions are as follows:\n";
        output += "-- Add a task into the task list --\n";
        output += "    - t/todo [string]: add a todo task\n";
        output += "    - e/event [string] /by YYYY-MM-DD: add an event task\n";
        output += "    - d/deadline [string] /at YYYY-MM-DD: add a deadline task\n";
        output += "- done [integer]: mark a task as done\n";
        output += "- f/find [key-word]: find tasks by searching some key word\n";
        output += "- l/list: list all tasks\n";
        output += "- l/list [level]: list tasks with a specified priority level\n";
        output += "- p/priority [integer] [level]: change the priority level of a task\n";
        output += "- r/remove [integer]: remove a task from task list\n";
        output += "- c/clear a/all/d/done: clear all tasks or only completed tasks\n";
        output += "- hi/hello: show this greeting message again\n";
        output += "- b/bye: exit the app\n";
        return output;
    }
}
