package bot.command.instruction.concrete;

import bot.Storage;
import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.StorageWriting;
import bot.command.instruction.parse.TextInstruction;

import bot.task.Task;

public class TodoInstruction extends TextInstruction
        implements StorageWriting<Task> {

    public TodoInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void writeToStore(Storage<Task> store, Ui ui, Task t) {
        store.store(t);
        ui.showTaskStoreMessage(store.getSize());
    }
}
