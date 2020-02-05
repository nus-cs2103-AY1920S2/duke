package e0148811.duke;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String path;

    public Storage(String filePath) {
        path = filePath;
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            boolean isEmptyFile = true;
            String line;
            while ((line = br.readLine()) != null) {
                isEmptyFile = false;
                addEventToList(list, line);
            }
            if (isEmptyFile) {
                System.out.println("File found but empty. Start with an empty task list.");
            } else {
                System.out.println("File found. Load saved task list.");
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
        switch (lineByWord[0]) {
        case "T":
            list.add(new Todo(isDone, lineByWord[2]));
            break;
        case "D":
            list.add(new Deadline(isDone, lineByWord[2], LocalDate.parse(lineByWord[3])));
            break;
        case "E":
            list.add(new Event(isDone, lineByWord[2], LocalDate.parse(lineByWord[3])));
            break;
        }
    }

    public void writeToHardDisk(List<Task> list) throws IOException {
        FileWriter writer = new FileWriter("data/duke.txt");
        for (Task t : list) {
            writer.write(t.toSimplerString() + "\n");
        }
        writer.close();
    }
}
