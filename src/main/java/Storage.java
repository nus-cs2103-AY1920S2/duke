import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            if (file.length() != 0) {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream oit = new ObjectInputStream(fin);
                taskList = (ArrayList<Task>) oit.readObject();
            }
        } catch (Exception ex) {
            Ui ui = new Ui();
            ui.showLoadingError();
        }

        return taskList;
    }

    public void store(TaskList taskList) {
        try {
            FileOutputStream fout = new FileOutputStream(this.filePath);
            ObjectOutputStream oot = new ObjectOutputStream(fout);
            oot.writeObject(taskList.tasks);
            oot.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
