import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;;

public class Storage {
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(fileName);

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String result = sc.nextLine();
                String[] split = result.split("~");
                String command = split[0];
                if (command.compareTo("T") == 0) {
                    Boolean isDone = Integer.parseInt(split[1]) == 1 ? true : false;
                    Todo todo = new Todo(split[2], isDone);
                    tasks.add(todo);
                } else if (command.compareTo("E") == 0) {
                    Boolean isDone = Integer.parseInt(split[1]) == 1 ? true : false;
                    Event event = new Event(split[2], isDone, split[3]);
                    tasks.add(event);
                } else if (command.compareTo("D") == 0) {
                    Boolean isDone = Integer.parseInt(split[1]) == 1 ? true : false;
                    Deadline deadline = new Deadline(split[2], isDone, split[3]);
                    tasks.add(deadline);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        }

        return tasks;
    }

    public void writeToFile(String fileName, String data) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(data);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            Ui.printLines("Sorry, the file input is invalid.");
        }
    }

    public void doTask(int idx) {
        File originalFile = new File(this.fileName);
        File tempFile = new File("../data/temp.txt");

        try {
            FileWriter fw = new FileWriter("../data/temp.txt", false);
            Scanner sc = new Scanner(originalFile);
            int i = 1;

            while (sc.hasNext()) {
                if (i == idx) {
                    String originalTask = sc.nextLine();
                    String modifiedTask = originalTask.replaceFirst("0", "1");
                    fw.write(modifiedTask + "\n");
                } else {
                    fw.write(sc.nextLine() + "\n");
                }
                i++;
            }
            sc.close();
            fw.close();

            originalFile.delete();
      
            tempFile.renameTo(originalFile);
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        } catch (IOException e) {
            Ui.printLines("IOException");
        }
    }

    public void deleteTask(int idx) {
        File originalFile = new File(this.fileName);
        File tempFile = new File("../data/temp.txt");

        try {
            FileWriter fw = new FileWriter("../data/temp.txt", false);
            Scanner sc = new Scanner(originalFile);
            int i = 1;

            while (sc.hasNext()) {
                String task = sc.nextLine();
                if (i != idx) {
                    fw.write(task + "\n");
                }
                i++;
            }
            sc.close();
            fw.close();

            originalFile.delete();
      
            tempFile.renameTo(originalFile);
        } catch (FileNotFoundException e) {
            Ui.printLines("File not found. Try again!");
        } catch (IOException e) {
            Ui.printLines("IOException");
        }
    }
}