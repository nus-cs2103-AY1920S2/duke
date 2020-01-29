import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();;
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task get(int index) {
        return list.get(index);
    }

    public String saveList() {
        String toSave = "";
        for (int i = 0; i < list.size(); i++) {
            toSave += list.get(i).toPrint();
            toSave += "\n";
        }
        return toSave;
    }

    public int size() {
        return list.size();
    }

    public void printList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + list.get(i));
        }
    }

    public void printSize() {
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    public void done(int index, Storage storage) throws IOException {
        list.get(index).markDone();
        storage.writeToFile(saveList());
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + list.get(index));
    }

    public void delete(int index, Storage storage) throws IOException {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + list.get(index));
        list.remove(index);
        storage.writeToFile(saveList());
    }
}