import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {
        List<Task> Tasklist = new ArrayList<Task>();

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        String currentline = reader.readLine();

        while (currentline != null) {
            String[] arr = currentline.split(" ", 3);

            String expression = arr[0];

            switch (expression) {
                case "[E]" :
                    Tasklist.add(new Event(arr[1], arr[2]));
                    break;
                case "[D]" :
                    Tasklist.add(new Deadline(arr[1], arr[2]));
                    break;
                case "[T]" :
                    Tasklist.add(new Todo(arr[1]));
                    break;
            }

            currentline = reader.readLine();
        }
        reader.close();

        return Tasklist;
    }

    public void store(TaskList alltasks) throws IOException {
        String line = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
        for (Task task : alltasks.getListOfTask()) {
            if (task.getType().equals("[T]")) {
                line = "[T] " + task.getDesc();
            } else {
                line = task.getType() + " " + task.getDesc() + " " + task.getDate();
            }
            writer.write(line);
            writer.newLine();
            line = "";
        }
        writer.close();
    }

}
