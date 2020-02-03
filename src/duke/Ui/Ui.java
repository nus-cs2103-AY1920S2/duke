package duke.Ui;

import duke.command.IllegalCommandException;

public class Ui {

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    public String showError() {
        return new IllegalCommandException().getMessage();
    }
}
