package duke;

import commands.CommandHub;
import commands.CommandInvoker;
import commands.UserCommand;
import exceptions.DukeException;

import java.util.Scanner;

/**
 * The type Duke.
 */
public class Duke {
    private CommandInvoker invoker  = new CommandInvoker();

    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        DukeInit.initBot();
    }

    /**
     * The entry point of application for cli
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Run.
     */
    public void run() {

        while (!CommandInvoker.isExit()) {
            String userInput = getUserInput();
            try {
                UserCommand c = CommandHub.fetchCommand(userInput);
                invoker.invoke(c);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }
    }


    private static String getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print(">");
        String input = sc.nextLine();

        return input;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input the input
     * @return the response
     */
    public String getResponse(String input) {
        String response = "";
        try {
            UserCommand c = CommandHub.fetchCommand(input);
            response = invoker.invoke(c);
            if (CommandInvoker.isExit()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            response = e.getMessage();
        }


        return response;
    }
}


