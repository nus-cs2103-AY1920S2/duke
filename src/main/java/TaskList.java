import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> record;

    public TaskList() {
        ArrayList<Task> list = new ArrayList<>();
        this.record = list;
    }

    public TaskList(ArrayList<Task> record) {
        this.record = record;
    }

    public ArrayList<Task> getRecord() {
        return record;
    }

    public void addToDo(String record) {
        Task task = new ToDo(record);
        this.record.add(task);

        System.out.println("--------------------------------------------------");
        System.out.println("piaked in: " + record);
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    public void addDeadline(String record, String by) {
        Task task = new Deadline(record, by);
        this.record.add(task);

        System.out.println("--------------------------------------------------");
        System.out.println("piaked in: " + record);
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    public void addEvent(String record, String at) {
        Task task = new Event(record, at);
        this.record.add(task);

        System.out.println("--------------------------------------------------");
        System.out.println("piaked in: " + record);
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    public void listRecord() {
        System.out.println("--------------------------------------------------");

        for(int i = 0; i < record.size(); i++) {
            int j = i + 1;
            Task current = record.get(i);
            System.out.print(j + "." + current + "\n");
        }

        System.out.println("--------------------------------------------------\n");
    }

    public void delete(int num) {
        Task task = record.remove(num - 1);

        System.out.println("--------------------------------------------------");
        System.out.println("Destroyed: ");
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    public void setDone(int num) {
        Task task = record.get(num - 1);

        task.setIsDone();

        System.out.println("--------------------------------------------------");
        System.out.println("Next time do yourself la! No paper meh?");
        System.out.println(task);
        System.out.println("--------------------------------------------------\n");
    }
}
