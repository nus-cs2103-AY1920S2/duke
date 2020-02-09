import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PersistentStorageObserver implements PropertyChangeListener {
    public PersistentStorageObserver(TaskCollection taskCollection) {
        taskCollection.addChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        System.out.println("Change property");
    }
}
