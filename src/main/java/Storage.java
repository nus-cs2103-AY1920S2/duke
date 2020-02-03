import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    String filePath = "data/duke.txt";

    public void loadFile(MyList myList) {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String listItem = sc.nextLine();
                String[] splitStringArr = splitString(listItem);
                String task = splitStringArr[0];
                String status = splitStringArr[1];
                String name = splitStringArr[2];

                switch (task) {
                case "T": Todo todo = new Todo(name);
                          todo.setCompleted(status);
                          myList.setListArray(todo);
                          break;
                case "D": String[] deadlineArray = name.split(" \\(by: ");
                          String deadlineDate = deadlineArray[1].substring(0, deadlineArray[1].indexOf(")"));
                          Deadline deadline = new Deadline(deadlineArray[0], deadlineDate);
                          myList.setListArray(deadline);
                          break;
                case "E": String[] eventArray = name.split(" \\(at: ");
                          String eventDate = eventArray[1].substring(0, eventArray[1].indexOf(")"));
                          Event event = new Event(eventArray[0], eventDate);
                          myList.setListArray(event);
                          break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String string) throws IOException {
        File file = new File(filePath);
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(string + "\n");
        fileWriter.close();
    }

    private String[] splitString(String string) {
        String[] result = new String[3];
        String[] splitString = string.split("\\]");
        String[] retrieveTask = splitString[0].split("\\[");
        String[] retrieveStatus = splitString[1].split("\\[");
        result[0] = retrieveTask[1];
        result[1] = retrieveStatus[1];
        result[2] = splitString[2].substring(1);
        return result;
    }

}
