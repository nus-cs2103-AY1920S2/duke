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

    /** 
     * Initializes Dude chatbot with internal ArrayList keeping track of tasks
     * Greets the user
     */
    public Dude() {
        this.tasks = new ArrayList<>(100);
        respond("Wassup dude!");
    }

    /**
     * Repeatedly takes user input and responds to commands appropriately
     * until "bye" is given as input
     * 
     * @param input Supplier of user input (eg. Scanner object)
     */
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
                try {
                    if (msg.startsWith("todo")) {
                        addTask(Todo.parseTodo(msg));
                    } else if (msg.startsWith("deadline")) {
                        addTask(Deadline.parseDeadline(msg));
                    } else if (msg.startsWith("event")) {
                        addTask(Event.parseEvent(msg));
                    } else {
                        respond("Sorry mate, I didn't catch your drift",
                                "Maybe you could try talking to me in one of these formats:\n",
                                "  list",
                                "  done index_of_completed_task",
                                "  " + Todo.getMessageFormat(),
                                "  " + Deadline.getMessageFormat(),
                                "  " + Event.getMessageFormat(),
                                "  bye");
                        continue;
                    }
                } catch (ParsingException e) {
                    respondError("Sorry mate, I didn't quite getcha", e.getMessage());
                }
            }
        }
    }

    private Task getTask(int index) {
        return tasks.get(index - 1);
    }

    private void listTasks() {
        respond(() -> {
            speak("These are your tasks, dude:");
            int index = 1;
            for (Task t : tasks) {
                speak(String.format("%d.%s", index, t));
                index++;
            }
        });
    }

    private void addTask(Task task) {
        tasks.add(task);
        respond("I gotcha my dude. I've added this task:",
                "  " + task.toString(),
                String.format("Now you got %d tasks in your list", tasks.size()));
    }

    private void completeTask(String msg) {
        String usage = "done index_of_completed_task";
        String[] args = msg.split(" ");
        
        if (args.length != 2) {
            respondError("I don't get you, dude!", usage);
            return;
        }

        try {
            int index = Integer.parseInt(args[1]);
            Task completed = getTask(index);
            completed.markAsDone();
            respond("Good job dude! I've marked this task as done:", "  " + completed);
        } catch (NumberFormatException e) {
            respondError("That's not a number, dude!", usage);
        } catch (IndexOutOfBoundsException e) {
            respondError("You don't have such a task, dude!", usage);
        }
    }


    private void respond(String ...responses) {
        respond(() -> {
            for (String response : responses) {
                speak(response);
            }
        });
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
