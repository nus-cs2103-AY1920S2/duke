package com.duke.bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Represents the permanent storage of the list of tasks managed by Duke Bot into the hard drive.
 */
public class Storage {
    private static final String DESTINATION_PATH = new File(".").getPath() + "/tasks.txt";
    private static final String ARCHIVE_PATH = new File(".").getPath()  + "/archive.txt";
    private TaskList tasks;

    private Storage() {
        tasks = TaskList.createTaskList();
    }

    /**
     *
     * Creates a Storage File.
     */
    public static Storage createSrorageFile() {
        return new Storage();
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Saves the input into a .txt file.
     *
     * @param input The message to be saved.
     */
    public void saveToFile(String input) {
        try {
            File file = new File(DESTINATION_PATH);
            FileWriter fw = new FileWriter(file);
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Arhives the current task list.
     *
     * @throws DukeException When the archive process is unsuccessful.
     */
    public void archive() throws DukeException{
        try {
            File archiveFile = new File(ARCHIVE_PATH);
            if (!archiveFile.exists()) {
                archiveFile.createNewFile();
            }

            File oldFile = new File(DESTINATION_PATH);
            if (!oldFile.exists()) {
                throw new DukeException("Current task list is empty!");
            }

            boolean success = oldFile.renameTo(archiveFile);
            oldFile.delete();
            tasks = TaskList.createTaskList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Retrieves all the tasks entered previously by the user from duke.txt and
     * adds them to the TaskList.
     *
     * @throws IOException if the file cannot be found
     */
    public void loadData() throws IOException {
        File data = new File(DESTINATION_PATH);

        if (!data.exists()) {
            data.createNewFile();
        }

        Scanner scanner = new Scanner(data);
        if (!scanner.hasNext()) {
            return;
        }

        String dummy = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String[] arr  = scanner.nextLine().split("[|]|[.]");
            Task newTask = null;

            if  (arr[1].trim().equals("T")) {
                newTask = TodoTask.createTodoTask(arr[3].trim());
            } else if (arr[1].trim().equals("D")) {
                newTask = DeadlineTask.createDeadlineTask(arr[3].trim(),
                        LocalDate.parse(arr[4].trim(), DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            } else if (arr[1].trim().equals("E")) {
                newTask = EventTask.createEventTask(arr[3].trim(),
                        LocalDate.parse(arr[4].trim(), DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            }

            if (arr[2].trim().equals("Done")) {
                newTask.markDone();
            }

            tasks.addTask(newTask);
            assert newTask != null : "No task to retrieve";
            assert newTask.getTaskName() != null
                    : "No description for this task";
        }
    }

    /**
     * Returns the path in which the output is saved.
     *
     * @return The path of the task list.
     */
    public String getDestinationPath() {
        return DESTINATION_PATH;
    }

    /**
     * Returns the path of the archive file.
     *
     * @return The path of the archive file.
     */
    public String getArchivePath() {
        return ARCHIVE_PATH;
    }

}
