import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {

    public String pathName;
    public ArrayList<Task> taskList;
    public static final String FILE_PATH = "/Users/jadetay/duke/data/tasks.txt";

    public Storage(String pathName) {
        this.pathName = pathName;
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> load() throws LoadException {
        try {
            File file = new File(FILE_PATH);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter inputTimeFormat = DateTimeFormatter.ofPattern("HH:mm");
            String line;

            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" \\| ");
                String task = temp[0];
                Integer isDone = Integer.parseInt(temp[1]);
                String description = temp[2];
                if (task.equals("T")) {
                    Task t = new Todo(description);
                    this.taskList.add(t);
                    if (isDone == 1) {
                        t.markAsDone();
                    }
                } else {
                    LocalDate ld = LocalDate.parse(temp[3].substring(0, 10), inputDateFormat);
                    LocalTime lt = LocalTime.parse(temp[3].substring(12), inputTimeFormat);
                    if (task.equals("D")) {
                        Task d = new Deadline(description, ld, lt);
                        this.taskList.add(d);
                        if (isDone == 1) {
                            d.markAsDone();
                        }
                    } else {
                        Task e = new Event(description, ld, lt);
                        this.taskList.add(e);
                        if (isDone == 1) {
                            e.markAsDone();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new LoadException();
        }
        return this.taskList;
    }

    public static void saveTasks(ArrayList<Task> tasks) throws SaveException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t : tasks) {
                fw.write(formatSavedFile(t) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new SaveException();
        }

    }

    private static String formatSavedFile(Task task) {
        String toAdd = "";
        int isDone = 0;
        if (task.isDone()) {
            isDone = 1;
        }

        if (task instanceof Todo) {
            toAdd += "T | " + isDone + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            toAdd += "D | " + isDone + " | " + d.getDescription() + " | " + d.getDeadline();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            toAdd += "E | " + isDone + " | " + e.getDescription() + " | " + e.getEvent();
        }
        return toAdd;
    }

}
