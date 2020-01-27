import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class TaskManagement {

    ArrayList<Task> taskList;
    String path;

    public TaskManagement(String path) {
        this.taskList = new ArrayList<Task>();
        this.path = path;
    }


    public void list() {

        String start = "\nHere are the tasks in your list:";
        System.out.println(start);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }

    }

    public Task addTask(String taskDescription, LocalDateTime date, Task.Types type) throws IOException {

        System.out.println("Got it. I've added this task:");
        Task task = null;
        switch (type) {
            case ToDo:
                ToDo task1 = new ToDo(date, taskDescription);
                task = task1;
                break;
            case Deadline:
                Deadline task2 = new Deadline(date, taskDescription);
                task = task2;
                break;
            case Event:
                Event task3 = new Event(date, taskDescription);
                task = task3;
                break;
            default:
                System.out.println("Task type does'nt exist");
                break;
        }

        taskList.add(task);

        this.updateFile("add");

        return task;
    }


    public Task addTask(String taskDescription, LocalDateTime date, Task.Types type, Task.status status) throws IOException {

        Task task = null;
        switch (type) {
            case ToDo:
                ToDo task1 = new ToDo(date, taskDescription);
                task = task1;
                break;
            case Deadline:
                Deadline task2 = new Deadline(date, taskDescription);
                task = task2;
                break;
            case Event:
                Event task3 = new Event(date, taskDescription);
                task = task3;
                break;
            default:
                System.out.println("Task type does'nt exist");
                break;
        }

        if (status.equals(status.Y)) {

           task.changeStatus(status.Y);

        }


        taskList.add(task);

        return task;
    }

    public Task deleteTask(int index) throws IOException {

        Task task = taskList.remove(index - 1);
        this.updateFile("delete");
        System.out.println("Noted. I've removed this task:");
        return task;

    }

    public String reportTotal() {

        return "Now you have " + taskList.size() + " tasks in the list";

    }

    public Task getTask(int index) {

        return taskList.get(index - 1);

    }

    public Task markDone(Task task) throws IOException {

        task.changeStatus(Task.status.Y);
        this.updateFile("done");
        String action = "Nice! I've marked this task as done:";
        System.out.println(action);
        return task;

    }

    public void loadFile() {

        try {
            File f = new File (path);
            f.createNewFile();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String content = null;
            String[] contentArr = null;
            String type;
            String status;

            while (!((content = br.readLine()) == null)) {

                contentArr = content.split("//");
                type = contentArr[0];
                status = contentArr[1];

                switch (type) {

                    case "ToDo":

                        if (status.equals("Y")) {

                            addTask(contentArr[2], null , Task.Types.ToDo, Task.status.Y);

                        } else {

                            addTask(contentArr[2], null , Task.Types.ToDo, Task.status.N);

                        }
                        break;

                    case "Deadline":
                        if (status.equals("Y")) {

                            addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Deadline, Task.status.Y);

                        } else {

                            addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Deadline, Task.status.N);

                        }
                        break;

                    case "Event":
                        if (status.equals("Y")) {

                            addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Event, Task.status.Y);


                        } else {

                            addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Event, Task.status.N);

                        }
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void updateFile(String operation) throws IOException {

        FileWriter writer = new FileWriter(new File(path));
        String input = null;


        for (int i = 0; i < taskList.size(); i++) {

            input = generateInput(taskList.get(i));
            writer.write(input);
            writer.flush();

        }
        writer.close();
    }

    public String generateInput(Task task) {

        String type = task.getType().toString();
        String status = task.getStatus().toString();
        String taskDescription = task.getTaskDescription();
        LocalDateTime dateTime = task.getDateTime();

        return type + "//" + status + "//" + taskDescription + "//" + dateTime + "\n";

    }


}



