package bot;

import bot.command.instruction.ParsedInstruction;

import bot.command.instruction.concrete.TerminateInstruction;
import bot.gui.Launcher;

import bot.loadsave.DummyLoader;
import bot.loadsave.LoadAndSave;
import bot.loadsave.TasksToDisk;

import bot.store.TaskStorage;
import bot.task.Task;

import bot.command.CommandParser;
import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;

import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Main driver class for 4LC3N-BOT
 */
public class Duke {
    public static final String FILE_DIRECTORY = "../user/data";
    public static final String FILE_NAME = "tasks.botstore";

    /**
     * Main program of 4LC3N-BOT, if started with the
     * command line argument "cli", the command line
     * interface will be used.
     *
     * <p>Example: "java bot/Duke cli"
     *
     * <p>If not, by default, the JavaFX graphical
     * user interface will be started in a
     * pop-up window
     *
     * @param args Command line arguments, if only "cli"
     *             is given as the argument, then the
     *             command line interface is started
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("cli")) {
            startCommandLineBot();
        } else {
            startGraphicalBot(args);
        }
    }

    /**
     * Starts 4LC3N-BOT in the command line interface,
     * also containing the primary loop that awaits
     * input from the command line input
     */
    public static void startCommandLineBot() {
        // initialise UI
        Ui botUi = new Ui();
        botUi.showVersion();
        botUi.showGreetings();
        botUi.showLoading();

        // initialise CommandParser
        CommandParser parser = new CommandParser();

        // initialise TaskStorage
        TaskStorage store = new TaskStorage();

        LoadAndSave<Task> botStore;
        try {
            botStore = new TasksToDisk(Duke.FILE_DIRECTORY, Duke.FILE_NAME);
        } catch (FileNotFoundException e) {
            botUi.showError(e);
            botStore = new DummyLoader<Task>();
        }

        store.importTasks(botStore.loadFromDisk());

        // initialise instruction Executor
        Executor executor = new Executor(store, botUi, botStore);
        Scanner input = new Scanner(System.in);

        botUi.showInitial();
        botUi.showAwaiting();

        // main bot system loop
        while (input.hasNext()) {
            String command = input.nextLine();
            // parse the command
            ParsedInstruction next;
            try {
                next = parser.parse(command);
            } catch (InadequateArgumentsException | TooManyArgumentsException
                    | UnknownInstructionException e
            ) {
                botUi.showError(e);
                continue;
            }

            if (!executor.execute(next)) {
                // should be exit instruction
                assert (next.getInstruction() instanceof TerminateInstruction)
                        : "unknown instruction in Executor.execute";
                break;
            }
            botUi.showAwaiting();
        }
        input.close();
    }

    /**
     * Starts 4LC3N-BOT in the JavaFX graphical
     * user interface (in a new window)
     *
     * @param args Command line arguments
     */
    public static void startGraphicalBot(String[] args) {
        Launcher.start(args);
    }
}
