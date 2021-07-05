package duke;

import java.util.Stack;

/**
 * Class created just to avoid circular dependency
 */
public class DukeHistory {
    private static Stack<Duke> history = new Stack<>();

    public static boolean revert() {
        if (history.size() < 2) {
            return false;
        }
        history.pop();
        save(history.peek());
        return true;
    }

    public static void progress(Duke next) {
        if (empty() || getCurrent() != next) {
            history.push(next);
            save(next);
        }
    }

    public static Duke getCurrent() {
        return history.peek();
    }

    public static boolean empty() {
        return history.empty();
    }

    private static void save(Duke duke) {
        duke.storage.save(duke.tasks.getTaskList());
    }

    public static void clearHistory() {
        if (!empty()) {
            Stack<Duke> newHis = new Stack<>();
            newHis.push(history.peek());
            history = newHis;
        }
    }
}
