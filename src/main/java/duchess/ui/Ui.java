package duchess.ui;

import duchess.task.Task;
import duchess.task.TaskList;
import duchess.util.Pair;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Ui} class helps to manage all inputs and outputs to the
 * System console.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Initialises a {@code Ui} instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads in the next line of user input.
     *
     * @return Next line of user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the default welcome message.
     */
    public void printWelcome() {
        String logo = "\t _____             _\n"
                + "\t|  __ \\           | |\n"
                + "\t| |  | |_   _  ___| |__   ___  ___ ___\n"
                + "\t| |  | | | | |/ __| '_ \\ / _ \\/ __/ __|\n"
                + "\t| |__| | |_| | (__| | | |  __/\\__ \\__ \\\n"
                + "\t|_____/ \\__,_|\\___|_| |_|\\___||___/___/";
        this.print("Hello from\n" + logo);
        this.print("My name is Duchess, as you can see above.");
        this.print("How may I help you?");
        this.printLine();
    }

    /**
     * Prints the default goodbye message.
     */
    public void printGoodbye() {
        this.print("Bye, is it? Shoo shoo then.");
        this.print("Don't need to worry, I'll remember what you told me today.");
    }

    /**
     * Prints the separator line. The line is made up of
     * the U-2501 unicode character.
     */
    public void printLine() {
        System.out.println("\t" + new String(new char[65]).replace("\0", "\u2501"));
    }

    /**
     * Prints the provided output out formatted appropriately.
     *
     * @param output The {@code String} to print.
     */
    public void print(String output) {
        System.out.println("\t" + output);
    }

    /**
     * Prints the given task in {@code String} type formatted appropriately.
     *
     * @param taskInString The task to print in {@code String} type.
     */
    public void printTask(String taskInString) {
        System.out.println("\t\t" + taskInString);
    }

    /**
     * Prints the given {@code Task} formatted accordingly.
     *
     * @param task The task to print.
     */
    public void printTask(Task task) {
        System.out.println("\t\t" + task);
    }

    /**
     * Prints out the given {@code TaskList} task by task.
     *
     * @param taskList The taskList to print.
     */
    public void printTaskList(TaskList taskList) {
        if (taskList.size() > 0) {
            this.print("Sighs... you never remember what you say, don't you.");
            this.print("You said these:");
            for (int i = 0; i < taskList.size(); i++) {
                this.printTask((i + 1) + ".\t" + taskList.getTask(i));
            }
        } else {
            this.print("Is this a trick question? You have not told me anything about 'tasks'.");
        }
    }

    public void printFilteredTaskList(ArrayList<Pair<Task, Integer>> filteredTaskList) {
        if (filteredTaskList.size() == 0) {
            this.print("Couldn't find anything that matches what you want.");
            this.print("I sure hope you're not testing me!");
        } else {
            this.print("Not bad, I found the following:");
            for (int i = 0; i < filteredTaskList.size(); i++) {
                Pair<Task, Integer> pair = filteredTaskList.get(i);
                this.printTask((i + 1) + ".\t" + pair.getFirst()
                        + "\n\t\t\t[REF INDEX FOR DELETE/DONE: " + (pair.getSecond() + 1) + "]");
            }
        }
    }

    /**
     * Prints out the success message when a task is added.
     *
     * @param task {@code Task} added to a {@code TaskList}.
     * @param size New size of the {@code TaskList} after the {@code Task} has been added.
     */
    public void printTaskAdded(Task task, int size) {
        this.print("As always, needing someone to keep track of things for you...");
        this.printTask(task);
        this.print("I've already tracked " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    /**
     * Prints out the success message when a task is deleted.
     *
     * @param task {@code Task} deleted to a {@code TaskList}.
     * @param size New size of the {@code TaskList} after the {@code Task} has been deleted.
     */
    public void printTaskDeleted(Task task, int size) {
        this.print("Great! One less thing for me to track for you.");
        this.printTask(task + " [DELETED]");
        this.print("Now I'm tracking " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    /**
     * Prints out an error message formatted appropriately.
     *
     * @param errorMessage ErrorMessage to print.
     */
    public void printError(String errorMessage) {
        System.out.println("\tStop causing me trouble...");
        System.out.println("\t" + errorMessage);
    }

    /**
     * Prints the default error message for failing to load storage.
     */
    public void printLoadingError() {
        System.out.println("Failed to load saved tasks. I'll just start anew.");
    }

    public void printHelpMessage() {
        this.print("Is this the first time I'm talking with you?");
        this.print("I can't do everything for you, you know? Here's what I do:");
        this.print(new String(new char[65]).replace("\0", "-"));
        this.print("list \t\t\t\t\t\t\tView current tasks.");
        this.print("todo [desc.] \t\t\t\t\tCreate ToDo.");
        this.print("event [desc.] /at [time] \t\tCreate Event.");
        this.print("deadline [desc.] /by [time] \tCreate Deadline.");
        this.print("done [index] \t\t\t\t\tComplete task at index.");
        this.print("delete [index] \t\t\t\t\tDelete task at index.");
        this.print("bye \t\t\t\t\t\t\tBid farewell (sounds great!).");
        this.print("help \t\t\t\t\t\t\tSee this message again.");
        this.print(new String(new char[65]).replace("\0", "-"));
        this.print("Accepted time formats are:");
        this.print("d/m/YY \t\t\t\t\t\t\td/m/YY HHmm");
        this.print("Today/Tonight/Tomorrow \t\t\tMonday/Tuesday etc.");
    }
}
