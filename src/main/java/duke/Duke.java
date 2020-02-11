package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Main.
 */
public class Duke {

    private static Ui ui;
    private static boolean isExit;
    private static Parser parser;
    private static MyList taskList;
    private static Storage storage;

    /**
     * Instantiates a new Duke.
     */
    public Duke() {
        taskList = new MyList();
        storage = new Storage();
        ui = new Ui();
        parser = new Parser(ui);
        isExit = false;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ui.showStartMessage();

        while(!isExit) {

            try{
                String word = sc.nextLine();
                Command command = parser.parseCommand(word);
                command.execute(word, taskList, ui, storage);
                isExit = command.isExit();
            } catch(DukeException | IOException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     *
     * @param input the input
     * @return the response
     */
    String getResponse(String input) {

        String result = "";

        try{
            Command command = parser.parseCommand(input);
            result += command.execute(input, taskList, ui, storage);
        } catch(DukeException | IOException e) {
            result += e.getMessage();
        }

        return result;

    }

}
