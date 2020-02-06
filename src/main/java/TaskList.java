import java.util.ArrayList;
/**
 * This class contains a list of todo items.
 */

public class TaskList {
    ArrayList<Item> items = new ArrayList<Item>();
    Ui ui = new Ui();
    int count;

    public TaskList() {
        this.count = 0;
    }

    /**
     * This method add a new item to the list.
     */
    public void addItem(Item item) {
        this.items.add(item);
        this.count++;
    }

    /**
     * This method delete an item from the list based on its index.
     */
    public TaskList delete(int index) {
        count--;
        this.items.remove(index);
        return this;
    }

    /**
     * Returns the string of the search result.
     */
    public String search(String task) {
        String temp = "";
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).toString().toLowerCase().contains(task.toLowerCase())) {
                temp += ui.space + items.get(i);
            }
        }
        return temp;
    }

    @Override
    public String toString() {
        if (count < 1) {
            return "No current task in the list";
        } else {
            String temp = " Here are the tasks in your list:\n";
            for (int i = 0; i < count; i++) {
                temp += " ";
                temp += Integer.toString(i + 1);
                temp += this.items.get(i);
            }
            return temp;
        }
    }
}
