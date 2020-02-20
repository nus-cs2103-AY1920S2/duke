package duke.storage;
import duke.commons.Deadline;
import duke.commons.Event;
import duke.commons.Task;
import duke.commons.Todo;
import duke.logic.TaskList;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private String SPLIT =  " \\| ";
    private String JOIN = " | ";

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
            Task task = null;
            if (type.equals("T")) {
                task = new Todo(isDone, description);
            } else {
                // Deadline/Event has tokens = {type, isDone, description, time}
                assert tokens.length == 4 : "missing arguments";
                String time = tokens[3];
                if (type.equals("D")) {
                    task = new Deadline(isDone, description, time);
                } else {
                    // type Event
                    assert type.equals("E");
                    task = new Event(isDone, description, time);
                }
            }
            assert task != null : "null task";
            taskList.add(task);
            line = reader.readLine();
        }

        reader.close();
        return taskList;
    }

    public void update(TaskList tasks) throws IOException{
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

    }
}
