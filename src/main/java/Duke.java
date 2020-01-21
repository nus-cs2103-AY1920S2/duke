import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static final String END_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DEADLINE_BY = "/by";
    private static final String EVENT_COMMAND = "event";
    private static final String EVENT_AT = "/at";

    private List<Task> list = new ArrayList<>();

    private static void print(String s) {
        List<String> temp = new ArrayList<>();
        temp.add(s);
        print(temp);
    }

    private static void print(List<String> stringList) {
        final String HORIZONTAL_LINE = "------------------------------------------------------------";
        final String OUTPUT_INDENTATION = "    ";
        final String FORMAT_STRING = OUTPUT_INDENTATION +  "|%-" + HORIZONTAL_LINE.length() + "s|";

        System.out.println(String.format(FORMAT_STRING, HORIZONTAL_LINE));
        for (String s : stringList) {
            System.out.println(String.format(FORMAT_STRING, s));
        }
        System.out.println(String.format(FORMAT_STRING, HORIZONTAL_LINE));
    }

    private static void greet() {
        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Hello! I'm Alfred!");
        outputStreamBuffer.add("How may I help you today?");
        print(outputStreamBuffer);
    }

    private static void bye() {
        print("Bye. Hope to see you again soon!");
    }

    private void printList() {
        if (list.isEmpty()) {
            print("List is empty");
            return;
        }

        List<String> outputStreamBuffer = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i).toString();
            String newStr = String.format("%d.%s", i + 1, str);
            outputStreamBuffer.add(newStr);
        }

        print(outputStreamBuffer);
    }

    private void markTaskAsDone(int taskIndex) {
        Task selectedTask = this.list.get(taskIndex);
        selectedTask.markAsDone();

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Nice! I've marked this task as done: ");
        outputStreamBuffer.add("  " + selectedTask);
        print(outputStreamBuffer);
    }

    private void createAndAddTask(String lineInput) {
        String[] splitInput = lineInput.split(" ");
        String command = splitInput[0];
        String[] inputWithoutCommand = Arrays.copyOfRange(splitInput, 1, splitInput.length);
        Task newTask;

        if (command.equals(TODO_COMMAND)) {
            String taskDescription = String.join(" ", inputWithoutCommand);
            newTask = new Todo(taskDescription);
        } else {
            String keyword = command.equals(DEADLINE_COMMAND) ? DEADLINE_BY : EVENT_AT;
            int keywordIndex = Arrays.asList(splitInput).indexOf(keyword);

            String description = String.join(" ",
                    Arrays.copyOfRange(splitInput, 1, keywordIndex));
            String deadlineOrTime = String.join(" ",
                    Arrays.copyOfRange(splitInput, keywordIndex + 1, splitInput.length));

            newTask = command.equals(DEADLINE_COMMAND) ?
                    new Deadline(description, deadlineOrTime) : new Event(description, deadlineOrTime);
        }

        this.list.add(newTask);

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Got it. I've added this task: ");
        outputStreamBuffer.add("  " + newTask);
        outputStreamBuffer.add(String.format("Now you have %d tasks in the list.", list.size()));
        print(outputStreamBuffer);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        greet();

        while (true) {
            String lineInput = sc.nextLine();
            String[] splitInput = lineInput.split(" ");
            String command = splitInput[0];

            if (command.equals(END_COMMAND)) {
                break;
            } else if (command.equals(LIST_COMMAND)) {
                duke.printList();
            } else if (command.equals(DONE_COMMAND)) {
                int taskIndex = Integer.parseInt(splitInput[1]) - 1;
                duke.markTaskAsDone(taskIndex);
            } else if (command.equals(TODO_COMMAND) ||
                    command.equals(DEADLINE_COMMAND) ||
                    command.equals(EVENT_COMMAND)) {
                duke.createAndAddTask(lineInput);
            } else {
                print("Error: unknown command");
            }
        }

        bye();
    }
}
