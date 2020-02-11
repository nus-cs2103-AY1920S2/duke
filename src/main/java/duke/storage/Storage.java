/**
 * Handles the loading, storing and saving list of tasks
 */
package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.FileCreationFailure;
import duke.tasks.*;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Looks for a file of existing list of tasks to be loaded into an ArrayList.
     * Creates a new file if file is not found
     * @return ArrayList of tasks
     * @throws DukeException If file cannot be found
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String currentTask = sc.nextLine();
                    taskList.add(parseTask(currentTask));
                }
            } catch (FileNotFoundException e) {
                throw new FileCreationFailure();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new FileCreationFailure();
            }
        }

        return taskList;
    }

    private Task parseTask(String taskDetail) {
        String[] taskDetails = taskDetail.split(" \\| ");

        Character taskType = taskDetails[0].charAt(0);
        boolean taskIsDone = taskDetails[1].equals("1");
        Task task = null;

        switch (taskType) {
            case 'T':
                task = new Todo(taskDetails[2]);
                break;
            case 'D':
                LocalDate date = LocalDate.parse(taskDetails[3], Deadline.dateFormatter);
                task = new Deadline(taskDetails[2], date);
                break;
            case 'E':
                LocalDateTime localDateTime = LocalDateTime.parse(taskDetails[3], Event.dateTimeFormatter);
                task = new Event(taskDetails[2], localDateTime);
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
     * @param taskList Existing task list
     */
    public void saveAllTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            ArrayList<Task> saveList = taskList.getList();
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
            sb.append("D | " + isDone + " | " + deadlineTask.getDescription() + " | " + deadlineTask.getDeadline());
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            sb.append("E | " + isDone + " | " + eventTask.getDescription() + " | " + eventTask.getDateTime());
        }
        sb.append("\n");
        return sb.toString();
    }
}
