import java.time.*;
import java.util.*;

public class TaskList {
    public ArrayList<Task> listOfTasks;


    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    public Task getTask(int index) throws DukeException{
        if(index >= listOfTasks.size() || index < 0) {
            throw new DukeException("Index out of bounds!");
        }
        return listOfTasks.get(index);
    }

    public void removeTask(int index) {
        listOfTasks.remove(index);
    }

    public void newToDo(String desc) {
        listOfTasks.add(new ToDo(desc));
    }

    public void newEvent(String desc, String timing) {
        listOfTasks.add(new Event(desc, timing));
    }

    public void newDeadline(String desc, String by) {
        listOfTasks.add(new Deadline(desc, by));
    }

    public ArrayList<Task> getTaskList() {
        return listOfTasks;
    }

    public TaskList getMatches(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            String descSplit[] = listOfTasks.get(i).getDescription().split(" ");
            for (int j = 0; j < descSplit.length; j++) {
                if (descSplit[j].equals(keyword)) {
                    matchingTasks.add(listOfTasks.get(i));
                    break;
                }
            }
        }
        TaskList tasks = new TaskList(matchingTasks);
        return tasks;
    }
}
