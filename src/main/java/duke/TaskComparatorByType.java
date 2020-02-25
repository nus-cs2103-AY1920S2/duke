package duke;

import java.util.Comparator;

public class TaskComparatorByType implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getType().compareTo(t2.getType());
    }
}
