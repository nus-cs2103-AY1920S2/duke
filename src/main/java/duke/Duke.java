/*
 * @author leslieharland
 */

package duke;


import duke.command.Operation;
import duke.expense.ExpenseItem;
import duke.expense.ExpenseList;
import duke.storage.Storage;
import duke.task.SearchTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.DateUtil;
import duke.util.EnumUtil;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import javax.naming.OperationNotSupportedException;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * The Class Duke.
 */
public class Duke {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/avatar.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Initialisers for storage used
     */
    private Storage taskStorage;
    private Storage expenseStorage;

    /** Constants for modes
     *
     */

    public static final int MODE_TASK_ID = 1;
    public static final int MODE_EXPENSE_ID = 2;

    /**
     * The tasks.
     */
    private TaskList tasks;
    private TaskList temp;

    private ExpenseList expenses;
    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     *
     * @param taskFilePath the file path used for storing tasks
     * @param expenseFilePath the file path used for storing expenses
     */
    public Duke(String taskFilePath, String expenseFilePath) {
        ui = new Ui();

        try {
            taskStorage = new Storage(taskFilePath);
            temp = new TaskList(taskStorage);
            tasks = new TaskList(taskStorage);
            expenseStorage = new Storage(expenseFilePath);
            expenses = new ExpenseList(expenseStorage);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        String command = "";

        String[] current = input.split(" ");
        command = current[0];
        Task t = null;

        if (EnumUtil.isValidOperation(command)) {
            Operation operation = Operation.valueOf(command.toUpperCase());
            switch (operation) {
            case BYE:
                return ui.clean();

            case LIST:
                if (current.length < 2 || !EnumUtil.isValidMode(current[1])){
                    try {
                        throw new DukeException(
                                " ☹ OOPS!!! what should i list? \n You can list tasks, expenses.");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                } else {
                    String mode = current[1];
                    switch (mode){
                    case "tasks":
                        temp.getTasks().clear();
                        tasks = taskStorage.loadTasks();
                        return ui.showTasks(tasks, false);
                    case "expenses":
                        return getExpensesResultsString(Arrays.stream(current).skip(1).toArray(String[]::new));
                    default:
                        return "";
                    }
                }
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    t = tasks.addTask(current, taskStorage);
                    return ui.taskAddSuccess(t, tasks.getSize());
                } catch (DukeException ex) {
                    return ui.showMessage(Arrays.asList(ex.getMessage()));
                }

            case FIND:
                temp = taskStorage.findTasks(Arrays.stream(current).skip(1).toArray(String[]::new));
                return ui.showTasks(temp, true);

            case DONE:
                if (current.length < 2){
                    try {
                        throw new DukeException(
                                " ☹ OOPS!!! Please supply the number next to the item to delete");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                }
                int id = Integer.parseInt(current[1]);
                boolean isFindTasks = !temp.getTasks().isEmpty();
                if (isFindTasks) {
                    id = ((SearchTask) temp.get(id - 1)).getKey();
                }
                try {
                    Task cur = tasks.markTaskAsDone(id);
                    return ui.taskMarkDone(cur);
                } catch (IndexOutOfBoundsException ex) {
                    return ui.taskNumberError();
                }

            case DELETE:
                if (current.length < 2 || !EnumUtil.isValidMode(current[1])){
                    try {
                        throw new DukeException(
                                " ☹ OOPS!!! what should i delete? \n\t You can delete tasks, expenses. \n" +
                                        "\tEg. delete tasks 1 \n\tlist tasks to display all tasks.");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                }
                String mode = current[1];

                if (current.length < 3){
                    try {

                        throw new DukeException(
                                "☹ OOPS!!! Please supply the number next to the item to delete");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                }
                try {
                    int keyToDelete = Integer.parseInt(current[2]);
                    switch (mode){
                        case "tasks":
                            if (!temp.getTasks().isEmpty()) {
                                keyToDelete = ((SearchTask) temp.get(keyToDelete - 1)).getKey();
                                temp.getTasks().clear();
                            }
                            Task cur = tasks.deleteTask(keyToDelete, taskStorage);
                            return ui.taskRemoveSuccess(cur, tasks.getSize());
                        case "expenses":
                            ExpenseItem e = expenses.deleteExpense(keyToDelete, taskStorage);
                            return ui.expenseRemoveSuccess(e, expenses.getSize());
                        default:
                            return "";
                    }
                } catch (IndexOutOfBoundsException ex) {
                    return ui.taskNumberError();
                }

            case SPEND:
                try {
                    ExpenseItem e = expenses.addExpense(current, expenseStorage);
                    return ui.expenseAddSuccess(e, expenses.getSize());
                } catch (DukeException ex) {
                    return ui.showMessage(Arrays.asList(ex.getMessage()));
                }


            default:
            return "";
            }
        } else {
            try {
                throw new DukeException(
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception ex) {
                return ui.showMessage(Arrays.asList(ex.getMessage()));
            }
        }


    }

    /**
     * method to collect the operations required to get the expenses from storage and prints the result to user
     * @param current user input array
     * @return String expenseResult
     */
    public String getExpensesResultsString(String[] current){
        LocalDate fmtStartDate = null;
        LocalDate fmtEndDate = null;
        if (current.length > 1 && current[1] != null){
            String startDate = current[1];
            fmtStartDate = DateUtil.formatDate(startDate);
        }

        if (current.length > 2 && current[2] != null){
            String endDate = current[2];
            fmtEndDate = DateUtil.formatDate(endDate);
        }

        expenses = expenseStorage.loadExpenses();
        return ui.showExpenses(expenses.filterExpensesByDate(fmtStartDate, fmtEndDate),
                fmtStartDate, fmtEndDate);
    }
}
