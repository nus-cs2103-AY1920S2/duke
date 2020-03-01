package logic.command;

import commons.Duke;

import static java.util.Objects.requireNonNull;

public class ListCommand implements Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Here are the tasks in your list";
    public static final String EMPTY_LIST = "List is empty.";

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        if (duke.getTaskList().isEmpty()) {
            return new CommandResult(EMPTY_LIST);
        }
        return new CommandResult(MESSAGE_SUCCESS + duke.getTaskList().printList());
    }
}
