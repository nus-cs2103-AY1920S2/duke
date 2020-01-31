import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> usrInputs = new ArrayList<>();


    public TaskList() {
        usrInputs = Storage.readFromFile();

    }

    /**
     * Lists all the tasks in the list of tasks
     */
    public void list() {
        int count = 1;
        for (Task usrTask : usrInputs) {
            System.out.println(count + ". " + usrTask);
            count++;
        }
    }

    /**
     * Sets the file at the specified index to done
     * @param index
     * @throws InvalidIndexException
     */
    public void done(int index) throws InvalidIndexException{
        if(index > usrInputs.size()) {
            throw new InvalidIndexException();

        }
        Task completedTask = usrInputs.get(index - 1);
        completedTask.setDone();
        System.out.println("Good job, mate. I have marked the following task as done.\n" + completedTask);

    }

    /**
     * Deletes the file at the specified index
     * @param index
     * @throws InvalidIndexException
     */
    public void delete(int index) throws InvalidIndexException{
        if(index > usrInputs.size()) {
            throw new InvalidIndexException();
        }

        Task removedTask = usrInputs.remove(index - 1);
        System.out.println("I have removed the following task\n" + removedTask);

    }

    /**
     * Adds the task to the list of tasks
     * @param task
     */
    public void add(Task task){
        usrInputs.add(task);
        System.out.println("Got it! I've added the following task \n" + task +
                "\nNow you have " + usrInputs.size() + " tasks");
    }

    /**
     * Saves the task list to the disk by calling on Storage classes storeIntoFile method
     */
    public void saveToDisk() {
        try {
            Storage.storeIntoFile(usrInputs);
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> find(String keyword){
        List<Task> foundTasks = new ArrayList<>();
        for(Task task : usrInputs) {
            if(task.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;

    }
}
