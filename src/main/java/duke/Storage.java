package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class for handling file input and output, saving past user data.
 */
public class Storage {

    /**
     * Reflects all Tasks in allTasks list to be saved in a lastSavedTasks.txt file.
     *
     * @param taskList List of Tasks for duke.Duke.
     */
    public static void saveChanges(TaskList taskList) {
        // Note: The whole list will be iterated everytime an update is needed for the file.
        try {
            FileWriter fw = new FileWriter("lastSavedTasks.txt");
            for (int i = 0; i < taskList.sizeOf(); i++) {
                String tickOrCross = taskList.getTask(i).obtainStatusIcon();
                String currentLine = String.valueOf(i + 1) + ". [" + tickOrCross + "] " + taskList.getTask(i);
                fw.write(currentLine + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            // no need for follow-up action, as the file is not found - we will simply create a new one.
        }
    }

    /**
     * Reads in from the lastSavedTasks.txt file if it exists, and updates duke.Duke allTasks list of Tasks.
     *
     * @param allTasks List of Tasks for Duke.
     */
    public void readFromLastSavedFile(TaskList allTasks) {

        try {
            // Read in from lastSavedTasks.txt file, if it exists, and load Tasks into allTasks ArrayList
            File f = new File("lastSavedTasks.txt");
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {

                // This is the line of interest
                String line = fileScanner.nextLine();

                // We want to get these useful Strings using our Parser
                Parser logic = new Parser();
                String doneStatus = logic.determineTaskDoneStatusFromFileLine(line);
                String taskType = logic.determineTaskTypeFromFileLine(line);
                String details = logic.determineTaskDetailsFromFileLine(line);

                switch (taskType) {

                case "T":
                    // This is a To-Do item
                    Task currentToDo = new ToDo(details);
                    if (logic.isDone(doneStatus)) {
                        currentToDo.doTask();
                    }
                    allTasks.addSavedTaskToStored(currentToDo);
                    break;

                case "D":
                    // This is a Deadline item
                    String deadlineCommand = getDeadlineCommand(details);
                    String deadlineLimit = getDeadlineLimit(details);
                    Task currentDeadline = new Deadline(deadlineCommand, deadlineLimit);

                    // Check if done
                    if (logic.isDone(doneStatus)) {
                        currentDeadline.doTask();
                    }

                    // Finally, add the correct Task
                    allTasks.addSavedTaskToStored(currentDeadline);
                    break;

                case "E":
                    // This is an Event item
                    String eventCommand = getEventCommand(details);
                    String eventLimit = getEventTime(details);
                    Task currentEvent = new Event(eventCommand, eventLimit);

                    // Check if done
                    if (logic.isDone(doneStatus)) {
                        currentEvent.doTask();
                    }

                    // Finally, add the correct Task
                    allTasks.addSavedTaskToStored(currentEvent);
                    break;

                default:
                    assert false : "Tasks should either be ToDo (T), Deadline (D), or Event (E).";
                    break;

                }

            }
        } catch (FileNotFoundException fnfe) {
            print("File is not found, but I gotcha back bro; will create one for you to save your tasks.");
        }
    }

    /**
     * Gets the Event's command based on the details upon removing task status and type.
     *
     * @param details the line read in, after removing unnecessary information on task status and type.
     * @return the Event's command.
     */
    private String getEventCommand(String details) {
        int indexOfAtKeyword = details.indexOf("(at: ");
        String eventCommand = details.substring(0, indexOfAtKeyword - 1);
        assert (eventCommand != null) : "Event should have a command.";
        return eventCommand;
    }

    /**
     * Gets the Event's time based on the details upon removing task status and type.
     *
     * @param details the line read in, after removing unnecessary information on task status and type.
     * @return the Event's time of happening.
     */
    private String getEventTime(String details) {
        int indexOfByKeyword = details.indexOf("(at: ");
        String eventTime = details.substring(indexOfByKeyword + 5,
                details.lastIndexOf(")"));
        assert (eventTime != null) : "Event should have a time.";
        return eventTime;
    }

    /**
     * Gets the Deadline's command based on the details upon removing task status and type.
     *
     * @param details the line read in, after removing unnecessary information on task status and type.
     * @return the Deadline's command.
     */
    private String getDeadlineCommand(String details) {
        int indexOfAtKeyword = details.indexOf("(by: ");
        String deadlineCommand = details.substring(0, indexOfAtKeyword - 1);
        assert (deadlineCommand != null) : "Deadline tasks should have a command.";
        return deadlineCommand;
    }

    /**
     * Gets the Deadline's time limit based on the details upon removing task status and type.
     *
     * @param details the line read in, after removing unnecessary information on task status and type.
     * @return the Deadline's time limit to which it should be completed.
     */
    private String getDeadlineLimit(String details) {
        int indexOfByKeyword = details.indexOf("(by: ");
        String deadlineLimit = details.substring(indexOfByKeyword + 5,
                details.lastIndexOf(")"));
        assert (deadlineLimit != null) : "Deadline tasks should have a date limit.";
        return deadlineLimit;
    }

    /**
     * Prints a given String.
     *
     * @param s String to be printed.
     */
    private static void print(String s) {
        System.out.println(s);
    }

}
