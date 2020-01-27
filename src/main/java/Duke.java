import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks;
    private static HardDisk hardDisk;

    public Duke(String path) throws IOException, InvalidTaskInputException {
        greet();
        tasks = new ArrayList<>();
        hardDisk = new HardDisk(path);
        hardDisk.addFileInputToTasks();
    }

    private static void runDuke() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String source = "user";
                String[] inputs = input.split(" ", 2);
                String command = inputs[0];
                if (command.equalsIgnoreCase("bye")) {
                    sayBye();
                    break;
                } else if (command.equalsIgnoreCase("todo")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String desc = inputs[1];
                    addTodo(desc, "N", source);
                } else if (command.equalsIgnoreCase("deadline")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String desc = inputs[1];
                    addDeadline(desc, "N", source);
                } else if (command.equalsIgnoreCase("event")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    }
                    String desc = inputs[1];
                    addEvent(desc, "N", source);
                } else if (command.equalsIgnoreCase("list")) {
                    printList();
                } else if (command.equalsIgnoreCase("done")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    } else if (!isNumeric(inputs[1])) {
                        throw new InvalidTaskInputException();
                    }
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > tasks.size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    markTaskAsDone(index);
                } else if (command.equalsIgnoreCase("delete")) {
                    if (inputs.length == 1) {
                        throw new EmptyDescriptionException();
                    } else if (!isNumeric(inputs[1])) {
                        throw new InvalidTaskInputException();
                    }
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > tasks.size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    deleteTask(index);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (DukeException | IOException e) {
                System.out.println(e.toString());
            }
        }
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int intNum = Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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

    protected static void addTodo(String desc, String doneStatus, String source) throws IOException, InvalidTaskInputException {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
        }
        tasks.add(todo);

        if (source.equals("user")) {
            hardDisk.addToHardDisk(todo);
            printAddToList();
            System.out.println(todo.toString());
            printNumTask();
        }
    }

    protected static void addDeadline(String desc, String doneStatus, String source) throws InvalidTaskInputException, IOException {
        String[] descs = desc.split(" /by |\\|") ;
        if (descs.length == 1) { // invalid Deadline input format
            throw new InvalidTaskInputException();
        }

        String deadlineDesc = descs[0].trim();
        String deadlineTime = descs[1].trim();
        Task deadline = new Deadline(deadlineDesc, deadlineTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);

        if (source.equals("user")) {
            hardDisk.addToHardDisk(deadline);
            printAddToList();
            System.out.println(deadline.toString());
            printNumTask();
        }
    }

    protected static void addEvent(String desc, String doneStatus, String source) throws InvalidTaskInputException, IOException {
        String[] descs = desc.split(" /at |\\|");
        if (descs.length == 1) { // invalid Event input format
            throw new InvalidTaskInputException();
        }
        String eventDesc = descs[0].trim();
        String eventTime = descs[1].trim();
        Task event = new Event(eventDesc, eventTime);

        if (doneStatus.equalsIgnoreCase("Y")) {
            event.markAsDone();
        }
        tasks.add(event);

        if (source.equals("user")) {
            hardDisk.addToHardDisk(event);
            printAddToList();
            System.out.println(event.toString());
            printNumTask();
        }
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

    private static void markTaskAsDone(int index) throws IOException {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        hardDisk.changeToHardDisk(index);
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

    public static void main(String[] args) throws IOException, InvalidTaskInputException, InvalidCommandException {
        Duke duke;
        duke = new Duke("./data/duke.txt");
        duke.runDuke();
    }
}
