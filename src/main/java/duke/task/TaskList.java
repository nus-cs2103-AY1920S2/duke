package duke.task;

import java.util.List;
import java.util.ArrayList;

/**
 * Task list to manage tasks created by users.
 */
public class TaskList {

    /**
     * ArrayList as the underlying implementation to store tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for task list.
     *
     * @param data List of tasks loaded from saved text file.
     */
    public TaskList(List<String> data) {
        this.taskList = new ArrayList<>(100);
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            String[] parsedLine = line.split("_");
            String taskType = parsedLine[0];
            String taskName = parsedLine[2];
            boolean isCompleted = Boolean.parseBoolean(parsedLine[1]);
            switch (taskType) {
                case "T":
                    taskList.add(new ToDo(taskName, isCompleted));
                    break;
                case "D":
                    String deadlineDateTime = parsedLine[3];
                    taskList.add(new Deadline(taskName, isCompleted, deadlineDateTime));
                    break;
                case "E":
                    String eventDateTime = parsedLine[3];
                    taskList.add(new Event(taskName, isCompleted, eventDateTime));
                    break;
                default:
            }
        }
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task removeTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        this.taskList.remove(taskNumber - 1);
        return task;
    }

    public Task completeTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        task.completeTask();
        return task;
    }

    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Retrieve task list in numbered order.
     * @return The task list appropriate string format.
     */
    public String getTaskList() {
        String list = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            String count = (i + 1) + "";
            String task = this.taskList.get(i).toString();
            list += count + ". " + task;
            if (i != this.taskList.size() - 1) {
                list += "\n";
            }
        }
        return list;
    }

    /**
     * Format task list to be saved into text file.
     * @return The task list in appropriate string format for saving.
     */
    public String getTaskListSaveFormat() {
        String data = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task item = this.taskList.get(i);
            data += item.getSaveFormat() + "\n";
        }
        return data;
    }

}
