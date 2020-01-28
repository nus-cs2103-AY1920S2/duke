// packages import
import tasks.*;
import ui.Ui;

// java imports
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;


public class Storage {
    private final String SAVE_FILE = "save_file.txt";

    public Storage() {
    }

    public void save(TaskList list) throws IOException {
        FileWriter file = new FileWriter(SAVE_FILE, false);
        BufferedWriter writer = new BufferedWriter(file);

        String text = list.toSaveFormat();
        writer.write(text);
        writer.close();
    }

    public void readSaveFile(TaskList list) throws FileNotFoundException {
        FileReader file = new FileReader(SAVE_FILE);
        BufferedReader reader = new BufferedReader(file);
        Ui ui = new Ui();

        try {
            String text = reader.readLine();

            while (text != null) {
                String[] fields = text.split(" \\| ");
                Task newTask;

                // Create corresponding specific task
                if (fields[0].equals("T")) {
                    newTask = new Todo(fields[2]);
                } else if (fields[0].equals("E")) {
                    newTask = new Event(fields[2], fields[3]);
                } else {
                    newTask = new Deadline(fields[2], fields[3]);
                }

                // Set isDone status
                if (newTask != null && Integer.parseInt(fields[1]) == 1) {
                    newTask.markAsDone();
                }

                list.save(newTask);
                text = reader.readLine();
            }
            reader.close();
        } catch (IOException ex) {
            ui.printFormattedOutput("Corrupted Task");
        }
    }
}
