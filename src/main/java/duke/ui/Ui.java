/*
 * @author leslieharland
 */

package duke.ui;

import duke.alias.AliasList;
import duke.expense.ExpenseItem;
import duke.expense.ExpenseList;
import duke.task.Task;
import duke.task.TaskList;

import java.time.LocalDate;
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
     * Prints the application logo.
     *
     * @return logo string
     */
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
        return "\t____________________________________________________________";
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
            sb.append("\t" + m);
        }
        return sb.toString();
    }

    /**
     * Show tasks.
     *
     * @param tl     the task list
     * @param isFind formats the display information to the user if the find command is supplied
     */
    public String showTasks(TaskList tl, boolean isFind) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tHere are the " + (isFind ? "matching " : "") + "tasks in your list: \n");
        for (int count = 0; count < tl.getSize(); count++) {
            sb.append("\t" + (count + 1) + ". ");
            sb.append(tl.get(count)).append("\n");
        }

        return sb.toString();
    }

    /**
     * Show aliases.
     *
     * @param aliases the aliases list
     */
    public String showAliases(AliasList aliases) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tHere are the aliases in your list: \n");
        aliases.getAll().forEach((key, value) -> sb.append("\t" + key + " <--> " + value));

        return sb.toString();
    }

    /**
     * Show expenses.
     *
     * @param expenses  the expenses
     * @param startDate start date
     * @param endDate   end date
     */
    public String showExpenses(ExpenseList expenses, LocalDate startDate, LocalDate endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tHere are the expenses you have made: \n " + (startDate != null ? "from " + startDate.toString()
                + (endDate != null ? " to " + endDate.toString() : "") : ""));
        sb.append("\n");
        for (int count = 0; count < expenses.getSize(); count++) {
            sb.append("\t" + (count + 1) + ". ");
            sb.append(expenses.get(count)).append("\n");
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
     * Task removed successfully.
     *
     * @param cur  current item
     * @param size the size
     */
    public String taskRemoveSuccess(Task cur, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tNoted. I've removed this task: \n");
        sb.append("\t" + cur + "\n");
        sb.append("\tNow you have " + size + " tasks in the list.  \n");
        return sb.toString();
    }

    /**
     * Expense removed successfully.
     *
     * @param e    current item
     * @param size the size
     */
    public String expenseRemoveSuccess(ExpenseItem e, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tNoted. I've removed this expense: \n");
        sb.append("\t" + e + "\n");
        return sb.toString();
    }

    /**
     * Alias removed successfully.
     *
     * @param alias current item
     * @param size  the size
     */
    public String aliasRemoveSuccess(String alias, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tNoted. I've removed this alias: \n");
        sb.append("\t" + alias + "\n");
        return sb.toString();
    }

    /**
     * Task is added successfully.
     *
     * @param cur  current item
     * @param size the size
     */
    public String taskAddSuccess(Task cur, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tGot it. I've added this task:  \n");
        sb.append("\t" + cur + "\n");
        sb.append("\tNow you have " + size + " tasks in the list.  \n");
        return sb.toString();
    }

    /**
     * Expense is added successfully.
     *
     * @param e    current item
     * @param size the size
     */
    public String expenseAddSuccess(ExpenseItem e, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tGot it. I've added:  \n");
        sb.append("\t" + e + "\n");
        sb.append("\tTo view your expenses, type 'list expenses'. \n");
        return sb.toString();
    }

    /**
     * Alias is added successfully.
     *
     * @param alias current item
     * @param size  the size
     */
    public String aliasAddSuccess(String alias, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("\tGot it. I've added:  \n");
        sb.append("\t" + alias + "\n");
        sb.append("\tTo view your aliases, type 'list alias'. \n");
        return sb.toString();
    }


}
