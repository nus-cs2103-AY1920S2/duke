import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Duke {
    private static List<Task> tasks;

    public Duke(String path) throws IOException, InvalidTaskInputException {
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));
        greet();
        tasks = new ArrayList<>();
        addFileInputToTasks(br);
    }

    private void addFileInputToTasks(BufferedReader br) throws IOException, InvalidTaskInputException {
        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            String[] input = inputLine.split("\\|", 3);
            String source = "file";
            String type = input[0].trim();
            String doneStatus = input[1].trim();
            String desc = input[2].trim();
            if (type.equalsIgnoreCase("T")) {
                addTodo(desc, doneStatus, source);
            } else if (type.equalsIgnoreCase("D")) {
                addDeadline(desc, doneStatus, source);
            } else if (type.equalsIgnoreCase("E")) {
                addEvent(desc, doneStatus, source);
            } else {
                throw new InvalidTaskInputException();
            }
        }
    }

    private void runDuke() {
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
                    if (!isNumeric(inputs[1])) {
                        throw new InvalidTaskInputException();
                    }
                    int index = Integer.parseInt(inputs[1]);
                    if (index < 1 || index > tasks.size()) {
                        throw new TaskIndexOutOfBoundsException();
                    }
                    markTaskAsDone(index);
                } else if (command.equalsIgnoreCase("delete")) {
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

    private static void addTodo(String desc, String doneStatus, String source) {
        Task todo = new Todo(desc);
        if (doneStatus.equalsIgnoreCase("Y")) {
            todo.markAsDone();
        }
        tasks.add(todo);

        if (source.equals("user")) {
            printAddToList();
            System.out.println(todo.toString());
            printNumTask();
        }
    }

    private static void addDeadline(String desc, String doneStatus, String source) throws InvalidTaskInputException {
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
            printAddToList();
            System.out.println(deadline.toString());
            printNumTask();
        }
    }

    private static void addEvent(String desc, String doneStatus, String source) throws InvalidTaskInputException {
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

    public static void main(String[] args) throws IOException, InvalidTaskInputException, InvalidCommandException {
        Duke duke;
        duke = new Duke("data/duke.txt");
        duke.runDuke();
    }
}
