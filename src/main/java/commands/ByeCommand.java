package commands;

import database.Storage;
import Duke.UI;
import exceptions.DukeException;

/**
 * The class Commands.ByeCommand exit the bot swiftly by saving memory and stopping Invoker.
 */
public class ByeCommand extends UserCommand{

    /**
     * Instantiates a new Bye command.
     */
    ByeCommand() {
     super(1);
    }


    @Override
    public void reply() {
        System.out.println(UI.getReply("bye"));
    }

    @Override
    public boolean action() throws DukeException {
        Storage.saveMemory();
        CommandInvoker.stop();
        return true;
    }

}
