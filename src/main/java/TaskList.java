import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TaskList {
    private List<Task> taskList;
    private Storage storage;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.taskList = storage.loadTask();
    }

    public Storage getStorage() {
        return storage;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

}
