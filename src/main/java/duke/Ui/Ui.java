package duke.Ui;

import duke.command.IllegalCommandException;

public class Ui {

    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";

        return logo + System.lineSeparator() + welcomeMessage;
    }

    public String showError() {
        return new IllegalCommandException().getMessage();
    }
}
