public class TaskList {
    /** Default maximum number of tasks. */
    private static final int DEFAULT_MAX_TASKS = 100;

    /** Stores a list of tasks. */
    private final String[] taskList;
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
        this(new String[maxTasks], 0); // Check if will cause null references
    }

    private TaskList(String[] taskList, int numTasks) {
        this.taskList = taskList;
        this.numTasks = numTasks;
    }

    /**
     * Adds a new tasks into the TaskList.
     *
     * @param task a new task.
     * */
    public TaskList addTask(String task) {
        String[] newList = new String[taskList.length];

        System.arraycopy(taskList, 0, newList, 0, numTasks);
        newList[numTasks] = task;

        return new TaskList(newList, numTasks + 1);
    }

    @Override
    public String toString() {
        StringBuilder tasks = new StringBuilder();

        for (int i = 0; i < numTasks; i++) {
            tasks.append(String.format("%2d. %s\n", i + 1, taskList[i]));
        }

        if (tasks.length() > 0) {
            tasks.deleteCharAt(tasks.length() - 1);
        }

        return tasks.toString();
    }
}
