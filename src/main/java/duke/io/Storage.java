package duke.io;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class handles the reading and writing of files as well as manage which data should be saved.
 */
public class Storage {
    public static final String CANNOT_READ_SAVE_FILE = "Cannot to read save File";
    public static final String CANNOT_CREATE_DIRECTORIES_AND_FILES = "Cannot create directories and files";
    public static final String CANNOT_WRITE_TO_TASK_LIST_SAVE_FILE = "Cannot write to task list save file";
    public String saveFilePath;

    /**
     * Constructs a Storage class whole save file is at saveFilePath.
     * This is the file read and written to.
     *
     * @param saveFilePath The path to the save file for an object of this class.
     */
    public Storage(String saveFilePath){
        this.saveFilePath = saveFilePath;
    }

    // READ FILE//////////////////////////////////////////////////////////////////////////
    /**
     * Parses through the savefile and puts elements into a TaskList object.
     *
     * @return returns the tasklist containing all the loaded tasks from the save file.
     * @throws  DukeException the save file cannot be read, might be due to system permissions
     */
    public TasksList loadTasksList() throws DukeException{
        TasksList tasksList = new TasksList();

        try {
            File saveFile = new File(saveFilePath);
            Scanner saveFileScanner = new Scanner(saveFile);

            // parses each line in the save file, add them to the tasksList one by one.
            while (saveFileScanner.hasNext()) {
                String curLine = saveFileScanner.nextLine();
                String[] taskDesc = curLine.split("\\|");

                String command = taskDesc[0];
                String taskArgs;
                switch (command) {
                case "todo":
                    taskArgs = taskDesc[2];
                    tasksList.addTodo(taskArgs,Boolean.parseBoolean(taskDesc[1]));
                    break;
                case "deadline":
                    taskArgs = taskDesc[2] + " /by " + taskDesc[3];
                    tasksList.addDeadline(taskArgs, Boolean.parseBoolean(taskDesc[1]));
                    break;
                case "event":
                    taskArgs = taskDesc[2] + " /at " + taskDesc[3];
                    tasksList.addEvent(taskArgs, Boolean.parseBoolean(taskDesc[1]));
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(CANNOT_READ_SAVE_FILE);
        }

        return tasksList;
    }

    // WRITE FILE//////////////////////////////////////////////////////////////////////////
    /**
     * Calls methods to write data to the savefile as well create as any missing directories.
     *
     * @param tasksList the TasksList to be saved into the save file
     * @throws  DukeException couldn't not create directories or the save file
     */
    public void saveTasksList(TasksList tasksList) throws DukeException {
        File saveFile = new File(saveFilePath);
        createDirectories(saveFile);
        createSaveFile(tasksList);
    }

    /**
     * Creates missing directories to the save file if they don't already exist
     * @param saveFile used to make the directories missing in the path.
     * @throws  DukeException couldn't not create directories
     */
    public static void createDirectories(File saveFile) throws DukeException {
        boolean fileExists = saveFile.exists();

        if (!fileExists) {
            try {
                saveFile.getParentFile().mkdirs();
                saveFile.createNewFile();
            } catch (IOException exception) {
                throw new DukeException(CANNOT_CREATE_DIRECTORIES_AND_FILES);
            }
        }
    }

    /**
     * Creates the save file if it didn't exist.
     * Writes(saves) each task in taskslist one by one into one line each in the save file.
     * Stores the fields for the different Task in human readable plain text
     *
     * @param tasksList used to create the save file itself
     * @throws  DukeException couldn't not create directories
     */
    public void createSaveFile(TasksList tasksList) throws DukeException {
        try {
            FileWriter saveFileWriter = new FileWriter(saveFilePath);

            // write each task as its own line in the save file
            for (Task task : tasksList.tasks) {
                if (task instanceof Todo) {
                    storeLine(saveFileWriter, "todo", Boolean.toString(task.isDone), task.description);
                }else if (task instanceof Deadline) {
                    storeLine(saveFileWriter, "deadline", Boolean.toString(task.isDone), task.description, ((Deadline) task).dueDate.toString());
                } else if (task instanceof Event) {
                    storeLine(saveFileWriter, "event", Boolean.toString(task.isDone), task.description, ((Event) task).startDate.toString());
                }
            }

            saveFileWriter.close();
        } catch (IOException exception) {
            throw new DukeException(CANNOT_WRITE_TO_TASK_LIST_SAVE_FILE);
        }
    }

    /**
     * Combines the elements in args as a single string that is then stored
     * in the save file as its own line.
     *
     * @param saveFileWriter used to write to the save file
     * @param elements the fields of the object that are to be combined into one string
     * @throws IOException unable to write to the save file
     */
    public static void storeLine(FileWriter saveFileWriter, String ... elements) throws IOException {
        StringBuilder lineToStore = new StringBuilder();
        for (String element : elements) {
            lineToStore.append(element).append("|");
        }
        lineToStore.append("\n");
        saveFileWriter.write(lineToStore.toString());
    }
}
