package duke.ui;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private int numOfIndentation = 4;
    private ReplyFormat message;
    private static Scanner userInput;
    private boolean isGui;

    /**
     * Class Constructor of Ui.
     */
    public Ui() {
        message = new ReplyFormat();
        userInput = new Scanner(System.in);
        message.setIndentation(numOfIndentation);
        isGui = true;
    }

    /**
     * Loop until user give input.
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
     * Set isGui to false.
     */
    public void setNotGui() {
        isGui = false;
    }

    /**
     * Close all scanners if available.
     */
    public void closeScanner() {
        userInput.close();
    }

    /**
     * Provides greeting message to print in String format.
     *
     * @return Return the greeting message back
     */
    public String greetWithoutPrint() {
        String logo = setLogoAccordingToConsoleOrGui();
        message.clearMessage();
        message.addSentence("Hello! I'm ", 1);
        message.addParagraph(logo, 15);
        message.addEmptyLine();
        message.addSentence("What can I do for you?", 1);
        return message.replyMessage();
    }

    /**
     * Return logo according to whether the output is console or gui.
     *
     * @return Logo message in string.
     */
    public String setLogoAccordingToConsoleOrGui() {
        if (isGui) {
            return "____          _                               \n"
                    + "|  _  \\ _    _| | _____                   \n"
                    + "| |  |  |  |  |  | |/ / __ \\              \n"
                    + "| |_ |  |  |_|  |  <   __/                 \n"
                    + "|____/ \\__,_|_|\\_\\___|                  \n";
        } else {
            return " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        }
    }

    /**
     * Greet the user and print the greeting message.
     */
    public void greet() {
        System.out.print(greetWithoutPrint());
    }

    /**
     * Print user command.
     *
     * @param command command to be printed
     */
    public void printUserCommand(String command) {
        System.out.println(command);
    }

    /**
     * Reply the message back to user.
     *
     * @param sentence message reply to user
     */
    public void reply(String sentence) {
        message.clearMessage();
        message.addSentence(sentence, 1);
        System.out.print(message.replyMessage());
    }

    /**
     * Reply the message back in String format.
     *
     * @param sentence message reply to user
     * @return Return the message in String format
     */
    public String replyInString(String sentence) {
        message.clearMessage();
        message.addSentence(sentence, 1);
        return message.replyMessage();
    }

    /**
     * Reply the listing back in String format.
     *
     * @param tasks List of tasks in records
     * @return Reply the list back
     */
    public String replyListing(List<Task> tasks) {
        message.clearMessage();
        if (tasks.isEmpty()) {
            message.addSentence("Horray! You do not have any task now!", 1);
        } else {
            message.addSentence("Here are the tasks in your list:", 1);
            message.addList(tasks);
        }
        return message.replyMessage();
    }

    /**
     * List out the tasks that found with the keyword back in String format.
     *
     * @param tasks List of tasks with keyword
     * @return Return message back
     */
    public String replyTaskFound(List<Task> tasks) {
        message.clearMessage();
        if (tasks.isEmpty()) {
            message.addSentence("No task with the keyword! Find another keyword?", 1);
        } else {
            message.addSentence("Here are the tasks found with the keyword:", 1);
            message.addList(tasks);
        }
        return message.replyMessage();
    }

    /**
     * Reply the respective task has been added to the list in String format.
     *
     * @param amtOfTask amount of tasks in records
     * @param task      task that being added
     * @return reply the message back in String format
     */
    public String replyAdded(int amtOfTask, Task task) {
        message.clearMessage();
        message.addSentence("Got it. I've added this task:", 1);
        message.addSentence(task.toString(), 3);
        setMessageAccordingToAmountOfTask(amtOfTask);
        return message.replyMessage();
    }

    /**
     * Set the reply message according to the amount of task in the taskList.
     *
     * @param amtOfTask Number of task in the taskList.
     */
    public void setMessageAccordingToAmountOfTask(int amtOfTask) {
        if (amtOfTask > 1) {
            message.addSentence("Now you have " + amtOfTask + " tasks in the list.", 1);
        } else {
            message.addSentence("Now you have 1 task in the list.", 1);
        }
    }

    /**
     * Reply the message which the task are being marked done in String format.
     *
     * @param task task that being marked done
     * @return Reply the message back in String format
     */
    public String replyDone(Task task) {
        message.clearMessage();
        message.addSentence("Nice! I've marked this task as done:", 1);
        message.addSentence(task.toString(), 5);
        return message.replyMessage();
    }

    /**
     * Reply that the task has deleted in String format.
     *
     * @param task task that being deleted
     * @return Reply the message back in String format
     */
    public String replyDelete(Task task) {
        message.clearMessage();
        message.addSentence("Noted. I've removed this task:", 1);
        message.addSentence("  " + task.toString(), 3);
        return message.replyMessage();
    }

    /**
     * Set the outline of the reply message.
     */
    public void setOutline() {
        message.setOutline();
    }
}
