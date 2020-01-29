package bot;

import bot.loadsave.DummyLoader;
import bot.loadsave.LoadAndSave;
import bot.loadsave.TasksToDisk;
import bot.task.Task;
import bot.task.Deadline;
import bot.task.Event;
import bot.task.Todo;
import bot.command.Command;
import bot.command.CommandParser;
import bot.command.Instruction;
import bot.command.exception.InadequateArgumentsException;
import bot.command.exception.TooManyArgumentsException;
import bot.command.exception.UnknownInstructionException;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Main driver class for 4LC3N-BOT, containing the
 * primary loop that awaits input from the user
 */
public class Duke {
    public static void main(String[] args) {
        //initialise UI
        Ui botUi = new Ui();
        botUi.version();
        botUi.greetings();
        botUi.load();

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
            botUi.error(e);
            botStore = new DummyLoader<Task>();
        }

        store.importTasks(botStore.loadStored());

        botUi.initial();
        botUi.awaiting();

        // main bot system loop
        while(input.hasNext()) {
            String command = input.nextLine();
            // parse the command
            Instruction next;
            try {
                next = parser.parse(command);
            } catch (UnknownInstructionException e) {
                botUi.error(e);
                continue;
            }

            if (next == Instruction.MARK_DONE) {
                int index;
                try {
                    index = getListIndex(command, Command.DONE);
                } catch (InadequateArgumentsException |
                        NumberFormatException | TooManyArgumentsException e
                ) {
                    botUi.error(e);
                    continue;
                }
                store.markAsDone(index);
                botUi.done();
                System.out.println(store.retrieve(index));

                store.saveToDisk(botStore);
            } else if (next == Instruction.DELETE) {
                int index;
                try {
                    index = getListIndex(command, Command.DELETE);
                } catch (InadequateArgumentsException |
                        NumberFormatException | TooManyArgumentsException e
                ) {
                    botUi.error(e);
                    continue;
                }
                String toBeDeleted = store.retrieve(index);
                store.delete(index);
                botUi.deleted();
                System.out.println(toBeDeleted);

                store.saveToDisk(botStore);
            } else if (next == Instruction.READ_STORAGE) {
                store.printStorage();
            } else if (next == Instruction.SEARCH_STORAGE) {
                String searchTerm;
                try {
                    searchTerm = getSecondTerm(command, Command.SEARCH);
                } catch (InadequateArgumentsException | TooManyArgumentsException e) {
                    botUi.error(e);
                    continue;
                }
                store.searchStorage(searchTerm)
                        .ifPresentOrElse(
                                taskIds -> {
                                    botUi.foundTask();
                                    for (Integer id : taskIds) {
                                        System.out.println(store.retrieve(id));
                                    }
                                },
                                () -> botUi.failedToFind()
                        );
            } else if (next == Instruction.STORE_DDL) {
                Deadline ddl;
                try {
                    ddl = new Deadline(command);
                } catch (InadequateArgumentsException e) {
                    botUi.error(e);
                    continue;
                }
                store.store(ddl);
                Duke.printTaskStoreMessage(store.getNumTasks());

                store.saveToDisk(botStore);
            } else if (next == Instruction.STORE_EVENT) {
                Event evn;
                try {
                    evn = new Event(command);
                } catch (InadequateArgumentsException e) {
                    botUi.error(e);
                    continue;
                }
                store.store(evn);
                Duke.printTaskStoreMessage(store.getNumTasks());

                store.saveToDisk(botStore);
            } else if (next == Instruction.STORE_TODO) {
                Todo tdo;
                try {
                    tdo = new Todo(command);
                } catch (InadequateArgumentsException e) {
                    botUi.error(e);
                    continue;
                }
                store.store(tdo);
                Duke.printTaskStoreMessage(store.getNumTasks());

                store.saveToDisk(botStore);
            } else if (next == Instruction.TERMINATE) {
                // terminate the bot program
                break;
            } else {
                // next == Instruction.AWAIT
                parser.echo(command);
            }
            botUi.awaiting();
        }
        input.close();
        botUi.goodbye();
    }

    /**
     * Prints a default message for storing a Task
     *
     * @param storeSize Number of Tasks already in
     *                  the Storage
     */
    private static void printTaskStoreMessage(int storeSize) {
        String storeMessage = "I have stored this task in my memory. Use" +
                " \"list\" to retrieve it!\nTotal of ";
        String tasks = " tasks stored";
        System.out.println(storeMessage + storeSize + tasks);
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
