package bot;

import bot.command.instruction.ParsedInstruction;

import bot.loadsave.DummyLoader;
import bot.loadsave.LoadAndSave;
import bot.loadsave.TasksToDisk;

import bot.task.Deadline;
import bot.task.Event;
import bot.task.Task;
import bot.task.Todo;

import bot.command.Command;
import bot.command.CommandParser;
import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;
import bot.command.instruction.Instruction;


import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Main driver class for 4LC3N-BOT, containing the
 * primary loop that awaits input from the user
 */
public class Duke {
    /**
     * Main program of 4LC3N-BOT
     *
     * @param args Command line arguments, can be
     *             safely ignored
     */
    public static void main(String[] args) {
        // initialise UI
        Ui botUi = new Ui();
        botUi.showVersion();
        botUi.showGreetings();
        botUi.showLoading();

        // initialise CommandParser
        CommandParser parser = new CommandParser();

        // initialise TaskStorage
        String fileDirectory = "../user/data";
        String fileName = "tasks.botstore";
        TaskStorage store = new TaskStorage();

        LoadAndSave<Task> botStore;
        try {
            botStore = new TasksToDisk(fileDirectory, fileName);
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
                break;
            }
            botUi.showAwaiting();
        }
        input.close();
    }
}
