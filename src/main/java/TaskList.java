import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TaskList implements Serializable {
    private List<Task> taskList;
    private Storage taskStorage;
    private List<Command> stats; //stats associated with this task list
    private Storage statStorage;

    public TaskList(Storage taskStorage, Storage statStorage) {
        this.taskStorage = taskStorage;
        this.taskList = taskStorage.loadTask();
        this.statStorage = statStorage;
        this.stats = statStorage.loadStats();
    }

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
