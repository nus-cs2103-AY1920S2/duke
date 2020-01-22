public class TaskList {
    /** Default maximum number of tasks. */
    private static final int DEFAULT_MAX_TASKS = 100;

    /** Stores a list of tasks. */
    private final Task[] taskList;
    /** Counts the number of tasks in the list. */
    private final int numTasks;

    /** Constructs a new default TaskList that can store 100 tasks. */
    public TaskList() {
        this(DEFAULT_MAX_TASKS);
    }

    /**
     * Constructs a new TaskList that can store an arbitrary size.
     *
     * @param maxTasks the maximum number of tasks that can be stored.
     * */
    public TaskList(int maxTasks) {
        this(new Task[maxTasks], 0); // Check if will cause null references
    }

    private TaskList(Task[] taskList, int numTasks) {
        this.taskList = taskList;
        this.numTasks = numTasks;
    }

    /**
     * Returns the task at the specified position in the list.
     *
     * @param taskId the position of the task in the list, starting from #1.
     * @return the task at the specified position in the list.
     */
    public Task get(int taskId) {
        return taskList[taskId - 1];
    }

    /**
     * Adds a new tasks into the TaskList.
     *
     * @param taskDescription a description for the new task.
     * @return a copy of this TaskList with the newly added task.
     */
    public TaskList addTask(String taskDescription) {
        Task[] newList = new Task[taskList.length];

        System.arraycopy(taskList, 0, newList, 0, numTasks);
        newList[numTasks] = new Task(taskDescription);

        return new TaskList(newList, numTasks + 1);
    }

    /**
     * Marks a task in the TaskList as done.
     *
     * @param taskId the id of the task in the list.
     * @return a copy of this TaskList with the specified task marked as done.
     */
    public TaskList doneTask(int taskId) {
        Task[] newList = new Task[taskList.length];

        System.arraycopy(taskList, 0, newList, 0, numTasks);
        newList[taskId - 1] = newList[taskId - 1].markDone();

        return new TaskList(newList, numTasks);
    }

    @Override
    public String toString() {
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < numTasks; i++) {
            tasks.append(String.format("%2d.%s\n", i + 1, taskList[i]));
        }

        if (tasks.length() > 0) {
            tasks.deleteCharAt(tasks.length() - 1);
        }

        return tasks.toString();
    }
}
