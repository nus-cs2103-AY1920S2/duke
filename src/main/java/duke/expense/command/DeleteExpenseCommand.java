package duke.expense.command;

import duke.DukeException;
import duke.Storage;
import duke.common.message.ErrorMessage;
import duke.common.message.Message;
import duke.expense.Expense;
import duke.expense.ExpenseList;

public class DeleteExpenseCommand extends ExpenseCommand {

    private int index;

    public DeleteExpenseCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command and deletes the expense with the given index.
     * Then, displays the response to the user.
     */
    public String execute(ExpenseList expenses, Storage storage)
            throws DukeException {
        if (index < 1 || index > expenses.getLength()) {
            throw new DukeException(ErrorMessage.INVALID_INDEX);
        }
        
        Expense expense = expenses.getExpense(index);
        expenses.deleteExpense(index);
        storage.save(expenses);
        
        String output = Message.DELETE_MESSAGE + "\n"
                + Message.DIVIDER + "\n"
                + "  " + expense + "\n"
                + Message.DIVIDER + "\n"
                + Message.showNumberOfTasks(expenses.getLength()) + "\n";

        return output;
    }
}


