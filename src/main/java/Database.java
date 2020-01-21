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
    public void addData(String data) throws NoSuchFieldException{
        if (data.startsWith("todo")) {
            records.add(new Todo(data.substring(5)));
        } else if (data.startsWith("deadline")) {
            int indexForSeparator = data.indexOf('/');
            String description = data.substring(9, indexForSeparator - 1);
            String due = data.substring(indexForSeparator + 4);
            records.add(new Deadline(description, due));
        } else if (data.startsWith("event")) {
            int indexForSeparator = data.indexOf('/');
            String description = data.substring(6, indexForSeparator - 1);
            String duration = data.substring(indexForSeparator + 4);
            records.add(new Event(description, duration));
        } else {
            throw new NoSuchFieldException("Invalid input!");
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
