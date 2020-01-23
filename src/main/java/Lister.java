import java.util.ArrayList;

public class Lister {
    private ArrayList<Task> store;

    public Lister() {
        store = new ArrayList<>();
    }

    public void record(String command) {
        int x = command.indexOf(' ');
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list: ");
            for (int i=0; i<store.size(); i++) {
                System.out.println((i+1) + "." + returnTask(store.get(i)));
            }
        } else if (command.substring(0,x).equals("done")) {
            int y = Integer.valueOf(command.substring(x+1)) - 1;
            store.get(y).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(returnTask(store.get(y)));
        } else {
            store.add(new Task(command));
            System.out.println("added: " + command);
        }
    }

    public String returnTask(Task task) {
        return ("[" + task.getStatusIcon() + "] " + task.getDescription());
    }

}
