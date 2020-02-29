import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class handles all input and output from file.
 */

public class Storage {
    private Path filePath;

    /**
     * Creates a new instance of the Storage class.
     * @param filePath The path of the input file.
     */

    public Storage(String filePath) {
        String home = System.getProperty("user.home");
        this.filePath = Paths.get(home, filePath);
    }

    /**
     * Loads all information from the input file.
     * @return The ArrayList of all tasks read from the input file.
     * @throws DukeException This is the DukeException.
     */

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> inputTasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"))) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                Task newTask;
                LocalDate date;
                String[] input = currentLine.split("\\|");
                char firstChar = currentLine.charAt(0);
                if (firstChar == 'T') {
                    newTask = new ToDo(input[2]);
                    if (input[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    if (input.length == 4) {
                        newTask.updateTag(input[3]);
                    }
                    inputTasks.add(newTask);
                }
                if (firstChar == 'D') {
                    date = LocalDate.parse(input[3]);
                    newTask = new Deadline(input[2], date);
                    if (input[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    if (input.length == 5) {
                        newTask.updateTag(input[4]);
                    }
                    inputTasks.add(newTask);
                }
                if (firstChar == 'E') {
                    date = LocalDate.parse(input[3]);
                    newTask = new Event(input[2], date);
                    if (input[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    if (input.length == 5) {
                        newTask.updateTag(input[4]);
                    }
                    inputTasks.add(newTask);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return inputTasks;
    }

    /**
     * Writes back the remaining tasks to the output file.
     * @param dir This is the directory of the output file.
     * @param list This is the TaskList to be written to the output file.
     */

    public void writeBack(String dir, TaskList list) {
        String output = "";
        ArrayList<Task> taskList = list.getList();
        for (int i = 0; i < taskList.size(); i++) {
            Task outputTask = taskList.get(i);
            if (outputTask instanceof ToDo) {
                output += "T" + "|" + outputTask.getDone()
                        + "|" + outputTask.getDescription()
                        + "|" + outputTask.getTag();
            }
            if (outputTask instanceof Deadline) {
                output += "D" + "|" + outputTask.getDone()
                        + "|" + outputTask.getDescription()
                        + "|" + ((Deadline) outputTask).getBy()
                        + "|" + outputTask.getTag();
            }
            if (outputTask instanceof Event) {
                output += "E" + "|" + outputTask.getDone()
                        + "|" + outputTask.getDescription()
                        + "|" + ((Event) outputTask).getAt()
                        + "|" + outputTask.getTag();
            }
            if (i < taskList.size() - 1) {
                output += System.lineSeparator();
            }
        }
        try {
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, dir);
            assert (!output.equals(""));
            Files.write(path, output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops something went wrong");
        }
    }
}
