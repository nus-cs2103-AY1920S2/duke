import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TasksList {
    protected ArrayList<Task> tasks = new ArrayList<>();

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void markDone(String done) {
        if (done.length() <= 5) {
            throw new DukeMissingDescriptionException();
        }
        String num = done.substring(5);
        try {
            Integer.valueOf(num);
        } catch(NumberFormatException e) {
            throw new DukeUnknownInputException();
        }
        int index = Integer.valueOf(num) - 1;
        Task currTask = tasks.get(index);
        currTask.markDone();
        tasks.set(index, currTask);
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n" + tasks.get(index));
        System.out.println("____________________________________________________________");
        saveTask();
    }

    public void addTodo(String todo) {
        if (todo.length() <= 5) {
            throw new DukeMissingDescriptionException();
        }
        tasks.add(new Todo(todo.substring(5)));
        int taskNum = tasks.size();
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + tasks.get(taskNum-1)
                + "\nNow you have " + taskNum + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTask();
    }

    public void addDeadline(String deadline) {
        if (deadline.length() <= 9) {
            throw new DukeMissingDescriptionException();
        }
        String[] splitted = deadline.substring(9).split(" /by ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException();
        }
        tasks.add(new Deadline(splitted[0], splitted[1]));
        int taskNum = tasks.size();
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + tasks.get(taskNum-1)
                + "\nNow you have " + taskNum + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTask();
    }

    public void addEvent(String event) {
        if (event.length() <= 6) {
            throw new DukeMissingDescriptionException();
        }
        String[] splitted = event.substring(6).split(" /at ", 2);
        if (splitted.length < 2) {
            throw new DukeUnknownInputException();
        }
        tasks.add(new Event(splitted[0], splitted[1]));
        int taskNum = tasks.size();
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:\n" + tasks.get(taskNum-1)
                + "\nNow you have " + taskNum + " tasks in the list.");
        System.out.println("____________________________________________________________");
        saveTask();
    }

    public void list() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + "." + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void delete(String delete) {
        if (delete.length() <= 7) {
            throw new DukeMissingDescriptionException();
        }
        String num = delete.substring(7);
        try {
            Integer.valueOf(num);
        } catch(NumberFormatException e) {
            throw new DukeUnknownInputException();
        }
        int index = Integer.valueOf(num) - 1;
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:\n" + tasks.get(index)
                +  "\nNow you have " + (tasks.size()-1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
        tasks.remove(index);
        saveTask();
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
