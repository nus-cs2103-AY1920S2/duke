import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File f;
    private FileWriter fw;

    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
    }

    public List<Task> getList() throws FileNotFoundException {
        List<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] elements = str.split(" \\| ");
            Task t = new Task();
            switch (elements[0]) {
                case "T":
                    t = new Todo(elements[2]);
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

    public void writeTaskList(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list)
            fw.write(taskToString(t));
        fw.close();
    }

    private static String taskToString(Task t) {
        String str = "";
        int done = t.getIsDone() ? 1 : 0;
        if (t instanceof Todo)
            str = "T | " + done + " | " + t.getDescription();
        if (t instanceof Event)
            str = "E | " + done + " | " + t.getDescription() + " | " + ((Event) t).getDayTime();
        if (t instanceof Deadline)
            str = "D | " + done + " | " + t.getDescription() + " | " + ((Deadline) t).getDayTime();
        return str + "\n";
    }
}
