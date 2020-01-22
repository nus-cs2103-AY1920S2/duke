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
     * Welcome.
     */
    public void welcome() {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Clean.
     */
    public void clean() {
        List<String> msgs = new ArrayList<>();
        msgs.add("Bye. Hope to see you again soon!");
        showMessage(msgs);
        sc.close();
    }

    /**
     * Show loading error.
     */
    public void showLoadingError() {
        List<String> msgs = new ArrayList<>();
        msgs.add("Cannot start program");
        showMessage(msgs);
    }

    /**
     * Show message.
     *
     * @param msg the msg
     */
    public void showMessage(List<String> msg) {
        System.out.println("    ____________________________________________________________");
        for (String m : msg) {
            System.out.println("     " + m);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Show tasks.
     *
     * @param tl the tl
     */
    public void showTasks(TaskList tl, boolean isFind) {
        System.out.println("    ____________________________________________________________");
        System.out.println("      Here are the " + (isFind ? "matching " : "") + "tasks in your list: \n");
        for (int count = 0; count < tl.getSize(); count++) {
            System.out.print("      " + (count + 1) + ". ");
            System.out.println(tl.get(count));
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Task mark done.
     *
     * @param cur the cur
     */
    public void taskMarkDone(Task cur) {
        List<String> msgs = new ArrayList<>();
        msgs.add("Nice! I've marked this task as done: ");
        msgs.add(cur.getStatusIcon() + " "
                + cur.getDescription());
        showMessage(msgs);
    }

    /**
     * Task number error.
     */
    public void taskNumberError() {
        List<String> msgs = new ArrayList<>();
        msgs.add("Please enter a valid task number");
        showMessage(msgs);
    }

    /**
     * Task remove success.
     *
     * @param cur  the cur
     * @param size the size
     */
    public void taskRemoveSuccess(Task cur, int size) {
        System.out.println("    ____________________________________________________________");
        System.out.println("      Noted. I've removed this task: ");
        System.out.println("      " + cur);
        System.out.println("      Now you have " + size + " tasks in the list.  \n");
        System.out.println("    ____________________________________________________________");

    }

    /**
     * Task add success.
     *
     * @param cur  the cur
     * @param size the size
     */
    public void taskAddSuccess(Task cur, int size) {
        System.out.println("    ____________________________________________________________");
        System.out.println("      Got it. I've added this task:  ");
        System.out.println("      " + cur);
        System.out.println("      Now you have " + size + " tasks in the list.  \n");
        System.out.println("    ____________________________________________________________");

    }

}