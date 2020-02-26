package bot.command.instruction.parse;

import bot.command.Command;
import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

/**
 * Abstract class for an Instruction that consists
 * of one fixed word and one more word as
 * its single argument
 */
public abstract class TwoWordsInstruction extends Instruction {
    public TwoWordsInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public ParsedInstruction parse(String rawCommand, Command command)
            throws InadequateArgumentsException, TooManyArgumentsException {

        assert (rawCommand.startsWith(command.getWord()));
        String[] words = rawCommand.split("\\s+");
        if (words.length == 2) {
            return new ParsedInstruction(this, words[1]);
        } else if (words.length < 2) {
            System.out.println(command.getWord());
            throw new InadequateArgumentsException(command.getWord());
        } else {
            throw new TooManyArgumentsException(command.getWord());
        }
    }
}
