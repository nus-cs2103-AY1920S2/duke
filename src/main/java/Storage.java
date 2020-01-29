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
 * The Storage class implements the functionality of loading
 * tasks and saving tasks from and into a text file located
 * in the relative file path given.
 *
 * @author Kenny Ho
 */
public class Storage {

    /** System separator used to standardise across OS platforms */
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
     * @throws DukeException if abbreviation of Task read from storage does not match T, D or E.
     */
    public ArrayList<Task> load() throws DukeException{
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(pathFile);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                String[] taskArr = nextLine.split(" \\| ");
                CommandList taskAbbreviation = CommandList.get(taskArr[0]);
                switch (taskAbbreviation) {
                    case TODO:
                        ToDo storeTask = new ToDo(taskArr[2]);
                            if (taskArr[1].equals(DONE)) {
                            storeTask.markAsDone();
                        }
                        tasks.add(storeTask);
                        break;
                    case EVENT:
                        Event storeEvent = new Event(taskArr[2], LocalDate.parse(taskArr[3], inputFormat));
                        if (taskArr[1].equals(DONE)) {
                            storeEvent.markAsDone();
                        }
                        tasks.add(storeEvent);
                        break;
                    case DEADLINE:
                        Deadline storeDeadline = new Deadline(taskArr[2], LocalDate.parse(taskArr[3], inputFormat));
                        if (taskArr[1].equals(DONE)) {
                            storeDeadline.markAsDone();
                        }
                        tasks.add(storeDeadline);
                        break;
                    default:
                        throw new DukeException("Invalid abbreviation!", DukeErrorType.INVALID_ABBREVIATION);
                }
            }
        } catch (FileNotFoundException fileException) {
            try {
                createDataPath(ABSOLUTE_PATH);
            } catch (IOException io) {
                System.out.println("Unable to create storage file/directory\n" +
                        "Please create a data directory and Duke.txt in it");
            }
        }
        return tasks;
    }

    /**
     * Write back to storage text file for any changes made by user such as
     * adding new Task, deleting existing Task and updating Task status.
     *
     * @param updatedList Updated list of tasks after certain user command.
     */
    public void save(TaskList updatedList) {
        if (pathFile.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(absoluteStorageFilePath);
                String toBeWritten = "";
                for (Task eachTask : updatedList.getListOfTasks()) {
                    if (eachTask instanceof ToDo) {
                        toBeWritten += "T | " + eachTask.getStatusNumber() + " | " + eachTask.getDescription() + NEWLINE;
                    } else if (eachTask instanceof Event) {
                        toBeWritten += "E | " + eachTask.getStatusNumber() + " | " + eachTask.getDescription() + " | "
                                + ((Event) eachTask).getTime() + NEWLINE;
                    } else if (eachTask instanceof Deadline) {
                        toBeWritten += "D | " + eachTask.getStatusNumber() + " | " + eachTask.getDescription() + " | "
                                + ((Deadline) eachTask).getBy() + NEWLINE;
                    }
                    else {
                        throw new IOException("Problem encountered while saving/writing to data file");
                    }
                }
                fileWriter.write(toBeWritten);
                fileWriter.close();
            } catch (IOException io) {
                System.out.println("Problem encountered while saving/writing to data file");
            }
        } else {
            System.out.println("Please check write permission on " + absoluteStorageFilePath);
        }
    }
}
