/*
 * @author leslieharland
 */

package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class Ui.
 */
public class Ui {
    static Scanner sc;

    /**
     * Gets the single instance of Ui.
     *
     * @return single instance of Ui
     */
    public static Ui getInstance() {
        return new Ui();
    }

    public static String logo() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        return sb.toString();
    }

    /**
     * Welcome.
     */
    public static String welcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello I'm Duke\nWhat can I do for you?");
        return sb.toString();
    }

    public static String makeLine() {
        return "    ____________________________________________________________";
    }

    /**
     * Gets the scanner.
     *
     * @return the scanner
     */
    public Scanner getScanner() {
        sc = new Scanner(System.in);
        return sc;
    }

    /**
     * Clean.
     */
    public String clean() {
        List<String> msgs = new ArrayList<>();
        msgs.add("Bye. Hope to see you again soon!");
        return showMessage(msgs);

    }

    /**
     * Show loading error.
     */
    public String showLoadingError() {
        List<String> msgs = new ArrayList<>();
        msgs.add("Cannot start program");
        return showMessage(msgs);
    }

    /**
     * Show message.
     *
     * @param msg the msg
     */
    public String showMessage(List<String> msg) {
        StringBuilder sb = new StringBuilder();
        for (String m : msg) {
            sb.append("     " + m);
        }
        return sb.toString();
    }

    /**
     * Show tasks.
     *
     * @param tl the tl
     */
    public String showTasks(TaskList tl, boolean isFind) {
        StringBuilder sb = new StringBuilder();
        sb.append("      Here are the " + (isFind ? "matching " : "") + "tasks in your list: \n");
        for (int count = 0; count < tl.getSize(); count++) {
            sb.append("      " + (count + 1) + ". ");
            sb.append(tl.get(count) + "\n");
        }

        return sb.toString();
    }

    /**
     * Task mark done.
     *
     * @param cur the cur
     */
    public String taskMarkDone(Task cur) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done: \n");
        sb.append(cur.getStatusIcon() + " "
                + cur.getDescription());
        return sb.toString();
    }

    /**
     * Task number error.
     */
    public String taskNumberError() {
        StringBuilder sb = new StringBuilder();
        sb.append("Please enter a valid task number \n");
        return sb.toString();
    }

    /**
     * Task remove success.
     *
     * @param cur  the cur
     * @param size the size
     */
    public String taskRemoveSuccess(Task cur, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("      Noted. I've removed this task: \n");
        sb.append("      " + cur + "\n");
        sb.append("      Now you have " + size + " tasks in the list.  \n");
        return sb.toString();
    }

    /**
     * Task add success.
     *
     * @param cur  the cur
     * @param size the size
     */
    public String taskAddSuccess(Task cur, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("      Got it. I've added this task:  \n");
        sb.append("      " + cur + "\n");
        sb.append("      Now you have " + size + " tasks in the list.  \n");
        return sb.toString();
    }

}