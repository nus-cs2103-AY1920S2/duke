package duke.expense.command;

import duke.DukeException;
import duke.Storage;
import duke.common.message.Message;
import duke.expense.Expense;
import duke.expense.ExpenseList;

import java.time.LocalDate;

public class AddExpenseCommand extends ExpenseCommand {

    private String description;
    private double expense;
    private LocalDate date;

    /**
     * Creates an add expense command.
     * @param description The description of the expense.
     * @param expense The amount of expense.
     * @param date The date of the expense.
     */
    public AddExpenseCommand(String description, double expense, LocalDate date) {
        this.description = description;
        this.expense = expense;
        this.date = date;
    }

    /**
     * Executes the command. Check for the type of task to be added, and add
     * the relevant task to task list. The current list of tasks are then saved.
     * @param expenses The list of expenses to be executed upon.
     * @param storage The storage to save the expenses.
     * @return The response from executing the command.
     */
    public String execute(ExpenseList expenses, Storage storage)
            throws DukeException {

        Expense expenseItem = new Expense(description, expense, date);
        expenses.add(expenseItem);
        storage.save(expenses);
        
        String output = Message.EXPENSE_ADDED + "\n"
                + Message.DIVIDER + "\n"
                + "  " + expenseItem + "\n"
                + Message.DIVIDER;
        return output;
    }
}