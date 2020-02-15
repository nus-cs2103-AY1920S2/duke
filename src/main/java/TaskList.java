import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> usrInputs;


    public TaskList() {
        usrInputs = Storage.readFromFile();

    }

    public List<Task> getList() {
        return usrInputs;
    }

    /**
     * Lists all the tasks in the list of tasks
     */
    public String list() {
        String listString = "";
        int count = 1;
        for (Task usrTask : usrInputs) {
            listString += (count + ". " + usrTask + "\n");
            count++;
        }
        return listString;
    }

    /**
     * Sets the file at the specified index to done
     *
     * @param index
     * @throws InvalidIndexException
     */
    public String done(int index) throws InvalidIndexException {
        if (index > usrInputs.size() || index < 1) {
            throw new InvalidIndexException();

        }
        Task completedTask = usrInputs.get(index - 1);
        completedTask.setDone();
        return ("Good job, mate. I have marked the following task as done.\n" + completedTask + "\n");

    }

    /**
     * Deletes the file at the specified index
     *
     * @param index
     * @throws InvalidIndexException
     */
    public String delete(int index) throws InvalidIndexException {
        if (index > usrInputs.size() || index < 1) {
            throw new InvalidIndexException();
        }

        Task removedTask = usrInputs.remove(index - 1);
        this.saveToDisk();
        return ("I have removed the following task\n" + removedTask + "\n");

    }

    /**
     * Adds the task to the list of tasks
     *
     * @param task
     */
    public String add(Task task) {
        usrInputs.add(task);
        this.saveToDisk();
        return ("Got it! I've added the following task \n" + task +
                "\nNow you have " + usrInputs.size() + " tasks\n");
    }

    public String tag(int index, String tagDescription) throws InvalidIndexException{
        if (index > usrInputs.size() || index < 1) {
            throw new InvalidIndexException();
        }
        Task currentTask = this.usrInputs.get(index - 1);
        Tag myTag = currentTask.addTag(tagDescription);
        return "I have added this tag: '" + myTag + "' to this task:\n" + currentTask;
    }

    /**
     * Saves the task list to the disk by calling on Storage classes storeIntoFile method
     */
    public void saveToDisk() {
        try {
            Storage.storeIntoFile(usrInputs);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> find(String keyword) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : usrInputs) {
            if (task.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;

    }
}
