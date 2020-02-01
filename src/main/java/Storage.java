import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    private static Task stringToTask(String string) {
        char type = string.charAt(1);
        boolean isDone = string.charAt(4) == '\u2713' ? true: false;
        Task task;
        String desc;
        String date;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (type == 'T') {
            desc = string.substring(7);

            task = new Todo(desc);
        } else if(type == 'D') {
            desc = string.substring(7);
            date = desc.split("\\(by: ")[1];
            date = date.substring(0, date.length() - 1); // remove ending bracket
            desc = desc.split("\\(by: ")[0];

            task = new Deadline(desc, LocalDate.parse(date, format));
        } else {
            desc = string.substring(7);
            date = desc.split("\\(date: ")[1];
            date = date.substring(0, date.length() - 1); // remove ending bracket
            desc = desc.split("\\(date: ")[0];

            task = new Event(desc, LocalDate.parse(date, format));
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    public List<Task> load() throws DukeException{
        List<Task> taskList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line = reader.readLine();
            while(line != null) {
                Task task = stringToTask(line);
                taskList.add(task);

                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Something's wrong with loading the save file...");
        }

        return taskList;

    }

    public void save(TaskList tasks) throws DukeException{
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(filepath));
            for (int i = 0; i < tasks.size(); i++) {
                pw.println(tasks.get(i));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Something's wrong with saving the save file...");
        }
    }
}
