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
            } else if (msg.startsWith("done")) {
                completeTask(msg);
            } else {
                addTask(msg);
            }
        }
    }

    private Task getTask(int index) {
        return tasks.get(index - 1);
    }

    private void listTasks() {
        Runnable printlist = () -> {
            speak("These are your tasks, duuude:");
            int index = 1;
            for (Task t : tasks) {
                speak(String.format("%d.%s", index, t));
                index++;
            }
        };
        respond(printlist);
    }

    private void addTask(String task) {
        tasks.add(new Task(task));
        respond("added: " + task);
    }

    private void completeTask(String msg) {
        String[] args = msg.split(" ");
        
        if (args.length != 2) {
            respondError("I don't get you, dude!", "done index_of_completed_task");
            return;
        }

        try {
            int index = Integer.parseInt(args[1]);
            Task completed = getTask(index);
            completed.markAsDone();
            respond(() -> {
                speak("Good job dude! I've marked this task as done:");
                speak(" " + completed);
            });
        } catch (NumberFormatException e) {
            respondError("That's not a number, dude!", "done index_of_completed_task");
        } catch (IndexOutOfBoundsException e) {
            respondError("You don't have such a task, dude!", "done index_of_completed_task");
        }
    }


    private void respond(String response) {
        respond(() -> speak(response));
    }

    private void respondError(String errorMsg, String usageMsg) {
        respond(() -> {
            speak(errorMsg);
            speak("Just tell me what you want to do like this:\n");
            speak("  " + usageMsg + "\n");
            speak("Then we're chill");
        });
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
