package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
            try {
                if (!(data.charAt(4) == ' ')) {
                    throw new DukeException("Please indicate in this format: todo [description]");
                }
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("Please indicate in this format: todo [description]");
            }
            info = data.substring(5);
            if (info.strip().equals("")) {
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
            if (indexForSeparator <= 9) {
                throw new DukeException("Please indicate in this format: event [description] /at [duration].");
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
            records.add(new Deadline(description.substring(1), due));
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
            if (indexForSeparator <= 6) {
                throw new DukeException("Please indicate in this format: event [description] /at [duration].");
            }
            String description = data.substring(5, indexForSeparator - 1);
            System.out.println(description);
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
            records.add(new Event(description.substring(1), duration));
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
     * Set the duke.task as Done
     * @param num index where the duke.task at
     * @throws DukeException when no duke.task found in that index
     */
    public void markDone(int num) throws DukeException{
        try {
            Task task = records.get(num - 1);
            task.setStatusDone();
            records.set(num - 1, task);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No duke.task found in that index!");
        }
    }

    /**
     * Delete the duke.task at the index
     * @param num index where the duke.task located at
     * @return duke.task that being deleted
     * @throws DukeException when no duke.task found in that index
     */
    public Task deleteTask(int num) throws DukeException{
        Task task;
        try {
            task = records.get(num - 1);
            records.remove(num - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("No duke.task found in that index!");
        }
        amtOfTask -= 1;
        return task;
    }

    /**
     * Get duke.task.Task at specific index
     * @param num index the duke.task locate at
     * @return duke.task.Task at the index
     */
    public Task getTask(int num) {
        return records.get(num - 1);
    }

    public int getAmountOfTask() {
        return amtOfTask;
    }
}
