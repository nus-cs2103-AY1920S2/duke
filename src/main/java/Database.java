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
     * @param data data required to add
     */
    public void addData(String data) throws DukeException{
        int indexForSeparator;

        if (data.startsWith("todo")) {
            String info = data.substring(4).stripLeading();
            if (info.equals("")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            records.add(new Todo(data.substring(5)));
        } else if (data.startsWith("deadline")) {
            String due;
            if(data.contains("/by")) {
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
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                due = data.substring(indexForSeparator + 3);
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The due date of a deadline cannot be empty.");
            }
            if (due.strip().equals("")) {
                throw new DukeException("☹ OOPS!!! The due date of a deadline cannot be empty.");
            }
            records.add(new Deadline(description, due));
        } else if (data.startsWith("event")) {
            String duration;
            if(data.contains("/at")) {
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
                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
            }
            try {
                duration = data.substring(indexForSeparator + 4);
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The duration of a events cannot be empty.");
            }
            if (duration.strip().equals("")) {
                throw new DukeException("☹ OOPS!!! The duration of a event cannot be empty.");
            }
            records.add(new Event(description, duration));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
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
     * Set the status of the task to done
     * @param num the index the task at
     */
    public void markDone(int num) {
        Task task = records.get(num - 1);
        task.setStatusDone();
        records.set(num - 1, task);
    }

    /**
     * Get Task at specific index
     * @param num index the task locate at
     * @return Task at the index
     */
    public Task getTask(int num) {
        return records.get(num - 1);
    }

    public int getAmountOfTask() {
        return amtOfTask;
    }
}
