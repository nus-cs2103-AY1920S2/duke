package duke.storage;

import duke.task.Task;

/**
 * The {@class StorageContainer} class helps to wrap information to be saved.
 */
public class StorageContainer {
    Task[] tasks;
    Task[] archive;

    /**
     * Initialises a {@code StorageContainer} instance.
     *
     * @param tasks   Tasks to save to storage.
     * @param archive Archive to save to storage.
     */
    public StorageContainer(Task[] tasks, Task[] archive) {
        this.tasks = tasks;
        this.archive = archive;
    }
}
