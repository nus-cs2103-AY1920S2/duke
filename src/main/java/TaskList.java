import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(List<String> data) {
        this.taskList = new ArrayList<>(100);
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            // Parse saved data per line
            String[] parsedLine = line.split("_");
            switch (parsedLine[0]) {
                case "T":
                    taskList.add(new ToDo(parsedLine[2], Boolean.parseBoolean(parsedLine[1])));
                    break;
                case "D":
                    taskList.add(new Deadline(parsedLine[2], Boolean.parseBoolean(parsedLine[1]), parsedLine[3]));
                    break;
                case "E":
                    taskList.add(new Event(parsedLine[2], Boolean.parseBoolean(parsedLine[1]), parsedLine[3]));
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
