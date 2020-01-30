package duke.main;
import duke.exception.InvalidCommandException;
import duke.exception.OutOfBoundMarkingRequestException;
import duke.exception.TaskErrorException;

import java.util.Scanner;

public class Main {
    static final Scanner scanner = new Scanner(System.in);
    static final Duke bot = new Duke();

    /**
     * The main driver for the program
     * @param args
     * @throws OutOfBoundMarkingRequestException
     * @throws InvalidCommandException
     * @throws TaskErrorException
     */
    public static void main(String[] args) throws OutOfBoundMarkingRequestException, InvalidCommandException, TaskErrorException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        bot.greet();

        String userInput = "";

        while (!userInput.equals(Duke.byeCommand)) {
            userInput = scanner.nextLine();

            if (userInput.equals(Duke.byeCommand))
                break;
            if (userInput.equals(Duke.listCommand)) {
                bot.listStoredItems();
                continue;
            }

            bot.processUserInput(userInput);
        }

        bot.byeBye();
        scanner.close();
    }
}
