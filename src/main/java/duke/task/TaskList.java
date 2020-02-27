package duke.task;

import java.util.ArrayList;

import duke.Ui;
import duke.Storage;
import duke.Parser;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidTimeFormatException;

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
     * Marks a given task as done.
     * @param idx The index of the task in the task list.
     */
    public String doTask(int idx) {
        Task task = this.tasks.get(idx);
        task.setIsDone(true);
        return "Mmm, Cute thinks your hardworkingness smells yummy!\n" + task.toString();
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return "Wow, you add tasks faster than I eat fishes! Hmm...\n"
            + "Now you have " + this.tasks.size() + " task(s) in the list.";
    }

    /**
     * Deletes a task from the task list.
     * @param idx The index of the task to be deleted.
     */
    public String deleteTask(int idx) {
        try {
            Task task = this.tasks.get(idx);
            this.tasks.remove(idx);
            return ("Noted. I've eaten - uhh, I mean, removed this task:\n"
                + task.toString()
                + "\nNow you have " + this.tasks.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            return ("Task index is invalid. Try again!");
        }
    }

    /**
     * Finds tasks with descriptions containing the specified keyword.
     * @param input User input containing the keyword.
     * @return String containing all tasks matching the keyword.
     */
    public String findKeyword(String input) {
        String keyword = input.substring(5).trim();
        ArrayList<String> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                searchResults.add(task.toString());
            }
        }
        
        String results = "";
        for (int i = 0; i < searchResults.size(); i++) {
            String result = searchResults.get(i);
            results += (i + 1) + ".";
            results += result;
            if (i < searchResults.size() - 1) {
                results += "\n" + "     ";
            }
        }
        return results;
    }

    public String updateDescription(int idx, String input) {
        try {
            Task task = this.tasks.get(idx);
            int newDescriptionIndex = input.indexOf("" + (idx + 1));
            String newDescription = input.substring(newDescriptionIndex + 2);
            task.setDescription(newDescription);
            return "Noted. I've updated this task:\n"
                + task.toString();
        } catch (IndexOutOfBoundsException e) {
            return ("Task index is invalid. Try again!");
        }
    }

    public String updateTime(int idx, String input) {
        try {
            Task task = this.tasks.get(idx);
            int newTimeIndex = input.indexOf("" + (idx + 1));
            String newTime = Parser.reformatDateAndTime(input.substring(newTimeIndex + 2));
            task.setTime(newTime);
            return "Ooh, you updated the time, but is it time for me to eat yet?\n"
                + newTime;
        } catch (IndexOutOfBoundsException e) {
            return ("Task index is invalid. Try again!");
        }
    }

    /**
     * Manages a Todo task by parsing user input and storing it into the task list.
     * @param storage The Storage instance used to write the task into the task list file.
     * @param input The given user input.
     * @param fileName The path to the task list file.
     */
    public String manageTodo(Storage storage, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("todo");
            } else {
                String description = input.substring(input.indexOf(" ") + 1);
                Todo todo = new Todo(description, false);

                String result = "T~0~" + description;
                storage.writeToFile(fileName, result);

                return (addTask(todo));
            }
        } catch (EmptyDescriptionException e) {
            return ("Oops! The description of a " + e.getMessage() + " cannot be empty.");
        }
    }

    /**
     * Manages an Event task by parsing user input and storing it into the task list.
     * @param storage The Storage instance used to write the task into the task list file.
     * @param input The given user input.
     * @param fileName The path to the task list file.
     */
    public String manageEvent(Storage storage, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("event");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);

                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");

                if (split[0].compareTo("at") == 0) {
                    String time = Parser.reformatDateAndTime(input.substring(input.indexOf("/") + 4));
                    Event event = new Event(description, false, time);
                    String result = "E~0~" + description + "~" + time;
                    storage.writeToFile(fileName, result);

                    return addTask(event);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            return "Oops! The description of an " + e.getMessage() + " cannot be empty.";
        } catch (InvalidTimeFormatException e) {
            return "Your time format is incorrect. Try: /at yyyy-mm-dd 2300";
        } catch (Exception e) {
            return "Sorry, invalid syntax or command. Please try again!";
        }
    }

    /**
     * Manages a Deadline task by parsing user input and storing it into the task list.
     * @param storage The Storage instance used to write the task into the task list file.
     * @param input The given user input.
     * @param fileName The path to the task list file.
     */
    public String manageDeadline(Storage storage, String input, String fileName) {
        try {
            if (input.split(" ").length == 1) {
                throw new EmptyDescriptionException("deadline");
            } else {
                String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/") - 1);
                
                String remaining = input.substring(input.indexOf("/") + 1);
                String[] split = remaining.split(" ");

                if (split[0].compareTo("by") == 0) {
                    String time = Parser.reformatDateAndTime(input.substring(input.indexOf("/") + 4));
                    Deadline deadline = new Deadline(description, false, time);
                    String result = "D~0~" + description + "~" + time;
                    storage.writeToFile(fileName, result);

                    return addTask(deadline);
                } else {
                    throw new InvalidTimeFormatException();
                }
            }
        } catch (EmptyDescriptionException e) {
            return "Oops! The description of a " + e.getMessage() + " cannot be empty.";
        } catch (InvalidTimeFormatException e) {
            return "Your time format is incorrect. Try: /by [time]";
        } catch (Exception e) {
            return "Sorry, invalid syntax or command. Please try again!";
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