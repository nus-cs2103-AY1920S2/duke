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

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //to update the data file when any changes are made to the list of tasks
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

    //get an extra piece of information if the Task.Task is a Task.Deadline or Task.Event
    private String getSpecificDescription(Task t) {
        String text = t.getDescription();
        if (t instanceof Deadline) {
            text += "~" + ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            text += "~" + ((Event) t).getAt();
        }
        return text;
    }

    //get a Task.Task according to the text in the data file
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

    //load the list of tasks stored in hard disk
    public ArrayList<Task> start() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        List<String> data = Files.readAllLines(Paths.get(this.filePath));
        for (String s: data) {
            tasks.add(decode(s));
        }
        return tasks;
    }
}
