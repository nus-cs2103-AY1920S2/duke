import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class SaveToFile {

    /**
     * Saves tasks to out.txt.
     *
     * @param list TaskList to write to out.txt.
     * @throws IOException If file not available.
     */
    public static void usingFileWriter(String list) throws IOException {
        File file = new File("./out.txt");
        if (file.exists()) {
            FileWriter fileWriter = new FileWriter("./out.txt");
            fileWriter.write(list);
            fileWriter.close();
        } else {
            file.createNewFile();
            FileWriter fileWriter = new FileWriter("./out.txt");
            fileWriter.write(list);
            fileWriter.close();
        }
    }

    /**
     * Loads saved TaskList to Duke.
     *
     * @param filePath File path for duke's task list.
     * @return ArrayList of saved TaskList if not new ArrayList would be returned.
     */
    public ArrayList<Task> loadList(String filePath) {
        ArrayList<Task> arr = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File(filePath));
            while (s.hasNext()) {
                String fileInput = s.nextLine();
                String[] fileStringArr = fileInput.split("\\[");
                if (fileStringArr[1].equalsIgnoreCase("T]")) {
                    String[] todoSplit = fileStringArr[2].split(" ");
                    String[] todoDescription = Arrays.copyOfRange(todoSplit, 1, todoSplit.length);
                    String str = String.join(" ", todoDescription);
                    Todo readTodo = new Todo(str);
                    readTodo.isDone(todoSplit[0]);
                    arr.add(readTodo);
                } else if (fileStringArr[1].equalsIgnoreCase("D]")) {
                    String[] deadlineSplit = fileStringArr[2].split("\\(");
                    String checkIsDone = deadlineSplit[0].substring(0, 2);
                    String deadlineDescription = deadlineSplit[0].substring(3).strip();
                    String deadlineDate = deadlineSplit[1].substring(4, deadlineSplit[1].length() - 1);
                    Deadline readDeadline = new Deadline(deadlineDescription, deadlineDate);
                    readDeadline.isDone(checkIsDone);
                    arr.add(readDeadline);
                } else if (fileStringArr[1].equalsIgnoreCase("E]")) {
                    String[] eventSplit = fileStringArr[2].split("\\(");
                    String checkIsDone = eventSplit[0].substring(0, 2);
                    String eventDescription = eventSplit[0].substring(3).strip();
                    String eventDate = eventSplit[1].substring(4, eventSplit[1].length() - 1);
                    Event readEvent = new Event(eventDescription, eventDate);
                    readEvent.isDone(checkIsDone);
                    arr.add(readEvent);
                }
            }
            s.close();
        } catch (FileNotFoundException | DukeException ex) {
            ex.printStackTrace();
        }
        return arr;
    }

}
