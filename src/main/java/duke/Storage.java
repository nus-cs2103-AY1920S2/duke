package duke;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;
    File file;
    Scanner fileScanner;

    Storage(String filePath) throws DukeException {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            this.fileScanner = new Scanner(file);
        } catch (FileNotFoundException f) {
            throw new DukeException("file", "");
        }
    }

    public void saveData(TaskList tasks) throws DukeException {
        try {
           PrintWriter writer = new PrintWriter(filePath);
            for (int i = 0; i < tasks.getLength(); i++) {
                Task t = tasks.get(i);
                writer.println(t.getFormatForSave());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("file", "");
        }
    }

    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> loadedList = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String inputLine = fileScanner.nextLine();
            loadedList.add(interpretLine(inputLine));
        }
        if (loadedList.size() < 1) {
            throw new DukeException("emptyLoad", "");
        }
        return loadedList;
    }

    public static Task interpretLine(String inputLine) throws DukeException{
        String[] strArr = inputLine.split(" ");
        String doneStatus = strArr[0];
        String type = strArr[1];
        String input = inputLine.substring(2);
        switch (type) {
            case "deadline":
                Deadline d = new Deadline(input);
                if (doneStatus.equals("1")) {
                    d.setDone();
                }
                return d;
            case "event":
                Event e = new Event(input);
                if (doneStatus.equals("1")) {
                    e.setDone();
                }
                return e;
            case "todo":
                Todo td = new Todo(input);
                if (doneStatus.equals("1")) {
                    td.setDone();
                }
                return td;
            default:
                throw new DukeException("read", "");
        }
    }
}
