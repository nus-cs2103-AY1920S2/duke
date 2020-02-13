package duke.command;

import duke.exception.UnknownCommandException;

/**
 * Super class for executable commands.
 */
public interface Command {

    static String run() throws UnknownCommandException {
        throw new UnknownCommandException();
    }

}
