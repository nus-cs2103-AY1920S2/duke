import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    private static Task decode(String str) {
        String[] splitInput = str.split(Pattern.quote(Task.SEPERATOR));
        String taskType = splitInput[0];
        boolean isDone = splitInput[1].equals(Task.TRUE_SYMBOL);
        String taskDescription = splitInput[2];
        LocalDate date = null;
        Task toReturn = null;

        switch (taskType) {
            case Todo.TYPE_SYMBOL:
                toReturn = new Todo(taskDescription);
                break;
            case Deadline.TYPE_SYMBOL:
                date = LocalDate.parse(splitInput[3]);
                toReturn = new Deadline(taskDescription, date);
                break;
            case Event.TYPE_SYMBOL:
                date = LocalDate.parse(splitInput[3]);
                toReturn = new Event(taskDescription, date);
                break;
            default:
                print("Failed to decode. Unknown task type.");
                break;
        }

        if (isDone && toReturn != null) {
            toReturn.markAsDone();
        }

        return toReturn;
    }

    private void loadSavedTaskList(Path path) throws IOException {
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path);
            this.list = lines.stream().map(Duke::decode).collect(Collectors.toList());
        }
    }

    private void saveTaskList(Path path) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
        }
        List<String> lines = this.list.stream().map(Task::toStringForSaving).collect(Collectors.toList());
        Files.write(path, lines, StandardOpenOption.CREATE);
    }

    private void createAndAddTask(String lineInput) throws DukeEmptyDescriptionException, DukeNoKeywordException {
        String[] splitInput = lineInput.split(Pattern.quote(" "));
        String commandString = splitInput[0];
        String taskDescription;
        Task newTask = null;

        if (splitInput.length == 1) {
            throw new DukeEmptyDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }

        String[] inputWithoutCommand = Arrays.copyOfRange(splitInput, 1, splitInput.length);

        try {
            switch (DukeCommand.valueOf(commandString)) {
                case todo:
                    // empty string array would become empty string
                    taskDescription = String.join(" ", inputWithoutCommand);
                    newTask = new Todo(taskDescription);
                    break;
                case deadline:
                case event:
                    String keyword = commandString.equals(DukeCommand.deadline.getCommand())
                            ? DukeCommand.deadline_by.getCommand()
                            : DukeCommand.event_at.getCommand();
                    int keywordIndex = Arrays.asList(splitInput).indexOf(keyword);
                    if (keywordIndex == -1) {
                        throw new DukeNoKeywordException("OOPS!!! The description must contain a keyword.");
                    }

                    taskDescription = String.join(" ",
                            Arrays.copyOfRange(splitInput, 1, keywordIndex));
                    String deadlineOrTime = String.join(" ",
                            Arrays.copyOfRange(splitInput, keywordIndex + 1, splitInput.length));
                    LocalDate date = LocalDate.parse(deadlineOrTime);

                    newTask = commandString.equals(DukeCommand.deadline.getCommand())
                            ? new Deadline(taskDescription, date)
                            : new Event(taskDescription, date);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        this.list.add(newTask);

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Got it. I've added this task: ");
        outputStreamBuffer.add("  " + newTask);
        outputStreamBuffer.add(String.format("Now you have %d tasks in the list.", list.size()));
        print(outputStreamBuffer);
    }

    private void processInput(String lineInput) {
        String[] splitInput = lineInput.split(Pattern.quote(" "));
        // empty line would output string array of size 1, where the element is empty string
        String commandString = splitInput[0];
        int selectedTaskIndex;

        try {
            switch (DukeCommand.valueOf(commandString)) {
                case bye:
                    bye();
                    break;
                case list:
                    this.printList();
                    break;
                case done:
                    selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                    this.markTaskAsDone(selectedTaskIndex);
                    break;
                case delete:
                    selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                    this.deleteTask(selectedTaskIndex);
                    break;
                case todo:
                case deadline:
                case event:
                    try {
                        this.createAndAddTask(lineInput);
                    } catch (Exception e) {
                        print(e.toString());
                    }
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            print("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "code", "duke", "data", "duke.txt");
        try {
            duke.loadSavedTaskList(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        greet();

        while (true) {
            String lineInput = sc.nextLine();
            duke.processInput(lineInput);
            try {
                duke.saveTaskList(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
