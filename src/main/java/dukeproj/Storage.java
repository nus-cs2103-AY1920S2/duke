package dukeproj;

import dukeproj.data.Schedule;
import dukeproj.exception.BadDateException;
import dukeproj.tasks.Deadline;
import dukeproj.tasks.Event;
import dukeproj.tasks.Task;
import dukeproj.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage file for DukeProject.
 */
public class Storage {
    /** File object that connects to Duke's storage file on the hard drive. */
    private File file;

    /**
     * Prints the file in storage into an ArrayList.
     * Takes in a calender to update the calender with the tasks in the file.
     *
     * @param schedule calender to be updated by tasks in storage file.
     * @return ArrayList made from tasks in storage file.
     */
    public ArrayList<Task> printFileIntoList(Schedule schedule) {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] parts = str.split("\\|");
                addTask(parts, tasks, schedule);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            return null;
        } catch (BadDateException e) {
            System.err.println("Bad dates in File\n Please correct it and reload");
            return null;
        }
    }

    private void addTask(String[] inputs, ArrayList<Task> tasks, Schedule schedule)
            throws BadDateException {
        switch (inputs[0]) {
        case "T":
            Task todo = newTodo(Boolean.parseBoolean(inputs[1]), inputs[2]);
            tasks.add(todo);
            break;
        case "E":
            Task event = newEvent(Boolean.parseBoolean(inputs[1]), inputs[2], inputs[3]);
            tasks.add(event);
            schedule.addDate(event);
            break;
        case "D":
            Task deadline = newDeadline(Boolean.parseBoolean(inputs[1]), inputs[2], inputs[3]);
            tasks.add(deadline);
            schedule.addDate(deadline);
            break;
        default:
            System.err.println("Error in file reading");
            break;
        }
    }

    private Task newTodo(boolean isDone, String description) {
        return new Todo(isDone, description);
    }

    private Task newEvent(boolean isDone, String description, String date) throws BadDateException {
        return new Event(isDone, description, date);
    }

    private Task newDeadline(boolean isDone, String description, String date) throws BadDateException {
        return new Deadline(isDone, description, date);
    }

    /**
     * Writes the tasks in an ArrayList into the storage file.
     *
     * @param tasks ArrayList of tasks to be written into the storage file.
     */
    public void writeListIntoFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                writeTaskToFile(task, fw);
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Unable to write");
        }
    }

    private void writeTaskToFile(Task task, FileWriter fw) throws IOException {
        switch (task.getType()) {
        case TODO:
            fw.write("T|" + task.getDone() + "|" + task.getTask()
                    + System.lineSeparator());
            break;
        case EVENT:
            fw.write("E|" + task.getDone() + "|" + task.getTask() + "|"
                    + task.getDate().format(Parser.DATE_WRITE_FORMATTER)
                    + System.lineSeparator());
            break;
        case DEADLINE:
            fw.write("D|" + task.getDone() + "|" + task.getTask() + "|"
                    + task.getDate().format(Parser.DATE_WRITE_FORMATTER)
                    + System.lineSeparator());
            break;
        default:
            System.err.println("Error in TaskList");
            break;
        }
    }

    /**
     * Constructs a Storage with the file pointing to the filepath provided.
     * Will make a directory if parent directory (default: data directory) is missing.
     *
     * @param filepath filepath to be assigned to the storage.
     */
    public Storage(String filepath) {
        file = new File(filepath);
        try {
            file.getParentFile().mkdir(); //makes data directory if does not exists
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("Unable to create file");
        }
    }
}
