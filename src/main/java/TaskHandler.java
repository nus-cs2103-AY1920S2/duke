import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskHandler {
    ArrayList<Task> taskList;
    Saver saver;

    public TaskHandler() throws IOException {
        taskList = new ArrayList<Task>(100);
        saver = new Saver();
    }

    public void greet() {
        String greeting = "Hey there, Red. Anything I can do for you?";
        print(greeting);
    }

    public void event(String task) {
        String[] split = task.split("/", 2);
        Event newTask = new Event(split[0], split[1]);
        taskList.add(newTask);
        print("added: " + task);
    }

    public void deadline(String task) {
        String[] split = task.split("/", 2);
        Deadline newTask = new Deadline(split[0], split[1]);
        print(newTask.toString());
        taskList.add(newTask);
        print("added: " + task);
    }

    public void todo(String task) {
        Todo newTask = new Todo(task);
        taskList.add(newTask);
        print("added: " + task);
    }

    public void list() {
        taskList.forEach(task -> TaskHandler.print(String.format(
                "%d. %s",
                (taskList.indexOf(task) + 1),
                task.toString())));
        print(String.format(
                "That's %d in the list.", taskList.size()));
    }

    public void done(int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        task.markAsDone();
        print("That's another one down. That'll be: ");
        print(task.toString());
    }

    public void delete(int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        taskList.remove(taskIndex - 1);
        print("Don't need this here anymore eh? Off it goes.");
        print(task.toString());
    }

    public void exit() throws IOException {
        Saver.fullSaver(this.taskList);
        String farewell = "Guess that's enough for now. I've got your back, so you keep me close.";
        print(farewell);
    }

    public static void print(String toPrint) {
        System.out.println(toPrint);
    }

    public void handle() throws IOException {
        Saver.loader(taskList);

        Scanner scanner = new Scanner(System.in);
        ExceptionHandler exceptionHandler = new ExceptionHandler();
        boolean loop = true;

        do {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);

            try {
                exceptionHandler.checkWrongCommand(inputSplit[0]);
                exceptionHandler.checkEmptyField(inputSplit, inputSplit[0]);

                switch (inputSplit[0]) {
                    case "todo":
                        this.todo(inputSplit[1]);
                        break;
                    case "deadline":
                        this.deadline(inputSplit[1]);
                        break;
                    case "event":
                        this.event(inputSplit[1]);
                        break;
                    case "done":
                        int index;
                        index = Integer.parseInt(inputSplit[1]);
                        this.done(index);
                        break;
                    case "delete":
                        index = Integer.parseInt(inputSplit[1]);
                        this.delete(index);
                        break;
                    case "list":
                        this.list();
                        break;
                    case "bye":
                        this.exit();
                        loop = false;
                        break;
                    default:
                        print("Uhh... You're gonna have to say that again, Red.");
                        break;
                }
            } catch(DukeException | IOException ex) {
                System.err.println(ex);
            }
        } while (loop);
    }
}
