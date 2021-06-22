package duke.save;

import java.util.ArrayList;

import duke.task.TaskList;

public class SaveStateStack {
    private ArrayList<SaveState> saveStates;
    public static final int MAX_SAVE_STATE_CAPACITY = 10;

    /**
     * Initialises a {@code SaveStateStack} that can hold up to 10 saves.
     *
     * <p>This 10 is an arbitrary value, to prevent the application from
     * taking up too much space.
     */
    public SaveStateStack() {
        this.saveStates = new ArrayList<>(MAX_SAVE_STATE_CAPACITY);
    }

    /**
     * Pushes a save state into the stack.
     *
     * @param saveState Save state to push.
     */
    public void push(SaveState saveState) {
        if (this.saveStates.size() == MAX_SAVE_STATE_CAPACITY) {
            this.saveStates.remove(0);
        }
        this.saveStates.add(saveState);
    }

    /**
     * Returns the latest save state.
     *
     * @return Latest save state.
     */
    public SaveState pop() {
        int lastIndex = this.saveStates.size() - 1;
        if (lastIndex == -1) {
            return null;
        }
        SaveState latestSaveState = this.saveStates.get(lastIndex);
        this.saveStates.remove(lastIndex);
        return latestSaveState;
    }

    /**
     * Saves the current command and task list by creating a new save state and
     * pushing it into the stack.
     *
     * @param command  Latest command.
     * @param taskList Task list before update.
     */
    public void saveState(String command, TaskList taskList) {
        SaveState newSaveState = new SaveState(taskList, command);
        this.push(newSaveState);
    }
}
