import java.util.ArrayList;
import java.util.List;

public class Database {
    static List<Task> records;

    /**
     * Constructor of database
     */
    public Database() {
        records = new ArrayList<>();
    }

    /**
     * Add data into database
     * @param data data required to add
     */
    public void addData(String data) {
        records.add(new Task(data));
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
}
