package duke.command.expense;

import java.time.LocalDate;

import duke.DukeException;
import duke.Storage;
import duke.common.Message;
import duke.expense.Expense;
import duke.expense.ExpenseList;

public class AddExpenseCommand extends ExpenseCommand {

    private String description;
    private double expense;
    private LocalDate date;

    public AddExpenseCommand(String description, double expense, LocalDate date) {
        this.description = description;
        this.expense = expense;
        this.date = date;
    }

    /**
     * Executes the command. Check for the type of task to be added, and add
     * the relevant task to task list. The current list of tasks are then saved.
     */
    public String execute(ExpenseList expenses, Storage storage)
            throws DukeException {

        Expense expenseItem = new Expense(description, expense, date);
        expenses.add(expenseItem);
        storage.save(expenses);
        
        String output = Message.TASK_ADDED + "\n"
                + Message.DIVIDER + "\n"
                + "  " + expenseItem + "\n"
                + Message.DIVIDER + "\n"
                + Message.showNumberOfTasks(expenses.getLength());
        return output;
    }
}