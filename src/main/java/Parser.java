import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    public static TaskList storageToTaskList(String s) {
        TaskList tl = new TaskList(new ArrayList<Task>());
        String[] lines = s.split("\\r?\\n");
        for (String line : lines) {
            String[] arr = line.split(" \\| ");

            if (arr[0].equals("T")) {
                Task todo = new ToDoTask(arr[2]);
                if (Integer.parseInt(arr[1]) == 1) todo.markAsDone(); 
                tl.list.add(todo);

            } else if (arr[0].equals("D")) {
                Task deadline = new DeadlineTask(arr[2], LocalDate.parse(arr[3]));
                if (Integer.parseInt(arr[1]) == 1) deadline.markAsDone(); 
                tl.list.add(deadline);

            } else if (arr[0].equals("E")) {
                Task event = new EventTask(arr[2], LocalDate.parse(arr[3]));
                if (Integer.parseInt(arr[1]) == 1) event.markAsDone(); 
                tl.list.add(event);
            } 
        }
        return tl;
    }
}