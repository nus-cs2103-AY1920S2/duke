package bot.command.instruction.concrete;

import bot.Storage;
import bot.Ui;

import bot.command.Command;

import bot.command.instruction.execute.StorageReading;

import bot.command.instruction.parse.OneWordInstruction;

import bot.task.Task;

public class ListInstruction extends OneWordInstruction
        implements StorageReading<Task>
{
    public ListInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void readStore(Storage<Task> store, Ui ui) {
        // Prints out all the stored items,
        // in order which they were stored
        int length = store.getSize();
        for (int i = 0; i < length; i++) {
            System.out.println(store.retrieve(i + 1));
        }
    }
}
