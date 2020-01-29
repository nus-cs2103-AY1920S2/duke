import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    //deals with loading tasks from the file and saving tasks in the file
    String filePath;

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public void retrieveInfo() {
        try {
            Scanner scanner = new Scanner(new File("duke.txt"));
            while (scanner.hasNextLine()) {
                String[] arr  = scanner.nextLine().split("[|]");
                Task newTask = null;

                if  (arr[0].trim().equals("T")) {
                    newTask = new ToDo(arr[2].trim());
                } else if (arr[0].trim().equals("D")) {
                    newTask = new Deadline(arr[2].trim(), arr[3].trim(), Task.formatter);
                } else if (arr[0].trim().equals("E")) {
                    newTask = new Event(arr[2].trim(), arr[3].trim(), Task.formatter);
                }

                if (arr[1].trim().equals("Y")) {
                    newTask.markAsDone();
                }
                Duke.newList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        }
    }

    public  void  updateInfo() {
        String  fileString = "";
        for (int i = 0; i < Duke.newList.size(); i += 1) {
            fileString += Duke.newList.get(i).fileString() + "\n";
        }

        try {
            new File("duke.txt").createNewFile();
            FileWriter fw = new FileWriter("duke.txt");
            fw.write(fileString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}