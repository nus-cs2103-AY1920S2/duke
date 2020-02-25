package bot;

import bot.command.instruction.Instruction;
import bot.command.instruction.ParsedInstruction;

import bot.command.instruction.concrete.AliasInstruction;
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
import bot.util.Pair;

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
    private final Ui ui;
    private final Storage<Task> taskStorage;
    private final LoadAndSave<Task> taskStoreLocation;
    private final Storage<Pair<String, String>> aliasStorage;
    private final LoadAndSave<Pair<String, String>> aliasStoreLocation;

    /**
     * Constructs a new Executor, handling execution
     * of instructions for the bot
     * @param ui The UI of the bot
     * @param taskStore The Task storage of the bot
     * @param tasksLoc The LoadAndSave representing file
     *                 directory and file name of the
     *                 Task storage
     * @param aliasStore The storage containing aliases
     *                   for commands
     * @param aliasesLoc The LoadAndSave representing file
     *                   directory and file name of the
     *                   aliases storage
     */
    public Executor(Ui ui, Storage<Task> taskStore, LoadAndSave<Task> tasksLoc,
                    Storage<Pair<String, String>> aliasStore,
                    LoadAndSave<Pair<String, String>> aliasesLoc) {

        this.ui = ui;
        this.taskStorage = taskStore;
        this.taskStoreLocation = tasksLoc;
        this.aliasStorage = aliasStore;
        this.aliasStoreLocation = aliasesLoc;
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
        } else if (inst instanceof AliasInstruction) {
            return this.executeAlias((AliasInstruction) inst, arguments);
        } else if (inst instanceof StorageWriting) {
            // this is guaranteed to be a task writing instruction
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
            parsed.modifyStore(this.taskStorage, this.ui,
                    Integer.parseInt(arguments.get(0)));
        } catch (NumberFormatException e) {
            this.ui.showError(e);
        }
        this.taskStorage.saveToDisk(this.taskStoreLocation);
        return true;
    }

    private boolean executeReading(StorageReading<Task> parsed, ArrayList<String> arguments) {
        // assumes parsed is a ListInstruction
        assert (parsed instanceof ListInstruction)
                : "unknown StorageReading in Executor.executeReading";
        parsed.readStore(this.taskStorage, this.ui);
        return true;
    }

    private boolean executeSearching(StorageSearching<Task> parsed, ArrayList<String> arguments) {
        // assumes all StorageSearching Instructions have the
        // desired search terms as their second argument
        assert (arguments.size() == 2)
                : "executeSearching with more than 2 parameters";
        parsed.searchStore(this.taskStorage, this.ui, arguments.get(0));
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
        parsed.writeToStore(this.taskStorage, this.ui, toWrite);
        this.taskStorage.saveToDisk(this.taskStoreLocation);
        return true;
    }

    private boolean executeAlias(AliasInstruction parsed, ArrayList<String> arguments) {
        // first argument is the original name
        // second argument is the aliased name
        Pair<String, String> toWrite =
                new Pair<String, String>(arguments.get(0), arguments.get(1));
        parsed.writeToStore(this.aliasStorage, this.ui, toWrite);
        this.aliasStorage.saveToDisk(this.aliasStoreLocation);
        return true;
    }
}
