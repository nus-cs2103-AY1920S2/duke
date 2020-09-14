/**
 * Represents the task statistics.
 */
public class Statistics {
    private int numberOfTasks;
    private int numberOfEvents;
    private int numberOfDeadlines;
    private int numberOfTodos;
    private int numberOfCompletedTasks;

    /**
     * Creates a Statistics object.
     * Intially all statistics are set as zero.
     */
    public Statistics() {
        this.numberOfTasks = 0;
        this.numberOfEvents = 0;
        this.numberOfDeadlines = 0;
        this.numberOfTodos = 0;
        this.numberOfCompletedTasks = 0;
    }

    /**
     * Records a new event.
     */
    public void eventIncrement() {
        numberOfTasks++;
        numberOfEvents++;
    }

    /**
     * Records a new deadline.
     */
    public void deadlineIncrement() {
        numberOfTasks++;
        numberOfDeadlines++;
    }

    /**
     * Records a new todo.
     */
    public void todoIncrement() {
        numberOfTasks++;
        numberOfTodos++;
    }

    /**
     * Records a new completed task.
     */
    public void completeTask() {
        numberOfCompletedTasks++;
    }

    /**
     * Get the total number of tasks.
     * @return numberofTasks Self=explained.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Get the total number of events.
     * @return numberofTasks Self=explained.
     */
    public int getNumberOfEvents() {
        return numberOfEvents;
    }

    /**
     * Get the total number of todos.
     * @return numberofTodos Self=explained.
     */
    public int getNumberOfTodos() {
        return numberOfTodos;
    }

    /**
     * Get the total number of deadlines.
     * @return numberofDeadlines Self=explained.
     */
    public int getNumberOfDeadlines() {
        return numberOfDeadlines;
    }

    /**
     * Get the total number of completed tasks.
     * @return numberofCompletedTasks Self=explained.
     */
    public int getNumberOfCompletedTasks() {
        return numberOfCompletedTasks;
    }
}