package duchess.ui;

import duchess.exception.DuchessException;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    private String formatString(String string) {
        return String.format("%s\n", string);
    }

    /**
     * Formats the provided strings appropriately. The strings
     * provided will be formatted to be printable line by line.
     *
     * @param strings Strings to format to be line-by-line.
     * @return The formatted {@code String}s.
     */
    public String print(String... strings) {
        StringBuilder returnString = new StringBuilder();

        for (String string : strings) {
            returnString.append(this.formatString(string));
        }

        return returnString.toString();
    }

    /**
     * Returns the default welcome message.
     *
     * @return The welcome message {@code String}.
     */
    public String printWelcome() {
        return this.print("Hello! My name is Duchess.", "How may I help you?");
    }

    /**
     * Returns the default goodbye message.
     *
     * @return The default goodbye message {@code String}.
     */
    public String printGoodbye() {
        return this.print("Bye, is it? Shoo shoo then.",
                "Don't need to worry, I'll remember what you told me today.");
    }


    /**
     * Returns out the given {@code TaskList} formatted, task by task.
     *
     * @param taskList The taskList to print.
     * @return The {@code TaskList} formatted {@code String}.
     * @throws DuchessException If the task changes size during printing, resulting
     *                          in index out of bounds.
     */
    public String printTaskList(TaskList taskList) throws DuchessException {
        if (taskList.size() > 0) {
            List<String> result = IntStream.range(0, taskList.size())
                    .mapToObj(i -> (i + 1) + ".\t" + taskList.getTask(i))
                    .collect(Collectors.toList());
            result.add(0, "Sighs... you never remember what you say, don't you.");
            result.add(1, "You said these:");
            String[] resultToPrint = new String[result.size()];
            return this.print(result.toArray(resultToPrint));
        } else {
            return this.print("Is this a trick question? You have not told me anything about 'tasks'.");
        }
    }

    /**
     * Returns the given {@code filteredTaskList} task by task as {@code String}.
     * Will return an appropriate message if the array is empty.
     *
     * @param filteredTaskList The filteredTaskList to print.
     * @return The filtered task list {@code String}.
     */
    public String printFilteredTaskList(ArrayList<Pair<Task, Integer>> filteredTaskList) {
        if (filteredTaskList.size() == 0) {
            return this.print("Couldn't find anything that matches what you want.",
                    "I sure hope you're not testing me!");
        } else {
            this.print("Not bad, I found the following:");
            List<String> result = IntStream.range(0, filteredTaskList.size())
                    .mapToObj(i -> {
                        Pair<Task, Integer> pair = filteredTaskList.get(i);
                        return (i + 1) + ".\t" + pair.getFirst()
                                + "\n\t[REF INDEX FOR DELETE/DONE: " + (pair.getSecond() + 1) + "]";
                    })
                    .collect(Collectors.toList());
            result.add(0, "Not bad, I found the following:");
            String[] resultToPrint = new String[result.size()];
            return this.print(result.toArray(resultToPrint));
        }
    }

    /**
     * Returns the success message when a task is added.
     *
     * @param task {@code Task} added to a {@code TaskList}.
     * @param size New size of the {@code TaskList} after the {@code Task} has been added.
     * @return The addition message {@code String}.
     */
    public String printTaskAdded(Task task, int size) {
        return this.print("As always, needing someone to keep track of things for you...", task.toString(),
                "I've already tracked " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    /**
     * Returns the success message when a task is deleted.
     *
     * @param task {@code Task} deleted to a {@code TaskList}.
     * @param size New size of the {@code TaskList} after the {@code Task} has been deleted.
     * @return The deletion message {@code String}.
     */
    public String printTaskDeleted(Task task, int size) {
        return this.print("Great! One less thing for me to track for you.", task + " [DELETED]",
                "Now I'm tracking " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    /**
     * Returns the task completed message formatted appropriately.
     *
     * @param task The task that is completed.
     * @return The completion message {@code String}.
     */
    public String printTaskCompleted(Task task) {
        return this.print("Oh? You actually completed something? Impressive...", task.toString());
    }

    /**
     * Returns an error message formatted appropriately.
     *
     * @param errorMessage ErrorMessage to print.
     * @return The error message {@code String}.
     */
    public String printError(String errorMessage) {
        return this.print("Stop causing me trouble...", errorMessage);
    }

    /**
     * Returns the error message for failing to load storage.
     *
     * @param errorMessage Error message to be printed for failing to load storage.
     * @return The loading error message {@code String}.
     */
    public String printLoadingError(String errorMessage) {
        return this.print(errorMessage);
    }

    /**
     * Returns the default help message with information on commands and datetime formats.
     *
     * @return The default help message {@code String}.
     */
    public String printHelpMessage() {
        return this.print("Is this the first time I'm talking with you?",
                "I can't do everything for you, you know? Here's what I do:",
                new String(new char[65]).replace("\0", "-"),
                "list \t\t\t\t\tView current tasks.",
                "todo [desc.] \t\t\t\tCreate ToDo.",
                "event [desc.] /at [time] \t\tCreate Event.",
                "deadline [desc.] /by [time] \t\tCreate Deadline.",
                "done [index] \t\t\t\tComplete task at index.",
                "find [word(s)] \t\t\t\tFind tasks with said word(s).",
                "delete [index] \t\t\t\tDelete task at index.",
                "bye \t\t\t\t\tBid farewell (sounds great!).",
                "help \t\t\t\t\tSee this message again.",
                new String(new char[65]).replace("\0", "-"),
                "Accepted time formats are:",
                "d/m/YY \t\t\t\t\td/m/YY HHmm",
                "Today/Tonight/Tomorrow \t\t\tMonday/Tuesday etc.");
    }

    // Console Mode Specific Methods

    /**
     * Reads in the next line of user input.
     *
     * @return Next line of user input.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the separator line. The line is made up of
     * the U-2501 unicode character.
     */
    public void printLine() {
        System.out.println(new String(new char[65]).replace("\0", "\u2501")); // border character
    }

    /**
     * Prints output to console for console mode.
     *
     * @param output String to print.
     */
    public void printToConsole(String output) {
        System.out.print(output);
    }

    /**
     * Returns the console welcome message.
     *
     * @return The welcome message {@code String}.
     */
    public String printConsoleWelcome() {
        String logo = " _____             _\n"
                + "|  __ \\           | |\n"
                + "| |  | |_   _  ___| |__   ___  ___ ___\n"
                + "| |  | | | | |/ __| '_ \\ / _ \\/ __/ __|\n"
                + "| |__| | |_| | (__| | | |  __/\\__ \\__ \\\n"
                + "|_____/ \\__,_|\\___|_| |_|\\___||___/___/";
        return this.print("Hello from", logo, "My name is Duchess, as you can see above.", "How may I help you?");
    }
}
