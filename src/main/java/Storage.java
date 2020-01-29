import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to handle stored items within the bot,
 * and mark them as done. Class Storage also
 * handles file writing and reading
 */
public class Storage {
    private final ArrayList<Task> storedTasks;
    private final String fileDirectory;

    /**
     * Constructs a new Storage, loading saved items
     * from the local file system if possible. If not,
     * an empty Storage is created.
     *
     * @param fd String representing file directory to
     *           load from and store to
     */
    public Storage(String fd) {
        this.fileDirectory = fd;
        this.storedTasks = new ArrayList<Task>();
        FileReader toLoadFrom;
        boolean hasReadFile = false;
        try {
            toLoadFrom = new FileReader(this.fileDirectory);
            hasReadFile = true;
        } catch (FileNotFoundException e) {
            // could not find file in location specified
            // create new empty store
            System.out.println("Could not find local storage");
            toLoadFrom = null;
        }

        if (hasReadFile) {
            Scanner io = new Scanner(toLoadFrom);
            while (io.hasNext()) {
                // main loop to load each saved task
                String typeAndDone = io.nextLine();
                Task currentTask = null;
                boolean isCompleted;
                if (typeAndDone.startsWith(Deadline.TYPE)) {
                    currentTask = new Deadline(io.nextLine(), io.nextLine());
                    isCompleted = typeAndDone.charAt(Deadline.TYPE.length()) == 1;
                } else if (typeAndDone.startsWith(Event.TYPE)) {
                    currentTask = new Event(io.nextLine(), io.nextLine());
                    isCompleted = typeAndDone.charAt(Event.TYPE.length()) == 1;
                } else if (typeAndDone.startsWith(Todo.TYPE)) {
                    currentTask = Todo.makeTodoRaw(io.nextLine());
                    io.nextLine();
                    isCompleted = typeAndDone.charAt(Todo.TYPE.length()) == 1;
                } else {
                    // unknown task
                    System.out.println("Unknown task found!");
                    continue;
                }

                if (isCompleted) {
                    currentTask.markAsDone();
                }

                this.storedTasks.add(currentTask);
            }
        }
    }

    /**
     * Adds a String to the stored items
     *
     * @param toStore The Task to be stored
     */
    public void store(Task toStore) {
        this.storedTasks.add(toStore);
    }

    /**
     * Prints out all the stored items,
     * in order which they were stored
     */
    public void printStorage() {
        int length = this.storedTasks.size();
        for (int i = 0; i < length; i++) {
            System.out.println(retrieve(i + 1));
        }
    }

    /**
     * Marks a specific item on the list
     * as "done"
     *
     * @param index The index of the item,
     *              as it appears in the list
     */
    public void markAsDone(int index) {
        this.storedTasks.get(index - 1).markAsDone();
    }

    /**
     * Retrieves an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it
     *          appears in the list
     * @return A String representing the
     *         item, its position on the list,
     *         and its "done" status
     */
    public String retrieve(int i) {
        return i + ". " + this.storedTasks.get(i - 1);
    }

    /**
     * Gets the number of Tasks currently logged.
     *
     * @return int representing number of Tasks.
     */
    public int getNumTasks() {
        return this.storedTasks.size();
    }

    /**
     * Removes an entry from the stored
     * items (at index i)
     *
     * @param i Index of the item as it appears
     *          in the list
     */
    public void delete(int i) {
        this.storedTasks.remove(i - 1);
    }

    /**
     * Attempts to save the stored items
     * to a file on the local system
     */
    public void saveToDisk() {
        String toBeSaved = "";
        for (Task task : storedTasks) {
            // use line breaks to separate the tasks
            toBeSaved = toBeSaved + task.type()
                    + (task.isDone() ? "1" : "0")
                    +"\n" + task.getTaskDetails() + "\n"
                    + task.getTaskTime() + "\n";
        }

        File saveLocation = new File(this.fileDirectory);
        if (!saveLocation.exists()) {
            try {
                saveLocation.createNewFile();
            } catch (IOException e) {
                // error in creating new file
                System.err.println("IOException1");
                System.err.println(e.getMessage());
            }
        }

        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(this.fileDirectory)
            );
            writer.write(toBeSaved);
            writer.close();
        } catch (IOException e) {
            // error in writing to file
            System.err.println("IOException2");
            System.err.println(e.getMessage());
        }
    }
}
