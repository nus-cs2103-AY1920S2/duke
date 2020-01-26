package commands;

import exceptions.DukeException;
import processor.DukeProcessor;

/**
 * Command interface. Forms the base for all commands of Duke.
 */
public interface Command {

    /**
     * Execute method to be implemented by all classes that derive from this interface.
     * @param processor The instantiated DukeProcessor object.
     * @param args The arguments as entered by the user.
     * @throws DukeException
     */
    public void execute(DukeProcessor processor, String args) throws DukeException;
}
