package duke.command.expense;

import duke.DukeException;
import duke.Storage;
import duke.command.Command;
import duke.expense.ExpenseList;

public abstract class ExpenseCommand extends Command {

    public abstract String execute(ExpenseList expenses, Storage storage)
            throws DukeException;
}