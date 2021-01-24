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

    /**
     * Creates Storage object to read and write data from File object.
     */
    public Storage() {
        if (!Files.exists(Paths.get("./data"))) {
            boolean ok = new File("./data").mkdir();
            if (!ok) {
                System.err.println("Error in creating directory.\n");
            }
        }
        textFile = new File("./data/duke.txt");
    }

    /**
     * Saves current task list into text file, stored in ./data/duke.txt.
     * @param taskList list of tasks to store
     * @throws IOException Exception if file in default location is not found
     */
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

    /**
     * Loads list of tasks from text file.
     * @return List of Tasks parsed from text file
     * @throws IllegalArgumentException if text file contains invalid line
     * @throws FileNotFoundException if file cannot be found in default location
     */
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
