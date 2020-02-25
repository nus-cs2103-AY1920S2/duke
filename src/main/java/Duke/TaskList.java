package Duke;

import Duke.command.Command;
import Duke.task.Task;

import java.io.Serializable;
import java.util.List;

public class TaskList implements Serializable {
    private List<Task> taskList;
    private Storage taskStorage;
    private List<Command> stats; //stats associated with this task list
    private Storage statStorage;

    /**
     * Constructor for TaskList with 2 storage for taskList and stats respectively.
     * @param taskStorage Storage to store taskList
     * @param statStorage Storage to store stats
     */
    public TaskList(Storage taskStorage, Storage statStorage) {
        this.taskStorage = taskStorage;
        this.taskList = taskStorage.loadTask();
        this.statStorage = statStorage;
        this.stats = statStorage.loadStats();
    }

    /**
     * Constructor for TaskList with 1 storage for taskList only. Stats storage to be created at default file path.
     * @param taskStorage Storage to store taskList
     */
    public TaskList(Storage taskStorage) {
        this.taskStorage = taskStorage;
        this.taskList = taskStorage.loadTask();
        this.statStorage = getStatStorage();
        this.stats = statStorage.loadStats();
    }

    public Storage getTaskStorage() {
        return taskStorage;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Getter for storage for stats. Creates new Storage at default file path data/stats.txt if no existing storage.
     * @return if no existing storage, new storage at default file path data/stats.txt, else, return current storage
     */
    public Storage getStatStorage() {
        if (this.statStorage == null) {
            String newPath = "data/stats.txt"; //default filepath
            return new Storage(newPath);
        } else {
            return this.statStorage;
        }
    }

    public List<Command> getStats() {
        return stats;
    }

}
