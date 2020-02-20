package duke.storage;
import duke.commons.Deadline;
import duke.commons.Event;
import duke.commons.Task;
import duke.commons.Todo;
import duke.logic.TaskList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private static final String SPLIT =  " \\| ";
    private static final String JOIN = " | ";

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = "";
        line = reader.readLine();

        while (line != null) {
            String[] tokens = line.split(SPLIT);
            String type = tokens[0];
            boolean isDone = Boolean.parseBoolean(tokens[1]);
            String description = tokens[2];
            Task task;
            if (type.equals("T")) {
                task = new Todo("todo", isDone, description);
            } else {
                // Deadline/Event has tokens = {type, isDone, description, time}
                assert tokens.length == 4 : "missing argument(s)";
                String time = tokens[3];
                if (type.equals("D")) {
                    task = new Deadline("deadline", isDone, description, time);
                } else {
                    // Event type is provided
                    assert type.equals("E") : "invalid type";
                    task = new Event("event", isDone, description, time);
                }
            }
            assert task != null : "null task";
            taskList.add(task);
            line = reader.readLine();
        }

        reader.close();
        return taskList;
    }

    public void update(TaskList tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false));
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task: taskList) {
            String[] tokens = task.toDataTokens();
            writer.append(String.join(JOIN, tokens));
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    public void initialiseData() {
        File file = new File("data/tasks.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            // initialiseData() only called when file not already exists
            assert 1 == 0 : "data already exists";
        }
    }
}
