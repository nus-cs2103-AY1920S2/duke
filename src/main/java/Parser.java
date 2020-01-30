import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Parses a String object to TaskList of Tasks.
 */
public class Parser {
    /**
     * Converts String of tasks read from storage to TaskList
     * 
     * @param s String object of tasks read from memory on disk
     * @return Returns a TaskList of tasks corresponding to the String object s
     */
    public static TaskList storageToTaskList(String s) {
        TaskList tl = new TaskList(new ArrayList<Task>());
        String[] lines = s.split("\\r?\\n");
        for (String line : lines) {
            String[] arr = line.split(" \\| ");

            if (arr[0].equals("T")) {
                Task todo = new ToDoTask(arr[2]);
                if (Integer.parseInt(arr[1]) == 1)
                    todo.markAsDone();
                tl.list.add(todo);

            } else if (arr[0].equals("D")) {
                Task deadline = new DeadlineTask(arr[2], LocalDate.parse(arr[3]));
                if (Integer.parseInt(arr[1]) == 1)
                    deadline.markAsDone();
                tl.list.add(deadline);

            } else if (arr[0].equals("E")) {
                Task event = new EventTask(arr[2], LocalDate.parse(arr[3]));
                if (Integer.parseInt(arr[1]) == 1)
                    event.markAsDone();
                tl.list.add(event);
            }
        }
        return tl;
    }
}