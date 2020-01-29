import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public interface Task extends Serializable {
    static List<Task> taskList = new ArrayList<>();
    static final long serialVersionUID = 1L;

    public static void addTask(String taskName){
        try {
            String taskType = taskName.split(" ", 2)[0];
            String taskDesc = taskName.split(" ", 2)[1];
            Task newTask;
            if (taskType.equals("todo")) {
                newTask = new ToDo(taskDesc);
            } else if (taskType.equals("deadline")) {
                String[] in = taskDesc.split("/",2);
                newTask = new Deadline(in[0], in[1]);
            } else {
                String[] in = taskDesc.split("/", 2);
                newTask = new Event(in[0], in[1]);
            }
            taskList.add(newTask);
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + newTask);
            System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e){
            System.err.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
        } finally {
            saveTask();
        }
    }

    public static void deleteTask(String taskName){
        try {
            String taskNum = taskName.split(" ", 2)[1];
            Task currTask = taskList.get(Integer.parseInt(taskNum) - 1);
            taskList.remove(currTask);
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + currTask);
            System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e){
            System.err.println("     ☹ OOPS!!! Please input a valid number in the range of the task list to delete.");
        } catch (NumberFormatException e) { // when non-int arg provided
            System.err.println("OOPS!!! Delete must take a valid integer in the range of the task list.");
        } finally {
            saveTask();
        }
    }
    public static void printList(){
        System.out.println("     Here are the tasks in your list:");
        int i = 1;
        for(Task task : Task.taskList){
            System.out.println("     " + i + "." + task);
            i++;
        }
    }

    public static void printDone(String in){
        try{
            int num = Integer.parseInt(in.substring(5));
            System.out.println("     Nice! I've marked this task as done: ");
            Task taskDone = taskList.get(num - 1);
            taskDone.markDone();
            String out =  "       " + taskDone;
            System.out.println(out);
        } catch (IndexOutOfBoundsException e){ // when no int arg provided
            System.err.println("OOPS!!! Done must take a valid number in the range of the task list.");
        } catch (NumberFormatException e) { // when non-int arg provided
            System.err.println("OOPS!!! Done must take a valid integer in the range of the task list.");
        } finally {
            saveTask();
        }
    }

    public static boolean isValidTask(String task){
        return task.equals("todo") || task.equals("event") || task.equals("deadline");
    }

    public static void saveTask(){
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "data", "Task.txt");
        try {
            FileOutputStream fileOut = new FileOutputStream(String.valueOf(path));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(taskList);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadTask() {
        String home = System.getProperty("user.dir");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "data", "Task.txt");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            try {
                FileInputStream fileIn = new FileInputStream(String.valueOf(path));
                if (fileIn.available() > 0){
                    ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                    try {
                        List<Task> obj = (ArrayList<Task>) objectIn.readObject();
                        taskList.addAll(obj);
                    } catch (IOException ex) {} // end file reading if EOF
                    finally {
                        objectIn.close();
                    }
                fileIn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void markDone();

}