import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that handles loading tasks from file and saving tasks in the file.
 */
public class Storage {

    private File file;

    /**
     * Creates a Storage object with the filepath provided.
     * If filepath does not exist, then the file will be created.
     *
     * @param filePath A string representation of the file directory.
     */
    public Storage(String filePath) {
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file = new File("data");
                file.mkdir();
                file = new File("data/duke.txt");
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Updates task in file to be marked as done.
     *
     * @param number An Integer that represents the task index.
     */
    public void updateDone(int number) {
        try {
            String s = "";
            Scanner scanFile = new Scanner(file);
            for (int i = 1; i <= TaskList.size(); i++) {
                if (i == number) {
                    if (i == TaskList.size()) {
                        s = s + scanFile.nextLine().replaceFirst("0", "1");
                    } else {
                        s = s + scanFile.nextLine().replaceFirst("0", "1") + System.lineSeparator();
                    }
                } else {
                    if (i == TaskList.size()) {
                        s = s + scanFile.nextLine();
                    } else {
                        s = s + scanFile.nextLine() + System.lineSeparator();
                    }
                }
            }
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(s);
            fw.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tInvalid task number.");
        } catch (FileNotFoundException e) {
            System.out.println("\tFile does not exist");
        } catch (IOException e) {
            System.out.println("\t" + e.getMessage());
        }
    }

    /**
     * Loads the tasks in file into an ArrayList of Task.
     *
     * @return ArrayList containing tasks from file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String eventType = sc.next();
                switch (eventType) {
                case "T":
                    String todo = sc.nextLine().trim();
                    String[] arr = todo.split("\\|");
                    Task todoTask = new Todo(arr[2].trim());
                    if (arr[1].trim().equals("1")) {
                        todoTask.markAsDone();
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    String deadline = sc.nextLine().trim();
                    String[] arr2 = deadline.split("\\|");
                    Task deadlineTask = new Deadline(arr2[2].trim(), arr2[3].trim());
                    if (arr2[1].trim().equals("1")) {
                        deadlineTask.markAsDone();
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    String event = sc.nextLine().trim();
                    String[] arr3 = event.split("\\|");
                    Task eventTask = new Event(arr3[2].trim(), arr3[3].trim());
                    if (arr3[1].trim().equals("1")) {
                        eventTask.markAsDone();
                    }
                    tasks.add(eventTask);
                    break;
                default:
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tFile does not exist");
        }
        return tasks;
    }

    /**
     * Adds Todo task to the file.
     *
     * @param taskName A string representation of the task description.
     */
    public void addTodo(String taskName) {
        try {
            FileWriter append = new FileWriter("data/duke.txt", true);
            if (TaskList.size() == 0) {
                append.write("T | 0 | " + taskName);
            } else {
                append.write("\n" + "T | 0 | " + taskName);
            }
            append.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds Deadline task to the file.
     *
     * @param deadline A string representation of the Deadline task description.
     */
    public void addDeadline(String deadline) {
        try {
            String[] arrDeadline = deadline.split("/by");
            String timeDeadline = Parser.convertDateAndTime(arrDeadline[1].trim());
            Task t = new Deadline(arrDeadline[0].trim(), timeDeadline);
            FileWriter append = new FileWriter("data/duke.txt", true);
            if (TaskList.size() == 0) {
                append.write("D | 0 | " + arrDeadline[0].trim() + " | " + timeDeadline);
            } else {
                append.write("\n" + "D | 0 | " + arrDeadline[0].trim() + " | " + timeDeadline);
            }
            append.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds the Event task to the file.
     *
     * @param event A string representation of the Event task description.
     */
    public void addEvent(String event) {
        try {
            String[] arrEvent = event.split("/at");
            String time = Parser.convertDateAndTime(arrEvent[1].trim());
            Task task = new Event(arrEvent[0].trim(), time);
            FileWriter append = new FileWriter("data/duke.txt", true);
            if (TaskList.size() == 0) {
                append.write("E | 0 | " + arrEvent[0].trim() + " | " + time);
            } else {
                append.write("\n" + "E | 0 | " + arrEvent[0].trim() + " | " + time);
            }
            append.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task in the file.
     *
     * @param deletionNumber An Integer representing the task index to be deleted.
     */
    public void delete(int deletionNumber) {
        try {
            String s = "";
            Scanner scanFile = new Scanner(file);
            for (int i = 1; i <= TaskList.size(); i++) {
                if (i == deletionNumber) {
                    scanFile.nextLine();
                } else if (i == TaskList.size()) {
                    s = s + scanFile.nextLine();
                } else if (i == TaskList.size() - 1 && TaskList.size() == deletionNumber) {
                    s = s + scanFile.nextLine();
                } else {
                    s = s + scanFile.nextLine() + System.lineSeparator();
                }
            }
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(s);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("\tFile does not exist.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tTask does not exist.");
        } catch (IOException e) {
            System.out.println("\t" + e.getMessage());
        }
    }
}