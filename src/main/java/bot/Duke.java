package bot;

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
import bot.command.Instruction;


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
        //initialise UI
        Ui botUi = new Ui();
        botUi.showVersion();
        botUi.showGreetings();
        botUi.showLoading();

        // initialise CommandParser
        Scanner input = new Scanner(System.in);
        CommandParser parser = new CommandParser();

        // initialise Storage
        String fileDirectory = "../user/data";
        String fileName = "tasks.botstore";
        Storage store = new Storage();

        LoadAndSave<Task> botStore;
        try {
            botStore = new TasksToDisk(fileDirectory, fileName);
        } catch (FileNotFoundException e) {
            botUi.showError(e);
            botStore = new DummyLoader<Task>();
        }

        store.importTasks(botStore.loadFromDisk());

        botUi.showInitial();
        botUi.showAwaiting();

        // main bot system loop
        while(input.hasNext()) {
            String command = input.nextLine();
            // parse the command
            Instruction next;
            try {
                next = parser.parse(command);
            } catch (UnknownInstructionException e) {
                botUi.showError(e);
                continue;
            }

            if (next == Instruction.MARK_DONE) {
                int index;
                try {
                    index = getListIndex(command, Command.DONE);
                } catch (InadequateArgumentsException |
                        NumberFormatException | TooManyArgumentsException e
                ) {
                    botUi.showError(e);
                    continue;
                }
                store.markAsDone(index);
                botUi.showDone();
                System.out.println(store.retrieve(index));

                store.saveToDisk(botStore);
            } else if (next == Instruction.DELETE) {
                int index;
                try {
                    index = getListIndex(command, Command.DELETE);
                } catch (InadequateArgumentsException |
                        NumberFormatException | TooManyArgumentsException e
                ) {
                    botUi.showError(e);
                    continue;
                }
                String toBeDeleted = store.retrieve(index);
                store.delete(index);
                botUi.showDeleted();
                System.out.println(toBeDeleted);

                store.saveToDisk(botStore);
            } else if (next == Instruction.READ_STORAGE) {
                store.printStorage();
            } else if (next == Instruction.SEARCH_STORAGE) {
                String searchTerm;
                try {
                    searchTerm = getSecondTerm(command, Command.SEARCH);
                } catch (InadequateArgumentsException | TooManyArgumentsException e) {
                    botUi.showError(e);
                    continue;
                }
                store.searchStorage(searchTerm)
                        .ifPresentOrElse(
                                taskIds -> {
                                    botUi.showFoundTask();
                                    for (Integer id : taskIds) {
                                        System.out.println(store.retrieve(id));
                                    }
                                },
                                () -> botUi.showFailedToFind()
                        );
            } else if (next == Instruction.STORE_DDL) {
                Deadline ddl;
                try {
                    ddl = new Deadline(command);
                } catch (InadequateArgumentsException e) {
                    botUi.showError(e);
                    continue;
                }
                store.store(ddl);
                botUi.showTaskStoreMessage(store.getNumTasks());

                store.saveToDisk(botStore);
            } else if (next == Instruction.STORE_EVENT) {
                Event evn;
                try {
                    evn = new Event(command);
                } catch (InadequateArgumentsException e) {
                    botUi.showError(e);
                    continue;
                }
                store.store(evn);
                botUi.showTaskStoreMessage(store.getNumTasks());

                store.saveToDisk(botStore);
            } else if (next == Instruction.STORE_TODO) {
                Todo tdo;
                try {
                    tdo = new Todo(command);
                } catch (InadequateArgumentsException e) {
                    botUi.showError(e);
                    continue;
                }
                store.store(tdo);
                botUi.showTaskStoreMessage(store.getNumTasks());

                store.saveToDisk(botStore);
            } else if (next == Instruction.FIND_KEYWORD) {
                String searchTerm;
                try {
                    searchTerm = getSecondTerm(command, Command.FIND);
                } catch (InadequateArgumentsException | TooManyArgumentsException e) {
                    botUi.showError(e);
                    continue;
                }
                store.findInDesc(searchTerm)
                        .ifPresentOrElse(
                                taskIds -> {
                                    botUi.showFoundTask();
                                    for (Integer id : taskIds) {
                                        System.out.println(store.retrieve(id));
                                    }
                                },
                                () -> botUi.showFailedToFind()
                        );
            } else if (next == Instruction.HELP) {
                botUi.showHelpMessage();
            } else if (next == Instruction.TERMINATE) {
                // terminate the bot program
                break;
            } else {
                // next == Instruction.AWAIT
                parser.echo(command);
            }
            botUi.showAwaiting();
        }
        input.close();
        botUi.showGoodbye();
    }

    /**
     * Gets the index of a Storage modifying command, given the
     * raw String input of the command (e.g. "done" and "delete").
     * Such a command is composed of one word and one number,
     * separated by a space
     *
     * @param rawCommand The original command input
     * @param com The Command action to be executed
     *
     * @return An integer representing the index in the
     *         list to set as "done"
     * @throws InadequateArgumentsException
     *         When no numbers found after the command word
     */
    private static int getListIndex(String rawCommand, Command com)
        throws InadequateArgumentsException, NumberFormatException,
            TooManyArgumentsException
    {
        return Integer.parseInt(getSecondTerm(rawCommand, com));
    }

    /**
     * Given a command of two terms, returns
     * only the second term used
     *
     * @param rawCommand The original command input
     * @param com The Command action to be executed
     *
     * @return String representing the second term
     */
    private static String getSecondTerm(String rawCommand, Command com)
            throws InadequateArgumentsException, TooManyArgumentsException
    {
        String[] words = rawCommand.split("\\s+");
        if (words.length == 2) {
            return words[1];
        } else if (words.length < 2) {
            throw new InadequateArgumentsException(com.word);
        } else {
            throw new TooManyArgumentsException(com.word);
        }
    }
}
