package storage;

import collection.TaskCollection;
import task.Task;
import storage.FileUtility;

import java.util.ArrayList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PersistentStorageObserver implements PropertyChangeListener {
    public PersistentStorageObserver(TaskCollection taskCollection) {
        taskCollection.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event.getPropertyName().equals("tasks updated")) {
            ArrayList<Task> newTasks = (ArrayList<Task>) event.getNewValue();
            FileUtility.save((Object) newTasks, "../../../tasks.tmp");
        }
    }
}
