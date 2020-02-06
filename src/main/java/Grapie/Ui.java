package Grapie;

import Grapie.Exceptions.GrapieExceptions;

import java.io.IOException;

public class Ui {
    String userInput;
    Parser parser;

    public static final String LIST = "list";
    public static final String ADD = "add";
    public static final String DELETE = "delete";
    public static final String DONE = "done";
    public static final String FIND = "find";


    /**
     * Constructor for Grapie.Ui.
     */
    public Ui() {
        //deals with interactions with the user
        parser = new Parser();
    }


    /**
     * Use Grapie.Parser to read user's command to call correct method, and prints out any error encountered.
     *
     * @param command User's input.
     * @param tasks Grapie.TaskList class created in Duke.
     * @throws IOException Throws away the exception.
     */
    public String readCommand(String command, TaskList tasks) throws IOException, GrapieExceptions {
        String commandArr = parser.makeSenseOfUserCommand(command);

        if (commandArr.equals(LIST)) {
            String list = tasks.listTheList();
            return list;
        } else if (commandArr.equals(DONE)) {
            try {
                String result = tasks.completeTask(command);
                return result;
            } catch (GrapieExceptions grapieExceptions) {
                String ha = "" + grapieExceptions;
                return ha;
            }
        } else if (commandArr.equals(ADD)) {
            try {
                String result = tasks.addToList(command);
                return result;
            } catch (GrapieExceptions grapieExceptions) {
                String ha = "" + grapieExceptions;
                return ha;
            }
        } else if (commandArr.equals(DELETE)) {
            try {
                String result = tasks.deleteTask(command);
                return  result;
            } catch (GrapieExceptions grapieExceptions) {
                String ha = "" + grapieExceptions;
                return ha;
            }
        } else if (commandArr.equals(FIND)) {
            //find
            try {
                String result = tasks.findFromList(command);
                return result;
            } catch (GrapieExceptions grapieExceptions) {
                String ha = "" + grapieExceptions;
                return ha;
            }
        }

        return "OOPS!!! I do not understand you :(";
    }


}
