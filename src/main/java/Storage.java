import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Storage {
    private final Path path;
    public final List<Task> tasks = new ArrayList<Task>();

    public Storage(String location){
        this.path = Paths.get(location, "..", "..", "..", "data", "duke.txt");
    }

    public List<Task> loadFromSave() {
        try{
            List<String> lines = Files.readAllLines(path);
            String outputLine;
            String[] arr;
            String type;
            boolean completed;
            String name;
            String others;

            for (int i = 0; i < lines.size(); i++) {
                outputLine = lines.get(i);
                arr = outputLine.split("\\| \\|");
                type = arr[0];
                completed = (arr[1].equals("true"));
                name = arr[2];

                switch (type) {
                case "E":
                    others = arr[3];
                    tasks.add(new Event(name, completed, others));
                    break;
                case "D":
                    others = arr[3];
                    tasks.add(new Deadline(name, completed, others));
                    break;
                case "T":
                    tasks.add(new Todo(name, completed));
                    break;
                default:
                }
            }
        } catch(IOException e) {}
        return tasks;
    }

    public void saveToSave() throws IOException {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += (tasks.get(i).storeFormat() + "\n");
        }
        Files.writeString(path, content);
    }
    
}