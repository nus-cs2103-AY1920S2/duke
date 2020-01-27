import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private List<Task> list = new ArrayList<>();

    private static void print(String s) {
        List<String> temp = new ArrayList<>();
        temp.add(s);
        print(temp);
    }

    private static void print(List<String> stringList) {
        final String HORIZONTAL_LINE = "------------------------------------------------------------";
        final String OUTPUT_INDENTATION = "    ";
        final String FORMAT_STRING_FOR_H_LINE = OUTPUT_INDENTATION +  "|%-" + HORIZONTAL_LINE.length() + "s|";
        final String FORMAT_STRING_FOR_CONTENT = OUTPUT_INDENTATION +  "|  %-" + (HORIZONTAL_LINE.length() - 2) + "s|";

        System.out.println(String.format(FORMAT_STRING_FOR_H_LINE, HORIZONTAL_LINE));
        for (String s : stringList) {
            System.out.println(String.format(FORMAT_STRING_FOR_CONTENT, s));
        }
        System.out.println(String.format(FORMAT_STRING_FOR_H_LINE, HORIZONTAL_LINE));
    }

    private static void greet() {
        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Hello! I'm Alfred!");
        outputStreamBuffer.add("How may I help you today?");
        print(outputStreamBuffer);
    }

    private static void bye() {
        print("Bye. Hope to see you again soon!");
        System.exit(0);
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

    private void deleteTask(int taskIndex) {
        Task selectedTask = this.list.get(taskIndex);
        this.list.remove(taskIndex);

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Noted. I've removed this task: ");
        outputStreamBuffer.add("  " + selectedTask);
        outputStreamBuffer.add(String.format("Now you have %d tasks in the list.", list.size()));
        print(outputStreamBuffer);
    }

    private void createAndAddTask(String lineInput) throws DukeEmptyDescriptionException, DukeNoKeywordException {
        String[] splitInput = lineInput.split(" ");
        String command = splitInput[0];

        if (splitInput.length == 1) {
            throw new DukeEmptyDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }

        String[] inputWithoutCommand = Arrays.copyOfRange(splitInput, 1, splitInput.length);
        Task newTask;

        if (command.equals(DukeCommand.TODO_COMMAND.getCommand())) {
            // empty string array would become empty string
            String taskDescription = String.join(" ", inputWithoutCommand);
            newTask = new Todo(taskDescription);
        } else {
            String keyword = command.equals(DukeCommand.DEADLINE_COMMAND.getCommand()) ?
                    DukeCommand.DEADLINE_BY.getCommand() :
                    DukeCommand.EVENT_AT.getCommand();
            int keywordIndex = Arrays.asList(splitInput).indexOf(keyword);

            if (keywordIndex == -1) {
                throw new DukeNoKeywordException("OOPS!!! The description must contain a keyword.");
            }

            String description = String.join(" ",
                    Arrays.copyOfRange(splitInput, 1, keywordIndex));
            String deadlineOrTime = String.join(" ",
                    Arrays.copyOfRange(splitInput, keywordIndex + 1, splitInput.length));

            newTask = command.equals(DukeCommand.DEADLINE_COMMAND.getCommand()) ?
                    new Deadline(description, deadlineOrTime) : new Event(description, deadlineOrTime);
        }

        this.list.add(newTask);

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Got it. I've added this task: ");
        outputStreamBuffer.add("  " + newTask);
        outputStreamBuffer.add(String.format("Now you have %d tasks in the list.", list.size()));
        print(outputStreamBuffer);
    }

    private void processInput(String lineInput) {
        String[] splitInput = lineInput.split(" ");
        // empty line would output string array of size 1, where the element is empty string
        String commandString = splitInput[0];
        DukeCommand command = null;
        int selectedTaskIndex;

        if (commandString.equals(DukeCommand.END_COMMAND.getCommand())) {
            command = DukeCommand.END_COMMAND;
        } else if (commandString.equals(DukeCommand.LIST_COMMAND.getCommand())) {
            command = DukeCommand.LIST_COMMAND;
        } else if (commandString.equals(DukeCommand.DONE_COMMAND.getCommand())) {
            command = DukeCommand.DONE_COMMAND;
        } else if (commandString.equals(DukeCommand.DELETE_COMMAND.getCommand())) {
            command = DukeCommand.DELETE_COMMAND;
        } else if (commandString.equals(DukeCommand.TODO_COMMAND.getCommand())) {
            command = DukeCommand.TODO_COMMAND;
        } else if (commandString.equals(DukeCommand.DEADLINE_COMMAND.getCommand())) {
            command = DukeCommand.DEADLINE_COMMAND;
        } else if (commandString.equals(DukeCommand.EVENT_COMMAND.getCommand())) {
            command = DukeCommand.EVENT_COMMAND;
        } else {
            print("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (command != null) {
            switch (command) {
                case END_COMMAND:
                    bye();
                    break;
                case LIST_COMMAND:
                    this.printList();
                    break;
                case DONE_COMMAND:
                    selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                    this.markTaskAsDone(selectedTaskIndex);
                    break;
                case DELETE_COMMAND:
                    selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                    this.deleteTask(selectedTaskIndex);
                    break;
                case TODO_COMMAND:
                case DEADLINE_COMMAND:
                case EVENT_COMMAND:
                    try {
                        this.createAndAddTask(lineInput);
                    } catch (Exception e) {
                        print(e.toString());
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        greet();

        while (true) {
            String lineInput = sc.nextLine();
            duke.processInput(lineInput);
        }
    }
}
