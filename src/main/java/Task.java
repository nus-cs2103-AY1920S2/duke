import java.io.*;

public interface Task extends Serializable {
    public static boolean isValidTask(String task){
        return task.equals("todo") || task.equals("event") || task.equals("deadline");
    }

    public void markDone();

}