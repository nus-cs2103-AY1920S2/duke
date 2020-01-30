import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private ArrayList<Item> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * Loads the tasks given a path to the save file.
     * @param path The path of the save file.
     * @return The Tasklist object with tasks loaded.
     */
    static TaskList load(String path) {
        File file = new File(path);
        TaskList tasks = new TaskList();

        try {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String[] details = scanner.nextLine().split(" \\| ");
                boolean isDone = !details[1].equals("0");
                String description = details[2];

                switch (details[0]) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(description, details[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(description, details[3], isDone));
                    break;
                default:
                    continue;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            // do nothing
        }

        return tasks;
    }

    /**
     * Saves the tasks to the given file path.
     * @param path The path of the save file.
     */
    public void save(String path) {
        File file = new File(path);

        // remove existing file
        if (file.exists()) {
            file.delete();
        }

        // create directory parent directory of file if it does not exist
        new File(file.getParent()).mkdirs();

        try {
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(toSaveFormat());
            writer.close();
        } catch (IOException e) {
            // unable to create file
        }
    }

    /**
     * Add a new item to the list.
     * @param item The item to be added.
     */
    public void add(Item item) {
        items.add(item);
    }

    /**
     * Get an item from the list by index.
     * @param index The index of the item in the list.
     * @return The item specified by the index.
     */
    public Item getItem(int index) {
        return items.get(index - 1);
    }

    /**
     * Obtain the number of items in the list.
     * @return The length of the list.
     */
    public int getLength() {
        return items.size();
    }

    /**
     * Mark an item in the list as done.
     * @param index The index of the item in the list.
     */
    public void markDone(int index) {
        items.get(index - 1).markDone();
    }

    /**
     * Delete an item in the list.
     * @param index The index of the item in the list.
     */
    public void deleteItem(int index) {
        items.remove(index - 1);
    }

    /**
     * Formats the task list to be saved.
     * @return The tasks in save format.
     */
    public String toSaveFormat() {
        String output = "";
        for (Item item : items) {
            output += item.toSaveFormat() + "\n";
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "----------\n";
        for (int i = 0; i < items.size(); i++) {
            output += String.format("%d.%s\n", i + 1, items.get(i));
        }
        output += "----------";
        return output;
    }
}