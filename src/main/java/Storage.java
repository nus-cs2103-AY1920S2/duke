import Task.Task;
import Task.Deadline;
import Task.Event;
import Task.Todo;

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
     * constructs a Storage to save and load Tasks.
     * @param filePath a String indicates where the tasks are going to be saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * updates the data file when any changes are made to the list of tasks.
     * @param taskList the TaskList being updated.
     * @throws IOException if the file path cannot be found.
     */
    public void rewriteFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        StringBuilder text = new StringBuilder();
        for (Task t: taskList.getTasks()) {
            List<String> details = new ArrayList<>() {{
                add(t.getClass().getSimpleName());
                add(t.getStatus());
                add(getSpecificDescription(t));
            }};
            text.append(String.join("~", details)).append("\n");
        }
        fw.write(text.toString());
        fw.close();
    }

    /**
     * gets an extra piece of information if the Task is a Deadline or Event
     * @param t the Task we are dealing with.
     * @return the description of the Task and extra information if applicable.
     */
    private String getSpecificDescription(Task t) {
        String text = t.getDescription();
        if (t instanceof Deadline) {
            text += "~" + ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            text += "~" + ((Event) t).getAt();
        }
        return text;
    }

    /**
     * decode the String gotten from the data file into a Task.
     * @param data a String gotten from the data file
     * @return the Task after decoding the String.
     */
    private Task decode(String data) {
        StringTokenizer st = new StringTokenizer(data);
        String className = st.nextToken("~");
        String status = st.nextToken("~");
        String description = st.nextToken("~");
        if (st.hasMoreTokens()) {
            String extra = st.nextToken("~");
            if (className.equals("Task.Deadline")) {
                Deadline ddl =  new Deadline(description, extra);
                if (status.equals("1")) {
                    ddl.markAsDone();
                }
                return ddl;
            } else {
                Event ev =  new Event(description, extra);
                if (status.equals("1")) {
                    ev.markAsDone();
                }
                return ev;
            }
        }
        Todo td = new Todo(description);
        if (status.equals("1")) {
            td.markAsDone();
        }
        return td;
    }

    /**
     * loads the list of tasks stored in hard disk.
     * @return a list of Tasks gotten from the hard disk.
     * @throws IOException if the file path cannot be found.
     */
    public ArrayList<Task> start() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> data = Files.readAllLines(Paths.get(this.filePath));
        for (String s: data) {
            tasks.add(decode(s));
        }
        return tasks;
    }
}
