package command;

import exception.*;
import main.*;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws UnknownCommandException{
        throw new UnknownCommandException();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}