package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get the string format of a list of task objects
     *
     * @param  list   the list of task objects
     * @return  listString    the list of tasks in string format
     */
    public String convertListToString(TaskList list) {
        String listString = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            int isDone = (task.getIsDone()) ? 1 : 0;
            if (task instanceof Deadline) {
                listString += "D | " + isDone + " | " + task.getDescription() + " | " +
                        ((Deadline) task).getDate() + " " + ((Deadline) task).getTime();
            } else if (task instanceof Event) {
                listString += "E | " + isDone + " | " + task.getDescription() + " | " +
                        ((Event) task).getDate() + " " + ((Event) task).getFromTimeToTime();
            } else if (task instanceof Todo) {
                listString += "T | " + isDone + " | " + task.getDescription();
            }
            if (i != list.size() - 1) {
                listString += "\n";
            }
        }
        return listString;
    }

    /**
     * Get a list of tasks from the string format of list
     *
     * @param  listString   the list of tasks in string format
     * @return  list    the list of task objects
     */
    public List<Task> convertStringToList(String listString) throws DukeException {
        List<Task> list = new ArrayList<>();

        try {
            listString.lines().forEach((listItem) -> {
                String[] taskItems = listItem.split(" \\| ");
                Task task = null;
                boolean isDone = (Integer.parseInt(taskItems[1]) != 0);
                String[] dateOrTimeTokens;

                switch (taskItems[0]) {
                    case "T":
                        task = new Todo(taskItems[2]);
                        break;
                    case "D":
                        dateOrTimeTokens = taskItems[3].split(" ");
                        try {
                            LocalDate deadlineDate = LocalDate.parse(dateOrTimeTokens[0]);
                            LocalTime time = LocalTime.parse(dateOrTimeTokens[1]);
                            task = new Deadline(taskItems[2], deadlineDate, time);
                        } catch (Exception e) {
                            break;
                        }
                        break;
                    case "E":
                        dateOrTimeTokens = taskItems[3].split(" ");
                        try {
                            LocalDate eventDate = LocalDate.parse(dateOrTimeTokens[0]);
                            task = new Event(taskItems[2], eventDate, dateOrTimeTokens[1]);
                        } catch (Exception e) {
                            break;
                        }
                        break;
                }
                if (task != null) {
                    task.markAsDone(isDone);
                    list.add(task);
                }
            });
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Data is corrupted.");
        }
        return list;
    }

    /**
     * Save the list of tasks to file on the disk
     *
     * @param   list    the list of tasks to be saved
     * @return  isSuccessful   whether save to file is successful
     */
    public boolean save(TaskList list) {
        String listString = convertListToString(list);

        File dataPath = new File(System.getProperty("user.dir"));
        File newFile = new File(dataPath.getAbsolutePath() + File.separator + "duke.txt");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
            writer.write(listString);

            writer.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the list of tasks from file saved on disk
     *
     * @return  list   the list of tasks from file
     */
    public List<Task> load() throws DukeException {
        File dataPath = new File(System.getProperty("user.dir"));
        File newFile = new File(dataPath.getAbsolutePath() + File.separator + "duke.txt");
        String listString = "";
        List<Task> list;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(newFile));
            String line;
            while ((line = reader.readLine()) != null) {
                listString += "\n" + line;
            }
            list = convertStringToList(listString.substring(1));
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! Failed to load list!");
        }
        return list;
    }
}
