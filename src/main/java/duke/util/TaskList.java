package duke.util;

import duke.exception.OutOfBoundMarkingRequestException;
import duke.task.Task;
import java.util.ArrayList;

public class TaskList {

    /**
     * Mark task at pos in storedItems as Done
     * @param pos
     * @param storedItems
     * @throws OutOfBoundMarkingRequestException
     */
    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);
        storedItems.get(pos).markDone();
        Ui.markItemAsDone(pos, storedItems);
    }

    /**
     * Delete task at pos in storedItems
     * @param pos
     * @param storedItems
     * @throws OutOfBoundMarkingRequestException
     */
    public static void deleteItem(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);
        Task t = storedItems.remove(pos);
        Ui.deleteItem(t, storedItems);
    }
}
