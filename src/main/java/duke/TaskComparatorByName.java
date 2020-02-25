package duke;

import java.util.Comparator;

public class TaskComparatorByName implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        return t1.getInstr().compareTo(t2.getInstr());
    }
}
