package duke.task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String workingDir = System.getProperty("user.dir");
    private Path savePath = Paths.get(workingDir, "data", "duke.txt");

    public Storage(Path savePath) {
        this.savePath = savePath;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        String saveString = "";
        for (Task task : tasks) {
            saveString += task.getSaveRepresentation();
        }
        Files.write(savePath, saveString.getBytes());
    }

    public List<Task> loadTasks() throws IOException {
        //new task array list to store the loaded tasks
        List<Task> tasks = new ArrayList<>();

        //get the tasks from the save file and add to list
        Files.createDirectories(savePath.getParent());
        if (Files.exists(savePath)) {
            List<String> savedList = Files.readAllLines(savePath);
            for (String task : savedList) {
                String[] taskBuilder = task.split("\\|\\|\\|");
                String type = taskBuilder[0];
                boolean isDone = Boolean.parseBoolean(taskBuilder[1]);
                if (type.equals("T")) {
                    tasks.add(new Todo(taskBuilder[2], isDone));
                } else if (type.equals("D")) {
                    tasks.add(new Deadline(taskBuilder[2], isDone, taskBuilder[3]));
                } else if (type.equals("E")) {
                    tasks.add(new Event(taskBuilder[2], isDone, taskBuilder[3]));
                } else {
                    continue;
                }
            }
        } else {
            Files.createFile(savePath);
        }

        //return the task list
        return tasks;
    }
}
