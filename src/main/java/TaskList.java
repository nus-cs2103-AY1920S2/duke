import java.util.ArrayList;

/**
 * This class inherits from ArrayList, and serves as
 * a working list for the list being managed by Duke.
 * It is also stored in ActionHistory as snapshots of
 * the list's version history.
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super(100);
    }
}
