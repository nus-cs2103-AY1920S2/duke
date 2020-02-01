package seedu.java.util;

import java.util.Scanner;

/**
 * handles interactions with the user
 * intro()
 * showLoadingError()
 * inputLoop()
 */
public class Ui{
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * prints an ASCII Duke
     */
    public void intro() {
        System.out.println(logo + "\n" +
                "     Oi What you waaaaaant?\n" +
                "    _________________________________" +
                "\n" + "Excuse my rudeness, but you know what to do anot?" +
                "\n" + "Type a command and some other text, then enter :)" +
                "\n" + "Here's how what you can do" +
                "\n\n" + "TODO" +
                "\n" + "Adds a to-do task. Key in 'todo' and then whatever ridiculous task you desire." +
                "\n\n" + "DEADLINE" +
                "\n" + "Adds a task with a deadline. Key in 'deadline', the task, and then the date in the format YYYY-MM-DD." +
                "\n" + "currently keying in the date is rather restrictive, so please be patient as we'll make a better version in the future :(" +
                "\n\n" + "EVENT" +
                "\n" + "Adds an event with a spanning timeline. Key in 'event', the event description and the date in whatever format." +
                "\n\n" + "DONE" +
                "\n" + "Completes an event/task. Type 'done' & a task number to complete that specific task." +
                "\n\n" + "DELETE" +
                "\n" + "Deletes a task. Type 'delete' & a task number to delete that task." +
                "\n\n" + "LIST" +
                "\n" + "If you want to view your List of task, key in 'list'." +
                "\n\n" + "BYE" +
                "\n" + "When you're sick of using this useless software and want to quit, type 'bye' & I'll terminate operation." +
                "\n\n" + "Have fun! :)");
    }

    /**
     * Prints an error. Intended for storage if it fails to load
     */
    public void showLoadingError(){
        System.out.println("System failed to load file. Opening with a new blank file");
    }

    /**
     * Takes in input and returns that input. Intend to pass onto TaskList
     * @return input
     */
    public String input() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    /**
     * Takes an output and print
     * @param out
     */
    public void output(String out){
        System.out.println(out);
    }
}