/*
 * @author leslieharland
 */

package duke;


import duke.alias.AliasList;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * The Class Duke.
 */
public class Duke {
    /**
     * Constants for modes.
     */

    public static final int MODE_TASK_ID = 1;
    public static final int MODE_EXPENSE_ID = 2;
    static Logger logger = Logger.getLogger(EnumUtil.class.getName());
    private Image user = new Image(this.getClass().getResourceAsStream("/images/avatar.jpg"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    /**
     * Initialisers for storage used.
     */
    private Storage taskStorage;
    private Storage expenseStorage;
    private Storage aliasStorage;
    /**
     * The tasks.
     */
    private TaskList tasks;
    private TaskList temp;

    private ExpenseList expenses;
    private AliasList aliases;
    /**
     * The ui.
     */
    private Ui ui;

    /**
     * Instantiates a new duke.
     *
     * @param taskFilePath    the file path used for storing tasks
     * @param expenseFilePath the file path used for storing expenses
     * @param aliasFilePath   the file path used for storing alias
     */
    public Duke(String taskFilePath, String expenseFilePath, String aliasFilePath) {
        ui = new Ui();

        try {
            taskStorage = new Storage(taskFilePath);
            temp = new TaskList(taskStorage);
            tasks = new TaskList(taskStorage);
            aliases = new AliasList(aliasStorage);
            expenseStorage = new Storage(expenseFilePath);
            aliasStorage = new Storage(aliasFilePath);
            expenses = new ExpenseList(expenseStorage);
        } catch (DukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) throws DukeException {
        String command = "";

        String[] current = input.split(" ");
        command = current[0];
        Task t = null;
        //search for alias
        for (Map.Entry<String, String> keyValue : aliases.getAll().entrySet()) {
            logger.log(Level.INFO, "alias name " + keyValue.getKey());
            if (keyValue.getKey().equals(command)) {
                command = keyValue.getValue();
            }
        }

        if (EnumUtil.isValidOperation(command)) {
            Operation operation = Operation.valueOf(command.toUpperCase());
            switch (operation) {
            case BYE:
                return ui.clean();

            case LIST:
                new EnumUtil();
                if (current.length < 2 || !EnumUtil.isValidMode(current[1])) {
                    try {
                        throw new DukeException(
                                " ☹ OOPS!!! what should i list? \n You can list tasks, expenses, alias.");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                } else {
                    String mode = current[1];
                    switch (mode) {
                    case "tasks":
                        temp.getTasks().clear();
                        tasks = taskStorage.loadTasks();
                        return ui.showTasks(tasks, false);
                    case "expenses":
                        return getExpensesResultsString(Arrays.stream(current).skip(1).toArray(String[]::new));
                    case "alias":
                        return ui.showAliases(aliases);
                    default:
                        return "";
                    }
                }
            case TODO:
            case DEADLINE:
            case EVENT:
                try {
                    String[] words = Arrays.stream(current).skip(1).toArray(String[]::new);
                    t = tasks.addTask(command, words, taskStorage);
                    return ui.taskAddSuccess(t, tasks.getSize());
                } catch (DukeException ex) {
                    return ui.showMessage(Arrays.asList(ex.getMessage()));
                }

            case FIND:
                temp = taskStorage.findTasks(Arrays.stream(current).skip(1).toArray(String[]::new));
                return ui.showTasks(temp, true);

            case DONE:
                if (current.length < 2) {
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
                new EnumUtil();
                if (current.length < 2 || !EnumUtil.isValidMode(current[1])) {
                    try {
                        throw new DukeException(
                                " ☹ OOPS!!! what should i delete? \n\t You can delete tasks, expenses, alias. \n"
                                       + "\tEg. delete tasks 1 \n\tlist tasks to display all tasks.");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                }
                String mode = current[1];

                if (current.length < 3) {
                    try {
                        throw new DukeException(
                                "☹ OOPS!!! Please supply the number next to the item to delete");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                }
                try {

                    switch (mode) {
                    case "tasks":
                        int keyToDelete = Integer.parseInt(current[2]);

                        if (!temp.getTasks().isEmpty()) {
                            keyToDelete = ((SearchTask) temp.get(keyToDelete - 1)).getKey();
                            temp.getTasks().clear();
                        }
                        Task cur = tasks.deleteTask(keyToDelete, taskStorage);
                        return ui.taskRemoveSuccess(cur, tasks.getSize());
                    case "expenses":
                        keyToDelete = Integer.parseInt(current[2]);
                        ExpenseItem e = expenses.deleteExpense(keyToDelete, taskStorage);
                        return ui.expenseRemoveSuccess(e, expenses.getSize());
                    case "alias":
                        String alias = current[2];
                        aliases.deleteAlias(alias, aliasStorage);
                        return ui.aliasRemoveSuccess(alias, aliases.getSize());
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

            case SET:
                if (current.length > 1 && current[1] != null) {
                    switch (current[1]) {
                    case "alias":
                        if (current.length < 3) {
                            try {
                                throw new DukeException(
                                        "☹ OOPS!!! Please specify the alias name. \n\t e.g. set alias t todo");
                            } catch (Exception ex) {
                                return ui.showMessage(Arrays.asList(ex.getMessage()));
                            }
                        }

                        if (current.length > 3 && current[3] == null) {
                            try {
                                throw new DukeException(
                                        "☹ OOPS!!! Please specify which command to map the alias. "
                                               + "\n\te.g. set alias t todo");
                            } catch (Exception ex) {
                                return ui.showMessage(Arrays.asList(ex.getMessage()));
                            }
                        }
                        try {
                            aliases = aliases.addAlias(current[2], current[3], aliasStorage);
                            return ui.aliasAddSuccess(current[2], aliases.getSize());
                        } catch (DukeException ex) {
                            return ui.showMessage(Arrays.asList(ex.getMessage()));
                        }
                    default:
                        return ui.showMessage(Arrays.asList("Operation not supported"));

                    }
                } else {
                    try {
                        throw new DukeException(
                                "☹ OOPS!!! Please specify what to set");
                    } catch (Exception ex) {
                        return ui.showMessage(Arrays.asList(ex.getMessage()));
                    }
                }

            default:
                return "";
            }
        } else {
            //invalid command
            try {
                throw new DukeException(
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception ex) {
                return ui.showMessage(Arrays.asList(ex.getMessage()));
            }
        }


    }

    /**
     * method to collect the operations required to get the expenses from storage and prints the result to user.
     *
     * @param current user input array
     * @return String expenseResult
     */
    public String getExpensesResultsString(String[] current) {
        LocalDate fmtStartDate = null;
        LocalDate fmtEndDate = null;
        if (current.length > 1 && current[1] != null) {
            String startDate = current[1];
            fmtStartDate = DateUtil.formatDate(startDate);
        }

        if (current.length > 2 && current[2] != null) {
            String endDate = current[2];
            fmtEndDate = DateUtil.formatDate(endDate);
        }

        expenses = expenseStorage.loadExpenses();
        return ui.showExpenses(expenses.filterExpensesByDate(fmtStartDate, fmtEndDate),
                fmtStartDate, fmtEndDate);
    }
}
