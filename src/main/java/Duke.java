import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    public Duke(String filePath) {
        try {
            ui = new UI();
            storage = new Storage(filePath);
            taskList = storage.getTaskList();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

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
