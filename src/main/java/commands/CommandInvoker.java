package commands;/*
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
    public CommandInvoker(){};

    /**
     * Invoke userCommand
     *
     * @param c the userCommand
     */
    CommandInvoker(UserCommand c){
        invoke(c);
    }


    /**
     * Invoke the Usercommand.
     *
     * @param c the Commands.UserCommand
     */
    public static void invoke(UserCommand c){
        try {
            c.action();
            c.reply();
        }
        catch(DukeException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Stop the program. Sets the endProgram as true
     */
    public static void stop(){
        isProgramEnd = true;
    }

    /**
     * Check if program is quit
     *
     * @return the boolean
     */
    public static boolean checkOff(){
        return isProgramEnd;
    }

}
