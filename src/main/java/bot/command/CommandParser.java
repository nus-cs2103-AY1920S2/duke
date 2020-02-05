package bot.command;

import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;

import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

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

    private static final HashMap<String, Instruction> instrMap =
            new HashMap<String, Instruction>();
    private static final HashMap<String, Command> commandMap =
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
        if (CommandParser.instrMap.containsKey(firstWord)
                && CommandParser.commandMap.containsKey(firstWord)
        ) {
            return CommandParser.instrMap.get(firstWord)
                    .parse(command, CommandParser.commandMap.get(firstWord));
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
                CommandParser.instrMap.put(com.getWord(), inst);
                CommandParser.commandMap.put(com.getWord(), com);
            }
        }
    }
}