package bot.command;

import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;

import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

import bot.command.instruction.concrete.AliasInstruction;
import bot.command.instruction.concrete.DeadlineInstruction;
import bot.command.instruction.concrete.DeleteInstruction;
import bot.command.instruction.concrete.EventInstruction;
import bot.command.instruction.concrete.FindKeywordInstruction;
import bot.command.instruction.concrete.HelloInstruction;
import bot.command.instruction.concrete.HelpInstruction;
import bot.command.instruction.concrete.ListInstruction;
import bot.command.instruction.concrete.MarkDoneInstruction;
import bot.command.instruction.concrete.MarkNotDoneInstruction;
import bot.command.instruction.concrete.SearchTimeInstruction;
import bot.command.instruction.concrete.TerminateInstruction;
import bot.command.instruction.concrete.ThanksInstruction;
import bot.command.instruction.concrete.TodoInstruction;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class to contain logic for commands
 * given to bot
 */
public class CommandParser {
    public static final Instruction[] ALL_INSTRUCTIONS = {
        new AliasInstruction(Command.ALIAS),
        new DeadlineInstruction(Command.DEADLINE),
        new DeleteInstruction(Command.DELETE),
        new EventInstruction(Command.EVENT),
        new FindKeywordInstruction(Command.FIND),
        new HelloInstruction(Command.HELLO),
        new HelpInstruction(Command.HELP),
        new ListInstruction(Command.LIST),
        new MarkDoneInstruction(Command.DONE),
        new MarkNotDoneInstruction(Command.NOT_DONE),
        new SearchTimeInstruction(Command.SEARCH),
        new TerminateInstruction(
                Command.BYE,
                Command.EX,
                Command.EXI,
                Command.EXIT),
        new ThanksInstruction(Command.THANKS),
        new TodoInstruction(Command.TODO)
    };

    public static final HashMap<String, Instruction> INSTR_MAP =
            new HashMap<String, Instruction>();
    public static final HashMap<String, Command> COMMAND_MAP =
            new HashMap<String, Command>();

    public CommandParser() {
        CommandParser.updateMaps();
    }

    /**
     * Main method to accept a command, and
     * generate the corresponding instruction
     * for the bot to execute
     *
     * @param command The command, as a String
     * @return Instruction to the bot, telling
     *         it what to do next
     */
    public ParsedInstruction parse(String command)
            throws InadequateArgumentsException, TooManyArgumentsException,
            UnknownInstructionException {

        String firstWord = command.split("\\s+", 2)[0];
        if (CommandParser.INSTR_MAP.containsKey(firstWord)
                && CommandParser.COMMAND_MAP.containsKey(firstWord)
        ) {
            return CommandParser.INSTR_MAP.get(firstWord)
                    .parse(command, CommandParser.COMMAND_MAP.get(firstWord));
        } else {
            throw new UnknownInstructionException(command);
        }
    }

    /**
     * Updates the command map and instruction map based
     * on which Commands are present in the Instructions
     * within the static Instruction[] allInstructions
     */
    private static void updateMaps() {
        for (Instruction inst : ALL_INSTRUCTIONS) {
            Collection<Command> commands = inst.getCommandList();
            for (Command com : commands) {
                CommandParser.INSTR_MAP.put(com.getWord(), inst);
                CommandParser.COMMAND_MAP.put(com.getWord(), com);
            }
        }
    }
}