package duke.storage;

import duke.exceptions.Exceptions;
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
 * @version 1.1
 * @since 4/2/2020
 */
public class Storage {

    String path;
    TaskList taskList;

    /**
     * Constructor.
     *
     * @param path refers to the path of file.
     */
    public Storage(String path) {

        this.path = path;

    }

    /**
     * Adds TaskList to file storage.
     *
     * @param taskList refers to the TaskList object that will manage taskList.
     */
    public void addTaskList(TaskList taskList) {

        this.taskList = taskList;

    }

    /**
     * Interprets content of file and calls method to add it into taskList.
     */
    public String loadFile() {

        try {
            File f = new File(path);
            f.getParentFile().mkdir();
            f.createNewFile();
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            String content;
            String[] contentArr;
            String type;
            String status;
            int lines = 0;

            while (!((content = br.readLine()) == null)) {

                contentArr = content.split("//");
                type = contentArr[0];
                status = contentArr[1];

                switch (type) {

                case "T":

                    if (status.equals("Y")) {

                        taskList.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]), Task.Types.T, Task.Status.Y);

                    } else {

                        taskList.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]), Task.Types.T, Task.Status.N);

                    }
                    lines++;
                    break;

                case "D":

                    if (status.equals("Y")) {

                        taskList.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]), Task.Types.D, Task.Status.Y);

                    } else {

                        taskList.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]), Task.Types.D, Task.Status.N);

                    }
                    lines++;
                    break;

                case "E":

                    if (status.equals("Y")) {

                        taskList.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]), Task.Types.E, Task.Status.Y);


                    } else {

                        taskList.addTask(contentArr[2], LocalDateTime.parse(contentArr[3]),
                                LocalDateTime.parse(contentArr[4]), Task.Types.E, Task.Status.N);

                    }
                    lines++;
                    break;

                default:
                    System.out.println("Task type does not exists!");
                    break;
                }

            }

            assert lines == taskList.getList().size() : "Number of tasks in file and taskList don't match";
            return "File loaded";

        } catch (Exception e) {

            if (e instanceof Exceptions) {

                return e.toString();

            } else {

                return "Error encountered when loading file. Please restart the app!";

            }

        }
    }

    /**
     * Updates file with respect to the operations performed to taskList.
     *
     * @throws IOException when file writer encounters error.
     */
    public void updateFile() throws IOException {

        ArrayList<Task> list = taskList.getList();

        FileWriter writer = new FileWriter(new File(path));
        String input;

        for (int i = 0; i < list.size(); i++) {

            input = generateInput(list.get(i));
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
