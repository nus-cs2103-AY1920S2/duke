package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Duke {
    public static ArrayList<Task> commandList = new ArrayList<>();

    public static void main(String[] args) {
        Storage storage = new Storage();
        storage.startReading();
        Ui ui = new Ui();
        ui.opening();
        Parser parser = new Parser();
        parser.parse();
        ui.ending();

    }

}
