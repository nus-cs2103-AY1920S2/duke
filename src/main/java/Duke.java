import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        TaskList manager = new TaskList();
        Storage storage = new Storage("../../DataFile.txt");

        manager.addStorage(storage);
        storage.addManager(manager);
        storage.loadFile();
        Ui ui = new Ui(manager);
        ui.frontDesk();


    }

}
