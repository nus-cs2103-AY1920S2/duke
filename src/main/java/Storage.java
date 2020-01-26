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
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        File file = new File(filePath);
        Scanner readFile = new Scanner(file);

        while (readFile.hasNext()) {
            String task = readFile.nextLine();
            char taskType = task.charAt(1);
            char isDone = task.charAt(4);
            String description = task.substring(7);

            switch (taskType) {
            case 'D':
                int position = description.indexOf(" by ");
                String date = description.substring(position + 4, position + 15);
                String time = description.substring(position + 16);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate localDate = LocalDate.parse(date, formatter);

                formatter = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime localTime = LocalTime.parse(time, formatter);

                description = description.substring(0, position);

                if (isDone == 'X') {
                    tasks.add(new Deadline(description, taskType, localDate, localTime, false));
                } else {
                    tasks.add(new Deadline(description, taskType, localDate, localTime, true));
                }

                break;
            case 'E':
                int pos = description.indexOf(" at ");
                String eventDate = description.substring(pos + 4, pos + 15);
                String eventTime = description.substring(pos + 16);

                DateTimeFormatter forFormatting = DateTimeFormatter.ofPattern("MMM d yyyy");
                LocalDate localEventDate = LocalDate.parse(eventDate, forFormatting);

                forFormatting = DateTimeFormatter.ofPattern("hh:mm a");
                LocalTime localEventTime = LocalTime.parse(eventTime, forFormatting);

                description = description.substring(0, pos);

                if (isDone == 'X') {
                    tasks.add(new Event(description, taskType, localEventDate, localEventTime, false));
                } else {
                    tasks.add(new Event(description, taskType, localEventDate, localEventTime, true));
                }

                break;
            default:
                if (isDone == 'X') {
                    tasks.add(new ToDo(description, taskType, false));
                } else {
                    tasks.add(new ToDo(description, taskType, true));
                }

                break;
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
        FileWriter writer = new FileWriter(filePath);

        if (tasks.getSize() == 0) {
            writer.write("");
            writer.close();
            return;
        }

        writer.write(tasks.getTask(0).obtainTaskInfo());
        writer.close();

        FileWriter appender = new FileWriter(filePath, true);

        for (int i = 1; i < tasks.getSize(); i++) {
            appender.write(System.lineSeparator() + tasks.getTask(i).obtainTaskInfo());
        }

        appender.close();
    }
}
