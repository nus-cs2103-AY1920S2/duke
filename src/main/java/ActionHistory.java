import java.util.Stack;

/**
 * This class inherits from Stack, and stores consecutive versions
 * of the working task list in a stack. It allows for the program's
 * 'undo' function by enabling the tracing back of previous versions
 * of the task list.
 */
public class ActionHistory extends Stack<TaskList> {

    /**
     * This method perfectly clones the contents of one TaskList
     * into another TaskList.
     *
     * @param original The task list to be copied from.
     * @param copy The task list to be copied into.
     */
    public static void copyList(TaskList original, TaskList copy) {
        int copyLength = copy.size();
        for (int i = 0; i < copyLength; i++) {
            copy.remove(0);
        }
        int originalLength = original.size();
        for (int i = 0; i < originalLength; i++) {
            copy.add(Task.duplicate(original.get(i)));
            if (original.get(i).isDone) {
                copy.get(i).markDone();
            }
        }
    }
}
