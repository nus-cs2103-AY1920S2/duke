import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private Storage storage;
    private Parser parser = new Parser();
    TaskList tasklist;
    String allInst;

    public Duke(String filePath) {
        try {
            storage = new Storage(filePath); //reads txt file
            tasklist = storage.readFileContents();
            allInst =  tasklist.convertToString();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void run() {
        try {
            parser.scan();
            List<Task> task = tasklist.getDoneTasks();
            storage.setDoneTasks(task);
            storage.load();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}