package duke.commands;

import duke.ui.Ui;

public class HelpCommand implements Command {

    /**
     * Prints out the list of available commands
     */
    public static String execute()  {
        StringBuilder output = new StringBuilder();

        output.append("Hi Patrick, these are the things you can do!\n");
        output.append(Ui.printCommands());
        return output.toString();
    }
}
