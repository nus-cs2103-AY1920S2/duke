import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Storage {
    static File savedData = new File("data/duke.txt");

    public static TaskList load() {
        TaskList tasklist = new TaskList();

        try {
            FileReader fr = new FileReader(savedData);
            BufferedReader br = new BufferedReader(fr);
            System.out.println("Loading file at " + savedData.toString());
            while (true) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] taskContent = line.split(" ~ ");
                    boolean isDone = false;
                    if (taskContent[1].equals("1")) {
                        isDone = true;
                    }

                    if (taskContent[0].equals("T")) {
                        tasklist.newTodo(taskContent[0].charAt(0), isDone, taskContent[2]);
                    } else {
                        if (taskContent[0].equals("D")) {
                            tasklist.newDeadline(taskContent[0].charAt(0), isDone, taskContent[2], taskContent[3]);

                        } else if (taskContent[0].equals("E")) {
                            tasklist.newEvent(taskContent[0].charAt(0), isDone, taskContent[2], taskContent[3]);
                        } else {}
                    }
                } catch (IOException e) {
                    System.out.println("Oops! Unable to create save file due to " + e + "!");
                    break;
                }

            }


        } catch (FileNotFoundException e) {
            System.out.println("No save file found. Creating new file at " + savedData.toString() + " ...");

            try {
                savedData.createNewFile();
                System.out.println("Created file at " + savedData.toString());
                load();
            } catch (Exception ex) {
                System.out.println("Oops! Unable to create save file due to " + ex + "!");
            }
        }
        return tasklist;
    }

    public static void save(TaskList tasklist) {
        try {
            FileWriter fw = new FileWriter(savedData);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task thisTask : tasklist.list) {
                String taskStr = thisTask.getTaskType() + " ~ "
                        + (thisTask.getStatus() ? "1" : "0") + " ~ "
                        + thisTask.getTaskName() + " ~ "
                        + (thisTask.getTaskType() == 'T' ? "" : thisTask.getTaskTime()) + "\n";
                bw.write(taskStr);
            }
            bw.close();

        } catch (Exception e) {
            System.out.println("Oops! Unable to write to file due to " + e + "!");
        }


    }


}
