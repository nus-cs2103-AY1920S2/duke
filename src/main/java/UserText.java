import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class UserText {
    private String filePath;
    private List<Task> allTasks;

    public UserText(String filePath) {
        this.filePath = filePath;
        allTasks = new ArrayList<>();
        readText();
    }

    public void addInput(Task s) {
        this.allTasks.add(s);
        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
    }

    public void saveTasks() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task: allTasks) {
                if (task instanceof ToDos) {    // to test if the object is an instant of the ToDos, returns true/false
                    writer.write("T");
                } else if (task instanceof Deadlines) {
                    writer.write("D");
                } else if (task instanceof Events) {
                    writer.write("E");
                }
                writer.write("/divide" + task.isDone() + "/divide" + task.getDescription());
                if (task instanceof Deadlines) {
                    writer.write("/divide" + ((Deadlines) task).getDeadline());
                } else if (task instanceof Events) {
                    writer.write("/divide" + ((Events) task).getVenue());
                }
                writer.write("\n");
            }
        } catch (IOException ioException) {

        }
    }

    public void readText() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader(filePath));
            String s;
            while ((s = bufferedreader.readLine()) != null) {
                String[] taskParams = s.split("/divide");
                Task task = Task.createStartingTask(taskParams);
                allTasks.add(task);
            }
        } catch (IOException ioException) {

        }
    }

    public void printTasks() {
        int count = 1;
        System.out.println("Here is your list");
        for (Task s : allTasks) {
            System.out.println(count + ". " + s);
            count++;
        }
    }

    public Task getTask(int n) {
        return this.allTasks.get(n-1);
    }

    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    public void markDone(int taskNo) {
        allTasks.get(taskNo - 1).markAsDone();
        System.out.println("Nice! I marked this task as done");
    }

    public void removeTask(int taskNo) {
        Task tempTask = allTasks.remove(taskNo - 1);
        System.out.println("Noted. I have removed this task");
        System.out.println(" " + tempTask);
        System.out.println("Now you have " + allTasks.size() + " tasks in the list.");
    }
}
