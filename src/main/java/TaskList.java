import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        tasks = listOfTasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }

    public Task markDone(int taskNum) {
        if (taskNum > tasks.size()) {
            throw new DukeException("Task for this number doesn't exist.");
        }
        int index = taskNum - 1;
        Task currTask = tasks.get(index);
        currTask.setDone();
        tasks.set(index, currTask);
        return currTask;
    }

    public Task addTodo(String todo) {
        Task todoTask = new Todo(todo);
        tasks.add(todoTask);
        return todoTask;
    }

    public Task addDeadline(String deadline, String byWhen) {
        Task deadlineTask = new Deadline(deadline, byWhen);
        tasks.add(deadlineTask);
        return deadlineTask;
    }

    public Task addEvent(String event, String atWhen) {
        Task eventTask = new Event(event, atWhen);
        tasks.add(eventTask);
        return eventTask;
    }

    public Task delete(int taskNum) {
        int index = taskNum - 1;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    public void saveTask() {
        Path file = java.nio.file.Paths.get("/Users/Min Suk/IdeaProjects/duke/data/duke.txt");
        ArrayList<String> tasksForFile = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            tasksForFile.add(tasks.get(i) + "\n");
        }
        try {
            Files.write(file, tasksForFile);
        } catch (IOException e) {
            System.out.println("duke.txt file doesn't exist, please create one in /duke/data");
        }
    }
}
