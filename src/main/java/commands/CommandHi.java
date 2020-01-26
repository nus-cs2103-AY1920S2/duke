package commands;

import processor.DukeProcessor;
import processor.Ui;

/**
 * Command to say hello to the user.
 */
public class CommandHi implements Command {

    /**
     * Says hello to the user.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public void execute(DukeProcessor processor, String args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Ui.print("Hello from\n" + logo);
        Ui.print("What can I help you with today? :)");
    }
}
