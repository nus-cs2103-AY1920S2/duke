package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String fp;

    public Storage(String path) {
        fp = path;
    }

    private boolean convertToBoolean(String value) {
        boolean returnValue = false;
        if ("1".equalsIgnoreCase(value) || "yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value)
                || "on".equalsIgnoreCase(value)) {
            returnValue = true;
        }
        return returnValue;
    }

    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> arr = new ArrayList<>();
        File file = new File(this.fp);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] s = task.split(",", 0);
            switch (s[0].trim()) {
                case ("T"):
                    Task todo = new Todo(s[2].trim(), convertToBoolean(s[1].trim()));
                    arr.add(todo);
                    break;
                case ("E"):
                    Task ev = new Event(s[2].trim(), s[3].trim(), convertToBoolean(s[1].trim()));
                    arr.add(ev);
                    break;
                case ("D"):
                    Task dl = new Deadline(s[2].trim(), s[3].trim(), convertToBoolean(s[1].trim()));
                    arr.add(dl);
                    break;
                default:
                    throw new DukeException();
            }
        }
        return arr;
    }

    public void save(ArrayList<Task> data) throws IOException {
        StringBuilder s = new StringBuilder();
        for (Task datum : data) {
            s.append(datum.saveFormat()).append("\n");
        }
        FileWriter fileWriter = new FileWriter(this.fp);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.print(s);
        printWriter.close();
    }
}
