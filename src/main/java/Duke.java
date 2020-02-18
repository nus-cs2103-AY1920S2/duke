import e0148811.duke.Deadline;
import e0148811.duke.DukeException;
import e0148811.duke.Event;
import e0148811.duke.Parser;
import e0148811.duke.PriorityLevel;
import e0148811.duke.Storage;
import e0148811.duke.Task;
import e0148811.duke.TaskList;
import e0148811.duke.Todo;
import e0148811.duke.Ui;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;

public class Duke {
    private static int ONE_TO_CONVERT_1_BASED_INDEX_INTO_0_BASED = 1;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        new Duke("data/duke.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(ui);
        try {
            ui.showLogo();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } finally {
            ui.linkToTaskList(tasks);
            ui.greet();
        }
    }

    public void run() {
        while (!parser.readInputLine().equals("bye")) {
            String[] instructionByWord = parser.breakIntoWords();
            try {
                int lengthOfArray = instructionByWord.length;
                String actionWord = instructionByWord[0].toLowerCase();
                Task task;
                switch (actionWord) {
                case "clear":
                    clearList(instructionByWord, lengthOfArray);
                    break;
                case "deadline":
                    task = createDeadlineOrEventTask("deadline", instructionByWord, lengthOfArray);
                    addAndStoreTask(task);
                    break;
                case "delete":
                    doneOrDeleteATask("delete", instructionByWord, lengthOfArray);
                    break;
                case "done":
                    doneOrDeleteATask("done", instructionByWord, lengthOfArray);
                    break;
                case "event":
                    task = createDeadlineOrEventTask("event", instructionByWord, lengthOfArray);
                    addAndStoreTask(task);
                    break;
                case "find":
                    findTasks(instructionByWord[1], lengthOfArray);
                    break;
                case "list":
                    printList(lengthOfArray);
                    break;
                case "priority":
                    prioritiseTask(instructionByWord, lengthOfArray);
                    break;
                case "todo":
                    task = createATodoTask(instructionByWord, lengthOfArray);
                    addAndStoreTask(task);
                    break;
                case "":
                    ui.throwEmptyLineException();
                    break;
                default:
                    ui.throwUnknownCommandException();
                }
            } catch (DukeException | IOException e) {
                ui.printErrorMessage(e);
            }
        }
        ui.sayGoodbye();
    }

    private void prioritiseTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray != 3) {
            ui.throwWrongFormatException("\"priority index_of_the_task level_of_priority"
                    + " (which can be one of the following: n/normal, h/high, t/top)\"");
        }
        int index = getIndexOfTaskToBePrioritised(instructionByWord) - ONE_TO_CONVERT_1_BASED_INDEX_INTO_0_BASED;
        if (index < 0 || index >= tasks.getList().size()) {
            ui.throwInvalidIndexException();
        }
        assignPriorityToTask(instructionByWord[2], index);
    }

    private void assignPriorityToTask(String level, int index) throws DukeException {
        if (level.equals("h") || level.equals("high")) {
            tasks.assignPriorityToTask(index, PriorityLevel.HIGH);
        } else if (level.equals("t") || level.equals("top")) {
            tasks.assignPriorityToTask(index, PriorityLevel.TOP);
        } else if (level.equals("n") || level.equals("normal")) {
            tasks.assignPriorityToTask(index, PriorityLevel.NORMAL);
        } else {
            ui.throwOtherException("Invalid level of priority.\n"
                    + "Please input one of the following: h/high, t/top");
        }
        tasks.showTaskPrioritised(index);
    }

    private int getIndexOfTaskToBePrioritised(String[] instructionByWord) throws DukeException {
        int index = -1;
        try {
            index = Integer.parseInt(instructionByWord[1]);
        } catch (NumberFormatException e) {
            ui.throwWrongFormatException("\"priority index_of_the_task level_of_priority"
                    + "(which can be one of the following: h/high, t/top)\"");
        }
        return index;
    }

    void clearList(String[] instructionByWord, int lengthOfArray) throws DukeException, IOException {
        if (lengthOfArray != 2
                || !((instructionByWord[1].equals("all")) || (instructionByWord[1].equals("done")))) {
            ui.throwWrongFormatException("\"clear all/done\"");
        } else {
            String word = instructionByWord[1];
            if (word.equals("all")) {
                System.out.println("Are sure you want to clear all the tasks in the list?");
            } else {
                System.out.println("Are sure you want to clear all completed tasks in the list?");
            }
            System.out.println("Type \"yes\" or \"y\" to proceed. Type any other input to cancel.");
            String input = parser.readInputLine();
            if (input.equals("yes") || input.equals("y")) {
                if (word.equals("all")) {
                    tasks.clearTheList();
                    System.out.println("List is now empty.");
                } else {
                    tasks.cleanTheList();
                }
                storage.writeToHardDisk(tasks.getList());
            } else {
                System.out.println("Canceled.");
            }
        }
    }

    private void printList(int lengthOfArray) throws DukeException {
        if (lengthOfArray != 1) {
            ui.throwWrongFormatException("\"list\"");
        }
        tasks.printList();
    }

    void findTasks(String keyword, int lengthOfArray) throws DukeException {
        if (lengthOfArray != 2) {
            ui.throwWrongFormatException("\"find a_single_word_without_empty_space\"");
        }
        HashMap<Integer, Task> selectedList = new HashMap<>();
        int count = 0;
        for (int j = 0; j < tasks.getList().size(); j++) {
            Task task = tasks.getTask(j);
            String taskDescription = task.getDescription();
            String[] descriptionByWord = taskDescription.split(" ");
            for (String s : descriptionByWord) {
                if (s.equals(keyword)) {
                    selectedList.put(j + 1, task);
                    count++;
                    break;
                }
            }
        }
        System.out.println(count + " task(s) were found containing keyword " + keyword + ":");
        for (Integer index : selectedList.keySet()) {
            System.out.println(index + ". " + selectedList.get(index));
        }
    }

    private void doneOrDeleteATask(String command, String[] instructionByWord, int lengthOfArray)
            throws DukeException {
        assert command.equals("done") || command.equals("delete") :
                "only two types of commands (done and delete) are considered for this method";
        if (lengthOfArray != 2) {
            if (command.equals("done")) {
                ui.throwWrongFormatException(
                        "\"done a_positive_integer_indicating_the_index_of_the_task_done\"");
            } else {
                ui.throwWrongFormatException(
                        "\"delete a_positive_integer_indicating_the_index_of_the_task_you_want_to_delete\"");
            }
        }
        try {
            int index = Integer.parseInt(instructionByWord[1]) - ONE_TO_CONVERT_1_BASED_INDEX_INTO_0_BASED;
            if (index >= tasks.getList().size() || index < 0) {
                ui.throwInvalidIndexException();
            } else {
                if (command.equals("done")) {
                    tasks.markATaskDone(index);
                } else {
                    tasks.deleteATask(index);
                }
            }
            storage.writeToHardDisk(tasks.getList());
        } catch (NumberFormatException e) {
            ui.throwWrongFormatException("\"done a_positive_integer\"");
        } catch (IOException e) {
            ui.throwIOException();
        }
    }

    void addAndStoreTask(Task t) throws IOException {
        tasks.addTaskToList(t);
        storage.writeToHardDisk(tasks.getList());
    }

    private Task createATodoTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray == 1) {
            ui.throwWrongFormatException("\"todo a_string_describing_the_task\"");
        }
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, lengthOfArray));
        return new Todo(false, description, PriorityLevel.NORMAL);
    }

    private Task createDeadlineOrEventTask(String typeOfTask, String[] instructionByWord, int lengthOfArray)
            throws DukeException {
        assert typeOfTask.equals("deadline") || typeOfTask.equals("event") :
                "this method should only create either a deadline task or an event task";
        try {
            int indexOfByOrAt = getIndexOfByOrAt(typeOfTask, instructionByWord, lengthOfArray);
            checkIfIndexIsValid(typeOfTask, lengthOfArray, indexOfByOrAt);
            return constructDeadlineOrEventTask(typeOfTask, instructionByWord, lengthOfArray, indexOfByOrAt);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect format of date.\n"
                    + "The correct format should be YYYY-MM-DD.");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid value for year/month/date");
        }
    }

    private int getIndexOfByOrAt(String typeOfTask, String[] instructionByWord, int lengthOfArray) {
        assert typeOfTask.equals("deadline") || typeOfTask.equals("event");
        int indexOfByOrAt = -1;
        for (int i = 1; i < lengthOfArray; i++) {
            if (typeOfTask.equals("deadline")) {
                if (instructionByWord[i].equals("/by")) {
                    indexOfByOrAt = i;
                    break;
                }
            } else {
                if (instructionByWord[i].equals("/at")) {
                    indexOfByOrAt = i;
                    break;
                }
            }
        }
        return indexOfByOrAt;
    }

    private void checkIfIndexIsValid(String typeOfTask, int lengthOfArray, int indexOfByOrAt) throws DukeException {
        assert typeOfTask.equals("deadline") || typeOfTask.equals("event");
        if (indexOfByOrAt == -1 || indexOfByOrAt == 1 || indexOfByOrAt == (lengthOfArray - 1)) {
            if (typeOfTask.equals("deadline")) {
                ui.throwWrongFormatException("\"deadline a_string_describing_the_task /by YYYY-MM-DD\"");
            } else {
                ui.throwWrongFormatException("\"event a_string_describing_the_task /at YYYY-MM-DD\"");
            }
        }
    }

    private Task constructDeadlineOrEventTask(String typeOfTask, String[] instructionByWord,
                                              int lengthOfArray, int indexOfByOrAt) {
        String description = String.join(" ",
                Arrays.copyOfRange(instructionByWord, 1, indexOfByOrAt));
        String deadline = String.join(" ",
                Arrays.copyOfRange(instructionByWord, indexOfByOrAt + 1, lengthOfArray));
        Task task;
        if (typeOfTask.equals("deadline")) {
            task = new Deadline(false, description, LocalDate.parse(deadline), PriorityLevel.NORMAL);
        } else {
            task = new Event(false, description, LocalDate.parse(deadline), PriorityLevel.NORMAL);
        }
        return task;
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "Duke heard: " + input;
    }
}