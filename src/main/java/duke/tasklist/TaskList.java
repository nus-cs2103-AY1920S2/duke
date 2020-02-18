/**
 * contains the task list, including operations to add/delete tasks in that list
 */

//contains the task list (contains operations to add/delete tasks in the list)
package duke.tasklist;

import duke.tasks.Events;
import duke.tasks.Deadline;
import duke.tasks.ToDos;
import duke.tasks.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public TaskList() {
        list = new ArrayList<Task>();
    }

    public List<Task> getList() {
        return list;
    }

    public void setDone(int doneTask) {
        Task t = list.get(doneTask);
        t.setDone();
    }

    public void undoDone(int doneTask) {
        Task t = list.get(doneTask);
        t.undoDone();
    }

    public void deleteTask(int deleteTask) {
        Task t = list.get(deleteTask);
        list.remove(deleteTask);
    }

    public void addTodo(String task) {
        ToDos t = new ToDos(task);
        list.add(t);
    }

    public void addDeadline(String task, String deadline) {
        Deadline d = new Deadline(task, deadline);
        list.add(d);
    }

    public void addEvent(String event, String time) {
        Events e = new Events(event, time);
        list.add(e);
    }
}