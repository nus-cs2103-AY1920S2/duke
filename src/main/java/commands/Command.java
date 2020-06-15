package commands;

import exceptions.DukeException;

/**
 * An interface skeleton for all UserCommands
 */
public interface Command {
    /**
     * Replies User when command is executed
     *
     * @return String the response
     * @throws DukeException the duke exception
     */
     String reply() throws DukeException;

    /**
     * Executes the Action of the Commands.Command
     *
     * @return the boolean
     * @throws DukeException the duke exception
     */
     void action() throws DukeException;

    /**
     * Returns the ID of the command
     *
     * @return the id
     */
     public int getID();
}
