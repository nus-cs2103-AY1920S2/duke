import java.util.LinkedList;

public class Tracker {
    private LinkedList<String> list;
    private int totalItems;

    public Tracker() {
        this.list = new LinkedList();
        this.totalItems = 0;
    }

    public void add(String text) {
        this.totalItems++;
        this.list.add(this.totalItems + ". " + text);
    }

    public LinkedList<String> showList() {
        return this.list;
    }
}
