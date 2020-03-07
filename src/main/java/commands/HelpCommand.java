package commands;

import storage.Storage;
import taskList.TaskList;

public class HelpCommand extends Command{
    public static final String COMMAND_WORD = "help";

    public CommandResult execute(TaskList tasks, Storage storage) {
        String helpString = "Hi, it seems that you are in need of some help, let me offer my assistance!\n";
        helpString += "**********************************************************\n";
        helpString += "Duke is your personal task management tool. It has functionalities in built for managing " +
                "different types of tasks that you might have, helping you check whether you have done your tasks " +
                "and helping you clear your different tasks.\n";
        helpString += "__________________________________________________________\n";
        helpString += "The commands that are available are as follows:\n";
        helpString += "Task Creation Commands:\n";
        helpString += "    1. todo [task] - allows you to add a simple todo\n    2. event [task] /at [yyyy-mm-dd] - " +
                "allows you to add a event that is occuring on given date\n    3. deadline [task] /by [yyyy-mm-dd] " +
                "- allows you to add a deadline event occuring on given date\n\n";
        helpString += "Task Action Commands:\n";
        helpString += "    1. list - returns a list of all the current events and their action status\n    2. " +
                "done [index] - completes the item at given [index] (1-indexing) in the list\n    3. " +
                "delete [index] - deletes the item at given [index] (1-indexing) in the list\n" +
                "find [keyword] - returns a list of items that contain the keyword\n\n";
        helpString += "bye - the exit command to close the application\n";
        helpString += "**********************************************************\n";

        return new CommandResult(helpString);
    }
}
