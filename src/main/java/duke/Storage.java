package duke;

import duke.command.CommandList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage with the functionality of loading
 * tasks and saving tasks from and into a text file located
 * in the relative file path given.
 *
 * @author Kenny Ho
 */
public class Storage {

    /**
     * System separator used to standardise across OS platforms
     */
    public final static String NEWLINE = System.lineSeparator();

    private final static String DONE = "1";
    private final static String ABSOLUTE_PATH = Paths.get("").toAbsolutePath().toString();
    private File pathFile;
    private String absoluteStorageFilePath;

    /**
     * A constructor to create the File object reference to the
     * absolute file path of the storage text file.
     * It also creates the directory named 'data' under root directory
     * and the storage text file named 'duke.txt' if it does not exist
     * in project folder.
     *
     * @param filePath Relative path of the storage text file used.
     * @throws IOException if directory or text file is unable to be created.
     */
    public Storage(String filePath) throws IOException {
        String path = Paths.get(ABSOLUTE_PATH + System.getProperty("file.separator") + filePath).toString();
        File file = new File(path);
        this.absoluteStorageFilePath = path;
        this.pathFile = file;
        if (!file.exists()) {
            createDataPath(ABSOLUTE_PATH);
        }
    }

    /**
     * Create storage text file named 'duke' and directory named 'data'
     * if it does not exist.
     *
     * @param rootDirectory Root directory of project.
     * @throws IOException if directory or text file is unable to be created.
     */
    public void createDataPath(String rootDirectory) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        File dataDirectory = new File(rootDirectory + fileSeparator + "data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        pathFile.createNewFile();
    }

    /**
     * Reads from storage text file for any saved tasks recorded
     * previously by user and populate it into an ArrayList for
     * further processing.
     *
     * @return ArrayList of tasks stored in storage text file.
     * @throws DukeException if abbreviation of duke.task.Task read from storage does not match T, D or E.
     */
    public ArrayList<Task> load() throws DukeException {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(pathFile);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                String[] taskArr = nextLine.split(" \\| ");
                if (taskArr.length <= 1) {
                    throw new DukeException("Invalid file content format", DukeErrorType.INVALID_FILE_CONTENT);
                }
                CommandList taskAbbreviation = CommandList.get(taskArr[0]);
                switch (taskAbbreviation) {
                case TODO:
                    tasks.add(todoLoader(taskArr));
                    break;
                case EVENT:
                    tasks.add(eventLoader(taskArr, inputFormat));
                    break;
                case DEADLINE:
                    tasks.add(deadlineLoader(taskArr, inputFormat));
                    break;
                default:
                    throw new DukeException("Invalid abbreviation!", DukeErrorType.INVALID_ABBREVIATION);
                }
            }
            scanner.close();
        } catch (FileNotFoundException fileException) {
            try {
                createDataPath(ABSOLUTE_PATH);
            } catch (IOException io) {
                System.out.println("Unable to create storage file/directory\n" +
                        "Please create a data directory and duke.Duke.txt in it");
            }
        }
        return tasks;
    }

    /**
     * Write back to storage text file for any changes made by user such as
     * adding new duke.task.Task, deleting existing duke.task.Task and updating duke.task.Task status.
     *
     * @param updatedList Updated list of tasks after certain user command.
     */
    public void save(TaskList updatedList) {
        if (pathFile.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(absoluteStorageFilePath);
                String toBeWritten = saveParser(updatedList);
                fileWriter.write(toBeWritten);
                fileWriter.close();
            } catch (IOException io) {
                System.out.println("Problem encountered while saving/writing to data file");
            }
        } else {
            System.out.println("Please check write permission on " + absoluteStorageFilePath);
        }
    }

    /**
     * Loads the String array with todo details into a todo object
     * Index 1 of string array should be the status of the task.
     * Index 2 of string array should be the description of the task.
     *
     * @param taskArr String array that should describe a todo object.
     * @return ToDo object which corresponds to the taskArr.
     */
    public ToDo todoLoader(String[] taskArr) {
        ToDo storeTask = new ToDo(taskArr[2]);
        if (isDone(taskArr)) {
            storeTask.markAsDone();
        }
        return storeTask;
    }

    /**
     * Loads the String array with event details into a Event object
     * Index 1 of string array should be the status of the task.
     * Index 2 of string array should be the description of the task.
     * Index 3 of string array should be the due time of the task.
     *
     * @param taskArr String array that should describe a Event object.
     * @param inputFormat DateTimeFormatter object of the date format user should give.
     * @return Event Object which corresponds to the taskArr.
     */
    public Event eventLoader(String[] taskArr, DateTimeFormatter inputFormat) {
        Event storeEvent = new Event(taskArr[2], LocalDate.parse(taskArr[3], inputFormat));
        if (isDone(taskArr)) {
            storeEvent.markAsDone();
        }
        return storeEvent;
    }

    /**
     * Loads the String array with deadline details into a Deadline object
     * Index 1 of string array should be the status of the task.
     * Index 2 of string array should be the description of the task.
     * Index 3 of string array should be the due time of the task.
     *
     * @param taskArr String array that should describe a Deadline object.
     * @param inputFormat DateTimeFormatter object of the date format user should give.
     * @return Deadline Object which corresponds to the taskArr.
     */
    public Deadline deadlineLoader(String[] taskArr, DateTimeFormatter inputFormat) {
        Deadline storeDeadline = new Deadline(taskArr[2], LocalDate.parse(taskArr[3], inputFormat));
        if (isDone(taskArr)) {
            storeDeadline.markAsDone();
        }
        return storeDeadline;
    }

    /**
     * Checks if task read from file is previously been marked as done.
     *
     * @param taskArr String array that should describe a Task.
     * @return Boolean value of whether task is previously marked as done.
     */
    public boolean isDone(String[] taskArr) {
        return taskArr[1].equals(DONE);
    }

    /**
     * Parses TaskList object which contains the list of Task objects currently being saved by User
     * into a summarised string version corresponding to it.
     * String will be returned for writing into a text file.
     *
     * @param listToBeParsed TaskList object which contains the list of task User have saved up till now.
     * @return String which describes all the tasks in the TaskList object.
     * @throws IOException if file content does not match with format recognised.
     */
    public String saveParser(TaskList listToBeParsed) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task eachTask : listToBeParsed.getListOfTasks()) {
            if (eachTask instanceof ToDo) {
                stringBuilder.append("T | ");
                stringBuilder.append(eachTask.getStatusNumber());
                stringBuilder.append(" | ");
                stringBuilder.append(eachTask.getDescription());
                stringBuilder.append(NEWLINE);
            } else if (eachTask instanceof Event) {
                stringBuilder.append("E | ");
                stringBuilder.append(eachTask.getStatusNumber());
                stringBuilder.append(" | ");
                stringBuilder.append(eachTask.getDescription());
                stringBuilder.append(" | ");
                stringBuilder.append(eachTask.getTime());
                stringBuilder.append(NEWLINE);
            } else if (eachTask instanceof Deadline) {
                stringBuilder.append("D | ");
                stringBuilder.append(eachTask.getStatusNumber());
                stringBuilder.append(" | ");
                stringBuilder.append(eachTask.getDescription());
                stringBuilder.append(" | ");
                stringBuilder.append(((Deadline) eachTask).getBy());
                stringBuilder.append(NEWLINE);
            } else {
                throw new IOException("Problem encountered while saving/writing to data file");
            }
        }
        return stringBuilder.toString();
    }
}
