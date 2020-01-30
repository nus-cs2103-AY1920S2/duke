import java.util.ArrayList;

public class Tasklist {

    protected ArrayList<Task> mylist;

    public Tasklist(ArrayList<Task> list) {
        this.mylist = list;
    }

    public void addTask(Task task) {
        mylist.add(task);
    }

    public void removeTask(int index) {
        mylist.remove(index - 1);
    }

    public void markDone(int index) {
        Task current = mylist.get(index - 1);
        current.markAsDone();
    }

    public Task getTask(int index) {
        return mylist.get(index - 1);
    }

    public int getSize() {
        return mylist.size();
    }

    public ArrayList<Task> getList() {
        return mylist;
    }

    public void printList() {
        for (int i = 0; i < mylist.size(); i++) {
            System.out.println(i + 1 + ". " + mylist.get(i));
        }
    }
}