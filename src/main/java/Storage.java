import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task getNextTask(String[] taskElements) {
        Task t = null;
        switch (taskElements[0]) {
        case "T":
            t = new ToDo(taskElements[2]);
            if (taskElements[1].equals("1")) {
                t.setDone();
            }
            break;
        case "D":
            t = new Deadline(taskElements[2],
                    LocalDate.parse(taskElements[3], DateTimeFormatter.ofPattern("MMM d yyyy")));
            if (taskElements[1].equals("1")) {
                t.setDone();
            }
            break;
        case "E":
            t = new Event(taskElements[2],
                    LocalDate.parse(taskElements[3], DateTimeFormatter.ofPattern("MMM d yyyy")));
            if (taskElements[1].equals("1")) {
                t.setDone();
            }
            break;
        }
        return t;
    }

    public ArrayList<Task> load() throws LoadingException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new LoadingException();
        }
        while (s.hasNextLine()) {
            String currLine = s.nextLine();
            String[] taskElements = currLine.split(" \\| ");
            tasks.add(this.getNextTask(taskElements));
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws SavingException {
        try {
            this.writeToFile(this.filePath, this.parseTasks(tasks));
        } catch (IOException e) {
            throw new SavingException();
        }
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private String parseTask(Task t) {
        String taskString = t.getType() + " | ";
        if (t.isDone()) {
            taskString += "1 | ";
        } else {
            taskString += "0 | ";
        }
        taskString += t.getDescription();
        if (t instanceof DateTimeTask) {
            taskString += " | " + ((DateTimeTask) t).getDateTime();
        }
        taskString += "\n";
        return taskString;
    }

    private String parseTasks(ArrayList<Task> tasks) {
        String allTasks = "";
        for (Task t : tasks) {
            allTasks += parseTask(t);
        }
        return allTasks;
    }
}
