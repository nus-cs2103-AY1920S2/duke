import command.*;
import dukeexception.*;
import parser.*;
import storage.*;
import task.*;
import ui.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    String filePath = "/Users/Simon/Desktop/Y2S2/CS2103T/duke/src/main/java/duke.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException{
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.displayLoadError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } catch (IOException e) {
                ui.displaySaveError();
            } finally {
                ui.showLine();
            }
        }
    }

    static ArrayList<Task> list = new ArrayList<>();


    public static void main(String[] args) throws IOException{
        new Duke("/Users/Simon/Desktop/Y2S2/CS2103T/duke/src/main/java/duke.txt").run();
    }


}



