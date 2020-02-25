package duke;

import java.util.Comparator;

public class TaskComparatorByTime implements Comparator<Task> {
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Todo && t2 instanceof Todo) {
            return t1.getInstr().compareTo(t2.getInstr());
        } else if (t1 instanceof Todo && !(t2 instanceof Todo)) {
            return 1;
        } else if (!(t1 instanceof Todo) && t2 instanceof Todo) {
            return -1;
        } else if (t1 instanceof Deadline && t2 instanceof Deadline) {
            return ((Deadline) t1).getTime().compareTo(((Deadline) t2).getTime());
        } else if (t1 instanceof Event && t2 instanceof Event) {
            return ((Event) t1).getTime().compareTo(((Event) t2).getTime());
        } else if (t1 instanceof Deadline && t2 instanceof Event) {
            return ((Deadline) t1).getTime().compareTo(((Event) t2).getTime());
        } else if (t1 instanceof Event && t2 instanceof Deadline) {
            return ((Event) t1).getTime().compareTo(((Deadline) t2).getTime());
        } else {
            return 1;
        }
    }
}
