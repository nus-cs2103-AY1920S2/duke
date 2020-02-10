import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;


    /**
     * Constructor for Duke to start the initialise the necessary variables
     */
    public Duke() {
        storage = new Storage();


        try {
            tasks = storage.load();
            ui = new Ui(tasks);

        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui = new Ui(tasks);
        }

    }


    /**
     * Starts the whole program and get user input
     * @throws IOException if I/O error happens
     */
    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Parser parser = new Parser();
            String output = parser.parse(input, ui, tasks);
            storage.updateData(tasks);

            input = sc.nextLine();
        }

        ui.printGoodbye();

    }

    public String getResponse(String input) throws IOException {

        Parser parser = new Parser();
        String output = parser.parse(input, ui, tasks);
        storage.updateData(tasks);

        // this is like the system.in shit
        return output;
    }



    /**
     * Main method for the class
     * @param args no commmand line arguments are used explicitly
     * @throws IOException is thrown when there is I/O error
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

}




