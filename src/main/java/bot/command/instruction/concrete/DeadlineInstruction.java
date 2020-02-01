package bot.command.instruction.concrete;

import bot.Storage;
import bot.Ui;

import bot.command.Command;

import bot.command.instruction.execute.StorageWriting;

import bot.command.instruction.parse.AddTaskInstruction;

import bot.task.Deadline;
import bot.task.Task;

public class DeadlineInstruction extends AddTaskInstruction
        implements StorageWriting<Task>
{
    public DeadlineInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public String getSubCommand() {
        return Deadline.BY;
    }

    @Override
    public void writeToStore(Storage<Task> store, Ui ui, Task t) {
        store.store(t);
        ui.showTaskStoreMessage(store.getSize());
    }
}
