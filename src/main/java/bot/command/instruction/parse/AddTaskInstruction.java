package bot.command.instruction.parse;

import bot.command.Command;
import bot.command.exception.InadequateArgumentsException;
import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;
import bot.task.Deadline;

/**
 * Abstract class for an Instruction that adds a Task
 * with the strict requirement of a date/time field
 */
public abstract class AddTaskInstruction extends Instruction {
    public AddTaskInstruction(Command... commands) {
        super(commands);
    }

    /**
     * Gets the sub-command of the Instruction (a word
     * that is essential to the Instruction, but
     * occurs only in the middle)
     *
     * <p>Example: "/by" for "deadline"
     *
     * @return Returns a String containing only the
     *     sub-command word
     */
    public abstract String getSubCommand();

    @Override
    public ParsedInstruction parse(String rawCommand, Command command)
            throws InadequateArgumentsException {

        assert (rawCommand.startsWith(command.getWord()))
                : "command parse failure in AddTaskInstruction";
        String rest = rawCommand.substring(command.getWord().length()).stripLeading();
        return new ParsedInstruction(this,
                formatDesc(rest, command),
                formatTime(rest));
    }

    /**
     * Method to format the input String
     * properly for a TaskInstruction's
     * description
     *
     * @param withoutFirst The input String without
     *          the first command word
     * @param command The command that led to this
     *                Instruction being run
     * @return Returns the formatted String description
     */
    private String formatDesc(String withoutFirst, Command command)
            throws InadequateArgumentsException {

        int indexFirst = withoutFirst.indexOf(this.getSubCommand());
        if (indexFirst == -1) {
            // could not find sub-command in raw command
            throw new InadequateArgumentsException(command.getWord());
        }
        return withoutFirst.substring(0, indexFirst).stripTrailing().stripLeading();
    }

    /**
     * Method to format the input String properly
     * for a TaskInstruction's time (the argument
     * after sub-command)
     *
     * @param withoutFirst The input String without
     *          the first command word
     *
     * @return Returns the formatted String time
     */
    private String formatTime(String withoutFirst) {
        int indexLast = withoutFirst.lastIndexOf(this.getSubCommand());
        return withoutFirst.substring(indexLast + Deadline.BY.length())
                .stripLeading();
    }
}