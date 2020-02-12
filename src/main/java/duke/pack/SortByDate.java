package duke.pack;

import java.util.Comparator;

public class SortByDate implements Comparator<Task> {
    public SortByDate() {
    }

    @Override
    public int compare(Task a, Task b) {
        String dateA = a.getDateToCompare();
        String dateB = b.getDateToCompare();

        return dateA.compareTo(dateB);
    }
}
