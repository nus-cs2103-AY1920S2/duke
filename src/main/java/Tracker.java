import java.util.LinkedList;

public class Tracker {
    private LinkedList<String> list;

    public Tracker() {
        this.list = new LinkedList();
    }

    public void add(String text) {
        this.list.add(text);
    }
}
