package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Duke.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList = new TaskList();

    /**
     * The constant break_checker.
     */
    public static boolean break_checker = false;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DukeException the duke exception
     * @throws IOException   the io exception
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("/Users/joshua/Desktop/Schoolwork/Year 2 Sem 2/CS2103T/Individual_Project_Duke/data/duke.txt").run();
    }

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        //parser = new duke.Parser(storage);
    }

    private void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        // Welcome message for the user
        ui.showWelcome();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String fullCommand = ui.readCommand(input);
            Command c = Parser.parse(fullCommand,input);
            c.execute(storage, ui,taskList);
            //parser.understand_user_input(input);
            if(break_checker) {
                break;
            }
        }
    }



}