import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Duke class.
 */
public class Duke {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> list = new ArrayList<>();
    static File file;
    static BufferedReader br;
    static FileWriter fw;

    private UI ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser = new Parser();

    /**
     * Constructor.
     * Initialises the UI, Storage and TaskList classes.
     * @param filePath Path of the file to be read / written to.
     */
    public Duke(String filePath) {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            taskList = storage.getTaskList();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Method runs the scan method of the parser, which reads input from user.
     * Subsequently writes output into text file when command "bye" is entered.
     */
    public void run() {
        UI.printIntro();
        try {
            parser.scan();
            //Write the list into output
            storage.writeList();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
