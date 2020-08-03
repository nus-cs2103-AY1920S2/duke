package ip;

import java.util.Scanner;
import ip.task.Task;
import ip.task.TaskList;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    private static final String LINE = "\t_________________________\n";
    public static final String GOODBYE = LINE + "\tGoodbye! Hope to hear from you soon :)\n" + LINE;

    private String printResponse(String... strs) {
        StringBuilder sb = new StringBuilder(LINE);
        for (String s: strs) {
            sb.append('\t');
            sb.append(s);
            sb.append('\n');
        }
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Displays the error message
     * @param msg the error message
     */
    public String displayError(String msg) {
        return printResponse(":( OOPS!!! ", msg);
    }

    /**
     * Displays the initial greeting when the program starts
     */
    public static String initialGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        return "Hello from\n" + logo;
    }

    /**
     * Displays the response when a new task is added
     * @param t the task added
     * @param numTasks the no. of tasks in the list currently
     */
    public String displayTaskAdded(Task t, int numTasks) {
        return printResponse("Got it, I've added this task:", t.toString(),
                        "Now you have " + numTasks + " task(s) in the list.");
    }

    /**
     * Displays all tasks in the list
     * @param tasks the list of tasks
     */
    public String displayAllTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            return printResponse("There are no tasks in the list! Please add some :)");
        }

        String[] strList = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            strList[i] = "\t" + (i + 1) + ": " + tasks.get(i).toString();
        }
        return printResponse(strList);
    }

    /**
     * Displays a goodbye greeting when the user exits
     */
    public String displayGoodbye() {
        return GOODBYE;
    }

    /**
     * Displays the response when a task is marked as done by the user
     * @param t the task marked as done
     */
    public String displayTaskMarkedDone(Task t) {
        return printResponse("Awesome! I've marked this task as done: ", t.toString());
    }

    /**
     * Displays the response when a task is deleted by the user
     * @param t the task deleted
     * @param numTasks the no. of tasks in the list currently
     */
    public String displayTaskDeleted(Task t, int numTasks) {
        return printResponse("Noted, I've removed this task: ", t.toString(),
                        "Now you have " + numTasks + " task(s) in the list.");
    }
    public String displayFoundTasks(TaskList found) {
        if (found.size() == 0){
            return printResponse("No matching tasks were found :/");
        }
        String[] strList = new String[found.size() + 1];
        strList[0] = "Here are the matching tasks in your list:";
        for (int i = 0; i < found.size(); i++) {
            strList[i + 1] = "\t" + (i + 1) + ": " + found.get(i).toString();
        }
        return printResponse(strList);
    }
}