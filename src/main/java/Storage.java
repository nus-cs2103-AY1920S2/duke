import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File textFile;

    public Storage() {
        if (!Files.exists(Paths.get("./data"))) {
            boolean ok = new File("./data").mkdir();
            if (!ok) {
                System.err.println("Error in creating directory.\n");
            }
        }
        textFile = new File("./data/duke.txt");
    }

    public void saveList(List<Task> taskList) throws IOException {
        StringBuilder writeContents = new StringBuilder();
        for (Task v : taskList) {
            writeContents.append(v.writeFormat());
            writeContents.append("\n");
        }

        String path = textFile.getAbsolutePath();
        FileWriter fw = new FileWriter(path);
        fw.write(writeContents.toString());
        fw.close();
    }

    public List<Task> loadList() throws IllegalArgumentException, FileNotFoundException {
        Scanner sc = new Scanner(textFile);
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            switch (s.charAt(0)) {
                case 'T':
                    taskList.add(Todo.readFormat(s));
                    break;
                case 'D':
                    taskList.add(Deadline.readFormat(s));
                    break;
                case 'E':
                    taskList.add(Event.readFormat(s));
                    break;
                default:
                    throw new IllegalArgumentException("No corresponding command found");
            }
        }
        return taskList;
    }
}
