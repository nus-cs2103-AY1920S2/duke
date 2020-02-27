import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        String home = System.getProperty("user.home");
        this.filePath = Paths.get(home, filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> inputTasks = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"))){
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
                    inputTasks.add(newTask);
                }
                if (firstChar == 'D') {
                    date = LocalDate.parse(input[3]);
                    newTask = new Deadline(input[2], date);
                    if (input[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    inputTasks.add(newTask);
                }
                if (firstChar == 'E') {
                    date = LocalDate.parse(input[3]);
                    newTask = new Event(input[2], date);
                    if (input[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    inputTasks.add(newTask);
                }
            }
        } catch (IOException ex) {

        }
        return inputTasks;
    }

    public void writeBack(String dir, TaskList list) {
        String output = "";
        ArrayList<Task> taskList = list.getList();
        for (int i = 0; i < taskList.size(); i++) {
            Task outputTask = taskList.get(i);
            if (outputTask instanceof ToDo) {
                output += "T" + "|" + outputTask.getDone() + "|" + outputTask.getDescription();
            }
            if (outputTask instanceof Deadline) {
                output += "D" + "|" + outputTask.getDone() + "|" + outputTask.getDescription() + "|" + ((Deadline) outputTask).getBy();
            }
            if (outputTask instanceof Event) {
                output += "E" + "|" + outputTask.getDone() + "|" + outputTask.getDescription() + "|" + ((Event) outputTask).getAt();
            }
            if (i < taskList.size() - 1) {
                output += System.lineSeparator();
            }
        }
        try {
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, dir);
            Files.write(path, output.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops something went wrong");
        }
    }
}
