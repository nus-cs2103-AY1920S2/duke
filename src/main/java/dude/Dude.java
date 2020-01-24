package dude;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.function.Consumer;

public class Dude {
    /** Usage strings for valid commands to dude.Dude */
    public static final String LIST_USAGE = "list";
    public static final String DONE_USAGE = "done index_of_task";
    public static final String DELETE_USAGE = "delete index_of_task";
    public static final String BYE_USAGE = "bye";

    public static void main(String[] args) {
        Dude chatbot = new Dude();

        Scanner sc = new Scanner(System.in);
        
        chatbot.serve(sc::nextLine);

        sc.close();
        chatbot.saveState();
    }

    private IDudeTaskStore tasks;

    /** 
     * Initializes dude.Dude chatbot
     * Greets the user
     */
    public Dude() {
        this.tasks = DudeTaskStore.restoreSession();
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
            } else if (msg.startsWith("delete")) {
                deleteTask(msg);
            } else if (msg.startsWith("todo")) {
                addTask(Todo::parseTodo, msg);
            } else if (msg.startsWith("deadline")) {
                addTask(Deadline::parseDeadline, msg);
            } else if (msg.startsWith("event")) {
                addTask(Event::parseEvent, msg);
            } else {
                helpCommands();
            }
        }
    }

    public void saveState() {
        this.tasks.saveTasksToMemory();
    }

    private void helpCommands() {
        respond("Sorry mate, I didn't catch your drift",
                "Maybe you could try talking to me in one of these formats:" + System.lineSeparator(),
                "  " + LIST_USAGE,
                "  " + DONE_USAGE,
                "  " + DELETE_USAGE,
                "  " + Todo.USAGE,
                "  " + Deadline.USAGE,
                "  " + Event.USAGE,
                "  " + BYE_USAGE);
    }

    private void listTasks() {
        if (tasks.taskCount() == 0) {
            respond("You got nothing to do, dude. Ain't that awesome??");
            return;
        }
        
        respond(() -> {
            speak("These are your tasks, dude:");
            for (String t : tasks.showAllTasks()) {
                speak(t);
            }
        });
    }

    private void addTask(ThrowingFunction<String, Task, ParsingException> parser, String msg) {
        try {
            Task task = parser.apply(msg);
            tasks.addTask(task);
            respond("I gotcha my dude. I've added this task:",
                    String.format("  %s", task),
                    String.format("Now you got %d tasks in your list", tasks.taskCount()));
        } catch (ParsingException e) {
            respondError("Sorry mate, I didn't quite getcha", e.getMessage());
        }
    }

    private void taskListOperation(Consumer<Integer> tasksOp, String msg, String usage) {
        String[] args = msg.split("\\s+");

        if (args.length != 2) {
            respondError("I don't get you, dude!", usage);
            return;          
        }
        
        try {
            int index = Integer.parseInt(args[1]);
            tasksOp.accept(index);
        } catch (NumberFormatException e) {
            respondError("That's not a number, dude!", usage);
        } catch (IndexOutOfBoundsException e) {
            respondError("You don't have such a task, dude!", usage);
        }
    }

    private void completeTask(String msg) {
        Consumer<Integer> completeTaskAtIndex = index -> {
            Task completed = tasks.getTask(index);
            completed.markAsDone();
            respond("Good job dude! I've marked this task as done:", "  " + completed);
        };
        taskListOperation(completeTaskAtIndex, msg, DONE_USAGE);
    }
    
    private void deleteTask(String msg) {
        Consumer<Integer> deleteTaskAtIndex = index -> {
            Task deleted = tasks.removeTask(index);
            respond("I gotcha my dude. I've taken out this task:",
                    String.format("  %s", deleted),
                    String.format("Now you got %d tasks in your list", tasks.taskCount()));
        };
        taskListOperation(deleteTaskAtIndex, msg, DELETE_USAGE);
    }

    /** dude.Dude reply formatting convenience functions */
    private void respond(String ...responses) {
        respond(() -> {
            for (String response : responses) {
                speak(response);
            }
        });
    }

    private void respondError(String errorMsg, String usageMsg) {
        respond(errorMsg,
                "Just tell me what you want to do like this:" + System.lineSeparator(),
                "  " + usageMsg + System.lineSeparator(),
                "Then we're chill");        
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