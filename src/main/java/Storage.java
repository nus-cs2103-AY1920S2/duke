import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from file and saving tasks in file.
 */
public class Storage {

    private String filePath;

    /**
     * Class constructor.
     *
     * @param filePath Path to file where tasks are saved on hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks that have been saved in duke.txt.
     *
     * @return list of tasks that were previously saved in duke.txt.
     * @throws FileNotFoundException Thrown when file to load from cannot be found.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        File file = new File(filePath);
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()) {
            String input = readFile.nextLine();
            String inputs[] = input.split("\\|");

            String taskType = inputs[0];
            String isDone = inputs[1];
            String description = inputs[2];

            if (taskType.equals("D") || taskType.equals("E")) {
                String date = inputs[3];
                String time = inputs[4];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);

                formatter = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime localTime = LocalTime.parse(time, formatter);

                if (taskType.equals("D")) {
                    tasks.add(new Deadline(description, taskType, localDate, localTime, false));
                } else {
                    tasks.add(new Event(description, taskType, localDate, localTime, false));
                }

            } else {
                tasks.add(new ToDo(description, taskType, false));
            }

            if (isDone.equals("Y")) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
        return tasks;
    }

    /**
     * Updates tasks saved in hard disk when task list changes.
     *
     * @param tasks List of tasks to be saved.
     */
    public void updateFile(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath, false);

        if (tasks.getSize() != 0) {
            writer.write(tasks.getTask(0).formatForFile());

            for (int i = 1; i < tasks.getSize(); i++) {
                writer.write(System.lineSeparator() + tasks.getTask(i).formatForFile());
            }
        }

        writer.close();
    }
}
