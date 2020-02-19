package duke.ui;

import duke.exception.DuchessException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The {@code Ui} class helps to manage all inputs and outputs to the System
 * console.
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
     * Formats the provided strings appropriately. The strings provided will be
     * formatted to be printable line by line.
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
                    .mapToObj(i -> (i + 1) + ".\t" + taskList.getTask(i)).collect(Collectors.toList());
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
            List<String> result = IntStream.range(0, filteredTaskList.size()).mapToObj(i -> {
                Pair<Task, Integer> pair = filteredTaskList.get(i);
                return (i + 1) + ".\t" + pair.getFirst() + "\n\t[REF INDEX FOR DELETE/DONE: " + (pair.getSecond() + 1)
                        + "]";
            }).collect(Collectors.toList());
            result.add(0, "Not bad, I found the following:");
            String[] resultToPrint = new String[result.size()];
            return this.print(result.toArray(resultToPrint));
        }
    }

    /**
     * Returns the success message when a task is added.
     *
     * @param task {@code Task} added to a {@code TaskList}.
     * @param size New size of the {@code TaskList} after the {@code Task} has been
     *             added.
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
     * @param size New size of the {@code TaskList} after the {@code Task} has been
     *             deleted.
     * @return The deletion message {@code String}.
     */
    public String printTaskDeleted(Task task, int size) {
        return this.print("Great! One less thing for me to track for you.", task + " [DELETED]",
                "Now I'm tracking " + size + " " + (size == 1 ? "task" : "tasks") + " for you.");
    }

    /**
     * Returns the success message for the deletion of all tasks.
     *
     * @return The deletion message {@code String}.
     */
    public String printAllDeleted() {
        return this.print("All tasks have been deleted. If you regret this, try undoing.");
    }

    /**
     * Returns the task completed message formatted appropriately.
     *
     * @param task The task that is completed.
     * @return The completion message {@code String}.
     */
    public String printTaskCompleted(Task task) {
        if (!task.isCompleted()) {
            return this.print("Oh? You actually completed something? Impressive...",
                    "Your recurring task has been updated to its next recurrence.",
                    task.toString());
        }
        return this.print("Oh? You actually completed something? Impressive...", task.toString());
    }

    /**
     * Returns the task snoozed message formatted appropriately.
     *
     * @param task         The task that is snoozed.
     * @param snoozePeriod The duration that the task was snoozed for.
     * @return The success message {@code String}.
     */
    public String printTaskSnoozed(Task task, String snoozePeriod) {
        return this.print("Behind schedule as always... "
                + "I've pushed back the deadline for you by " + snoozePeriod + ".", task.toString());
    }

    /**
     * Returns a {@code TaskList} sorted success message formatted appropriately.
     *
     * @return The success message {@code String}.
     */
    public String printTaskListSorted() {
        return this.print("Your list of tasks has been sorted.", "Type 'list' to see the new order.");
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
     * Returns the default help message with information on commands and datetime
     * formats.
     *
     * @return The default help message {@code String}.
     */
    public String printHelpMessage() {
        return this.print("Is this the first time I'm talking with you?",
                "I can't do everything for you, you know? Here's what I do:",
                new String(new char[45]).replace("\0", "-"),
                "list: View current tasks.",
                "todo DESC: Create ToDo.",
                "event DESC /at TIME: Create Event.",
                "deadline DESC /by TIME [/every FREQ] [/stop TIME]: Create Deadline.",
                "done INDEX: Complete task at index.",
                "find WORD(S): Find tasks with said word(s) in name.",
                "delete INDEX / all: Delete task at index / delete all tasks.",
                "sort: Sorts your list of tasks.",
                "snooze INDEX /for DURATION: Pushes back deadline by given duration.",
                "undo: Undo your last command that changed your tasks",
                "bye: Bid farewell (sounds great!).",
                "help: See this message again.",
                new String(new char[45]).replace("\0", "-"),
                "Accepted time formats are:",
                "d-m-YY e.g. 2-12-20",
                "d-m-YY HHmm e.g. 2-12-20 1600",
                "Today/Tonight/Tomorrow",
                "Monday/Tuesday etc.",
                new String(new char[45]).replace("\0", "-"),
                "Accepted frequency formats are:",
                "Daily/Weekly/Biweekly/Fortnightly/Yearly",
                new String(new char[45]).replace("\0", "-"),
                "Accepted duration formats are:",
                "[number] hours/days/weeks/months/years");
    }

    public String printUndoMessage(String lastCommand) {
        return this.print("I've helped to fix your mistakes again. Your last command:",
                lastCommand, "has been undone.");
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
     * Prints the separator line. The line is made up of the U-2501 unicode
     * character.
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
        System.out.println(output);
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
        return this.print("Hello from", logo, "My name is Duchess, as you can see above.",
                "How may I help you?");
    }
}
