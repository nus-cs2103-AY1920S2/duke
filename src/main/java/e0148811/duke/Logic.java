package e0148811.duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;

public class Logic {
    private static int ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX = 1;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Logic(Storage s, TaskList tl, Ui u) {
        storage = s;
        tasks = tl;
        ui = u;
    }

    public String execute(String[] instructionByWord) {
        try {
            int lengthOfArray = instructionByWord.length;
            String actionWord = instructionByWord[0].toLowerCase();
            Task task;
            switch (actionWord) {
            case "c":
                // Fallthrough
            case "clear":
                return clearList(instructionByWord, lengthOfArray);
            case "d":
                // Fallthrough
            case "deadline":
                task = createDeadlineOrEventTask("deadline", instructionByWord, lengthOfArray);
                return addAndStoreTask(task);
            case "done":
                return doneOrRemoveTask("done", instructionByWord, lengthOfArray);
            case "e":
                // Fallthrough
            case "event":
                task = createDeadlineOrEventTask("event", instructionByWord, lengthOfArray);
                return addAndStoreTask(task);
            case "f":
                // Fallthrough
            case "find":
                return findTasks(instructionByWord[1], lengthOfArray);
            case "l":
                // Fallthrough
            case "list":
                return printList(instructionByWord, lengthOfArray);
            case "p":
                // Fallthrough
            case "priority":
                return prioritiseTask(instructionByWord, lengthOfArray);
            case "r":
                // Fallthrough
            case "remove":
                return doneOrRemoveTask("remove", instructionByWord, lengthOfArray);
            case "t":
                // Fallthrough
            case "todo":
                task = createATodoTask(instructionByWord, lengthOfArray);
                return addAndStoreTask(task);
            case "":
                ui.throwEmptyLineException();
                break;
            default:
                ui.throwUnknownCommandException();
            }
        } catch (DukeException e) {
            ui.printErrorMessage(e);
        }
        return "";
    }

    private String prioritiseTask(String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray != 3) {
            ui.throwWrongFormatException("\"priority index_of_the_task level_of_priority"
                    + " (which can be one of the following: l/low, n/normal, h/high, t/top)\"");
        }
        int index = getIndexOfTaskToBePrioritised(instructionByWord) - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX;
        if (index < 0 || index >= tasks.getList().size()) {
            ui.throwInvalidIndexException();
        }
        String output = assignPriorityToTask(instructionByWord[2], index);
        storage.writeToHardDisk(tasks.getList());
        return output;
    }

    private String assignPriorityToTask(String priorityLevel, int index) throws DukeException {
        PriorityLevel level = determinePriorityLevel(priorityLevel);
        tasks.assignPriorityToTask(index, level);
        return tasks.showTaskPrioritised(index);
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

    String clearList(String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray != 2) {
            ui.throwWrongFormatException("\"clear a/all\" (if you want to remove all the tasks) " +
                    "OR \"clear d/done\" (if you only want to remove tasks that are marked done)");
        } else {
            if (instructionByWord[1].contains("a")) {
                return clearAllTasks();
            } else if (instructionByWord[1].contains("d")) {
                return clearCompletedTasks();
            } else {
                ui.throwWrongFormatException("\"clear (all)\" (if you want to remove all tasks) " +
                        "OR \"clear d/done\" (if you only want to remove tasks that are marked done)");
            }
        }
        return "";
    }

    private String clearCompletedTasks() throws DukeException {
        tasks.removeCompletedTasks();
        String output = "All completed tasks are removed.";
        storage.writeToHardDisk(tasks.getList());
        return output;
    }

    private String clearAllTasks() throws DukeException {
        tasks.removeAllTasks();
        String output = "All tasks are removed. List is now empty.";
        storage.writeToHardDisk(tasks.getList());
        return output;
    }

    private String printList(String[] instructionByWord, int lengthOfArray) throws DukeException {
        if (lengthOfArray == 1) {
            return tasks.printList();
        } else if (lengthOfArray == 2) {
            PriorityLevel level = determinePriorityLevel(instructionByWord[1]);
            return tasks.printListBasedOnPriority(level);
        } else {
            ui.throwWrongFormatException("\"list\"" + "OR" + "\"list a_priority_level" +
                    "(which include: l/low, n/normal, h/high, t/top)\"");
        }
        return "";
    }

    private PriorityLevel determinePriorityLevel(String priorityLevel) throws DukeException {
        switch (priorityLevel) {
        case "h":
            // Fallthrough
        case "high":
            return PriorityLevel.HIGH;
        case "l":
            // Fallthrough
        case "low":
            return PriorityLevel.LOW;
        case "n":
            // Fallthrough
        case "normal":
            return PriorityLevel.NORMAL;
        case "t":
            // Fallthrough
        case "top":
            return PriorityLevel.TOP;
        default:
            ui.throwOtherException("Invalid level of priority.\n"
                    + "Please input one of the following: l/low, n/normal, h/high, t/top");
        }
        return PriorityLevel.NORMAL;
    }

    String findTasks(String keyword, int lengthOfArray) throws DukeException {
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
                if (s.contains(keyword)) {
                    selectedList.put(j + 1, task);
                    count++;
                    break;
                }
            }
        }
        StringBuilder output = new StringBuilder((count + " task(s) were found containing keyword " + keyword + ":"));
        for (Integer index : selectedList.keySet()) {
            output.append(index).append(". ").append(selectedList.get(index)).append("\n");
        }
        return output.toString();
    }

    private String doneOrRemoveTask(String command, String[] instructionByWord, int lengthOfArray)
            throws DukeException {
        assert command.equals("done") || command.equals("remove") :
                "only two types of commands (done and remove) are considered for this method";
        if (lengthOfArray != 2) {
            if (command.equals("done")) {
                ui.throwWrongFormatException(
                        "\"done a_positive_integer_indicating_the_index_of_the_task_done\"");
            } else {
                ui.throwWrongFormatException(
                        "\"remove a_positive_integer_indicating_the_index_of_the_task_you_want_to_remove\"");
            }
        }
        try {
            int index = Integer.parseInt(instructionByWord[1]) - ONE_TO_CONVERT_BETWEEN_1_BASED_AND_0_BASED_INDEX;
            String output = "";
            if (index >= tasks.getList().size() || index < 0) {
                ui.throwInvalidIndexException();
            } else {
                if (command.equals("done")) {
                    output = tasks.markATaskDone(index);
                } else {
                    output = tasks.removeATask(index);
                }
            }
            storage.writeToHardDisk(tasks.getList());
            return output;
        } catch (NumberFormatException e) {
            ui.throwWrongFormatException("\"done a_positive_integer\"");
        }
        return "";
    }

   String addAndStoreTask(Task t) throws DukeException {
        String output = tasks.addTaskToList(t);
        storage.writeToHardDisk(tasks.getList());
        return output;
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
}
