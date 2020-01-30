package bot.loadsave;

import bot.task.Deadline;
import bot.task.Event;
import bot.task.Task;
import bot.task.Todo;

import bot.util.PrettyTime;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 * A class that handles saving and
 * loading of Tasks from the disk
 */
public class TasksToDisk extends LoadAndSave<Task> {
    /**
     * Constructor for a new TasksToDisk for the
     * given file directory and name
     *
     * @param fd Location of the file on the system
     * @param fn Name of the file in the directory
     *
     * @throws FileNotFoundException When file directory
     * or file name is invalid
     */
    public TasksToDisk(String fd, String fn) throws FileNotFoundException {
        super(fd, fn);
    }

    /**
     * Loads the stored tasks from the given
     * file directory and name
     *
     * @return ArrayList containing the stored tasks
     */
    @Override
    public ArrayList<Task> loadStored() {
        ArrayList<Task> storedTasks = new ArrayList<Task>();
        Scanner io = new Scanner(super.getToLoadFrom());
        while (io.hasNext()) {
            // main loop to load each saved task
            String typeAndDone = io.nextLine();
            Task currentTask;
            boolean isCompleted;
            if (typeAndDone.startsWith(Deadline.TYPE)) {
                currentTask = new Deadline(
                        io.nextLine(),
                        new PrettyTime(io.nextLine())
                );
                isCompleted = Integer.parseInt(
                        Character.toString(
                                typeAndDone.charAt(
                                        Deadline.TYPE.length())
                        )
                ) == 1;
            } else if (typeAndDone.startsWith(Event.TYPE)) {
                currentTask = new Event(
                        io.nextLine(),
                        new PrettyTime(io.nextLine())
                );
                isCompleted = Integer.parseInt(
                        Character.toString(
                                typeAndDone.charAt(
                                        Event.TYPE.length())
                        )
                ) == 1;
            } else if (typeAndDone.startsWith(Todo.TYPE)) {
                currentTask = Todo.makeTodoRaw(io.nextLine());
                io.nextLine();
                isCompleted = Integer.parseInt(
                        Character.toString(
                                typeAndDone.charAt(
                                        Todo.TYPE.length())
                        )
                ) == 1;
            } else {
                // unknown task
                // System.out.println("Unknown task found!");
                continue;
            }

            if (isCompleted) {
                currentTask.markAsDone();
            }

            storedTasks.add(currentTask);
        }
        return storedTasks;
    }

    /**
     * Saves a given Collection of Tasks to the
     * given file directory and name (overwriting
     * the previous content!)
     *
     * @param tasksList The Tasks to be saved
     */
    @Override
    public void saveToDisk(Collection<Task> tasksList) {
        StringBuilder toBeSaved = new StringBuilder();
        for (Task task : tasksList) {
            // use line breaks to separate the tasks
            toBeSaved.append(task.type())
                    .append(task.isDone() ? "1" : "0")
                    .append("\n")
                    .append(task.getTaskDetails())
                    .append("\n")
                    .append(task.getPrettyTime().toRaw())
                    .append("\n");
        }

        File saveLocation = new File(super.getRelativeAddress());
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(saveLocation)
            );
            writer.write(toBeSaved.toString());
            writer.close();
        } catch (IOException e) {
            // error in writing to file
            System.err.println("IOException2");
            System.err.println(e.getMessage());
        }
    }
}
