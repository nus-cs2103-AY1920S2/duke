import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private String filePathname;

    public Storage(String filePath) {
        this.filePathname = filePath;
    }

    public TaskList load() throws DukeException {
        Path filePath = Paths.get(this.filePathname);
        if (Files.notExists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new InvalidTaskFileExecption();
        }

        ArrayList<Task> tasks = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    filePathname));
            String line = reader.readLine();
            Task t;
            while (line != null) {
                String[] tokens = line.split("\\|");
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }

                try {
                    switch (tokens[0]) {
                        case "T":
                            t = new TodoTask(tokens[2]);
                            break;
                        case "D":
                            t = new DeadlineTask(tokens[2], tokens[3]);
                            break;
                        case "E":
                            t = new EventTask(tokens[2], tokens[3]);
                            break;
                        default:
                            throw new InvalidDukeFormatException("Unknown Command.");
                    }
                    if (tokens[1].equals("1")) {
                        t.markAsDone();
                    }
                    tasks.add(t);
                } catch (InvalidDukeFormatException e) {
                    System.out.println("unable to add this item");
                    System.out.println(e.getMessage());
                    System.out.println(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TaskList(tasks);
    }

    public void save(TaskList tasks) {

        final String FILE_PATH = "./data/duke.txt";
        StringBuilder buffer = new StringBuilder();
        for (Task task : tasks.getList()) {
            buffer.append(task.format()).append("\n");
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(FILE_PATH));
            writer.write(buffer.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
