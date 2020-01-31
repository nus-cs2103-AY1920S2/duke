package duke.ui;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    protected int numOfIndentation = 4;
    protected ReplyFormat message;
    protected static Scanner userInput;

    public Ui() {
        message = new ReplyFormat();
        userInput = new Scanner(System.in);
        message.setIndentationInFront(numOfIndentation);
    }

    /**
     * Loop until user give input
     *
     * @return the sentence user inputs
     */
    public String getUserInput() {
        while (true) {
            if (userInput.hasNext()) {
                return userInput.nextLine();
            }
        }
    }

    /**
     * Close all scanners if available
     */
    public void closeScanner() {
        userInput.close();
    }

    /**
     * Greet the user
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        message.clearMessage();
        message.addSentence("Hello! I'm ", 1);
        message.addParagraph(logo, 15);
        message.addEmptyLine();
        message.addSentence("What can I do for you?", 1);
        System.out.print(message.replyMessage());
    }

    /**
     * Print user command
     *
     * @param command command to be printed
     */
    public void printUserCommand(String command) {
        System.out.println(command);
    }

    /**
     * duke.Duke reply one sentence to the user
     *
     * @param sentence message reply to user
     */
    public void reply(String sentence) {
        message.clearMessage();
        message.addSentence(sentence, 1);
        System.out.print(message.replyMessage());
    }

    /**
     * List out the listing to user
     *
     * @param tasks List of tasks in records
     */
    public void replyListing(List<Task> tasks) {
        message.clearMessage();
        if (tasks.isEmpty()) {
            message.addSentence("Horray! You do not have any task now!", 1);
        } else {
            message.addSentence("Here are the tasks in your list:", 1);
            message.addList(tasks);
        }
        System.out.print(message.replyMessage());
    }

    /**
     * List out the tasks that found with the keyword to user
     *
     * @param tasks List of tasks with keyword
     */
    public void replyTaskFound(List<Task> tasks) {
        message.clearMessage();
        if (tasks.isEmpty()) {
            message.addSentence("No task with the keyword! Find another keyword?", 1);
        } else {
            message.addSentence("Here are the tasks found with the keyword:", 1);
            message.addList(tasks);
        }
        System.out.print(message.replyMessage());
    }

    /**
     * Reply the user that respective task has been added to the list
     *
     * @param AmtOfTask amount of tasks in records
     * @param task      task that being added
     */
    public void replyAdded(int AmtOfTask, Task task) {
        message.clearMessage();
        message.addSentence("Got it. I've added this task:", 1);
        message.addSentence(task.toString(), 3);
        if (AmtOfTask > 1) {
            message.addSentence("Now you have " + AmtOfTask + " tasks in the list.", 1);
        } else {
            message.addSentence("Now you have 1 task in the list.", 1);
        }
        System.out.print(message.replyMessage());
    }

    /**
     * Reply to the user that which task are being marked done
     *
     * @param task task that being marked done
     */
    public void replyDone(Task task) {
        message.clearMessage();
        message.addSentence("Nice! I've marked this task as done:", 1);
        message.addSentence(task.toString(), 5);
        System.out.print(message.replyMessage());
    }

    /**
     * Reply the user that the task has deleted
     *
     * @param task task that being deleted
     */
    public void replyDelete(Task task) {
        message.clearMessage();
        message.addSentence("Noted. I've removed this task:", 1);
        message.addSentence("  " + task.toString(), 3);
        System.out.print(message.replyMessage());
    }
}
