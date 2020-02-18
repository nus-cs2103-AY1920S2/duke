package duke.ui;

import duke.task.Task;
import duke.task.TaskList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Ui displays the output to the user.
 * @author Lua Jia Zheng.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Print instruction of the commands in JayZbot.
     */
    public void botInstruction() {
        System.out.println("Use the list command to see your tasks.");
        System.out.println("Set up tasks by using the command shown below:");
        System.out.println("todo {your task}");
        System.out.println("deadline {your task} /by {date/time}");
        System.out.println("event {your task} /at {date/time}");
        System.out.println("Use the bye command to quit.");
    }

    /**
     * Welcome message when bot first initialize.
     */
    public void welcome() {
        System.out.println("Hello! I'm JayZ-Bot\nWhat can i do for you?");
        botInstruction();
    }

    /**
     * Echo message back in a text box.
     * @param message provided by user, prints it.
     */
    public void printMessageInBox(String message) {
        System.out.println(String.format("%60s",' ').replace(' ','*'));
        System.out.println(String.format("%-57s",' '));
        String[] splited = message.split("\n");
        for (String s : splited) {
            System.out.println(String.format("  %-57s", s));
        }
        System.out.println(String.format(" %-57s",' '));
        System.out.println(String.format("%60s",' ').replace(' ','*'));
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodBye() {
        System.out.println(String.format("%60s",' ').replace(' ','*'));
        System.out.println(String.format("* %-57s*",' '));
        System.out.println(String.format("* %-57s*","Thank you for using JayZ-Bot! Hope to see you again :D"));
        System.out.println(String.format("* %-57s*",' '));
        System.out.println(String.format("%60s",' ').replace(' ','*'));
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLoadingError() {
        printMessageInBox("There is a error");
    }

    public void showError(String e) {
        printMessageInBox(e);
    }

    /**
     * Prints feedback that task is successfully added.
     * @param task show the task to user.
     */
    public void addTaskSuccess(Task task) {
        String addedTask = "Got it. I've added this task:";
        String message = String.format("%s\n%s", addedTask, task.toString());
        printMessageInBox(message);
    }

    /**
     * Prints feedback that task is deleted.
     * @param task show user the deleted task.
     */
    public void deleteTaskSuccess(Task task) {
        String deleteTask = "Got it. I've delete this task:";
        String message = String.format("%s\n%s", deleteTask, task.toString());
        printMessageInBox(message);
    }

    /**
     * Prints feedback that task has been marked as Done.
     * @param task show eser the marked task.
     */
    public void doneTask(Task task) {
        String toPrint1 = "Nice! I've marked this task as done:";
        String message = String.format("%s\n%s", toPrint1, task.toString());
        printMessageInBox(message);
    }

    /**
     * Prints all the tasks stored.
     */
    public void printStoredTask(TaskList tasksList) {
        TreeMap<Integer,Task> tasks = tasksList.getList();
        System.out.println(String.format("%60s",' ').replace(' ','*'));
        System.out.println(String.format("%-57s",' '));
        System.out.println(String.format("  %-55s", "The following task below are stored in the Task List."));
        int counter = 1;
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            Task message = entry.getValue();
            System.out.println(String.format("%d.%-55s", counter, message));
            counter++;
        }
        System.out.println(String.format("%-57s",' '));
        System.out.println(String.format("%60s",' ').replace(' ','*'));
    }

    /**
     * Prints all the found tasks stored.
     */
    public void printFoundTask(TreeMap<Integer, Task> foundTasks) {
        String matching = " Here are the matching tasks in your list:";
        System.out.println(String.format("%60s",' ').replace(' ','*'));
        System.out.println(String.format("%-57s",' '));
        System.out.println(String.format("%-57s",matching));
        int counter = 1;
        for (Map.Entry<Integer, Task> entry : foundTasks.entrySet()) {
            Task message = entry.getValue();
            System.out.println(String.format("%d.%-55s", counter, message));
            counter++;
        }
        System.out.println(String.format("%-57s",' '));
        System.out.println(String.format("%60s",' ').replace(' ','*'));
    }
}
