import java.util.*;
import java.io.*;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public void printList(){
        System.out.print("____________________________________________________________\n" +
                "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print("____________________________________________________________\n");
    }

    public void markTaskDone(int index){
        tasks.get(index).markAsDone();
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n" + tasks.get(index).toString()
                + "\n____________________________________________________________\n");
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public int getSize(){
        return tasks.size();
    }

    public Task getIndex(int index){
        return tasks.get(index);
    }

    public Task removeTask(int index){
        Task task = tasks.remove(index);
        return task;
    }

    // Empty Constructor
    public TaskList(){}

    public TaskList(File f) throws DukeException{
        try {
            readFile(f);
        }
        catch (Exception e){
            throw new DukeException("No file found");
        }
    }

    private void readFile(File f) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            char c = input.charAt(0);
            if (c == 'T') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                Task t = new Todo(description, isDone);
                tasks.add(t);
            } else if (c == 'D') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String deadline = input.split("/")[3];
                Task t = new Deadline(description, isDone, deadline);
                tasks.add(t);
            } else if (c == 'E') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String deadline = input.split("/")[3];
                Task t = new Event(description, isDone, deadline);
                tasks.add(t);
            }
        }
    }


    public void search(String searchString){
        ArrayList<Task> searchList = new ArrayList<>();
        for (Task t: tasks){
            if(t.getDescription().contains(searchString)){
                searchList.add(t);
            }
        }
        System.out.print("____________________________________________________________\n" +
                "Here are the matching tasks in your list:\n");
        for (int i = 0; i < searchList.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print("____________________________________________________________\n");
    }
}
