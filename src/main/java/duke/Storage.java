/**
 * The Storage class handles all I/O in the software with two key methods, load() and save(). The load() method
 * handles loading of any existing saved data into the TaskList object, while the save() method handles saving the most
 * recent version of the TaskList object when the method is called.
 */

package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.format.DateTimeParseException;

public class Storage {
    static final File savedData = new File("data/duke.txt");
    static final File thisLoc = new File(System.getProperty("user.dir") + "/data");
    static final String delimiter = " ~ ";

    /**
     * This method handles loading of an existing data file stored in the directory 'data', where the program is
     * located, and populate the respective TaskList with Tasks.
     * If the directory is absent, a new directory is created.
     * If the directory is created and the data file is absent, a new data file is created.
     * @return a list of TaskList objects.
     */
    public static TaskList[] load () {
        TaskList todoList = new TaskList();
        TaskList deadlineList = new TaskList();
        TaskList eventList = new TaskList();

        try {
            // Checks if the directory exists, and creates it if it does not exist.
            thisLoc.mkdir();

            // Checks if the file exists, and creates it if it does not exist.
            savedData.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(savedData));
            System.out.println("Loading file 'duke.txt'... ");

            while (true) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    Task thisTask = createTaskFromFile(line);
                    if (thisTask instanceof Todo) {
                        todoList.add(thisTask);
                    } else if (thisTask instanceof Deadline) {
                        deadlineList.add(thisTask);
                    } else if (thisTask instanceof Event) {
                        eventList.add(thisTask);
                    }

                } catch (IOException e) {
                    System.out.println("Oops! Unable to read save file due to " + e + "!");
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println(Ui.WRONG_DATE_TIME_FORMAT);
                    break;
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Oops! Unable to create or load save file due to " + e + "!");
        }

        return new TaskList[] {todoList, deadlineList, eventList};
    }

    /**
     * This method saves a current TaskList object into the data file in the directory 'data', where the program
     * is located, assuming that the file has already been created before or during the launch of the program.
     * @param tasklists the task lists to parse into the data file.
     */
    public static void save(TaskList[] tasklists) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(savedData));
        for (TaskList thisList : tasklists) {
            for (Task thisTask : thisList.getList()) {
                String taskStr = thisTask.getTaskType() + delimiter
                        + (thisTask.isDone() ? "1" : "0") + delimiter
                        + thisTask.getTaskName() + delimiter
                        + (thisTask instanceof Todo ? "" : thisTask.getTaskDateTime()) + "\n";
                writer.write(taskStr);
            }
        }
        writer.close();
    }

    /**
     * Creates a new Task from each line in the data file.
     * @param line The input line from the data file.
     * @return a Task object.
     * @throws DateTimeParseException If the Date/Time is in the wrong format.
     */
    public static Task createTaskFromFile(String line) throws DateTimeParseException {
        String[] taskContent = line.split(delimiter);
        boolean isDone = taskContent[1].equals("1");
        switch(taskContent[0]) {
            case "T":
                return new Todo(isDone, taskContent[2]);
            case "D":
                return new Deadline(isDone, taskContent[2], Parser.parseDateTime(taskContent[3]));
            case"E":
                return new Event(isDone, taskContent[2], Parser.parseDateTime(taskContent[3]));
            default:
                return null;
        }
    }



}
