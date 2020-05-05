package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import java.nio.file.Path;

/**
 * This class takes care storage and retrieval of information
 * from the drive of computer.
 **/
public class Storage {
    private File file;

    /**
     * Constructs a Storage object, it takes in a file path to the file to be modified.
     **/
    Storage(String filePath) throws IOException {
        try {
            this.file = getFile(filePath);
            new Scanner(file);
        } catch (FileNotFoundException e) {
            this.file = createNewFile();
        }
    }

    /**
     * Retrieves the file object for this Storage.
     * @return The file object specified for this Storage.
     **/
    private File getFile(String filePath) throws IOException {
        String home = System.getProperty("user.home");
        Path path = java.nio.file.Paths.get(home, "duke", "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        File newFile;
        if (directoryExists) {
            newFile = new File(filePath);
        } else {
            newFile = createNewFileAndDirectory();
        }
        return newFile;
    }

    /**
     * Creates new file and directory for this Storage if not previously available.
     * @return The file object specified for this Storage.
     **/
    private File createNewFileAndDirectory() throws IOException {
        Path pathCreateDirectory = java.nio.file.Paths.get(System.getProperty("user.dir"), "data");
        File toFile = new File(pathCreateDirectory.toString());
        toFile.mkdir();
        File newFile = new File(pathCreateDirectory.toString(), "duke.txt");
        newFile.createNewFile();
        return newFile;
    }

    /**
     * Creates new file for this Storage if not previously available.
     * @return The file object specified for this Storage.
     **/
    private File createNewFile() throws IOException {
        Path pathCreateFile = java.nio.file.Paths.get(System.getProperty("user.dir"), "data");
        File file = new File(pathCreateFile.toString());
        file.mkdir();
        File duke = new File(pathCreateFile.toString(), "duke.txt");
        duke.createNewFile();
        return duke;
    }

    /**
     * Loads information from the file specified for this Storage.
     * @return An Array List of tasks loaded from the file.
     **/
    public ArrayList<Task> loadFile () throws IOException, DukeException {
        Scanner s = new Scanner(this.file);
        return readData(s);
    }

    /**
     * Reads task list from the scanner that reads a file.
     * @return An Array List of tasks.
     **/
    private ArrayList<Task> readData(Scanner s) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNextLine()) {
            String curr = s.nextLine();
            String type = curr.substring(0, 1);
            String isDone = curr.substring(2, 3);
            Task add;
            if (type.equals("T")) {
                add = new ToDo(curr.substring(4), !isDone.equals("0"));
            } else {
                String name = curr.substring(4, curr.indexOf('|', 4));
                String date = curr.substring(curr.indexOf('|', 4) + 1);
                if (type.equals("D")) {
                    try {
                        add = new Deadline(name, Parser.formatDateAndTime(date),
                                !isDone.equals("0"));
                    } catch (DukeException de) {
                        throw new DukeException("☹ OOPS!!! The file duke.txt has an invalid date and time format");
                    }
                } else {
                    try {
                        add = new Event(name, Parser.formatDateAndTime(date),
                                !isDone.equals("0"));
                    } catch (DukeException de) {
                        throw new DukeException("☹ OOPS!!! The file duke.txt has an invalid date and time format");
                    }
                }
            }
            tasks.add(add);
        }
        return tasks;
    }

    /**
     * Updates information to the file specified for this Storage
     * by adding on a new task object.
     * @param updatedTask Task to be updated
     **/
    public void updateFile(Task updatedTask) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, true));
        writer.append(updatedTask.writeDrive());
        writer.newLine();
        writer.close();
    }

    /**
     * Updates information to the file specified for this Storage
     * by removing on a new task object.
     * @param size The order of the task that are to be removed
     **/
    public void deleteFromFile(int size) throws FileNotFoundException, IOException {
        ArrayList<String> tasks = readNonDeletedData(size);
        writeNonDeletedData(tasks);
    }

    /**
     * Reads task list from the file specified for this Storage
     * and neglect the deleted task.
     * @param size The order of the task that are to be removed
     * @return An Array List of tasks.
     **/
    private ArrayList<String> readNonDeletedData(int size) throws FileNotFoundException {
        ArrayList<String> tasks = new ArrayList<>();
        FileReader fr = new FileReader(this.file);
        Scanner s = new Scanner(fr);
        int i = 1;
        while (s.hasNextLine()) {
            if (i == size) {
                i++;
                s.nextLine();
            } else {
                String line = s.nextLine();
                tasks.add(line);
                i++;
            }
        }
        return tasks;
    }

    /**
     * Writes tasks from task list from the file/
     * @param tasks The task list to be written
     **/
    private void writeNonDeletedData(ArrayList<String> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        for (String st : tasks) {
            writer.append(st);
            writer.newLine();
        }
        writer.close();
    }
}


