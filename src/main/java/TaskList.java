import java.util.ArrayList;
/**
 * This class contains a list of todo items.
 */
public class TaskList {
    ArrayList<Item> items = new ArrayList<Item>();
    Ui ui = new Ui();
    int count = 0;

    public TaskList() {
        this.items = items;
        this.count = count;
    }

    /**
     * This method add a new item to the list.
     */
    public void addItem(Item item) {
        this.items.add(item);
        this.count ++;
        String response = ui.addTask(this, count);
        if (this.count > 1) {
            response += "s";
        }
            response += " in the list.\n";
        response += ui.line;
        System.out.println(response);
    }

    /**
     * This method delete an item from the list based on its index.
     */
    public TaskList delete(int index) {
        count--;
        String response = ui.removeTask(this, index, count);
        if (this.count > 1) {
            response += "s";
        }
        response += " in the list.\n";
        response += ui.line;
        System.out.println(response);
        this.items.remove(index);
        return this;
    }

    @Override
    public String toString() {
        String temp = ui.space + " Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            temp += ui.space;
            temp += " ";
            temp += Integer.toString(i+1);
            temp += this.items.get(i);
        }
        temp += ui.line;
        return temp;
    }
}
