import java.util.ArrayList;

public class TaskListTest {
    private ArrayList<Task> listOfTexts;

    public TaskListTest(ArrayList<Task> listOfTasks) {
        this.listOfTexts = listOfTasks; // need to implement storage
    }

    public TaskListTest() {
        this.listOfTexts = new ArrayList<Task>();
    }

    public void addTask(Task t) {
        this.listOfTexts.add(t);
    }

    public ArrayList<Task> getTasks() {
        return this.listOfTexts;
    }

    public void showCurrentTasks() {
        int counter = 1;
        if (listOfTexts.size() == 0) {
            System.out.println("To Do List is empty! Congratulations!");
        }

    }
}