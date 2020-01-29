import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> store;
    private PrintWriter writer;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void storeData(TaskList t) {
        store = t.getStore();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < store.size(); i++) {
            Task curr = store.get(i);
            switch (curr.getType()) {
                case TODO:
                    sb.append("T | " + (curr.isDone ? 1 : 0) + " | " + curr.description + "\n");
                    break;
                case EVENT:
                    sb.append("E | " + (curr.isDone ? 1 : 0) + " | " + curr.description + " | " + curr.time + "\n");
                    System.out.println("curr time " + curr.time);
                    System.out.println("curr description " + curr.description);
                    break;
                case DEADLINE:
                    sb.append("D | " + (curr.isDone ? 1 : 0) + " | " + curr.description + " | " + curr.time + "\n");
                    break;
            }
        }

        try {
            writer = new PrintWriter(filePath);
            writer.write(sb.toString());
            writer.flush();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe);
        }

    }

    public ArrayList loadData() {
        store = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader( new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                int x = line.indexOf('|');
                String command = line.substring(0, x-1);
                switch (command) {
                    case "T":
                        ToDo task = new ToDo(line.substring(8), "");
                        if (line.substring(4, 5).equals("1")) {
                            task.markAsDone() ;
                        }
                        this.store.add(task);
                        break;
                    case "E":
                        String details = line.substring(8);
                        int y = details.indexOf('|');
                        System.out.println(details);
                        Event event = new Event(details.substring(0, y-1), details.substring(y+2));
                        if (line.substring(4, 5).equals("1")) {
                            event.markAsDone() ;
                        }
                        this.store.add(event);
                        break;
                    case "D":
                        String detail = line.substring(8);
                        int z = detail.indexOf('|');
                        System.out.println(detail);
                        Deadline deadline = new Deadline(detail.substring(0, z-1), detail.substring(z+2));
                        if (line.substring(4, 5).equals("1")) {
                            deadline.markAsDone() ;
                        }
                        this.store.add(deadline);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return store;
    }

}
