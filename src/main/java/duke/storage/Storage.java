/**
 * Handles the loading, storing and saving list of tasks
 */
package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Looks for a file of existing list of tasks to be loaded into an ArrayList.
     * Creates a new file if file is not found
     * @return ArrayList of tasks
     * @throws IOException If file cannot be found and created
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdir();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String currentTask = sc.nextLine();
                taskList.add(parseTask(currentTask));
            }
        } catch (FileNotFoundException e) {
            file.createNewFile();
        }
        return taskList;
    }

    private Task parseTask(String taskDetail) {
        String[] taskDetails = taskDetail.split(" \\| ");

        Character taskType = taskDetails[0].charAt(0);
        boolean taskIsDone = taskDetails[1].equals("1");
        String description = taskDetails[2];
        Task task = null;

        switch (taskType) {
            case 'T':
                task = new Todo(description);
                break;
            case 'D':
                LocalDate deadlineDate = LocalDate.parse(taskDetails[3], Parser.outputDateFormatter);

                task = new Deadline(description, deadlineDate);
                break;
            case 'E':
                String[] dateTimeString = taskDetails[3].split("-");
                LocalDate eventDate = LocalDate.parse(dateTimeString[0], Parser.outputDateFormatter);
                LocalTime eventTime = LocalTime.parse(dateTimeString[1], Parser.outputTimeFormatter);

                task = new Event(description, eventDate, eventTime);
                break;
        }

        if (task != null && taskIsDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Appends the current task to existing list of tasks
     * @param task Task to be added to list
     */
    public void saveTask(Task task) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String toAdd = formatTaskForSaving(task);
            fw.write(toAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in saving tasks to storage");
        }
    }

    /**
     * Updates existing task list when there is a completion or deletion
     * @param saveList Existing ArrayList of tasks
     */
    public void saveAllTasks(ArrayList<Task> saveList) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            for (Task task : saveList) {
                String toAdd = formatTaskForSaving(task);
                fw.write(toAdd);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error in saving tasks to storage");
        }
    }

    private String formatTaskForSaving(Task task) {
        StringBuilder sb = new StringBuilder();

        int isDone = 0;
        if (task.isDone()) {
            isDone = 1;
        }

        if (task instanceof Todo) {
            sb.append("T | " + isDone + " | " + task.getDescription());
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            sb.append("D | " + isDone + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getDateString());
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            sb.append("E | " + isDone + " | " + eventTask.getDescription() + " | " + eventTask.getDateString()
                    + "-" + eventTask.getTimeString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
