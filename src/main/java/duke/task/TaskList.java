package duke.task;

import duke.core.Storage;
import duke.core.Message;
import duke.core.Parser;

import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTimeFormatException;
import duke.exception.KeywordNotFoundException;
import duke.exception.TaskIndexException;

import java.util.ArrayList;

/**
 * Represents a list of tasks to be done.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList instance to store and handle tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList instance with a supplied ArrayList of tasks.
     * @param tasks The supplied task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves a task from the list by index.
     * @param index The task index.
     * @return The requested Task object.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a given task as done.
     * @param idx The index of the task in the task list.
     * @return Confirmation message if successful.
     * @throws TaskIndexException Exception when the specified task index is invalid.
     */
    public String doTask(int idx) throws TaskIndexException {
        try {
            Task task = this.tasks.get(idx);
            task.setIsDone(true);
            return Message.DO_TASK + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        }
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     * @return Confirmation message if successful.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return Message.ADD_TASK + "Now you have " + this.tasks.size() + " task(s) in the list.";
    }

    /**
     * Deletes a task from the task list.
     * @param idx The index of the task to be deleted.
     * @return Confirmation message if successful.
     * @throws TaskIndexException The exception when the specified task index is invalid.
     */
    public String deleteTask(int idx) throws TaskIndexException {
        try {
            Task task = this.tasks.get(idx);
            this.tasks.remove(idx);
            return Message.DELETE_TASK + task.toString()
                + "\nNow you have " + this.tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        }
    }

    /**
     * Finds tasks with descriptions containing the specified keyword.
     * @param input User input containing the keyword.
     * @return String containing all tasks matching the keyword.
     * @throws KeywordNotFoundException The exception when the keyword is not found.
     */
    public String findKeyword(String input) throws KeywordNotFoundException {
        String keyword = input.substring(5).trim();
        ArrayList<String> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                searchResults.add(task.toString());
            }
        }
        
        if (!searchResults.isEmpty()) {
            String results = Message.FIND_KEYWORD;
            for (int i = 0; i < searchResults.size(); i++) {
                String result = searchResults.get(i);
                results += (i + 1) + ".";
                results += result;
                if (i < searchResults.size() - 1) {
                    results += "\n";
                }
            }
            return results;
        } else {
            throw new KeywordNotFoundException(Message.KEYWORD_ERROR);
        }
    }

    /**
     * Updates the description of a task.
     * @param idx The task index.
     * @param input The user input.
     * @return Confirmation message if successful.
     * @throws TaskIndexException The exception when the specified task index is invalid.
     * @throws InvalidCommandException The exception when the user inputs an invalid command.
     */
    public String updateDescription(int idx, String input) 
            throws TaskIndexException, InvalidCommandException {
        try {
            Task task = this.tasks.get(idx);
            int newDescriptionIndex = input.indexOf("" + (idx + 1));
            String newDescription = input.substring(newDescriptionIndex + 2);
            task.setDescription(newDescription);
            return Message.UPDATE_DESCRIPTION + task.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } catch (Exception e) {
            throw new InvalidCommandException(Message.UPDATE_GENERAL_ERROR);
        }
    }

    /**
     * Updates the time of a task.
     * @param idx The task index.
     * @param input The user input.
     * @return Confirmation message if successful.
     * @throws TaskIndexException The exception when the specified task index is invalid.
     * @throws InvalidTimeFormatException The exception when the time format is invalid.
     */
    public String updateTime(int idx, String input) 
            throws TaskIndexException, InvalidTimeFormatException, InvalidCommandException {
        try {
            Task task = this.tasks.get(idx);
            int newTimeIndex = input.indexOf("" + (idx + 1));
            String newTime = Parser.reformatDateAndTime(input.substring(newTimeIndex + 2));
            task.setTime(newTime);
            return Message.UPDATE_TIME + newTime;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexException(Message.INDEX_ERROR);
        } catch (Exception e) {
            throw new InvalidCommandException(Message.UPDATE_TIME_GENERAL_ERROR);
        }
    }

    /**
     * Manages a Todo task by parsing user input and storing it into the task list.
     * @param storage The Storage instance used to write the task into the task list file.
     * @param input The given user input.
     * @return Confirmation message if successful.
     * @throws EmptyDescriptionException The exception when an empty description is provided.
     */
    public String manageTodo(Storage storage, String input) throws EmptyDescriptionException {
        if (input.split(" ").length == 1) {
            throw new EmptyDescriptionException(Message.DESCRIPTION_ERROR);
        } else {
            String description = input.substring(input.indexOf(" ") + 1);
            Todo todo = new Todo(description, false);

            String result = "T~0~" + description;
            storage.writeToFile(result);

            return (addTask(todo));
        }
    }

    /**
     * Manages an Event task by parsing user input and storing it into the task list.
     * @param storage The Storage instance used to write the task into the task list file.
     * @param input The given user input.
     * @return Confirmation message if successful.
     * @throws InvalidCommandException The exception when the user inputs an invalid command.
     * @throws EmptyDescriptionException The exception when an empty description is provided.
     * @throws InvalidTimeFormatException The exception when the user inputs an invalid time format.
     */
    public String manageEvent(Storage storage, String input) 
            throws InvalidCommandException, EmptyDescriptionException, InvalidTimeFormatException {
        String description = "";
        String remaining = "";
        String[] split = new String[]{};

        try {
            description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(Message.DESCRIPTION_ERROR);
        }

        try {
            remaining = input.substring(input.indexOf("/") + 1);
            split = remaining.split(" ");

            if (split[0].compareTo("at") == 0) {
                String rawDateTime = input.substring(input.indexOf("/") + 4);
                String time = Parser.reformatDateAndTime(rawDateTime);
                Event event = new Event(description, false, time);
                String result = "E~0~" + description + "~" + time;
                storage.writeToFile(result);

                return addTask(event);
            } else {
                throw new InvalidCommandException(Message.EVENT_FORMAT_ERROR);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.EVENT_FORMAT_ERROR);
        }
    }

    /**
     * Manages a Deadline task by parsing user input and storing it into the task list.
     * @param storage The Storage instance used to write the task into the task list file.
     * @param input The given user input.
     * @return Confirmation message if successful.
     * @throws InvalidCommandException The exception when the user inputs an invalid command.
     * @throws EmptyDescriptionException The exception when an empty description is provided.
     * @throws InvalidTimeFormatException The exception when the user inputs an invalid time format.
     */
    public String manageDeadline(Storage storage, String input) 
            throws InvalidCommandException, EmptyDescriptionException, InvalidTimeFormatException {
        String description = "";
        String remaining = "";
        String[] split = new String[]{};

        try {
            description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(Message.DESCRIPTION_ERROR);
        }

        try {
            remaining = input.substring(input.indexOf("/") + 1);
            split = remaining.split(" ");
            if (split[0].compareTo("by") == 0) {
                String rawDateTime = input.substring(input.indexOf("/") + 4);
                String time = Parser.reformatDateAndTime(rawDateTime);
                Deadline deadline = new Deadline(description, false, time);
                String result = "D~0~" + description + "~" + time;
                storage.writeToFile(result);

                return addTask(deadline);
            } else {
                throw new InvalidCommandException(Message.DEADLINE_FORMAT_ERROR);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidCommandException(Message.DEADLINE_FORMAT_ERROR);
        }
    }

    @Override
    public String toString() {
        String result = "";
        if (this.tasks.isEmpty()) {
            result += "Sorry, your list is currently empty!";
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            String str = (i + 1) + "." + task.toString();
            result += str;

            if (i != this.tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}