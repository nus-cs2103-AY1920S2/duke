package bot.command.instruction.concrete;

import bot.store.Storage;
import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.StorageWriting;
import bot.command.instruction.parse.ThreeWordsInstruction;

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

        ui.showAliasMessage();
    }
}