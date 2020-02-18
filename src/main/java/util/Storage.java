package util;

import util.task.DeadlineTask;
import util.task.EventTask;
import util.task.TaskList;
import util.task.ToDoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 * A class to write to and read from a text file containing Strings of the Tasks.
 */
public class Storage {
    private static final File filePath = new File("myTasks.txt");

    /**
     * Writes Tasks to a text file.
     *
     * @param dl A TaskList containing the current Tasks.
     * @throws IOException if the file cannot be found.
     */
    public static void writeTasks(TaskList dl) throws IOException {
        assert dl != null;

        FileWriter fw = new FileWriter(Storage.filePath);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < dl.listSize(); i++) {
            String string = dl.printTask(i);
            char taskType = string.charAt(1);
            int isComplete = dl.isTaskDone(i) ? 1 : 0;
            String[] arr = string.split("\\(");
            String taskDescription = arr[0].substring(8).trim();
            String taskInString = taskType + " | " + isComplete + " | " + taskDescription;

            if (Character.toUpperCase(taskType) != 'T') {
                String time = arr[1].split(":")[1];
                time = time.trim().substring(0, time.length() - 2);
                taskInString += " | " + time;
            }

            taskInString += "\n";
            sb.append(taskInString);
        }

        fw.write(sb.toString());
        fw.close();
    }

    /**
     * Retrieves a previous TaskList from a text file.
     *
     * @return A TaskList updated with the Tasks from the text file.
     * @throws FileNotFoundException if the file cannot be found.
     * @throws ParseException        if the date and time cannot be parsed.
     */
    public static TaskList retrieveTasks() throws FileNotFoundException, ParseException {
        TaskList dl = new TaskList();
        Scanner sc = new Scanner(Storage.filePath);

        while (sc.hasNextLine()) {
            String[] details = sc.nextLine().split("\\|");
            boolean isTaskDone = Integer.parseInt(details[1].trim()) == 1;

            if (details[0].trim().equals("T")) {
                dl.addToList(new ToDoTask(details[2].trim(), isTaskDone));
            } else {
                String dateTime = stringToDate(details[3]);
                Date deadline = Parser.stringToDate(dateTime);
                if (details[0].trim().equals("E")) {
                    dl.addToList(EventTask.createEventTask(details[2].trim(), deadline));
                } else {
                    dl.addToList(DeadlineTask.createDeadlineTask(details[2].trim(), deadline));
                }
            }
        }

        return dl;
    }

    /**
     * Converts a String to a Date used in creating Tasks.
     *
     * @param detail A String of the details of a Task.
     * @return A Date.
     */
    public  static String stringToDate(String detail) {
        assert detail != null;

        String[] arr = detail.trim().split(", ");
        String time = arr[0].split(" ")[1];
        String year = arr[2].substring(0, 4);

        String[] dayMonth = arr[1].split(" ");
        String day = dayMonth[1].length() == 3 ? "0" + dayMonth[1].substring(0, 1) : dayMonth[1].substring(0, 2);
        String month = dayMonth[0];

        switch (month) {
            case DukeConstant.JAN_STRING:
                month = DukeConstant.JAN_NUM;
                break;
            case DukeConstant.FEB_STRING:
                month = DukeConstant.FEB_NUM;
                break;
            case DukeConstant.MAR_STRING:
                month = DukeConstant.MAR_NUM;
                break;
            case DukeConstant.APR_STRING:
                month = DukeConstant.APR_NUM;
                break;
            case DukeConstant.MAY_STRING:
                month = DukeConstant.MAY_NUM;
                break;
            case DukeConstant.JUN_STRING:
                month = DukeConstant.JUN_NUM;
                break;
            case DukeConstant.JUL_STRING:
                month = DukeConstant.JUL_NUM;
                break;
            case DukeConstant.AUG_STRING:
                month = DukeConstant.AUG_NUM;
                break;
            case DukeConstant.SEP_STRING:
                month = DukeConstant.SEP_NUM;
                break;
            case DukeConstant.OCT_STRING:
                month = DukeConstant.OCT_NUM;
                break;
            case DukeConstant.NOV_STRING:
                month = DukeConstant.NOV_NUM;
                break;
            case DukeConstant.DEC_STRING:
                month = DukeConstant.DEC_NUM;
                break;
        }

        return day + "/" + month + "/" + year + " " + time;
    }
}