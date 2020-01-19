import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class Duke {
    private ArrayList<Task> tasks;

    public void listTasks() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%d. %s\n", i, tasks.get(i - 1));
        }
    }

    public void markDone(int index) {
        Task task = getTask(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.printf("   %s\n", task.toString());
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    public void addTodo(String task) {
        Todo new_task = new Todo(task);
        tasks.add(new_task);
        System.out.println("Got it. I've added this task: ");
        System.out.printf("    %s\n", new_task);
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    public void addDeadline(String task, String by_schedule) {
        Deadline new_task = new Deadline(task, by_schedule);
        tasks.add(new_task);
        System.out.println("Got it. I've added this task: ");
        System.out.printf("    %s\n", new_task);
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    public void addEvent(String task, String at_schedule) {
        Event new_task = new Event(task, at_schedule);
        tasks.add(new_task);
        System.out.println("Got it. I've added this task: ");
        System.out.printf("    %s\n", new_task);
        System.out.printf("Now you have %d task(s) in the list.\n", tasks.size());
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public Duke() {
        this.tasks = new ArrayList<>();
    }

    public void processCommand(String arguments) {
        String[] splitted_arguments = arguments.split(" ");
        String command = splitted_arguments[0];
        if (command.equals("list")) {
            listTasks();
        } else if (command.equals("done")) {
            int index = Integer.parseInt(splitted_arguments[1]);
            markDone(index);
        } else if(command.equals("todo")) {
            String description = arguments.split(" ", 2)[1];
            addTodo(description);
        } else if (command.equals("deadline")) {
            String[] description_with_schedule = arguments.split(" ", 2)[1].split(" /by ");
            String description = description_with_schedule[0];
            String schedule = description_with_schedule[1];
            addDeadline(description, schedule);
        } else if (command.equals("event")) {
            String[] description_with_schedule = arguments.split(" ", 2)[1].split(" /at ");
            String description = description_with_schedule[0];
            String schedule = description_with_schedule[1];
            addEvent(description, schedule);
        }
    }
}
