import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    protected TaskList(List<String> data) {
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

    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    protected Task removeTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        this.taskList.remove(taskNumber - 1);
        return task;
    }

    protected Task completeTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        task.completeTask();
        return task;
    }

    protected int getSize() {
        return this.taskList.size();
    }

    protected String getTaskList() {
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

    protected String getTaskListSaveFormat() {
        String data = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task item = this.taskList.get(i);
            data += item.getSaveFormat() + "\n";
        }
        return data;
    }

}
