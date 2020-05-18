import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Boolean hasEnded = false;
    private Parser parser;
    private static Path filePath = Paths.get("Data", "Duke.txt");
    private static Storage storage = new Storage(filePath.toString());


    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList(this.storage.load());
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        System.out.println(duke.ui.printIntro());
        while(!duke.ui.hasEnded()) {
            String input = duke.ui.getInput();
            try {
                System.out.println(duke.start(input));
            } catch (DukeException e) {
                duke.ui.printErr(e);
            }
        }
    }
    //@@author BransonNg -reused
    //heavily referenced to BransonNg's code

    private String start(String input) throws DukeException {
        switch (input) {
            case "List":
                return this.ui.printList(this.taskList);
            case "list":
                return this.ui.printList(this.taskList);
            case "bye":
                this.hasEnded = true;
                return this.ui.printBye();
            default:
                if (this.parser.isDoneOrDelete(input)) {
                    int taskIndex = this.parser.getTaskIndex(input) - 1;
                    if (taskIndex >= this.taskList.size()) {
                        throw new DukeException("task index out of range!");
                    }
                    if (input.contains("done")) {
                        this.taskList.markDone(taskIndex);

                        try {
                            this.storage.save(taskList);
                        } catch (IOException err) {
                            System.out.println(err.getMessage());
                        }

                        return this.ui.printMarkedTask(this.taskList.get(taskIndex));
                    } else {
                        Task removedTask = this.taskList.removeTask(taskIndex);
                        try {
                            this.storage.save(taskList);
                        } catch (IOException err) {
                            System.out.println(err.getMessage());
                        }

                        return this.ui.printRemovedTask(removedTask, taskList.size());
                    }
                } else if (this.parser.isFind(input)) {
                    String searchTerm = this.parser.getSearchTerm(input);
                    String searchResults =
                            taskList.search(searchTerm)
                                    .stream()
                                    .collect(Collectors.joining(String.format("%n")));
                    if (searchResults.isEmpty()) {
                        return "No matching tasks!";
                    }
                    return this.ui.printSearchResult(searchResults);
                } else if (this.parser.isEdit(input)) {
                    int editTaskIndex = Integer.parseInt(input.split("\\s+")[1]) - 1;
                    Task t = this.taskList.get(editTaskIndex);
                    if (t.getType().equals("todo")) {
                        throw new DukeException("Todo tasks do not have date and time! Only Events and Deadline " +
                                "tasks can be edited!");
                    }
                    if (input.split("\\s+").length < 4) {
                        throw new DukeException("Please provide Date and Time");
                    }
                    String taskDesc = this.parser.getDescription(input);
                    String dateTime = this.parser.getDateTime(taskDesc, "\\d+");
                    LocalDate date = this.parser.getDate(dateTime);
                    LocalTime time = this.parser.getTime(dateTime);
                    LocalDateTime dT= LocalDateTime.of(date, time);
                    t.edit(dT);
                    return this.ui.printEdited(this.taskList);
                } else {
                    if (input.split("\\s+").length < 2) {
                        throw new DukeException("Command not recognised!");
                    }
                    String taskType = this.parser.getType(input);
                    String taskDesc = this.parser.getDescription(input);
                    switch (taskType) {
                        case "todo":
                            ToDo todo = new ToDo(taskDesc);
                            this.taskList.addTask(todo);
                            try {
                                this.storage.save(taskList);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            return this.ui.printTaskAdded(todo, this.taskList.size());
                        case "event":
                            String taskName = this.parser.getTaskName(taskDesc, "/at");
                            String dT = this.parser.getDateTime(taskDesc, "/at");
                            LocalDate date = this.parser.getDate(dT);
                            LocalTime time = this.parser.getTime(dT);
                            LocalDateTime dateTime = LocalDateTime.of(date, time);
                            Event event = new Event(taskName, dateTime);
                            this.taskList.addTask(event);
                            try {
                                this.storage.save(taskList);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            return this.ui.printTaskAdded(event, this.taskList.size());
                        case "deadline":
                            String taskNamee = this.parser.getTaskName(taskDesc, "/by");
                            String dTT = this.parser.getDateTime(taskDesc, "/by");
                            LocalDate datee = this.parser.getDate(dTT);
                            LocalTime timee = this.parser.getTime(dTT);
                            LocalDateTime dateTimee = LocalDateTime.of(datee, timee);
                            Deadline t = new Deadline(taskNamee, dateTimee);
                            this.taskList.addTask(t);
                            try {
                                this.storage.save(taskList);
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                            return this.ui.printTaskAdded(t, this.taskList.size());
                        default:
                            throw new DukeException("Task not recognised!");
                    }
                }
        }
    }

    //@@author

    public String getResponse(String input) {
        try {
            String output = start(input);
            return output;
        } catch (DukeException err ) {
            return err.getMessage();
            }
    }

    public boolean hasEnded() {
        return this.hasEnded;
    }
}