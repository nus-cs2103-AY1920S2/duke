import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] task = s.nextLine().split(" \\| ");
                Task temp = null;
                switch (task[0]) {
                    case "T":
                        temp = new ToDo(task[2]);
                        break;
                    case "D":
                        temp = new Deadline(task[2], task[3]);
                        break;
                    case "E":
                        temp = new Event(task[2], task[3]);
                        break;
                    default:
                        break;
                }
                if (task[1].equals("1")) {
                    temp.markAsDone();
                }
                list.add(temp);
            }
            s.close();
        } catch (FileNotFoundException e) {
            createNewFile();
        }
        return list;
    }

    private void createNewFile() throws DukeException{
        try {
            Path path = Paths.get(filePath);
            Path parent = path.getParent();
            Files.createDirectories(parent);
            Files.createFile(path);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Could not create file for data.");
        }
    }

    public void saveTasksToStorage(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task: tasks.getTasks()) {
                fw.write(task.toSaveName());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Fail to save tasks to storage.");
        }

    }
}
