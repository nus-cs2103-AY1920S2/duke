import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {
    public int compare(Task a, Task b) {
        return a.getPriority() - b.getPriority();
    }
}
