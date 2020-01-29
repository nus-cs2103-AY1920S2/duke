package duke;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class Storage {

    public static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<Task> list = new ArrayList<>();

        while (s.hasNext()) {
            String[] parts = s.nextLine().split(" | ");
            if (parts[0].equals("T")) {
                list.add(new Todo(parts[2], Integer.parseInt(parts[1])));
            } else if (parts[0].equals("D")) {
                list.add(new Deadline(parts[2], Integer.parseInt(parts[1])));
            } else if (parts[0].equals("E")) {
                list.add(new Event(parts[2], Integer.parseInt(parts[1])));
            }
        }

        return list;
    }

    public void writeToFile(String textToAdd) throws IOException  {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void rewriteFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.size(); i++) {
            String text = list.get(i).toString();
            fw.write(text + "\n");
        }
        fw.close();
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }
}