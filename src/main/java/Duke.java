import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run(){
        /**
         * Run Ui intro, then inputLoop()
         */

    }
    public static void main(String[] args) {
        //TaskList taskList = new TaskList();
        //taskList.resumeSave("data/duke.txt");
        //taskList.inputLoop();
    }

}

