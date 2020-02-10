package main;

import exception.DuplicateMarkAelitaException;
import exception.IoAelitaException;

import task.Deadline;
import task.Event;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * A module to store and load tasks to and from a file given a path name.
 */
public class Storage {

    private Path path;

    /**
     * Constructs a new instance of main.Storage.
     *
     * @param path the file path string.
     */
    public Storage(Path path) {

        this.path = path;
    }

    /**
     * Loads tasks from the associated file and populate the supplied main.TaskList.
     *
     * @param taskList the task list.
     */
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

                default:
                    //Do nothing.

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

    /**
     * Save tasks on the supplied main.TaskList into the associated file.
     *
     * @param taskList the supplied main.TaskList.
     * @throws IoAelitaException if FileWriter cannot be created.
     */
    public void saveTasks(TaskList taskList) throws IoAelitaException {

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            FileWriter writer = new FileWriter(path.toString(), false);

            for (int i = 0; i < taskList.getSize(); i++) {

                if (taskList.get(i) instanceof Todo) {
                    writer.write("T/" + taskList.get(i).isDone() + "/" + taskList.get(i).getDescription() +
                            "\n");

                } else if (taskList.get(i) instanceof Deadline) {
                    writer.write("D/" + taskList.get(i).isDone() + "/" + taskList.get(i).getDescription() + "/"

                            + ((Deadline) taskList.get(i)).getDate() + "\n");

                } else if (taskList.get(i) instanceof Event) {
                    writer.write("E/" + taskList.get(i).isDone() + "/" + taskList.get(i).getDescription() + "/"
                            + ((Event) taskList.get(i)).getDate() + "/" + ((Event) taskList.get(i)).getStartTime()
                            + "/" + ((Event) taskList.get(i)).getEndTime() + "\n");

                }
            }
            writer.close();

        } catch (IOException e) {
            throw new IoAelitaException();
        }
    }

}
