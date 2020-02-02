import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    Ui ui;
    Storage storage;
    TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList();
            storage.readFile(tasks.taskList);

        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        ui.readCommands();

    }



    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }


}
