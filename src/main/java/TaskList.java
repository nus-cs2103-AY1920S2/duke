import java.util.ArrayList;
public class TaskList {
    ArrayList<Item> items = new ArrayList<Item>();
    Ui ui = new Ui();
    int count = 0;
    public TaskList() {
        this.items = items;
        this.count = count;
    }

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
