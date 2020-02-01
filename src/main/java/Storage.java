import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String savePath;

    public Storage(String savePath) {
        this.savePath = savePath;
    }

    public void saveFile(List<Task> list) throws IOException, DukeException {
        PrintWriter out = new PrintWriter(savePath, "UTF-8");
        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i <= list.size(); i++) {
            Task task = list.get(i - 1);
            sb.append(task.getSaveString()).append("\n");
        }
        out.print(sb.toString());
        out.close();
    }

    public List<Task> loadFile() throws IOException {
        List<Task> list = new ArrayList<>(100);
        BufferedReader br = new BufferedReader(new FileReader(savePath));
        String nextLine = br.readLine();
        String[] line;
        if (nextLine != null) {
            line = nextLine.split("\\|");
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].trim();
            }
            while (nextLine != null) {
                Task task;
                Parser p;
                switch (line[0]) {
                    case "T":
                        task = new Todo(line[2]);
                        if (line[1].equals("1")) {
                            task.markDone();
                        }
                        System.out.println("\tLoaded a todo");
                        break;
                    case "D":
                        p = new Parser(line[2], line[3]);
                        task = new Deadline(p.getDescription(), p.time, p.hasTime);
                        if (line[1].equals("1")) {
                            task.markDone();
                        }
                        System.out.println("\tLoaded a deadwinye");
                        break;
                    default:
                        p = new Parser(line[2], line[3]);
                        task = new Event(p.getDescription(), p.time, p.hasTime);
                        if (line[1].equals("1")) {
                            task.markDone();
                        }
                        System.out.println("\tLoaded an event");
                        break;
                }
                list.add(task);
                nextLine = br.readLine();
                if (nextLine != null) {
                    line = nextLine.split("\\|");
                }
            }
        }
        if (list.size() == 0) {
            throw new IOException("Save data is empty, we will stawt fwesh (・`ω´・)");
        }
        return list;
    }
}
