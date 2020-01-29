import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
                 String[] args = line.split("[\\[\\]]");
                 String code = args[0];
                 boolean isDone = args[1].equals("Y");
                 String[] taskArgs = Arrays.copyOfRange(args, 2, args.length);
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

    public Task buildTask(String code, String[] args, boolean isDone) {
        if (code.equals("T")) {
            // args like {"borrow, "book}
            Todo todo = new Todo(String.join(" ", args), isDone);
            return todo;
        } else if (code.equals("D")) {
            // deadlineArgs like {"homework", "2/12/2019 1800"}
            String[] deadlineArgs = String.join(" ", args).split(" /by ");
            Deadline deadline = new Deadline(deadlineArgs[0].strip(), deadlineArgs[1].strip(), isDone);
            return deadline;
        } else if (code.equals("E")) {
            // eventArgs like {"attend festival", "2pm-4pm"}
            String[] eventArgs = String.join(" ", args).split(" /at ");
            Event event = new Event(eventArgs[0].strip(), eventArgs[1].strip(), isDone);
            return event;
        } else {
            throw new DukeIOException("Cannot understand the type of task.");
        }
    }
}