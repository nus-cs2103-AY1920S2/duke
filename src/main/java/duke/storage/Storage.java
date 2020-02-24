package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.enums.ErrorCodes;
import duke.exceptions.DukeException;
import duke.tags.Tag;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Class for handling data to and from storage files.
 *
 * @author Firzan Armani
 */
public class Storage {
    private String filePath;
    private ArrayList<Task> taskList;

    /**
     * Storage constructor.
     *
     * @param filePath String path of the duke.txt file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the list of tasks from the file specified.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws DukeException DukeException thrown if file is not found or any line not being parsed succesfully.
     */
    public ArrayList<Task> load() throws DukeException {
        taskList = new ArrayList<Task>();
        try {
            Scanner readFile = new Scanner(new File(this.filePath));
            int lineNo = 1;
            while (readFile.hasNextLine()) {
                loadTask(readFile.nextLine(), lineNo);
                lineNo++;
            }
            readFile.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException(ErrorCodes.FILE_NOT_FOUND);
        }
    }

    /**
     * Load the individual lines from the input file.
     *
     * @param taskString A string of the current line to be loaded from storage.
     * @param lineNo The current index of the line in the file.
     * @throws DukeException DukeException thrown if any line cannot be loaded.
     */
    private void loadTask(String taskString, int lineNo) throws DukeException {
        String[] splitString = taskString.split(" \\| ");
        ArrayList<Tag> tags = loadTaskTags(splitString[3]);
        switch (splitString[0]) {
        case "T":
            taskList.add(new ToDo(splitString[2], tags));
            break;
        case "D":
            taskList.add(new Deadline(splitString[2], LocalDate.parse(splitString[4]), tags));
            break;
        case "E":
            taskList.add(new Event(splitString[2], LocalDate.parse(splitString[4]), tags));
            break;
        default:
            assert splitString[0].equals("T")
                || splitString[0].equals("D")
                || splitString[0].equals("E") : "There's an invalid line in the input file.";
            throw new DukeException(ErrorCodes.LOADING_ERROR, String.valueOf(lineNo));
        }
        if (splitString[1].equals("true")) {
            taskList.get(taskList.size() - 1).setTaskDone();
        }
    }

    private ArrayList<Tag> loadTaskTags(String tagsString) {
        ArrayList<Tag> tags = new ArrayList<Tag>();
        for (String tagName : tagsString.split(",\\s")) {
            tags.add(new Tag(tagName));
        }
        return tags;
    }

    /**
     * Overwrite the entire target file with the updated list of tasks and saves it.
     *
     * @param taskList The current list of tasks in the program.
     * @throws DukeException DukeException thrown if any task cannot be written into the buffer before saving the file.
     */
    public void save(TaskList taskList) throws DukeException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList.getTaskList()) {
                try {
                    writer.write(task.toFileString() + "\n");
                } catch (IOException e) {
                    throw new DukeException(ErrorCodes.SAVING_ERROR, task.toString());
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}