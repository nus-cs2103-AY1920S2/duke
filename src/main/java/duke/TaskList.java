package duke;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

class TaskList {
    private List<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    TaskList add(Task task) {
        tasks.add(task);
        return this;
    }

    Task complete(int taskNumber) throws TaskNumberOutOfBoundsException {
        try {
            int taskIndex = taskNumber - 1;
            Task completedTask = tasks.get(taskIndex).complete();
            tasks.set(taskIndex, completedTask);
            return completedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
    }

    Task delete(int taskNumber) throws TaskNumberOutOfBoundsException {
        try {
            int taskIndex = taskNumber - 1;
            Task deletedTask = tasks.remove(taskIndex);
            return deletedTask;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNumberOutOfBoundsException(taskNumber);
        }
    }

    String list() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i > 0) {
                list.append("\n");
            }
            list.append((i + 1) + ". " + tasks.get(i));
        }
        return list.toString();
    }

    TaskList find(String searchTerm) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    int size() {
        return tasks.size();
    }

    List<Task> asList() {
        return new ArrayList<>(tasks);
    }
}
