package duke.storage;

import duke.task.Task;

public class StorageContainer {
    Task[] tasks;
    Task[] archive;

    public StorageContainer(Task[] tasks, Task[] archive) {
        this.tasks = tasks;
        this.archive = archive;
    }
}
