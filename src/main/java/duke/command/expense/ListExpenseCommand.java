package duke.command.expense;

import duke.Storage;
import duke.common.Message;
import duke.expense.ExpenseList;

public class ListExpenseCommand extends ExpenseCommand {

    /**
     * Executes the command and lists all the expenses.
     */
    public String execute(ExpenseList expenses, Storage storage) {
        String output = Message.LIST_MESSAGE + "\n"
                + expenses.toString();
        return output;
    }
}