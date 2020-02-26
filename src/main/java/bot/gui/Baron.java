package bot.gui;

import bot.Executor;

import bot.store.AliasStorage;
import bot.store.TaskStorage;

import bot.command.CommandParser;

import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;
import bot.command.instruction.ParsedInstruction;
import bot.command.instruction.concrete.TerminateInstruction;

import bot.loadsave.LoadAndSave;

import bot.task.Task;

import bot.util.Pair;

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
    private final Executor executor;
    private final GraphicalUi graphUi;

    /**
     * Constructor for a new Baron.
     *  @param comParse The CommandParser to parse
     *                 text entered by the user
     * @param gui The GUI of the Bot, to display messages
     * @param taskStore The TaskStorage to save Tasks to
     * @param tasksDiskLoc The LoadAndSave representing location
*                          of the file on disk to
     *                     save Tasks data to
     * @param aliasStore The AliasStorage to save aliases to
     * @param aliasDiskLoc The LoadAndSave representing location
     *                     of the file on disk to save
     *                     alias data to
     */
    public Baron(
            CommandParser comParse,
            GraphicalUi gui, TaskStorage taskStore,
            LoadAndSave<Task> tasksDiskLoc,
            AliasStorage aliasStore,
            LoadAndSave<Pair<String, String>> aliasDiskLoc) {

        this.parser = comParse;
        this.graphUi = gui;
        this.executor =
                new Executor(this.graphUi, taskStore, tasksDiskLoc, aliasStore, aliasDiskLoc);
        // show welcome message
        this.graphUi.showInitial();
        // load stored items
        taskStore.importFromCollection(tasksDiskLoc.loadFromDisk());
        aliasStore.importFromCollection(aliasDiskLoc.loadFromDisk());
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
