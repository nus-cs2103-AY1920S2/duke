package bot.command.instruction.parse;

import bot.command.Command;

import bot.command.exception.InadequateArgumentsException;

import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

/**
 * Abstract class for an Instruction that consists
 * of one fixed word and an unlimited number
 * of arguments (the text)
 */
public abstract class TextInstruction extends Instruction {
    public TextInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public ParsedInstruction parse(String rawCommand, Command command)
            throws InadequateArgumentsException {

        assert (rawCommand.startsWith(command.getWord()));
        return new ParsedInstruction(this, TextInstruction.checkForNonEmpty(
                rawCommand.substring(command.getWord().length()).stripLeading(),
                command));
    }

    /**
     * Static method to check and throw an Exception
     * if raw command for TextInstruction is empty
     * (only contains whitespace)
     *
     * @param rawCommand The String to be checked
     * @param orig The original Command identified
     *             by the CommandParser
     * @return Returns the same String, unchanged
     * @throws InadequateArgumentsException When the String
     *         is an empty String
     */
    private static String checkForNonEmpty(String rawCommand, Command orig)
            throws InadequateArgumentsException {

        if (rawCommand.isBlank()) {
            throw new InadequateArgumentsException(orig.getWord());
        } else {
            return rawCommand;
        }
    }
}

