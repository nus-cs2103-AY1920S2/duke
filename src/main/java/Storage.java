import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Manages tasks stored on the hard disk in tasks.txt.
 */
public class Storage {
    private String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a task from the file and converts it to a Task object.
     * @param taskElements Array consisting of the task type, task description and task date (if present).
     * @return A Task object representing the task stored in the file.
     */
    private Task getNextTask(String taskElements[]) {
        Task t = null;
        switch (taskElements[0]) {
            case "T":
                t = new ToDo(taskElements[2]);
                if (taskElements[1].equals("1"))
                    t.setDone();
                break;
            case "D":
                t = new Deadline(taskElements[2], LocalDate.parse(taskElements[3], DateTimeFormatter.ofPattern("MMM d yyyy")));
                if (taskElements[1].equals("1"))
                    t.setDone();
                break;
            case "E":
                t = new Event(taskElements[2], LocalDate.parse(taskElements[3], DateTimeFormatter.ofPattern("MMM d yyyy")));
                if (taskElements[1].equals("1"))
                    t.setDone();
                break;
        }
        return t;
    }

    /**
     * Loads tasks from a file into Duke.
     * @return Array of Tasks as stored in the file.
     * @throws LoadingException If error occurs while loading tasks from file.
     */
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
            String taskElements[] = currLine.split(" \\| ");
            tasks.add(this.getNextTask(taskElements));
        }
        return tasks;
    }

    /**
     * Saves tasks from Duke to a file.
     * @param arrTasks Tasks to be stored in the file.
     * @throws SavingException If error occurs while saving tasks to file.
     */
    public void save(ArrayList<Task> arrTasks) throws SavingException {
        try {
            this.writeToFile(this.filePath, this.parseTasks(arrTasks));
        } catch (IOException e) {
            throw new SavingException();
        }
    }

    /**
     * Writes the String representation of all tasks in Duke to a file.
     * @param filePath Path of file.
     * @param textToAdd String representation of tasks.
     * @throws IOException If error occurs when writing to the file.
     */
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Converts a Task object into a String to store in a file.
     * Roughly equivalent to the reverse of the getNextTask method.
     * @param t Task to be converted.
     * @return String representation of the task.
     */
    private String parseTask(Task t) {
        String taskString = t.getType() + " | ";
        if (t.isDone())
            taskString += "1 | ";
        else
            taskString += "0 | ";
        taskString += t.getDescription();
        if (t instanceof DateTimeTask)
            taskString += " | " + ((DateTimeTask) t).getDateTime();
        taskString += "\n";
        return taskString;
    }

    /**
     * Converts all Tasks in Duke into a String to store in a file.
     * @param arrTasks An array of Task objects in Duke.
     * @return String representation of all tasks.
     */
    private String parseTasks(ArrayList<Task> arrTasks) {
        String allTasks = "";
        for (Task t: arrTasks) {
            allTasks += parseTask(t);
        }
        return allTasks;
    }
}
