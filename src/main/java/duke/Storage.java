package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *  contains methods which deal with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * constructs a duke.Storage to save and load Tasks.
     * @param filePath a String indicates where the tasks are going to be saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            if (!(new File(filePath)).exists()) {
                String rootLocation = Paths.get("").toAbsolutePath().toString();
                StringTokenizer st = new StringTokenizer(filePath);
                String newDirectoryLocation = rootLocation + File.separator + st.nextToken("/");
                String newFileLocation = newDirectoryLocation + File.separator + st.nextToken("/");
                Files.createDirectories(Paths.get(newDirectoryLocation));
                Files.createFile(Paths.get(newFileLocation));
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * updates the data file when any changes are made to the list of tasks.
     * @param taskList the duke.TaskList being updated.
     * @throws IOException if the file path cannot be found.
     */
    public void rewriteFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        StringBuilder textToWrite = new StringBuilder();
        for (Task t: taskList.getTasks()) {
            List<String> details = new ArrayList<>() {{
                    add(t.getClass().getSimpleName());
                    add(t.getStatus());
                    add(getSpecificDescription(t));
                    add(t.printAllTags().trim());
                }};
            textToWrite.append(String.join("~", details)).append("\n");
        }
        fw.write(textToWrite.toString());
        fw.close();
    }

    /**
     * gets an extra piece of information if the Task is a Deadline or Event.
     * @param t the Task we are dealing with.
     * @return the description of the Task and extra information if applicable.
     */
    private String getSpecificDescription(Task t) {
        String description = t.getDescription();
        if (t instanceof Deadline) {
            description += "~" + ((Deadline) t).getDueDate();
        } else if (t instanceof Event) {
            description += "~" + ((Event) t).getScheduledDate();
        }
        return description;
    }

    /**
     * loads the list of tasks stored in hard disk.
     * @return a list of Tasks gotten from the hard disk.
     * @throws IOException if the file path cannot be found.
     */
    public ArrayList<Task> load() throws IOException {
        Parser parser = new Parser();
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> data = Files.readAllLines(Paths.get(this.filePath));
        for (String s: data) {
            tasks.add(parser.parseFromDataFile(s));
        }
        return tasks;
    }
}
