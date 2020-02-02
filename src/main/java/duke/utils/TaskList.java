package duke.utils;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() { }

    public List<Task> getList() {
        return tasks;
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "No scheduled task yet";
        }
        String list = "1." + tasks.get(0);
        for (int i = 2; i < tasks.size() + 1; i++) {
            list = list + "\n" + i + "." + tasks.get(i - 1);
        }
        return list;
    }

    public int size() {
        return tasks.size();
    }

    public void markTaskAsDone(int taskId) {
        tasks.get(taskId).markAsDone();
    }

    public void removeTask(int taskId) {
        tasks.remove(taskId);
    }

    public boolean addToList(String item, String type) throws ParseException {
        Task newTask;
        if (type.equals("todo")) {
            newTask = new Todos(item);
        } else if (type.equals("deadline")) {
            String[] tokens = item.split("/", 2);
            if (tokens.length < 2) {
                return false;
            }
            if (!tokens[1].substring(0, 2).equals("by")) {
                return false;
            }
            String time = tokens[1].substring(2).trim();
            if (!Parser.checkDateFormat(time)) {
                return false;
            }
            newTask = new Deadlines(tokens[0].trim(), Parser.stringToDate(time));
        } else {
            String[] tokens = item.split("/", 2);
            if (tokens.length < 2) {
                return false;
            }
            if (!tokens[1].substring(0, 2).equals("at")) {
                return false;
            }
            String time = tokens[1].substring(2).trim();
            if (!Parser.checkDateFormat(time)) {
                return false;
            }
            newTask = new Events(tokens[0].trim(), Parser.stringToDate(time));
        }
        tasks.add(newTask);
        return true;
    }
}
