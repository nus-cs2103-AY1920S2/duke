import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Ui {
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Start the duke program
        System.out.println("Hello from\n" + logo);
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Hey friend! I'm Duke V2.0.0 at your service\n" +
                        "     What can I do for you today?\n" +
                        "    ____________________________________________________________\n"
        );
    }

    /**
     * Format the given line into a pretty format and print it
     *
     * @param  line   the line to be formatted
     */
    private void prettyPrint(String line) {
        System.out.println(
                "    _____________________________DUKE___________________________\n" +
                        "     " + line + "\n" +
                        "    _________★゜・。。・゜゜・。。・゜☆゜・。。・゜゜・。。・゜★_______\n"
        );
    }

    /**
     * Format the given list into a pretty format and print it
     *
     * @param  tasks   the list of tasks to be formatted
     */
    public void prettyPrintList(TaskList tasks) {
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
        tasksString = tasksString.equals("") ? "There is nothing on your list." : tasksString;
        System.out.println(tasksString);
    }

    /**
     * Format the task added into a pretty format and print it
     *
     * @param  task   the task added
     * @param  size   the total number of tasks
     */
    public void showAddTask(Task task, Integer size) {
        String taskWord = (size > 1) ? "tasks" : "task";
        showLine("Got it. I've added this task: \n" +
                "       " + task + "\n"+
                "     Now you have " + size + " " + taskWord + " in the list.");
    }

    /**
     * Format the task deleted into a pretty format and print it
     *
     * @param  task   the task deleted
     * @param  size   the total number of tasks
     */
    public void showDeleteTask(Task task, Integer size) {
        String taskWord = (size > 1) ? "tasks" : "task";
        showLine("Noted. I've removed this task: \n" +
                "       " + task + "\n" +
                "     Now you have " + size + " " + taskWord + " in the list.");
    }

    public String readCommand() {
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public void showStartLine() {
        System.out.println("    _____________________________DUKE___________________________");
    }

    public void showEndLine() {
        System.out.println("    _________★゜・。。・゜゜・。。・゜☆゜・。。・゜゜・。。・゜★_______\n");
    }

    public void showError(String error) {
        showLine(error);
    }

    public void showLoadingError() {
        prettyPrint("☹ OOPS!!! Failed to load list!");
    }

    public void showGoodBye() {
        showLine("Aw goodbye for now! Hope to see you again soon :)");
    }

    public void showLine(String line) {
        System.out.println("     " + line);
    }
}
