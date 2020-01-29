import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;
import java.io.IOException;

/** Duke Task Manager */
public class Duke {

    /** Storage object to load and save tasks to text file. */
    private Storage storage;

    /** Task list object to store tasks. */
    private TaskList taskList;

    /**
     * Starts the Duke program.
     * Invokes the run() method to start simulation.
     *
     * @param args Main method arguments for when program runs.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Constructs the Duke program.
     */
    private Duke() {
        try {
            this.storage = new Storage("data/duke.txt");
            this.taskList = this.storage.loadTaskList();
        } catch (DukeException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Starts simulation.
     */
    private void run() {
        Ui.printIntro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(input);
                boolean quit = Parser.parseUserInput(input, this.storage, this.taskList);
                if (quit) break;
            } catch (DukeException e) {
                System.err.println(e.toString());
                continue;
            } catch (ArrayIndexOutOfBoundsException x) {
                Exception e = new DukeException("Please enter a valid instruction!");
                System.err.println(e.toString());
                continue;
            } catch (IOException e) {
                System.err.println(e.toString());
                continue;
            }
        }
    }

}