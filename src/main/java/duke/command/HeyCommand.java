package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HeyCommand extends Command {
    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        String logo = " ______ _____  _____ _____ \n"
                + "|  ____|  __ \\|_   _/ ____|\n"
                + "| |__  | |__) | | || | \n"
                + "|  __| |  _  /  | || | \n"
                + "| |____| | \\ \\ _| || |__\n"
                + "|______|_|  \\_\\_____\\_____|\n";
        String greetings = "Hi Stan! This is\n" + logo + "\nGive me some things to do so I can lose some weight!\n";
        return greetings;
    }
}
