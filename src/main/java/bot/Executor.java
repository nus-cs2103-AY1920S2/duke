package bot;

import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

import bot.command.instruction.concrete.DeadlineInstruction;
import bot.command.instruction.concrete.EventInstruction;
import bot.command.instruction.concrete.ListInstruction;
import bot.command.instruction.concrete.TerminateInstruction;

import bot.command.instruction.concrete.TodoInstruction;
import bot.command.instruction.execute.NotStorable;
import bot.command.instruction.execute.StorageModifying;
import bot.command.instruction.execute.StorageReading;
import bot.command.instruction.execute.StorageSearching;
import bot.command.instruction.execute.StorageWriting;

import bot.loadsave.LoadAndSave;

import bot.store.Storage;
import bot.task.Deadline;
import bot.task.Event;
import bot.task.Task;
import bot.task.Todo;

import java.util.ArrayList;

/**
 * Executor receives instructions from the
 * CommandParser and makes the necessary changes
 * to the state of the application
 *
 * <p>Executor is currently written specifically for
 * instructions that deal with Tasks
 */
public class Executor {
    private final Storage<Task> storage;
    private final Ui ui;
    private final LoadAndSave<Task> storageLocation;

    /**
     * Constructs a new Executor, handling execution
     * of instructions for the bot
     *
     * @param store The Task storage of the bot
     * @param ui The UI of the bot
     * @param storeLoc The LoadAndSave representing file
     *                 directory and file name of the
     *                 storage file on disk
     */
    public Executor(Storage<Task> store, Ui ui, LoadAndSave<Task> storeLoc) {
        this.storage = store;
        this.ui = ui;
        this.storageLocation = storeLoc;
    }

    /**
     * Executes a given Instruction
     *
     * @param parsed The ParsedInstruction to execute
     *               (Instruction wrapped with its
     *               required arguments)
     *
     * @return Returns false, if the program is to
     *     be terminated. If the program is to
     *     continue, returns true.
     */
    @SuppressWarnings("unchecked")
    public boolean execute(ParsedInstruction parsed) {
        Instruction inst = parsed.getInstruction();
        ArrayList<String> arguments = parsed.getArguments();
        if (inst instanceof NotStorable) {
            return this.executeNotStorable((NotStorable) inst, arguments);
        } else if (inst instanceof StorageModifying) {
            return this.executeModifying((StorageModifying<Task>) inst, arguments);
        } else if (inst instanceof StorageReading) {
            return this.executeReading((StorageReading<Task>) inst, arguments);
        } else if (inst instanceof StorageSearching) {
            return this.executeSearching((StorageSearching<Task>) inst, arguments);
        } else if (inst instanceof StorageWriting) {
            return this.executeWriting((StorageWriting<Task>) inst, arguments);
        } else {
            // unknown instruction type, end program
            System.err.println("unknown Instruction type in Executor.execute()");
            return false;
        }
    }

    private boolean executeNotStorable(NotStorable parsed, ArrayList<String> arguments) {
        parsed.printUiMessage(this.ui);
        return !(parsed instanceof TerminateInstruction);
    }

    private boolean executeModifying(StorageModifying<Task> parsed, ArrayList<String> arguments) {
        // assumes all StorageModifying Instructions have the
        // required index as their second argument
        try {
            parsed.modifyStore(this.storage, this.ui,
                    Integer.parseInt(arguments.get(0)));
        } catch (NumberFormatException e) {
            this.ui.showError(e);
        }
        return true;
    }

    private boolean executeReading(StorageReading<Task> parsed, ArrayList<String> arguments) {
        // assumes parsed is a ListInstruction
        assert (parsed instanceof ListInstruction)
                : "unknown StorageReading in Executor.executeReading";
        parsed.readStore(this.storage, this.ui);
        return true;
    }

    private boolean executeSearching(StorageSearching<Task> parsed, ArrayList<String> arguments) {
        // assumes all StorageSearching Instructions have the
        // desired search terms as their second argument
        assert (arguments.size() == 2)
                : "executeSearching with more than 2 parameters";
        parsed.searchStore(this.storage, this.ui, arguments.get(0));
        return true;
    }

    private boolean executeWriting(StorageWriting<Task> parsed, ArrayList<String> arguments) {
        Task toWrite;
        if (parsed instanceof DeadlineInstruction) {
            toWrite = new Deadline(arguments.get(0), arguments.get(1));
        } else if (parsed instanceof EventInstruction) {
            toWrite = new Event(arguments.get(0), arguments.get(1));
        } else if (parsed instanceof TodoInstruction) {
            // parsed instanceof TodoInstruction
            toWrite = new Todo(arguments.get(0));
        } else {
            // unknown StorageWriting instruction
            System.err.println(
                    "unknown StorageWriting in Executor.executeWriting()"
            );
            return false;
        }
        parsed.writeToStore(this.storage, this.ui, toWrite);
        this.storage.saveToDisk(this.storageLocation);
        return true;
    }
}
