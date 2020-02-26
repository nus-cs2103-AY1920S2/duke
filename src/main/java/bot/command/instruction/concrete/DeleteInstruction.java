package bot.command.instruction.concrete;


import bot.store.Storage;
import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.StorageModifying;
import bot.command.instruction.parse.TwoWordsInstruction;

import bot.task.Task;

public class DeleteInstruction extends TwoWordsInstruction
        implements StorageModifying<Task> {

    public DeleteInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void modifyStore(Storage<Task> store, Ui ui, int index) {
        String toBeDeleted = store.retrieve(index);
        store.delete(index);
        ui.showDeleted(toBeDeleted);
    }
}
