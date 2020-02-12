package bot.command.instruction.concrete;

import bot.store.Storage;
import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.StorageModifying;
import bot.command.instruction.parse.TwoWordsInstruction;

import bot.task.Task;

public class MarkDoneInstruction extends TwoWordsInstruction
        implements StorageModifying<Task> {

    public MarkDoneInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void modifyStore(Storage<Task> store, Ui ui, int index) {
        store.get(index).markAsDone();
        ui.showDone(store.retrieve(index));
    }
}
