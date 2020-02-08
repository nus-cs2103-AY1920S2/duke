package Grapie.Functions;

import Grapie.Commands.TaskList;
import Grapie.Exceptions.GrapieExceptions;
import Grapie.Tasks.Task;

import java.io.IOException;

public class Ui {
    /**
     * Constructor for Grapie.Functions.Ui.
     */
    public Ui() {
        //deals with interactions with the user
    }

    /**
     * Returns greetings to user.
     */
    public static String greetings() {
        String intro = "Hello! I'm Grapie! \n"
                + "   _____                 _      \n"
                + "  / ____|               (_)     \n"
                + " | |  __ _ __ __ _ _ __  _  ___ \n"
                + " | | |_ | '__/ _` | '_ \\| |/ _ \\ \n"
                + " | |__| | | | (_| | |_) | |  __/ \n"
                + "  \\_____|_|  \\__,_| .__/|_|\\___| \n"
                + "                  | |           \n"
                + "                  |_|           \n"


                + "What do ya need from me?\n";

        return intro;
    }

    /**
     * Returns goodbye to the user.
     */
    public static String sayonara() {
        return "Okie!! Goodbye!";
    }

    public static String printAddingTask(Task task, int size) {
        String printStr = "Alrighty. I've added this task: \n"
                + task + "\n"
                + "Now you have " + size + " tasks in the list.";

        return printStr;
    }

}
