import java.util.Scanner;
import java.util.ArrayList;
import java.util.function.Supplier;

public class Dude {
    public static void main(String[] args) {
        Dude chatbot = new Dude();

        Scanner sc = new Scanner(System.in);
        
        chatbot.serve(() -> sc.nextLine());

        sc.close();
    }

    private ArrayList<Task> tasks;

    public Dude() {
        this.tasks = new ArrayList<>(100);
        respond("Wassup dude!");
    }

    public void serve(Supplier<String> input) {
        while (true) {
            String msg = input.get();
            if (msg.equals("bye")) {
                respond("See ya!");
                return;
            } else if (msg.equals("list")) {
                listTasks();
            } else {
                addTask(msg);
            }
        }
    }

    private void listTasks() {
        Runnable printlist = () -> {
            int index = 1;
            for (Task t : tasks) {
                speak(index + ". " + t);
                index++;
            }
        };
        respond(printlist);
    }

    private void addTask(String task) {
        tasks.add(new Task(task));
        respond("added: " + task);
    }


    private void respond(String response) {
        respond(() -> speak(response));
    }

    private void respond(Runnable r) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        r.run();
        System.out.println(line);
    }

    private void speak(String str) {
        System.out.println("     " + str);
    }
}
