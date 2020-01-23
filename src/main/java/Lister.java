import java.util.ArrayList;

public class Lister {
    private ArrayList<Task> store;

    public Lister() {
        store = new ArrayList<>();
    }

    public void record(String command) {
        if (command.equals("list")) {
            System.out.println("Here are the tasks in your list: ");
            for (int i=0; i<store.size(); i++) {
                System.out.println((i + 1) + "." + store.get(i).toString());
            }
        } else {
            int x = command.indexOf(' ');
            switch (command.substring(0, x)) {
                case "done":
                    int y = Integer.valueOf(command.substring(x + 1)) - 1;
                    store.get(y).markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + store.get(y).toString());
                    break;
                case "deadline":
                    int z = command.indexOf('/');
                    Task newDeadline = new Deadline(command.substring(x + 1, z - 1), command.substring(z+4));
                    store.add(newDeadline);
                    printTask(newDeadline);
                    break;
                case "event":
                    int a = command.indexOf('/');
                    Task newEvent = new Event(command.substring(x + 1, a - 1), command.substring(a+4));
                    store.add(newEvent);
                    printTask(newEvent);
                    break;
                case "todo":
                    Task newToDo = new ToDo(command.substring(x + 1));
                    store.add(newToDo);
                    printTask(newToDo);
                    break;
            }
        }
    }

    public void printTask(Task task) {
        System.out.println("Got it. I've added this task: \n" + task.toString());
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }
}
