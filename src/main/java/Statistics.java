public class Statistics {
    private int numberOfTasks;
    private int numberOfEvents;
    private int numberOfDeadlines;
    private int numberOfTodos;
    private int numberOfCompletedTasks;

    public Statistics() {
        this.numberOfTasks = 0;
        this.numberOfEvents = 0;
        this.numberOfDeadlines = 0;
        this.numberOfTodos = 0;
        this.numberOfCompletedTasks = 0;
    }

    public void eventIncrement() {
        numberOfTasks++;
        numberOfEvents++;
    }

    public void deadlineIncrement() {
        numberOfTasks++;
        numberOfDeadlines++;
    }

    public void todoIncrement() {
        numberOfTasks++;
        numberOfTodos++;
    }

    public void completeTask() {
        numberOfCompletedTasks++;
    }

    @Override
    public String toString() {
        double progress = (double) numberOfCompletedTasks / (double) numberOfTasks;
        return "You have " + numberOfTasks + " tasks in total, including " + numberOfEvents + " events, "
                    + numberOfDeadlines + " deadlines, and " + numberOfTodos " todos."
                    + "You have completed " + numberOfCompletedTasks + " tasks. "
                    + (progress * 100) + "% progress has been made!"
    }

}