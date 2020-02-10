package dukestorage;

import dukelist.DukeList;
import duketasks.Deadline;
import duketasks.Event;
import duketasks.Task;
import duketasks.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage function used to save tasks whenever there is a change in the list items
 * and load tasks when Duke program starts up
 */

public class DukeStorage {
    // try to change to relative path
    private static final String defaultPath = "C:\\MinGW\\msys\\1.0\\JavaTestFile\\2103Codes\\duke\\src\\main\\java\\data\\duke.txt";

    public final Path path;

    public DukeStorage(String filePath) {
        path = Paths.get(filePath);
    }

    public DukeStorage() {
        this(defaultPath);
    }

    /**
     * Saves the input DukeList into a text file by first encoded each entry of the DukeList into
     * a special format of 'T/D/E-X/O-eventDesc(-eventDeadline)'
     *
     * @param dl The DukeList that is to be saved
     */
    public void save(DukeList dl) {
        ArrayList<Task> inputDL = dl.getListOfTasks();
        List<String> outputList = saveEncoder(inputDL);
        try {
            System.out.println("Trying to save");
            Path test = Files.write(path, outputList);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private List<String> saveEncoder(ArrayList<Task> alT) {
        List<String> output = new ArrayList<>();
        for (Task currTask : alT) {
            output.add(currTask.getSaveString());
        }
        return output;
    }

    /**
     * Returns a DukeList regardless, content depends on whether the
     * designated text file has previously saved tasks
     *
     * @return An empty DukeList if text file is empty, else a DukeList with previously saved tasks
     * @throws IOException Thrown by Files.readAllLines
     */
    public DukeList load() throws IOException {
        if (!Files.exists(path)) {
            System.out.println("Path is empty!");
            return new DukeList();
        } else {
            System.out.println("Path is not empty!");
            DukeList output = new DukeList();
            try {
                List<String> input = Files.readAllLines(path);
                System.out.println("Successfully loaded");
                return loadDecoder(input, output);
            } catch (IOException ioe) {
                throw ioe;
            }
        }
    }

    private DukeList loadDecoder(List<String> savedList, DukeList dl) {
        for (String currSaveEntry : savedList) {
            String[] input = currSaveEntry.split("\\|");
            String command = input[0];
            String isDoneString = input[1];
            String taskDesc = input[2];
            if (command.equals("T")) {
                dl.addTask(new Todo(taskDesc, isDoneString));
            } else if (command.equals("E")) {
                LocalDate by = LocalDate.parse(input[3]);
                dl.addTask((new Event(taskDesc, by, isDoneString)));
            } else {
                LocalDate by = LocalDate.parse(input[3]);
                dl.addTask((new Deadline(taskDesc, by, isDoneString)));
            }
        }
        return dl;
    }
}
