package data.task;

public class EventTask extends Task{

    public EventTask() {
    }

    public EventTask(int taskIndex, String taskDescription, String taskStartTime) {
        super(taskIndex, taskDescription, 'E');
        this.taskTime = taskStartTime;
        //this.taskEndTime = taskEndTime;
    }

    public String getTaskStartTime() {
        return taskTime;
    }

    public String getTaskEndTime() {
        return taskTime;
    }
}
