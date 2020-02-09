package Server;

import task.Task;

import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

public class ReminderTask extends TimerTask
{
    private Task task;
    private HashMap<String, Timer> taskTimerMapping;

    public ReminderTask(HashMap<String, Timer> taskTimerMapping, Task task) {
        this.task = task;
        this.taskTimerMapping = taskTimerMapping;
    }

    public void run()
    {
        taskTimerMapping.remove(this.task.getDescription());
        System.out.println("REMIND " + this.task.getDescription());
    }
}