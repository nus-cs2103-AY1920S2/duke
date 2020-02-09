import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * TaskCollection stores the implementation of data structure underlying Tasks.
 * Also implements Observer Pattern on state change of tasks
 */
public class TaskCollection {
    private ArrayList<Task> tasks;
    private ArrayList<PropertyChangeListener> listeners;

    public TaskCollection() {
        this.tasks = new ArrayList<Task>();
        this.listeners = new ArrayList<PropertyChangeListener>();
    }

    public Integer size() {
        return this.tasks.size();
    }

    public Task get(Integer taskIndex) {
        return this.tasks.get(taskIndex);
    }

    /**
     * Add new task to collections.
     * @param task object
     */
    public void add(Task task) {
        ArrayList<Task> oldTasks = this.tasks;
        this.tasks.add(task);
        notifyListeners(this, "add", oldTasks, this.tasks);
    }

    /**
     * remove task from collection at task index.
     * @param taskIndex index of removed task
     */
    public void remove(Integer taskIndex) {
        ArrayList<Task> oldTasks = this.tasks;
        this.tasks.remove(taskIndex.intValue());
        notifyListeners(this, "remove", oldTasks, this.tasks);
    }

    private void notifyListeners(Object object, String property, ArrayList<Task> oldTasks, ArrayList<Task> newTasks) {
        for (PropertyChangeListener name : listeners) {
            name.propertyChange(new PropertyChangeEvent(this, property, oldTasks, newTasks));
        }
    }

    public void addChangeListener(PropertyChangeListener newListener) {
        listeners.add(newListener);
    }
}
