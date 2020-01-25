package commands;

import processor.DukeProcessor;
import processor.Ui;

public class CommandHi implements Command {

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
