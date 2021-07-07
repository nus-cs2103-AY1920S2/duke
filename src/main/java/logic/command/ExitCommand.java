package logic.command;

import commons.Duke;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Bye. Hope to see you again soon!\n"
            + "Exiting duke as requested...";

    @Override
    public CommandResult execute(Duke duke) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }
}
