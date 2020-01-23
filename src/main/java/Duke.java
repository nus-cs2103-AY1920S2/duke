import java.text.NumberFormat;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String version = "4LC3N v0.0.1";
        System.out.println(version);

        String load = "=======================================================\n" +
                "                  LOADING ... ... ...\n" +
                "=======================================================";
        String greetings = "      _.-'''''-._\n" +
                "    /=_.-~-~-~-._=\\      .-.  _\n" +
                "   :    _     _    :     | | / )\n" +
                "  /    (o)   (o)    \\    | |/ /\n" +
                "  |  _ _ _ _ _ _ _  |   _|__ /_\n" +
                "  |  \\           /  |  / __)-' )\n" +
                "   \\  '.       .'  /   \\  `(.-')\n" +
                "    '.  `'---'`  .'     > ._>-'\n" +
                "      '-._____.-'      / \\/";
        String initialMessage = "4LC3N-BOT initialised.\nGreetings, humans!";
        String awaitingMessage = "\n> ENTER your input:";
        String doneMessage = "You have completed: ";
        String errorMessage = "      _.-'''''-._  \n" +
                "    /=_.-~-~-~-._=\\\n" +
                "   :               :\n" +
                "  /    (X)   (X)    \\\n" +
                "  |                 |\n" +
                "  |     .-----.     |\n" +
                "   \\    '_ _ _'    /\n" +
                "    '.           .'\n" +
                "      '-._____.-'";

        System.out.println(greetings);
        System.out.println(load);

        Scanner input = new Scanner(System.in);
        CommandParser parser = new CommandParser();
        Storage store = new Storage();

        System.out.println(initialMessage);
        System.out.println(awaitingMessage);

        // main bot system loop
        while(input.hasNext()) {
            String command = input.nextLine();
            // parse the command
            Instruction next;
            try {
                next = parser.parse(command);
            } catch (UnknownInstructionException e) {
                System.err.println(e.getMessage());
                System.err.println(errorMessage);
                continue;
            }

            if (next == Instruction.MARK_DONE) {
                int index;
                try {
                    index = getDoneIndex(command);
                } catch (InadequateArgumentsException |
                    NumberFormatException | TooManyArgumentsException e
                ) {
                    System.err.println(e.getMessage());
                    System.err.println(errorMessage);
                    continue;
                }
                store.markAsDone(index);
                System.out.println(doneMessage);
                System.out.println(store.retrieve(index));
            } else if (next == Instruction.READ_STORAGE) {
                store.readStorage();
            } else if (next == Instruction.STORE_DDL) {
                Deadline ddl;
                try {
                    ddl = new Deadline(command);
                } catch (InadequateArgumentsException e) {
                    System.err.println(e.getMessage());
                    System.err.println(errorMessage);
                    continue;
                }
                store.store(ddl);
                Duke.printTaskStoreMessage(store.getNumTasks());
            } else if (next == Instruction.STORE_EVENT) {
                Event evn;
                try {
                    evn = new Event(command);
                } catch (InadequateArgumentsException e) {
                    System.err.println(e.getMessage());
                    System.err.println(errorMessage);
                    continue;
                }
                store.store(evn);
                Duke.printTaskStoreMessage(store.getNumTasks());
            } else if (next == Instruction.STORE_TODO) {
                Todo tdo;
                try {
                    tdo = new Todo(command);
                } catch (InadequateArgumentsException e) {
                    System.err.println(e.getMessage());
                    System.err.println(errorMessage);
                    continue;
                }
                store.store(tdo);
                Duke.printTaskStoreMessage(store.getNumTasks());
            } else if (next == Instruction.TERMINATE) {
                // terminate the bot program
                break;
            } else {
                // next == Instruction.AWAIT
                parser.echo(command);
            }
            System.out.println(awaitingMessage);
        }
        input.close();
        String goodbye = "\nGoodbye! You will be missed" +
                "\n      _.-'''''-._  \n" +
                "    /=_.-~-~-~-._=\\\n" +
                "   :    _     _    :\n" +
                "  /    (o)   (o)    \\\n" +
                "  |           `     |\n" +
                "  |     .-----.     |\n" +
                "   \\   :       :   /\n" +
                "    '.           .'\n" +
                "      '-._____.-'";
        System.out.println(goodbye);

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
     * Gets the index of a "done" command, given the
     * raw String input of the command
     *
     * @param doneCommand The original command input
     * @return An integer representing the index in the
     *         list to set as "done"
     * @throws InadequateArgumentsException
     *         When no numbers found after "done"
     */
    private static int getDoneIndex(String doneCommand)
        throws InadequateArgumentsException, NumberFormatException,
            TooManyArgumentsException
    {
        String[] words = doneCommand.split("\\s+");
        if (words.length == 2) {
            return Integer.parseInt(words[1]);
        } else if (words.length < 2) {
            throw new InadequateArgumentsException(Command.DONE.word);
        } else {
            throw new TooManyArgumentsException(Command.DONE.word);
        }
    }
}
