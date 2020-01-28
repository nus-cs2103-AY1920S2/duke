import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    // copy file into TaskList
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] split = nextLine.split("/");
            String indicator = split[0];
            switch (indicator) {
                case "D":
                    Deadline deadline = new Deadline(split[2], split[3]);
                    if (split[1].equals("Y")) {
                        deadline.setCheck();
                    }
                    tasks.add(deadline);
                    break;

                case "E":
                    Event event = new Event(split[2], split[3]);
                    if (split[1].equals("Y")) {
                        event.setCheck();
                    }
                    tasks.add(event);
                    break;

                case "T":
                    Todo todo = new Todo(split[2]);
                    if (split[1].equals("Y")) {
                        todo.setCheck();
                    }
                    tasks.add(todo);
                    break;

                default:
                    break;
            }
        }
        return tasks;
    }

    public void save(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList.tasks) {
            fw.write(t.toStringTxt() + System.lineSeparator());
        }
        fw.close();
    }
}