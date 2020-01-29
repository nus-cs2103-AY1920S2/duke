package duke.storage;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Storage program loads and updates the file. It interprets the content of
 * file in order to do so. It also contains TaskList.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class Storage {

    String path;
    TaskList manager;

    /**
     * Constructor.
     * @param path refers to the path of file.
     */
    public Storage(String path) {

        this.path = path;

    }

    /**
     * Adds TaskList to file storage.
     *
     * @param manager refers to the TaskList object that will manage taskList.
     */
    public void addManager(TaskList manager) {

        this.manager = manager;

    }

    /**
     * Interprets content of file and calls method to add it into taskList.
     */
    public void loadFile() {

        try {
            File f = new File(path);
            f.createNewFile();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String content = null;
            String[] contentArr = null;
            String type;
            String status;

            while (!((content = br.readLine()) == null)) {

                contentArr = content.split("//");
                type = contentArr[0];
                status = contentArr[1];

                switch (type) {

                case "ToDo":

                    if (status.equals("Y")) {

                        manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]),Task.Types.ToDo, Task.Status.Y);

                    } else {

                        manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]),Task.Types.ToDo, Task.Status.N);

                    }
                    break;

                case "Deadline":

                    if (status.equals("Y")) {

                        manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]),Task.Types.Deadline, Task.Status.Y);

                    } else {

                        manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]),Task.Types.Deadline, Task.Status.N);

                    }
                    break;

                case "Event":

                    if (status.equals("Y")) {

                        manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]),Task.Types.Event, Task.Status.Y);


                    } else {

                        manager.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]),Task.Types.Event, Task.Status.N);

                    }
                    break;

                default:

                    System.out.println("Task type does not exists!");
                    break;
                }

            }
        } catch (Exception e) {

            System.out.println(e);

        }
    }

    /**
     * Updates file with respect to the operations performed to taskList.
     *
     * @param operation refers to the updates on taskList.
     * @throws IOException when file writer encounters error.
     */
    public void updateFile(String operation) throws IOException {

        ArrayList<Task> taskList = manager.getList();

        FileWriter writer = new FileWriter(new File(path));
        String input = null;

        for (int i = 0; i < taskList.size(); i++) {

            input = generateInput((Task)taskList.get(i));
            writer.write(input);
            writer.flush();

        }
        writer.close();
    }

    /**
     * Generates the input to file for each Task.
     *
     * @param task is needed to generate its relevant input into the file.
     * @return input generated for file
     */
    public String generateInput(Task task) {

        String type = task.getType().toString();
        String status = task.getStatus().toString();
        String taskDescription = task.getTaskDescription();
        LocalDateTime[] dateTime = task.getDateTime();

        return type + "//" + status + "//" + taskDescription + "//" + dateTime[0] + "//" + dateTime[1] + "\n";

    }


}
