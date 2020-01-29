import java.util.ArrayList;

public class TaskList {
    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);
        storedItems.get(pos).markDone();
        Ui.markItemAsDone(pos, storedItems);
    }

    public static void deleteItem(int pos, ArrayList<Task> storedItems) throws OutOfBoundMarkingRequestException {
        if (pos >= storedItems.size() || pos < 0)
            throw new OutOfBoundMarkingRequestException(pos+1);
        Task t = storedItems.remove(pos);
        Ui.deleteItem(t, storedItems);
    }
}
