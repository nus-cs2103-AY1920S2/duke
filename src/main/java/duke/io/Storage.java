package duke.io;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String CANNOT_READ_SAVE_FILE = "Cannot to read save File";
    public static final String CANNOT_CREATE_DIRECTORIES_AND_FILES = "Cannot create directories and files";
    public static final String CANNOT_WRITE_TO_TASK_LIST_SAVE_FILE = "Cannot write to task list save file";
    public String saveFilePath;

    public Storage(String saveFilePath){
        this.saveFilePath = saveFilePath;
    }

    // READ FILE//////////////////////////////////////////////////////////////////////////
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
    public void saveTasksList(TasksList tasksList) throws DukeException {
        File saveFile = new File(saveFilePath);
        createDirectories(saveFile);
        createSaveFile(tasksList);
    }

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

    public void createSaveFile(TasksList tasksList) throws DukeException {
        try {
            FileWriter saveFileWriter = new FileWriter(saveFilePath);

            // write each task as its own line in the save file
            for (Task task : tasksList.tasks) {
                if (task instanceof Todo) {
                    storeLine(saveFileWriter, "todo", Boolean.toString(task.isDone), task.description);
                }else if (task instanceof Deadline) {
                    storeLine(saveFileWriter, "deadline", Boolean.toString(task.isDone), task.description, ((Deadline) task).endDate.toString());
                } else if (task instanceof Event) {
                    storeLine(saveFileWriter, "event", Boolean.toString(task.isDone), task.description, ((Event) task).endDate.toString());
                }
            }

            saveFileWriter.close();
        } catch (IOException exception) {
            throw new DukeException(CANNOT_WRITE_TO_TASK_LIST_SAVE_FILE);
        }
    }

    // combines all task elements to save as single line in the save file
    public static void storeLine(FileWriter saveFileWriter, String ... elements) throws IOException {
        StringBuilder lineToStore = new StringBuilder();
        for (String element : elements) {
            lineToStore.append(element).append("|");
        }
        lineToStore.append("\n");
        saveFileWriter.write(lineToStore.toString());
    }
}
