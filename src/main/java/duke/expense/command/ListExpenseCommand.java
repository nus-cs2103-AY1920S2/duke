package duke.expense.command;

import duke.Storage;
import duke.common.message.Message;
import duke.expense.ExpenseList;

public class ListExpenseCommand extends ExpenseCommand {

    /**
     * Executes the command and lists all the expenses.
     */
    public String execute(ExpenseList expenses, Storage storage) {
        String output = Message.EXPENSE_MESSAGE + "\n"
                + expenses.toString();
        return output;
    }
}