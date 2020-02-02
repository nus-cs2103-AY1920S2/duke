package seedu.java.util;

import java.util.Scanner;

/**
 * A UI for user interaction.
 * intro()
 * showLoadingError()
 * inputLoop()
 */
public class Ui {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Prints the logo & gives instructions.
     */
    public void introBot() {
        System.out.println(logo + "\n"
                + "     Oi What you waaaaaant?\n"
                + "    _________________________________\n"
                + "Excuse my rudeness, but you know what to do anot? \n "
                + "Type a command and some other text, then press ENTER :) Here's what you can do"
                + "\n" + "TODO -- Adds a to-do task. Key in 'todo' and then whatever ridiculous task you desire."
                + "\n" + "DEADLINE -- Adds a task with a deadline. Key in 'deadline', the task, and then the date in the format YYYY-MM-DD."
                + "\n" + "EVENT -- Adds an event with a spanning timeline. Key in 'event', the event description and the date in whatever format."
                + "\n" + "DONE -- Completes an event/task. Type 'done' & a task number to complete that specific task."
                + "\n" + "DELETE -- Deletes a task. Type 'delete' & a task number to delete that task."
                + "\n" + "LIST -- If you want to view what's on your tasklist, key in 'list'."
                + "\n" + "FIND -- Too many Task that you're not sure which is which? key in 'find' & the key word. A list of task based on the keyword will be printed."
                + "\n" + "BYE -- When you're sick of using this useless software and want to quit, type 'bye' & I'll terminate operation."
                + "\n" + "Have fun! :)");
    }

    /**
     * Prints an error. Intended for storage if it fails to load
     */
    public void showLoadingError() {
        System.out.println("System failed to load file. Opening with a new blank file");
    }

    /**
     * Takes in an input and returns the input. Intended for TaskList & parsing.
     * @return input for parsing
     */
    public String input() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public void output(String out) {
        System.out.println(out);
    }
}