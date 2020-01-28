import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {

    public TaskList loadTasksFile(File TASKS_FILE) throws IOException {

        boolean isCreated = TASKS_FILE.createNewFile();

        if (isCreated) {
            return new TaskList();
        }

        Scanner sc = new Scanner(TASKS_FILE);
        TaskList taskData = new TaskList();

        while (sc.hasNextLine()) {

            String[] taskString = sc.nextLine().split(":;:");
//            System.out.println(Arrays.asList(taskString));
            switch(taskString[0]) {
                case "T":
                    Task tdTask = new ToDo(taskString[2]);
                    if (taskString[1].equals("1")) {
                        tdTask.markAsDone();
                    }
                    taskData.addTask(tdTask);
                    break;

                case "D":
                    try {
                        Task dlTask = new Deadline(taskString[2], taskString[3]);
                        if (taskString[1].equals("1")) {
                            dlTask.markAsDone();
                        }
                        taskData.addTask(dlTask);
                    } catch (CathulhuException e) {
                        System.out.println("Error loading data from TASKS_FILE. Skipping the following line:");
                        System.out.println(Arrays.asList(taskString).toString());
                    }

                    break;

                case "E":
                    Task evTask = new Event(taskString[2], taskString[3]);
                    if (taskString[1].equals("1")) {
                        evTask.markAsDone();
                    }
                    taskData.addTask(evTask);
                    break;

                default:
                    break;
            }
        }
        return taskData;
    }

    public void writeTasksFile(File TASKS_FILE, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(TASKS_FILE);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            fw.write(task.toDataString() + "\n");
        }
        fw.close();
    }
}
