import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;

/**
 * Storage class that deals with loading tasks from a file
 * and saving tasks into a file.
 */
public class Storage {

    private File file;

    /**
     * Storage class constructor.
     * @param filepath Filepath of the file to load tasks from.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
    }

    /**
     * Method which allows Storage class to read the file and load tasks
     * that are stored in the file.
     * @return ArrayList of tasks that stores all tasks written in the file.
     */
    public ArrayList<Task> readFile() {
        ArrayList<Task> dukeList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] command = s.nextLine().split("/");

                String taskType = command[0].trim();
                boolean isDone;
                if (command[1].trim().equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }

                switch (taskType) {
                case "deadline":
                    String description = command[2].trim();
                    String by = command[3].trim();
                    dukeList.add(new Deadline(description, by));
                    if (isDone) {
                        dukeList.get(dukeList.size() - 1).markAsDone();
                    }
                    break;
                case "event":
                    description = command[2].trim();
                    String at = command[3].trim();
                    dukeList.add(new Event(description, at));
                    if (isDone) {
                        dukeList.get(dukeList.size() - 1).markAsDone();
                    }
                    break;
                case "todo":
                    description = command[2].trim();
                    dukeList.add(new ToDo(description));
                    if (isDone) {
                        dukeList.get(dukeList.size() - 1).markAsDone();
                    }
                    break;
                default:
                }
            }
        } catch (FileNotFoundException | ParseException e1) {
            System.out.println(e1.getMessage());
        }
        return dukeList;
    }

    /**
     * Method which allows Storage class to save the tasks in the
     * TaskList and write all the tasks into a file.
     * @param taskList ArrayList of tasks that contains all tasks to be saved.
     */
    public void saveFile(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(file);
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    String str = "todo" + "/" + task.getDone() + "/" + task.getDescription() + "\n";
                    writer.write(str);
                } else if (task instanceof Event) {
                    String str = "event" + "/" + task.getDone() + "/"
                            + task.getDescription() + "/" + ((Event) (task)).getAt() + "\n";
                    writer.write(str);
                } else {
                    String str = "deadline" + " /" + task.getDone() + " /"
                            + task.getDescription() + "/ " + ((Deadline) (task)).getBy() + "\n";
                    writer.write(str);
                }
            }
            writer.close();
        } catch (IOException e2) {
            System.out.println(e2.getMessage());
        }
    }

}
