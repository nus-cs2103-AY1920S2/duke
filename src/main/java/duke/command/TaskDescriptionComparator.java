package duke.command;

import java.util.Comparator;

public class TaskDescriptionComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.description.compareTo(o2.description);
    }
}
