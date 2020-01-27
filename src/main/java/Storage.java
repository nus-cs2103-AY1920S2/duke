import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {

    String path;
    TaskList manager;

    public Storage(String path) {

        this.path = path;

    }

    public void addManager(TaskList manager) {

        this.manager = manager;

    }

    public void loadFile() {

        try {
            File f = new File(path);
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

                            manager.addTask(contentArr[2], null, Task.Types.ToDo, Task.status.Y);

                        } else {

                            manager.addTask(contentArr[2], null, Task.Types.ToDo, Task.status.N);

                        }
                        break;

                    case "Deadline":
                        if (status.equals("Y")) {

                            manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Deadline, Task.status.Y);

                        } else {

                            manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Deadline, Task.status.N);

                        }
                        break;

                    case "Event":
                        if (status.equals("Y")) {

                            manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Event, Task.status.Y);


                        } else {

                            manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]), Task.Types.Event, Task.status.N);

                        }
                        break;
                }

            }
        } catch (Exception e) {

            System.out.println(e);

        }
    }


    public void updateFile(String operation) throws IOException {

        ArrayList<Task> taskList = manager.getList();

        FileWriter writer = new FileWriter(new File(path));
        String input = null;

        for (int i = 0; i < taskList.size(); i++) {

            input = generateInput((Task) taskList.get(i));
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
