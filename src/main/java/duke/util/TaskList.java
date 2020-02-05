package duke.util;

import duke.exception.OutOfBoundMarkingRequestException;
import duke.task.Task;
import duke.util.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    /**
     * Mark task at pos in storedItems as Done
     * @param pos
     * @param storedItems
     * @throws OutOfBoundMarkingRequestException
     */
    public static String markItemAsDone(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);

        storedItems.get(pos).markDone();
        return Ui.markItemAsDone(pos, storedItems);
    }

    /**
     * Delete task at pos in storedItems
     * @param pos
     * @param storedItems
     * @throws OutOfBoundMarkingRequestException
     */
    public static String deleteItem(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);

        Task t = storedItems.remove(pos);
        return Ui.deleteItem(t, storedItems);
    }

    /**
     * Search for tasks whose names contain the given sequence
     * Print them to the screen by calling Ui method
     * @param str
     * @param storedItems
     */
    public static String findItem(String str, ArrayList<Task> storedItems) {
        ArrayList<Task> foundList = new ArrayList<>();

        Scanner sc = new Scanner(str);
        sc.next();
        String criteria = sc.nextLine().trim();
        sc.close();

        int len = storedItems.size();
        for (int i = 0; i < len; i++) {
            Task cur = storedItems.get(i);
            if (cur.getName().contains(criteria))
                foundList.add(cur);
        }

        return Ui.printFoundList(foundList);
    }
}
