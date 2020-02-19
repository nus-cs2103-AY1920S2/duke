import java.util.ArrayList;

public class UndoCommand extends Command {

    public UndoCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    @Override
    public String execute(UI ui, TaskList list, Storage storage, HistoryManager historyManager) throws DukeException {
        if (historyManager.canUndo()) {
            TaskList previousState = historyManager.getLastState(list);
            this.transferList(list, previousState);
            storage.writeToFile(previousState.getTaskList());
            return "Previous command has been undone!";
        } else {
            throw new DukeException("No more commands to undo!");
        }
    }

    /**
     * Transfers the last state list to current state.
     * @param list current list.
     * @param previousState previous list.
     */
    public void transferList(TaskList list, TaskList previousState) {
        ArrayList<Task> destination = list.getTaskList();
        ArrayList<Task> source = previousState.getTaskList();
        destination.clear();
        for (Task task : source) {
            destination.add(task);
        }
    }
}
