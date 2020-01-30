package ip;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static class DukeException extends Exception {
        public DukeException(String msg){
            super(msg);
        }
    }

    public static void main(String[] args) {
        new Duke().start();
    }

    private void start(){
        Storage storage = new Storage();
        TaskList tasks = storage.readFromFile();
        Parser parser = new Parser();
        Ui ui = new Ui();
        ui.initialGreeting();

        String input;
        while (true){
            input = ui.getInput();
            Command c = parser.parse(input);
            c.execute(tasks, ui);
            if (c.isExit()) { break; }
        }
        storage.writeToFile(tasks);
    }
}
