import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PersistentStorageObserver implements PropertyChangeListener {
    public PersistentStorageObserver(TaskCollection taskCollection) {
        taskCollection.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        try {
            System.out.println("Change property");
            ArrayList<Task> newTasks = (ArrayList<Task>) event.getNewValue();
            FileOutputStream fos = new FileOutputStream("../../tasks.tmp");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(newTasks);
            oos.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
