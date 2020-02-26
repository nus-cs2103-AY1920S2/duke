package duke;

import java.util.*;
import java.io.IOException;

/**
 * The Duke program implements an application to interact with a user's tasks
 * Functionalities include:
 * 1) Able to store and mark as done for three different types of tasks (to-do, deadline, events)
 * 2) Able to  list down all the tasks in sequence
 * 3) Able to delete the tasks
 * 4) Able to find the task by it's name
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    ArrayList<Task> list;

    public Duke(String filePath){
        ui = new Ui ();
        storage = new Storage(filePath);
        parser = new Parser();
        list = new ArrayList<Task>();
    }

    public void run(){
        storage.load(list);
        tasks = new TaskList();

        Ui.printGreetings();
            try{
                Ui.readInput(list);
            } catch (IOException e) {
                e.printStackTrace();
                }
    }
    public static void main(String[] args) throws IOException {
        new Duke("C:\\Users\\User\\Documents\\CS2103T Projects\\repo\\duke\\src\\main\\java\\taskFile.txt").run();
    }
}