import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ", 2);
                String command = inputs[0];
                if (command.equals("bye")) {
                    sayBye();
                    break;
                } else if (command.equals("todo")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String desc = inputs[1];
                    addTodo(desc);
                } else if (command.equals("deadline")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String desc = inputs[1];
                    addDeadline(desc);
                } else if (command.equals("event")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String desc = inputs[1];
                    addEvent(desc);
                } else if (command.equals("list")) {
                    printList();
                } else if (command.equals("done")) {
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > tasks.size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    markTaskAsDone(index);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > tasks.size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    deleteTask(index);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    private static void greet() {
        System.out.println("Hi there, I'm Dodo!\nHow may I help you today?");
    }

    private static void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    private static void printAddToList() {
        System.out.println("Gotcha. Added this to your list:");
    }

    private static void printNumTask() {
        String taskWord;
        if (tasks.size() <= 1) {
            taskWord = "task";
        } else {
            taskWord = "tasks";
        }
        System.out.printf("Now you got %d %s in your list!\n", tasks.size(), taskWord);
    }

    private static void addTodo(String desc) {
        Task todo = new Todo(desc);
        tasks.add(todo);
        printAddToList();
        System.out.println(todo.toString());
        printNumTask();
    }

    private static void addDeadline(String desc) throws NoTimeSpecifiedException {
        String[] descs = desc.split(" /by ");
        if (descs.length == 1) { // there is no specified deadline time indicated by " /by "
            throw new NoTimeSpecifiedException();
        }
        String deadlineDesc = descs[0];
        String deadlineTime = descs[1];
        Task deadline = new Deadline(deadlineDesc, deadlineTime);
        tasks.add(deadline);
        printAddToList();
        System.out.println(deadline.toString());
        printNumTask();
    }

    private static void addEvent(String desc) throws NoTimeSpecifiedException {
        String[] descs = desc.split(" /at ");
        if (descs.length == 1) { // there is no specified event time indicated by " /at "
            throw new NoTimeSpecifiedException();
        }
        String eventDesc = descs[0];
        String eventTime = descs[1];
        Task event = new Event(eventDesc, eventTime);
        tasks.add(event);
        printAddToList();
        System.out.println(event.toString());
        printNumTask();
    }

    private static void printList() {
        if (tasks.size() == 0) {
            System.out.println("You currently don't have any task. Start listing now!");
        } else {
            System.out.println("Stop procrastinating. Do it now!");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
            }
        }
    }

    private static void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("Good job! One off your chest!");
        System.out.println(task.toString());
    }

    private static void deleteTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        printRemoveTask();
        System.out.println(task.toString());
        printNumTask();
    }

    private static void printRemoveTask() {
        System.out.println("Okay, I have removed this task for you:");
    }
}
