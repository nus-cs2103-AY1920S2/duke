package e0148811.duke;

public class Ui {
    final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    final String FORMAT_CORRECTION = "Invalid format for the instruction you gave.\n"
            + "The correct format should be ";

    public void showLoadingError() {
        System.out.println("File not found, or cannot be read properly. "
                + "Start with an empty task list.");
    }

    public void showLogo() {
        System.out.println(logo);
    }

    public void greet() {
        System.out.println("Hello, this is Duke. "
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
                + "The valid instructions include: todo, deadline, event, list, done, bye.");
    }
}
