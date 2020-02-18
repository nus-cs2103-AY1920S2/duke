package commands; /*
*  "Duke.UI"
* */

import exceptions.DukeException;

/**
 * The type Commands.Command invoker.
 */
public class CommandInvoker {

    private static boolean isProgramEnd = false;

    /**
     * Instantiates a new Commands.Command invoker.
     */
    public CommandInvoker() {
    };


    /**
     * Invoke the Usercommand.
     *
     * @param c the Commands.UserCommand
     */
    public static String invoke(UserCommand c) throws DukeException {
        c.action();
        return c.reply();
    }

    /**
     * Stop the program. Sets the endProgram as true
     */
    public static void stop() {
        isProgramEnd = true;
    }

    /**
     * Checks if program is turning off
     *
     * @return the boolean
     */
    public static boolean isExit() {
        return isProgramEnd;
    }

}
