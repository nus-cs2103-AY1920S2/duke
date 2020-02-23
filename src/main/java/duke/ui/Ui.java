package duke.ui;

import duke.exception.DukeNoSuchInputException;

public interface Ui {
    final String ASCII_LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    final String WELCOME_MESSAGE
            = String.format("Hello from \n%s", Ui.ASCII_LOGO);
    final String GOODBYE_MESSAGE = "Goodbye!";

    void begin();

    String readInput() throws DukeNoSuchInputException;

    void print(String message);

    void printException(Exception e);

    void end();
}
