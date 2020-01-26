package dude;

import dude.component.IUserInterface;
import dude.component.UI;
import dude.component.IStorage;
import dude.component.TextStorage;
import dude.component.TaskList;

import java.util.function.Consumer;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Dude {
    /** Usage strings for valid commands to dude.Dude */
    public static final String LIST_USAGE = "list";
    public static final String DONE_USAGE = "done index_of_task";
    public static final String DELETE_USAGE = "delete index_of_task";
    public static final String CHECK_USAGE = "check yyyy-mm-dd";
    public static final String TODAY_USAGE = "today";
    public static final String BYE_USAGE = "bye";

    public static void main(String[] args) {
        Dude chatbot = new Dude();
        chatbot.serve();
        chatbot.close();
    }

    private IStorage storage;
    private TaskList tasks;
    private IUserInterface ui;

    /** 
     * Initializes dude.Dude chatbot
     * Greets the user
     */
    public Dude() {
        this.ui = new UI();
        this.storage = new TextStorage();
        this.tasks = this.storage.restoreSession(this.ui);
    }

    /**
     * Repeatedly takes user input and responds to commands appropriately
     * until "bye" is given as input
     */
    public void serve() {
        while (true) {                          
            String msg = ui.readCommand();

            if (msg.equals("bye")) {
                ui.respond("See ya!");
                return;
            } else if (msg.equals("list")) {
                listTasks();
            } else if (msg.startsWith("done")) {
                completeTask(msg);
            } else if (msg.startsWith("delete")) {
                deleteTask(msg);
            } else if (msg.equals("today")) {
                showTasksOnDate(LocalDate.now());
            } else if (msg.startsWith("check")) {
                checkTasksOnDate(msg);
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

    public void close() {
        this.storage.saveSession(this.ui, this.tasks);
        this.ui.close();
    }

    private void helpCommands() {
        ui.respond("Sorry mate, I didn't catch your drift",
                "Maybe you could try talking to me in one of these formats:" + System.lineSeparator(),
                "  " + LIST_USAGE,
                "  " + DONE_USAGE,
                "  " + DELETE_USAGE,
                "  " + CHECK_USAGE,
                "  " + TODAY_USAGE,
                "  " + Todo.USAGE,
                "  " + Deadline.USAGE,
                "  " + Event.USAGE,
                "  " + BYE_USAGE);
    }

    private void listTasks() {
        if (tasks.taskCount() == 0) {
            ui.respond("You got nothing to do, dude. Ain't that awesome??");
            return;
        }
        
        ui.respond(() -> {
            ui.speak("These are your tasks, dude:");
            for (String t : tasks.showAllTasks()) {
                ui.speak(t);
            }
        });
    }

    private void checkTasksOnDate(String msg) {
        String[] args = msg.split("\\s+");

        if (args.length != 2) {
            ui.respondError("I don't get you, dude!", CHECK_USAGE);
            return;
        }

        try {
            LocalDate date = LocalDate.parse(args[1]);
            showTasksOnDate(date);
        } catch (DateTimeParseException e) {
            ui.respondError("What date do you want to check man?", CHECK_USAGE);
        }
    }

    private void showTasksOnDate(LocalDate date) {
        ui.respond(() -> {
            ui.speak("These are what you have on this day");
            for (int i = 1; i <= tasks.taskCount(); i++) {
                if (tasks.getTask(i).occursOn(date)) {
                    ui.speak(String.format("%d.%s", i, tasks.getTask(i)));
                }
            }
        });
    }

    private void addTask(ThrowingFunction<String, Task, ParsingException> parser, String msg) {
        try {
            Task task = parser.apply(msg);
            tasks.addTask(task);
            ui.respond("I gotcha my dude. I've added this task:",
                    String.format("  %s", task),
                    String.format("Now you got %d tasks in your list", tasks.taskCount()));
        } catch (ParsingException e) {
            ui.respondError("Sorry mate, I didn't quite getcha", e.getMessage());
        }
    }

    private void taskListOperation(Consumer<Integer> tasksOp, String msg, String usage) {
        String[] args = msg.split("\\s+");

        if (args.length != 2) {
            ui.respondError("I don't get you, dude!", usage);
            return;          
        }
        
        try {
            int index = Integer.parseInt(args[1]);
            tasksOp.accept(index);
        } catch (NumberFormatException e) {
            ui.respondError("That's not a number, dude!", usage);
        } catch (IndexOutOfBoundsException e) {
            ui.respondError("You don't have such a task, dude!", usage);
        }
    }

    private void completeTask(String msg) {
        Consumer<Integer> completeTaskAtIndex = index -> {
            Task completed = tasks.getTask(index);
            completed.markAsDone();
            ui.respond("Good job dude! I've marked this task as done:", "  " + completed);
        };
        taskListOperation(completeTaskAtIndex, msg, DONE_USAGE);
    }
    
    private void deleteTask(String msg) {
        Consumer<Integer> deleteTaskAtIndex = index -> {
            Task deleted = tasks.removeTask(index);
            ui.respond("I gotcha my dude. I've taken out this task:",
                    String.format("  %s", deleted),
                    String.format("Now you got %d tasks in your list", tasks.taskCount()));
        };
        taskListOperation(deleteTaskAtIndex, msg, DELETE_USAGE);
    }
}