import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.print("can't load file.");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        //noinspection InfiniteLoopStatement
        while (true) {
            String lineInput = ui.readCommand();
            this.processInput(lineInput);
            try {
                storage.save(tasks);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void markTaskAsDone(int taskIndex) {
        Task selectedTask = this.tasks.get(taskIndex);
        selectedTask.markAsDone();

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Nice! I've marked this task as done: ");
        outputStreamBuffer.add("  " + selectedTask);
        ui.print(outputStreamBuffer);
    }

    private void deleteTask(int taskIndex) {
        Task selectedTask = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Noted. I've removed this task: ");
        outputStreamBuffer.add("  " + selectedTask);
        outputStreamBuffer.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        ui.print(outputStreamBuffer);
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

        this.tasks.add(newTask);

        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Got it. I've added this task: ");
        outputStreamBuffer.add("  " + newTask);
        outputStreamBuffer.add(String.format("Now you have %d tasks in the list.", tasks.size()));
        ui.print(outputStreamBuffer);
    }

    private void processInput(String lineInput) {
        String[] splitInput = lineInput.split(Pattern.quote(" "));
        // empty line would output string array of size 1, where the element is empty string
        String commandString = splitInput[0];
        int selectedTaskIndex;

        try {
            switch (DukeCommand.valueOf(commandString)) {
                case bye:
                    ui.bye();
                    break;
                case list:
                    ui.printList(tasks);
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
                        ui.print(e.toString());
                    }
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            ui.print("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "code", "duke", "data", "duke.txt");
        new Duke(path).run();
    }
}
