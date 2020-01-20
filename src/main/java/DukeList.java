import java.util.ArrayList;

public class DukeList {
    private ArrayList<String> listOfTasks;

    public DukeList() {
        listOfTasks = new ArrayList<>();
    }

    public void add_task(String S) {
        System.out.println("    Added:" + S);
        listOfTasks.add(S);
    }

    public void view_task() {
        int numOfTasks = listOfTasks.size();

        for (int x = 0; x < numOfTasks; x++) {
            String output = String.format("    %d.%s", x + 1, listOfTasks.get(x));
            System.out.println(output);
        }
    }
}
