package bot.command.instruction.concrete;

import bot.command.exception.InstructionAlreadyExistsException;
import bot.store.Storage;
import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.StorageWriting;
import bot.command.instruction.parse.AddTaskInstruction;

import bot.task.Deadline;
import bot.task.Task;

public class DeadlineInstruction extends AddTaskInstruction
        implements StorageWriting<Task> {

    public DeadlineInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public String getSubCommand() {
        return Deadline.BY;
    }

    @Override
    public void writeToStore(Storage<Task> store, Ui ui, Task t) {
        try {
            store.store(t);
        } catch (InstructionAlreadyExistsException e) {
            // should never reach here!
            ui.showError(e);
        }
        ui.showTaskStoreMessage(store.getSize());
    }
}
