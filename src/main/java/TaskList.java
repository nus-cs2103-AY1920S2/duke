import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> usrInputs = new ArrayList<>();

    public TaskList() {
        try{
            usrInputs = Storage.readFromFile();
        } catch(FileNotFoundException e) {

        }
    }

    public void list() {
        int count = 1;
        for (Task usrTask : usrInputs) {
            System.out.println(count + ". " + usrTask);
            count++;
        }
    }

    public void done(int index) throws InvalidIndexException{
        if(index > usrInputs.size()) {
            throw new InvalidIndexException();

        }
        Task completedTask = usrInputs.get(index - 1);
        completedTask.setDone();
        System.out.println("Good job, mate. I have marked the following task as done.\n" + completedTask);

    }

    public void delete(int indice) throws InvalidIndexException{
        if(indice > usrInputs.size()) {
            throw new InvalidIndexException();
        }

        Task removedTask = usrInputs.remove(indice - 1);
        System.out.println("I have removed the following task\n" + removedTask);

    }

    public void add(Task task){
        usrInputs.add(task);
        System.out.println("Got it! I've added the following task \n" + task +
                "\nNow you have " + usrInputs.size() + " tasks");
    }

    public void saveToDisk() {
        try {
            Storage.storeIntoFile(usrInputs);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
