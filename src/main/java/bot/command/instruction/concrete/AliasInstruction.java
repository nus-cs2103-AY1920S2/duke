package bot.command.instruction.concrete;

import bot.Ui;

import bot.command.Command;
import bot.command.CommandParser;
import bot.command.instruction.Instruction;
import bot.command.instruction.execute.StorageWriting;
import bot.command.instruction.parse.ThreeWordsInstruction;

import bot.store.Storage;

import bot.util.Pair;

public class AliasInstruction extends ThreeWordsInstruction
        implements StorageWriting<Pair<String, String>> {

    public AliasInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void writeToStore(Storage<Pair<String, String>> commandStore,
            Ui ui, Pair<String, String> nameToReplace) {

        // first in pair is the original name
        // second in pair is the aliased name
        commandStore.store(nameToReplace);
        AliasInstruction.addAliasedName(
                nameToReplace.getFirst(),
                nameToReplace.getSecond()
        );
        ui.showAliasMessage();
    }

    /**
     * Given the original command name and new command name,
     * update the CommandParser maps such that the new
     * command name will work for the same Instruction
     * that the original command name refers to
     *
     * @param originalName String representing the
     *                     original command name
     * @param altName String representing the
     *                new command name
     */
    public static void addAliasedName(String originalName, String altName) {
        // assumes aliased name is not used already
        Command aliased = new Command(altName);
        Instruction inst = CommandParser.INSTR_MAP.get(originalName);
        // update maps
        CommandParser.INSTR_MAP.put(altName, inst);
        CommandParser.COMMAND_MAP.put(altName, aliased);
    }
}