package duke.logic;
import duke.commons.Deadline;
import duke.commons.Event;
import duke.commons.Task;
import duke.commons.Todo;
import duke.commons.exceptions.InvalidIndexException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int index) throws InvalidIndexException {
        try {
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Sorry dude but this index is nowhere to be found.");
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getListSize() {
        return this.taskList.size();
    }

    public String printTaskList(Ui ui) {
        String output;
        if (this.taskList.isEmpty()) {
            output = ui.printEmptyListMessage();
        } else {
            output = ui.printTaskMessage();
            for (int i = 1; i <= this.taskList.size(); i++) {
                Task task = this.taskList.get(i - 1);
                output += "\n";
                output += ui.printTask(i, task);
            }
        }
        return output;
    }

    public Task addTask(String commandWord, String[] commands) {
        Task task = null;
        String description;
        String[] descriptions;
        switch (commandWord) {
            case "todo":
                description = commands[1];
                task = new Todo(false, description);
                break;
            case "event":
                descriptions = commands[1].split(" /at ");
                task = new Event(false, descriptions[0], descriptions[1]);
                break;
            case "deadline":
                descriptions = commands[1].split(" /by ");
                task = new Deadline(false, descriptions[0], descriptions[1]);
                break;
            default:
                assert 1 == 0 : "default reached";
        }
        assert task != null : "null task";
        this.taskList.add(task);
        return task;
    }

    public void markTaskAsDone(int doneIndex) {
        Task task = this.taskList.get(doneIndex);
        task.markAsDone();
    }

    public void deleteTask(int deleteIndex) {
        this.taskList.remove(deleteIndex );
    }

    public String findTask(Ui ui, String keyword) {
        boolean isFound = false;
        String output = ui.printFoundTaskMessage();
        for (int i = 1; i <= this.taskList.size(); i++) {
            Task task = this.taskList.get(i - 1);
            if (task.getDescription().contains(keyword)) {
                isFound = true;
                output += "\n";
                output += ui.printTask(i, task);
            }
        }
        if (!isFound) {
            output = ui.printNotFoundTaskMessage();
        }
        return output;
    }
}
