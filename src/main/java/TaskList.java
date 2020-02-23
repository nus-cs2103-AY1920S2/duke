import java.util.ArrayList;
import java.util.Collections;
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

    public TaskList(ArrayList<Item> items, int count) {
        this.items = items;
        this.count = count;
    }

    /**
     * This method add a new item to the list.
     */
    public void addItem(Item item) {
        this.items.add(item);
        this.count++;
    }

    /**
     * This method check if an item already exits in the list.
     */
    public boolean isDuplicate(Item item) {
        if (item instanceof Todo) {
            Todo todo = (Todo)item;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getName().equals(todo.getName()) && items.get(i).getDone() == todo.getDone()) {
                    return true;
                }
            }
        } else if (item instanceof Deadline) {
            Deadline ddl = (Deadline)item;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) instanceof Deadline && items.get(i).getName().equals(ddl.getName())
                        && items.get(i).getDone()
                        == ddl.getDone() && items.get(i).getDate().equals(ddl.getDate())) {
                    return true;
                }
            }
        } else {
            Event event = (Event)item;
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i) instanceof Event && items.get(i).getName().equals(event.getName())
                        && items.get(i).getDone()
                        == event.getDone() && items.get(i).getDate().equals(event.getDate())) {
                    return true;
                }
            }
        }
        return false;
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
        if (temp.equals("")) {
            temp = ui.space + "No search results";
        }
        return temp;
    }

    /**
     * Returns TaskList sorted by ascending dates.
     */
    public TaskList sortAsc() {
        ArrayList<Item> sorted = new ArrayList<Item>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Todo) {
                continue;
            } else {
                sorted.add(items.get(i));
            }
        }

        DateCompareAsc compare = new DateCompareAsc();
        Collections.sort(sorted, compare);
        return new TaskList(sorted, sorted.size());
    }

    /**
     * Returns TaskList sorted by descending dates for events and deadlines.
     */
    public TaskList sortDes() {
        ArrayList<Item> sorted = new ArrayList<Item>();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) instanceof Todo) {
                continue;
            } else {
                sorted.add(items.get(i));
            }
        }

        DateCompareDes compare = new DateCompareDes();
        Collections.sort(sorted, compare);
        return new TaskList(sorted, sorted.size());
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
