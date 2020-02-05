import java.util.*;

public class TaskList {
    public static ArrayList <Task> arr = new ArrayList <Task>();

    public TaskList(ArrayList <Task> al) {
        this.arr = al;
    }

    public ArrayList <Task> getArraylist() {
        return arr;
    }

    public void print() {
        for (int i = 1; i <= arr.size(); i++) {
            System.out.println(i + "." + arr.get(i - 1).getIcon() + arr.get(i - 1).status + " " + arr.get(i - 1).getDescription());
        }
    }
    public void clear() {
        arr.clear();
    }
}
