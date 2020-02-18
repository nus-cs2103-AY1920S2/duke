package e0148811.duke;

public class Ui {
    TaskList tasks;

    static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String FORMAT_CORRECTION = "Invalid format for the instruction you gave.\n"
            + "The correct format should be ";

    public void linkToTaskList(TaskList list) {
        tasks = list;
    }

    public void throwInvalidIndexException() throws DukeException {
        throw new DukeException("Invalid index.\n" + tasks.getTotalNumOfTasks()
                + " Please note that the index is one-based (begins with 1 instead of 0).");
    }

    public void showLoadingError() {
        System.out.println("File not found, or cannot be read properly. "
                + "Start with an empty task list.");
    }

    public void showLogo() {
        System.out.println(LOGO);
    }

    public void greet() {
        System.out.println("Hello, this is NUS-Duke. "
                + "Please give me an instruction followed by relevant description.");
    }

    public void sayGoodbye() {
        System.out.println("Goodbye. See you next time!");
    }

    public void printErrorMessage(Exception e) {
        System.err.println(e.getMessage());
    }

    public void throwWrongFormatException(String format) throws DukeException {
        throw new DukeException(FORMAT_CORRECTION + format);
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
}
