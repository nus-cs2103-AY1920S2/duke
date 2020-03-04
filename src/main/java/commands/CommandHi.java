package commands;

import processor.DukeProcessor;

/**
 * Command to say hello to the user.
 */
public class CommandHi implements Command {

    /**
     * Says hello to the user.
     * @param processor The instantiated DukeProcessor object.
     * @param args      The arguments as entered by the user.
     */
    public String execute(DukeProcessor processor, String args) {
        return "Hello from Duke! What can I help you with today?";
    }
}
