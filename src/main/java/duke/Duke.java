package duke;

import command.Command;
import command.Deadlines;
import command.Events;
import command.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import command.*;
import tool.*;

public class Duke {
    public Duke(){ }

    public void run(){
        TaskList taskList = new TaskList();
        Storage storage = new Storage("src/data/tasks.txt", taskList);
        UI ui = new UI(storage);
        ui.printWelcomeMessage();;

        storage.readFromFile();

        Parser parser = new Parser(ui, taskList);
        ui.setParser(parser);
        ui.getInput();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

