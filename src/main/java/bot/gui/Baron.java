package bot.gui;

import bot.Executor;
import bot.TaskStorage;

import bot.command.CommandParser;

import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;
import bot.command.instruction.ParsedInstruction;
import bot.command.instruction.concrete.TerminateInstruction;
import bot.loadsave.LoadAndSave;

import bot.task.Task;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Baron is an intermediary between the Parser,
 * Executor and the GUI, similar to how bot.Duke is
 * the intermediary between the Parser, Executor
 * and Ui (for the command line application)
 */
public class Baron {
    private final CommandParser parser;
    private final TaskStorage botStore;
    private final LoadAndSave<Task> diskFile;
    private final Executor executor;
    private final GraphicalUi graphUi;

    /**
     * Constructor for a new Baron.
     *
     * @param comParse The CommandParser to parse
     *                 text entered by the user
     * @param store The Storage to save Tasks to
     * @param disk The LoadAndSave representing location
     *             of the file on disk to save data to
     * @param gui The GUI of the Bot, to display messages
     */
    public Baron(
            CommandParser comParse,
            TaskStorage store,
            LoadAndSave<Task> disk,
            GraphicalUi gui
    ) {
        this.parser = comParse;
        this.botStore = store;
        this.diskFile = disk;
        this.graphUi = gui;
        this.executor = new Executor(this.botStore, this.graphUi, this.diskFile);
        // show welcome message
        this.graphUi.showInitial();
        // load stored items
        this.botStore.importTasks(disk.loadFromDisk());
    }

    /**
     * Receives the input from the application,
     * then carries out the necessary actions
     * in response
     *
     * @param input String containing raw user
     *              input
     */
    public void receiveInput(String input) {
        this.graphUi.showUserChat(input);

        ParsedInstruction instr;
        try {
            // parse the instruction
            instr = parser.parse(input);
        } catch (InadequateArgumentsException | TooManyArgumentsException
                | UnknownInstructionException e
        ) {
            this.graphUi.showError(e);
            return;
        }

        // execute the instruction
        if (!executor.execute(instr)) {
            // should be exit instruction
            assert (instr.getInstruction() instanceof TerminateInstruction)
                    : "execution failure: unknown instruction";

            // exit after 0.5 seconds
            Executors.newScheduledThreadPool(1)
                    .schedule(() -> System.exit(0),750, TimeUnit.MILLISECONDS);
        }
    }
}
