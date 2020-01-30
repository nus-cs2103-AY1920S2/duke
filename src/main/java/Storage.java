import java.io.*;
import java.util.ArrayList;

public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void appendFile(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String line = task.toLine();
            writer.write(line);
            writer.newLine();
            writer.close();
        } catch (Exception ignored) {
        }
    }

    public void writeFile(TaskList list) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (int i = 0; i < list.arr.size(); i++) {
                String line = list.arr.get(i).toLine();
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (Exception ignored) {
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> data = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while (true) {
                String str = br.readLine();
                if (str == null) break;
                String[] arr = str.split("/");
                if (arr[0].equals("T")) {
                    Todo task = new Todo(arr[2]);
                    if (arr[1].equals("1")) task.setDone();
                    data.add(task);
                } else if (arr[0].equals("D")) {
                    Deadline task = new Deadline(arr[2], arr[3]);
                    if (arr[1].equals("1")) task.setDone();
                    data.add(task);
                } else { //event
                    Event task = new Event(arr[2], arr[3]);
                    if (arr[1].equals("1")) task.setDone();
                    data.add(task);
                }
            }
        } catch (Exception ignored) {
        }
        return data;
    }

}
