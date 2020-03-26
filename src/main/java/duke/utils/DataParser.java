package duke.utils;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>DataParser</code> class is a utility class to help parse string data from storage.
 */
public class DataParser {
    public static final String NEWLINE = "\n";
    public static final String PIPE = " \\| ";
    
    /**
     * Parses task data from storage into list.
     *
     * @param data Task data.
     * @return List of tasks.
     * @throws NullPointerException If data argument is empty.
     */
    public static List<Task> parseToTasks(String data) throws NullPointerException {
        if (data.isEmpty()) {
            throw new NullPointerException();
        }
        
        List<Task> tasks = new ArrayList<>();
        String[] lines = data.split(NEWLINE);
        for (String line : lines) {
            if (line.isEmpty()) {
                continue; // ignore empty lines
            }
            
            String[] taskArguments = line.strip().split(PIPE);
            String type = taskArguments[0].strip();
            String name = taskArguments[2].strip();
            boolean isCompleted = taskArguments[1].equals("1");
            switch (type) {
            case "T":
                tasks.add(new Todo(name, isCompleted));
                break;
            case "D":
                tasks.add(new Deadline(name, isCompleted, taskArguments[3]));
                break;
            case "E":
                tasks.add(new Event(name, isCompleted, taskArguments[3]));
                break;
            default:
                break;
            }
        }
        
        return tasks;
    }
}
