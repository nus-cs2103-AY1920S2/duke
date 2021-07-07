package logic.command;

import commons.Duke;
import commons.Messages;

import static java.util.Objects.requireNonNull;

//to fix
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    public static final String HELP = "alias: creates shortcut\n\talias a/alias c/command\n\n"
            + "bye: exits the programme\n\n"
            + "clear: clears list\n\n"
            + "deadline: creates task with a deadline \n\tdeadline n/name d/date [t/tag]\n\n"
            + "delete: deletes task with task number\n\tdelete index\n\n"
            + "done: marks task with task number as done\n\tdone index\n\n"
            + "event: creates task that is an event \n\tn/description d/date [t/tag]\n\n"
            + "find: returns list of tasks with keyword in name\n\tfind keyword\n\n"
            + "tag: returns list of tasks containing tag\n\t tag tagname\n\n"
            + "todo: creates task \n\ttodo n/name [t/tag]\n\n";

    @Override
    public CommandResult execute(Duke duke) {
        requireNonNull(duke);
        return new CommandResult(HELP, true, false);
    }
}
