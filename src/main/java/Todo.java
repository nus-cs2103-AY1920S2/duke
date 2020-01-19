import java.util.ArrayList;

public class Todo {
    private ArrayList<String> list;

    public Todo() {
        this.list = new ArrayList<String>();
    }

    public String add(String task) {
        this.list.add(task);
        return "added: " + task;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output = output + (i + 1) + ". "+ list.get(i) + "\n";
        }
        return output;
    }
}
