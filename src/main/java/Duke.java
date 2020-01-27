import java.util.*;
import java.io.*;

public class Duke {
    private ArrayList<Task> list;
    private int latest_index;
    private Ui ui;
    private Storage storage;

    public void run() throws Exception {

        this.storage = new Storage();
        this.ui = new Ui();

        System.out.println(ui.welcomeMessage());

        this.list = storage.readFile();
        this.latest_index = storage.returnInitialIndex();

        TaskList tasklist = new TaskList(list,latest_index,storage);
        Parser parser = new Parser(tasklist);
        parser.parse();
    }
}