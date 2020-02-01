public interface Task {

    public boolean isDone();

    public String getTaskName();

    public String getTaskType();

    public String getDoneString();

    public void markAsDone();

    public String getTaskTime();
}
