import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Storage {

    private Path path;

    public Storage(Path path) {
        this.path = path;
    }

    public void loadTasks(TaskList taskList) {

        try {
            File file = new File(path.toString());
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split("/");
                switch (task[0]) {
                    case "T":
                        taskList.add(new Todo(task[2]));
                        break;
                    case "D":
                        taskList.add(new Deadline(task[2], LocalDate.parse(task[3])));
                        break;
                    case "E":
                        taskList.add(new Event(task[2], LocalDate.parse(task[3]), task[4], task[5]));
                        break;
                }
                if (task[1].equals("true")) {
                    try {
                        taskList.get(taskList.getSize() - 1).markAsDone();
                    } catch (DuplicateMarkAelitaException e) {
                        //Not possible. Do nothing
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //Do nothing
        } catch (DateTimeParseException e) {
            System.out.println("Cannot parse date");
        }
    }

    public void saveTasks(TaskList taskList) throws IOAelitaException {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            FileWriter writer = new FileWriter(path.toString(), false);
            for (int i = 0; i < taskList.getSize(); i++) {

                if (taskList.get(i) instanceof Todo) {
                    writer.write("T/" + taskList.get(i).isDone + "/" + taskList.get(i).description + "\n");
                } else if (taskList.get(i) instanceof Deadline) {
                    writer.write("D/" + taskList.get(i).isDone + "/" + taskList.get(i).description + "/" + ((Deadline) taskList.get(i)).by + "\n");
                } else if (taskList.get(i) instanceof Event) {
                    writer.write("E/" + taskList.get(i).isDone + "/" + taskList.get(i).description + "/" + ((Event) taskList.get(i)).date + "/" + ((Event) taskList.get(i)).startTime +
                            "/" + ((Event) taskList.get(i)).endTime + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            throw new IOAelitaException();
        }
    }

}
