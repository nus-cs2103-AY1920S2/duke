package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> lst;

    TaskList() {
        this.lst = new ArrayList<Task>();
    }
    TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    void addTask(Task task) {
        lst.add(task);
    }

    void deleteTask(Task task) throws DukeException {
        if (lst.size() == 0) {
            throw new DukeException("No task to delete");
        }
        lst.remove(task);
    }

    Task getTask(int counter) {
        return lst.get(counter);
    }

    int getSize() {return lst.size();}

}

