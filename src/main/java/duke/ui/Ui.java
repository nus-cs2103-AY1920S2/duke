package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    /**
     * Show the welcome message, which consists of the logo, and greeting.
     * 
     * @return message to be displayed
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Start the duke program
        String start = "Hello from\n" + logo;
        start += "    ____________________________________________________________\n"
                    + "     Hey friend! I'm Duke V2.0.0 at your service\n"
                    + "     What can I do for you today?\n"
                    + "    ____________________________________________________________\n";
        return start;
    }

    /**
     * Format the given line into a pretty format and print it.
     *
     * @param line the line to be formatted
     * @return message to be displayed
     */
    private String prettyPrint(String line) {
        return "    _____________________________DUKE___________________________\n"
                    + "     "
                    + line
                    + "\n"
                    + "    _________--__--__--__--__--__--__--__--__--__--__--_________\n";
    }

    /**
     * Format the given list into a pretty format and print it.
     *
     * @param tasks the list of tasks to be formatted
     * @return message to be displayed
     */
    public String prettyPrintList(TaskList tasks) {
        String tasksString = "";
        int size = tasks.size();

        // Print out all items in list
        if (size > 0) {
            tasksString += "     Here are the tasks in your list:\n     ";
        }
        for (int i = 0; i < size; i++) {
            tasksString += (i + 1) + "." + tasks.get(i);
            if (i != size - 1) {
                tasksString += "\n     ";
            }
        }
        tasksString = tasksString.equals("") ? "There is nothing on your list.\n" : tasksString;
        return tasksString;
    }

    /**
     * Format the task added into a pretty format and print it.
     *
     * @param task the task added
     * @param size the total number of tasks
     * @return message to be displayed
     */
    public String showAddTask(Task task, Integer size) {
        String taskWord = (size > 1) ? "tasks" : "task";
        return showLine("Got it. I've added this task: \n"
                + "       " + task + "\n"
                + "     Now you have " + size + " " + taskWord + " in the list.");
    }

    /**
     * Format the task deleted into a pretty format and print it.
     *
     * @param task the task deleted
     * @param size the total number of tasks
     * @return message to be displayed
     */
    public String showDeleteTask(Task task, Integer size) {
        String taskWord = (size > 1) ? "tasks" : "task";
        return showLine("Noted. I've removed this task: \n"
                + "       "
                + task
                + "\n"
                + "     Now you have "
                + size
                + " "
                + taskWord
                + " in the list.");
    }

    /**
     * Read the command entered by the user.
     *
     * @return  the command entered by the user
     */
    public String readCommand() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Show on screen the top line, which is the line before the main message.
     *
     * @return message to be displayed
     */
    public String showStartLine() {
        return "    ______________________DUKE______________________\n";
    }

    /**
     * Show on screen the bottom line, which is the line after the main message.
     *
     * @return message to be displayed
     */
    public String showEndLine() {
        return "    ___--__--__--__--__--__--__--__--__--__--__--___\n";
    }

    /**
     * Show on screen the loading error, when the list could not be loaded from disk.
     *
     * @return message to be displayed
     */
    public String showLoadingError() {
        return prettyPrint("OOPS!!! Failed to load list!");
    }

    /**
     * Show on screen the goodbye message.
     * @return message to be displayed
     */
    public String showGoodBye() {
        return showLine("Aw goodbye for now! Hope to see you again soon :)");
    }

    /**
     * Format the line and show on screen to user.
     *
     * @param line the line to be formatted
     * @return message to be displayed
     */
    public String showLine(String line) {
        return "     " + line;
    }
}