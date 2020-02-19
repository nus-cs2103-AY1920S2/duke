package e0148811.duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String path;
    private Ui ui;

    public Storage(String filePath, Ui ui) {
        path = filePath;
        this.ui = ui;
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                addEventToList(list, line);
            }
            return list;
        } catch (IOException e) {
            throw new DukeException("Cannot find or read the file properly.");
        }
    }

    private static void addEventToList(List<Task> list, String line) {
        String[] lineByWord = line.split("//");
        boolean isDone;
        isDone = lineByWord[1].equals("T");
        PriorityLevel level;
        switch (lineByWord[2]) {
        case "t":
            level = PriorityLevel.TOP;
            break;
        case "h":
            level = PriorityLevel.HIGH;
            break;
        case "l":
            level = PriorityLevel.LOW;
            break;
        default:
            level = PriorityLevel.NORMAL;
        }
        switch (lineByWord[0]) {
        case "T":
            assert lineByWord.length == 4: "A correct line description should contain 4 parts separated by \"//\"";
            list.add(new Todo(isDone, lineByWord[3], level));
            break;
        case "D":
            assert lineByWord.length == 5: "A correct line description should contain 5 parts separated by \"//\"";
            list.add(new Deadline(isDone, lineByWord[3], LocalDate.parse(lineByWord[4]), level));
            break;
        case "E":
            assert lineByWord.length == 5: "A correct line description should contain 5 parts separated by \"//\"";
            list.add(new Event(isDone, lineByWord[3], LocalDate.parse(lineByWord[4]), level));
            break;
        }
    }

    public void writeToHardDisk(List<Task> list) throws DukeException {
        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            for (Task t : list) {
                writer.write(t.toSimplerString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.throwIOException();
        }

    }
}
