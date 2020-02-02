import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class SaveToFile {
    public static void usingFileWriter(String list) throws IOException {
        FileWriter fileWriter = new FileWriter("./out.txt");
        fileWriter.write(list);
        fileWriter.close();
    }

    public ArrayList<Task> loadList() {
        ArrayList<Task> arr = new ArrayList<>();
        try {
            Scanner s = new Scanner(new File("./out.txt"));
            while (s.hasNext()) {
                String fileInput = s.nextLine();
                String[] fileStringArr = fileInput.split("\\[");
                if (fileStringArr[1].equalsIgnoreCase("T]")) {
                    String[] todoSplit = fileStringArr[2].split(" ");
                    Todo readTodo = new Todo(todoSplit[1]);
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
