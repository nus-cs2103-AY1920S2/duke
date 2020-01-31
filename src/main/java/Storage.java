import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import DukeException.DukeIOException;

public class Storage {
    Path file;

    public Storage(String filePath) {
        file = java.nio.file.Paths.get(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                 String line = sc.nextLine();
                 String code = line.substring(1, 2);
                 boolean isDone = line.substring(4, 5).equals("Y");
                 String taskArgs = line.substring(7);
                 Task task = buildTask(code, taskArgs, isDone);
                 listOfTasks.add(task);
            }
            return listOfTasks;
        } catch (IOException e) {
            throw new DukeIOException("File does not exist in file path, load from file failed.");
        }
    }

    public void updateFile(TaskList tasks, int numOfTasks) {
        ArrayList<String> tasksForFile = new ArrayList<>();
        for (int i = 0; i < numOfTasks; i++) {
            tasksForFile.add(tasks.getTask(i) + "\n");
        }
        try {
            Files.write(file, tasksForFile);
        } catch (IOException e) {
            throw new DukeIOException("File does not exist in file path, file update failed.");
        }
    }

    public Task buildTask(String code, String args, boolean isDone) {
        if (code.equals("T")) {
            // args lik "borrow book"
            Todo todo = new Todo(args, isDone);
            return todo;
        } else if (code.equals("D")) {
            // deadlineArgs like "homework (by: 22/12/2019 1800)"
            String[] deadlineArgs = args.split("[ ][//(][b][y][//:][ ]");
            String description = deadlineArgs[0];
            String byWhen = deadlineArgs[1].substring(0, deadlineArgs[1].length());
            Deadline deadline = new Deadline(description, byWhen, isDone);
            return deadline;
        } else if (code.equals("E")) {
            // eventArgs like {"attend festival", "2pm-4pm"}
            String[] eventArgs = args.split("[ ][//(][a][t][//:][ ]");
            String description = eventArgs[0];
            String atWhen = eventArgs[1].substring(0, eventArgs[1].length());
            Event event = new Event(description, atWhen, isDone);
            return event;
        } else {
            throw new DukeIOException("Cannot understand the type of task.");
        }
    }
}