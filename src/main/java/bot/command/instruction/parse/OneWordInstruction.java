package bot.command.instruction.parse;

import bot.command.Command;

import bot.command.instruction.Instruction;

import bot.command.instruction.ParsedInstruction;

/**
 * Abstract class for an Instruction that consists
 * of only one word and no other arguments
 */
public abstract class OneWordInstruction extends Instruction {
    public OneWordInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public ParsedInstruction parse(String rawCommand, Command command) {
        assert (rawCommand.startsWith(command.getWord()))
                : "command parse failure in OneWordInstruction";
        return new ParsedInstruction(this);
    }
}
