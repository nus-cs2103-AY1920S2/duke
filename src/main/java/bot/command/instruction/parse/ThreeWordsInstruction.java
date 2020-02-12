package bot.command.instruction.parse;

import bot.command.Command;
import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

/**
 * Abstract class for an Instruction that consists
 * of one fixed word and two more words as
 * its two arguments
 */
public class ThreeWordsInstruction extends Instruction {
    public ThreeWordsInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public ParsedInstruction parse(String rawCommand, Command command)
            throws InadequateArgumentsException, TooManyArgumentsException {

        assert (rawCommand.startsWith(command.getWord()));
        String[] words = rawCommand.split("\\s+");
        if (words.length == 3) {
            return new ParsedInstruction(this, words[1], words[2]);
        } else if (words.length < 3) {
            System.out.println(command.getWord());
            throw new InadequateArgumentsException(command.getWord());
        } else {
            throw new TooManyArgumentsException(command.getWord());
        }
    }
}
