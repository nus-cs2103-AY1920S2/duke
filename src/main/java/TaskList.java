import java.util.ArrayList;

public class TaskList {

    private ArrayList<Item> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    /**
     * Add a new item to the list.
     * @param message The message of the item to be added.
     */
    public void add(String message) {
        items.add(new Item(message));
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
     * Mark an item in the list as done.
     * @param index The index of the item in the list.
     */
    public void markDone(int index) {
        items.get(index - 1).markDone();
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