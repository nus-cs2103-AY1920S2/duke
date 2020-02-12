package bot.command.instruction;

import bot.Ui;

import bot.command.Command;

import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * An abstract class that describes an Instruction. Each
 * Instruction knows what Commands refer to it, as
 * well as how the program state is changed when
 * the Instruction is run
 */
public abstract class Instruction {
    private final ArrayList<Command> commandList;

    /**
     * Constructs a new Instruction
     * @param commands All the Commands that
     *                 will call this Instruction
     */
    public Instruction(Command... commands) {
        this.commandList = new ArrayList<Command>(Arrays.asList(commands));
    }

    /**
     * Gets the list of Commands that correspond to
     * this Instruction
     *
     * @return Collection of Commands that cause this
     *     Instruction to execute
     */
    public Collection<Command> getCommandList() {
        return this.commandList;
    }

    /**
     * Adds Command to the list of Commands that
     * refer to this Instruction
     *
     * @param com The Command to be added for this
     *            Instruction
     */
    public void addToCommandList(Command com) {
        this.commandList.add(com);
    }

    /**
     * When given the raw command, this method breaks
     * up the command into its individual arguments
     * and returns a ParsedInstruction
     *
     * @param rawCommand The raw command typed by
     *                   the user
     * @param com The command that was identified by
     *            CommandParser
     * @return Returns a ParsedInstruction representing
     *     the Instruction and arguments of rawCommand
     */
    public abstract ParsedInstruction parse(String rawCommand, Command com)
            throws InadequateArgumentsException, TooManyArgumentsException;
}
