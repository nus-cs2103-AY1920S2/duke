import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a collection of methods to read and write files in the data folder.
 * A <code>Storage</code> object corresponds to a file path represented as a String
 * and a <code>File</code> object that accesses the file at the path.
 */
public class Storage {
    private String filePath;
    private File f;

    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
    }

    /**
     * Retrieves and returns list of tasks from data folder.
     *
     * @return List of tasks.
     * @throws FileNotFoundException If storage file cannot be found in data folder.
     */
    public List<Task> getList() throws FileNotFoundException {
        List<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] elements = str.split(" \\| ");
            Task t = new Task();
            switch (elements[0]) {
            case "T":
                if (elements.length > 3) {
                    t = new TodoWithinPeriod(elements[2], elements[3], elements[4]);
                } else {
                    t = new Todo(elements[2]);
                }
                break;
            case "E":
                t = new Event(elements[2], elements[3]);
                break;
            case "D":
                t = new Deadline(elements[2], elements[3]);
            }
            if (Integer.parseInt(elements[1]) == 1)
                t.markAsDone();
            list.add(t);
        }
        return list;
    }

    /**
     * Updates storage file in storage folder with list of tasks.
     *
     * @param list List of tasks.
     * @throws IOException If there is an error with writing to storage file.
     */
    public void writeTaskList(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list)
            fw.write(taskToString(t));
        fw.close();
    }

    private static String taskToString(Task t) {
        String str = "";
        int done = t.getIsDone() ? 1 : 0;
        if (t instanceof TodoWithinPeriod)
            str = "T | " + done + " | " + t.getDescription() + " | " + ((TodoWithinPeriod) t).getBetweenDate()
                    + " | " + ((TodoWithinPeriod) t).getToDate();
        if (t instanceof Todo)
            str = "T | " + done + " | " + t.getDescription();
        if (t instanceof Event)
            str = "E | " + done + " | " + t.getDescription() + " | " + ((Event) t).getDayTime();
        if (t instanceof Deadline)
            str = "D | " + done + " | " + t.getDescription() + " | " + ((Deadline) t).getDayTime();
        assert ! str.equals("") : "String has information";
        return str + "\n";
    }
}
