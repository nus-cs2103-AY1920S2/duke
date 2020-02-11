import java.time.LocalDateTime;

public class DetectDuplicates {
    private String str;
    private String type;
    private LocalDateTime time;

    DetectDuplicates(String str, String type, LocalDateTime time) {
        this.str = str;
        this.type = type;
        this.time = time;
    }

    DetectDuplicates(String str, String type) {
        this.str = str;
        this.type = type;
    }

    /**
     * Removes all duplicate tasks from the list.
     *
     * @param tasks List of tasks
     * @param storage Storage object
     */
    public void removeDuplicates(TaskList tasks, Storage storage) {
        if (tasks.getList().size() > 0) {
            for (int i = 0; i < tasks.getList().size(); i++) {
                if (tasks.getList().get(i).getTaskName().equalsIgnoreCase(str)
                        && tasks.getList().get(i).getType().equalsIgnoreCase(type)) {
                    Task ob = tasks.getList().get(i);
                    if (ob.getType() == "todo") {
                        Delete d = new Delete(i + 1);
                        d.execute(tasks, storage);
                    } else {
                        LocalDateTime object;
                        if (ob.getType() == "deadline") {
                            object = ((Deadline)ob).getTime();
                        } else {
                            object = ((Event) ob).getTime();
                        }
                        if (this.time.isEqual(object)) {
                            Delete d = new Delete(i + 1);
                            d.execute(tasks, storage);
                        }
                    }
                }
            }
        }
    }

}
