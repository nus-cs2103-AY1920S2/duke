import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final static String DONE = "1";
    private final static String ABSOLUTE_PATH = Paths.get("").toAbsolutePath().toString();
    private File pathFile;
    private String absoluteStorageFilePath;

    public Storage(String filePath) throws IOException {
        String path = Paths.get(ABSOLUTE_PATH + System.getProperty("file.separator") + filePath).toString();
        File file = new File(path);
        this.absoluteStorageFilePath = path;
        this.pathFile = file;
        if (!file.exists()) {
            createDataPath(ABSOLUTE_PATH);
        }
    }

    public void createDataPath(String rootDirectory) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        File dataDirectory = new File(rootDirectory + fileSeparator + "data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        } else {
            pathFile.createNewFile();
        }
    }

    public ArrayList<Task> load() {
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
                        // may add own DukeException to throw invalid abbreviation
                        break;
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

    public void save(List<Task> updatedList) {
        if (pathFile.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(absoluteStorageFilePath);
                String toBeWritten = "";
                for (Task eachTask : updatedList) {
                    if (eachTask instanceof ToDo) {
                        toBeWritten += "T | " + eachTask.getStatusNumber() + " | " + eachTask.getTask() + Duke.NEWLINE;
                    } else if (eachTask instanceof Event) {
                        toBeWritten += "E | " + eachTask.getStatusNumber() + " | " + eachTask.getTask() + " | "
                                + ((Event) eachTask).getTime() + Duke.NEWLINE;
                    } else if (eachTask instanceof Deadline) {
                        toBeWritten += "D | " + eachTask.getStatusNumber() + " | " + eachTask.getTask() + " | "
                                + ((Deadline) eachTask).getBy() + Duke.NEWLINE;
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
