package duke.task;

import static duke.util.MagicStrings.ERROR_INDEX_OUT_OF_BOUNDS;
import static duke.util.MagicStrings.ERROR_NO_COMPLETED_TASKS;
import static duke.util.MagicStrings.ERROR_TASK_ALREADY_COMPLETED;
import static duke.util.MagicStrings.ERROR_TASK_CREATED_BEFORE;
import static duke.util.StringCleaner.cleanAndLowerString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exception.DuchessException;
import duke.util.Pair;

/**
 * The {@code TaskList} object helps to store and manage {@code Task}s.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private ArrayList<Task> archive;
    private HashMap<String, Boolean> taskDescriptions;

    /**
     * Initialises an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.archive = new ArrayList<>();
        this.taskDescriptions = new HashMap<>();
    }

    /**
     * Initialises a {@code TaskList} containing existing tasks.
     *
     * @param tasks List of existing tasks to be included in the {@code TaskList}.
     */
    public TaskList(ArrayList<Task> tasks, ArrayList<Task> archive) {
        this.tasks = tasks;
        this.archive = archive;
        this.taskDescriptions = new HashMap<>();
        for (Task task : this.tasks) {
            this.taskDescriptions.put(hashTaskToString(task), true);
        }
    }

    /**
     * Returns the number of {@code Task}s in the {@code TaskList}.
     *
     * @return Number of tasks in {@code TaskList}.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the number of {@code Task}s in the {@code archive}.
     *
     * @return Number of tasks in {@code archive}.
     */
    public int archiveSize() {
        return this.archive.size();
    }

    /**
     * Adds a {@code Task} to the {@code TaskList}.
     *
     * @param task {@code Task} to be added.
     * @throws DuchessException When a task with identical details is currently in the list.
     */
    public void addTask(Task task) throws DuchessException {
        if (this.taskDescriptions.containsKey(hashTaskToString(task))) {
            throw new DuchessException(ERROR_TASK_CREATED_BEFORE);
        }
        taskDescriptions.put(hashTaskToString(task), true);
        this.tasks.add(task);
    }

    /**
     * Removes a {@code Task} from the {@code TaskList}.
     *
     * @param index Index of {@code Task} to be removed.
     * @throws DuchessException If the index is out of bounds.
     */
    public void removeTask(int index) throws DuchessException {
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Clears the list of tasks.
     */
    public void removeAllTasks() {
        this.tasks.clear();
        this.archive.clear();
    }

    /**
     * Returns a {@code Task} from the {@code TaskList} based on its {@code index}.
     *
     * @param index Index of {@code Task} to be retrieved.
     * @return {@code Task} at index given.
     * @throws DuchessException If the index is out of bounds.
     */
    public Task getTask(int index) throws DuchessException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Returns a {@code Task} from the {@code TaskList}'s archive based on its {@code index}.
     *
     * @param index Index of {@code Task} to be retrieved.
     * @return {@code Task} at index given.
     * @throws DuchessException If the index is out of bounds.
     */
    public Task getArchivedTask(int index) throws DuchessException {
        try {
            return this.archive.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DuchessException(ERROR_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Completes a {@code Task} at {@code index} in the {@code TaskList}.
     *
     * @param index Index of {@code Task} in the {@code TaskList}.
     * @return {@code Task} at the given index.
     * @throws DuchessException If the task has already been completed.
     */
    public Task completeTask(int index) throws DuchessException {
        Task taskToComplete = this.getTask(index);
        if (taskToComplete.isCompleted) {
            throw new DuchessException(ERROR_TASK_ALREADY_COMPLETED);
        }
        taskToComplete.completeTask();
        return taskToComplete;
    }

    /**
     * Returns the entire {@code TaskArray}.
     *
     * @return The list of tasks in {@code ArrayList<Task>}.
     */
    public ArrayList<Task> getTaskArray() {
        return this.tasks;
    }

    /**
     * Returns the entire {@code archive}.
     *
     * @return The archive in {@code ArrayList<Task>}.
     */
    public ArrayList<Task> getArchiveArray() {
        return this.archive;
    }

    /**
     * Returns a list of pairs of {@code Task}s and their index in the original
     * list. This allows the user to see the list with new indices while being able
     * to delete or complete tasks using the original indices.
     *
     * @param searchWords Cleaned word(s) to search for in the tasks' descriptions.
     * @return An array of pairs of {@code Task}s and {@code Integer}s. Returns an
     *         empty array if no tasks meet the requirement.
     */
    public ArrayList<Pair<Task, Integer>> find(String searchWords) {
        assert searchWords.equals(cleanAndLowerString(searchWords));
        // Solution below adapted from https://stackoverflow.com/a/18552071
        return IntStream.range(0, this.tasks.size()).mapToObj(i -> new Pair<>(this.tasks.get(i), i))
                .filter(p -> p.getFirst().description.toLowerCase().contains(searchWords))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Sorts the task array, with {@code Deadline}s at the front, and earlier deadlines
     * at the front.
     */
    public void sort() {
        this.tasks.sort((a, b) -> {
            boolean isAActiveDeadline = a instanceof Deadline && !a.isCompleted;
            boolean isBActiveDeadline = b instanceof Deadline && !b.isCompleted;
            if (isAActiveDeadline && !isBActiveDeadline) {
                return -1;
            } else if (isBActiveDeadline && !isAActiveDeadline) {
                return 1;
            } else if (isAActiveDeadline) {
                return ((Deadline) a).getDeadline().compareTo(((Deadline) b).getDeadline());
            } else if (a.isCompleted && !b.isCompleted) {
                return 1;
            } else if (b.isCompleted && !a.isCompleted) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    /**
     * Archives completed tasks into the archive.
     *
     * @throws DuchessException No tasks to archive.
     */
    public void archive() throws DuchessException {
        long numOfCompletedTasks = this.tasks.stream().filter(x -> x.isCompleted).count();
        if (numOfCompletedTasks == 0) {
            throw new DuchessException(ERROR_NO_COMPLETED_TASKS);
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isCompleted) {
                Task taskToArchive = this.tasks.remove(i);
                this.taskDescriptions.remove(hashTaskToString(taskToArchive));
                this.archive.add(taskToArchive);
            }
        }
    }

    /**
     * Returns an immutable deep copy of the active tasks in {@code TaskList}.
     *
     * @return Immutable deep copy.
     */
    public List<Task> getImmutableDeepCopyOfTasks() {
        // @@author zhuhanming-reused
        // Reused from https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/
        // Point 3 with minor modifications
        ArrayList<Task> tasksClone = new ArrayList<>();
        for (Task task : tasks) {
            tasksClone.add((Task) task.clone());
        }
        // @@author
        return List.of(tasksClone.toArray(new Task[0]));
    }

    /**
     * Returns an immutable deep copy of the archived tasks in {@code TaskList}.
     *
     * @return Immutable deep copy.
     */
    public List<Task> getImmutableDeepCopyOfArchive() {
        // @@author zhuhanming-reused
        // Reused from https://howtodoinjava.com/java/collections/arraylist/arraylist-clone-deep-copy/
        // Point 3 with minor modifications
        ArrayList<Task> tasksClone = new ArrayList<>();
        for (Task task : archive) {
            tasksClone.add((Task) task.clone());
        }
        // @@author
        return List.of(tasksClone.toArray(new Task[0]));
    }

    /**
     * Replaces the existing array of tasks with a new one. This is only relevant when
     * undoing.
     *
     * @param taskList New task list to replace current task list.
     */
    public void replaceLists(ArrayList<Task> taskList, ArrayList<Task> archiveList) {
        this.tasks = taskList;
        this.archive = archiveList;
        this.taskDescriptions = new HashMap<>();
        for (Task task : this.tasks) {
            this.taskDescriptions.put(hashTaskToString(task), true);
        }
    }

    /**
     * Returns stats for tasks created, tasks completed, tasks completed on time.
     *
     * @param statsPeriod Period to check back.
     * @return An array of {@code String}s containing the above numbers.
     */
    public Integer[] getStats(TemporalAmount statsPeriod) {
        LocalDateTime startTime = LocalDate.now().atTime(0, 0).minus(statsPeriod);
        int numOfTasksCreated = 0;
        int numOfTasksCompleted = 0;
        int numOfTasksCompletedOnTime = 0;
        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.addAll(this.tasks);
        allTasks.addAll(this.archive);
        for (Task task : allTasks) {
            if (task.creationTime.isAfter(startTime)) {
                numOfTasksCreated += 1;
            }
            if (task.isCompleted) {
                if (task.completionTime.isAfter(startTime)) {
                    numOfTasksCompleted += 1;
                }
                if (task instanceof Deadline && ((Deadline) task).isCompletedOnTime) {
                    numOfTasksCompletedOnTime += 1;
                }
            }
        }
        return new Integer[]{numOfTasksCreated, numOfTasksCompleted, numOfTasksCompletedOnTime};
    }


    private String hashTaskToString(Task task) {
        String description = task.description.trim().toLowerCase();
        if (task instanceof ToDo) {
            return description;
        } else if (task instanceof Event) {
            String timeFrame = ((Event) task).getTimeFrame().trim().toLowerCase();
            return description + timeFrame;
        } else {
            assert task instanceof Deadline;
            String deadline = ((Deadline) task).getDeadline().toString();
            return description + deadline;
        }
    }
}
