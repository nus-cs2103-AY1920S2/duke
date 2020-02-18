package commands;

import database.Storage;
import duke.UI;
import exceptions.DukeException;

/**
 * The class Commands.ByeCommand exit the bot swiftly by
 *  saving memory and stopping Invoker.
 */
public class ByeCommand extends UserCommand {

    /**
     * Instantiates a new Bye command.
     */
    ByeCommand() {
     super(1);
    }


    @Override
    public String reply() {
        return (UI.getReply("bye"));
    }

    @Override
    public void action() throws DukeException {
        Storage.saveMemory();
        CommandInvoker.stop();
    }

}
