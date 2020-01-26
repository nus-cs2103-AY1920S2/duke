import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class TaskReader {
    public ArrayList<Task> getTasksFromFile(String filepath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String currLine = s.nextLine();
            String taskElements[] = currLine.split(" \\| ");
            switch (taskElements[0]) {
                case "T":
                    Task todo = new ToDo(taskElements[2]);
                    if (taskElements[1].equals("1"))
                        todo.setDone();
                    tasks.add(todo);
                    break;
                case "D":
                    Task deadline = new Deadline(taskElements[2], taskElements[3]);
                    if (taskElements[1].equals("1"))
                        deadline.setDone();
                    tasks.add(deadline);
                    break;
                case "E":
                    Task event = new Event(taskElements[2], taskElements[3]);
                    if (taskElements[1].equals("1"))
                        event.setDone();
                    tasks.add(event);
                    break;
            }
        }
        return tasks;
    }
}