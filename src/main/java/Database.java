import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;

public class Database {
    static List<Task> records;
    static int amtOfTask;

    /**
     * Constructor of database
     */
    public Database() {
        records = new ArrayList<>();
        amtOfTask = 0;
    }

    /**
     * Add data into database
     * @param data input from user
     * @throws DukeException when wrong format or invalid input or missing information occurs
     */
    public void addData(String data) throws DukeException{
        int indexForSeparator;

        if (data.startsWith("todo")) {
            String info;
            if (!(data.charAt(4) == ' ')) {
                throw new DukeException("Please indicate in this format: todo [description]");
            }
            info = data.substring(5);
            if (info.equals("")) {
                throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
            }
            records.add(new Todo(info));
        } else if (data.startsWith("deadline")) {
            String due;
            if(data.contains(" /by ")) {
                try {
                    indexForSeparator = data.indexOf('/');
                } catch (IndexOutOfBoundsException e) {
                    throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
                }
            } else {
                throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
            }
            String description = data.substring(8, indexForSeparator - 1);
            if (!description.startsWith(" ")) {
                throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
            }
            if (description.strip().equals("")) {
                throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                due = data.substring(indexForSeparator + 4);
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! The due date of a deadline cannot be empty.");
            }
            if (due.strip().equals("")) {
                throw new DukeException("\u2639 OOPS!!! The due date of a deadline cannot be empty.");
            }
            records.add(new Deadline(description, due));
        } else if (data.startsWith("event")) {
            String duration;
            if(data.contains(" /at ")) {
                try {
                    indexForSeparator = data.indexOf('/');
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Please indicate in this format: event [description] /at [duration].");
                }
            } else {
                throw new DukeException("Please indicate in this format: event [description] /at [duration].");
            }

            String description = data.substring(5, indexForSeparator - 1);
            if (!description.startsWith(" ")) {
                throw new DukeException("Please indicate in this format: event [description] /at [duration].");
            }
            if (description.strip().equals("")) {
                throw new DukeException("\u2639 OOPS!!! The description of a event cannot be empty.");
            }
            try {
                duration = data.substring(indexForSeparator + 4);
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("\u2639 OOPS!!! The duration of a events cannot be empty.");
            }
            if (duration.strip().equals("")) {
                throw new DukeException("\u2639 OOPS!!! The duration of a event cannot be empty.");
            }
            records.add(new Event(description, duration));
        } else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        amtOfTask += 1;
    }

    /**
     * Get the listing in database
     * @return listing in database
     */
    public List<Task> getListing() {
        return records;
    }

    /**
     * Set the task as Done
     * @param num index where the task at
     * @throws DukeException when no task found in that index
     */
    public void markDone(int num) throws DukeException{
        try {
            Task task = records.get(num - 1);
            task.setStatusDone();
            records.set(num - 1, task);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No task found in that index!");
        }
    }

    /**
     * Delete the task at the index
     * @param num index where the task located at
     * @return task that being deleted
     * @throws DukeException when no task found in that index
     */
    public Task deleteTask(int num) throws DukeException{
        Task task;
        try {
            task = records.get(num - 1);
            records.remove(num - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No task found in that index!");
        }
        amtOfTask -= 1;
        return task;
    }

    /**
     * Get task.Task at specific index
     * @param num index the task locate at
     * @return task.Task at the index
     */
    public Task getTask(int num) {
        return records.get(num - 1);
    }

    public int getAmountOfTask() {
        return amtOfTask;
    }
}
