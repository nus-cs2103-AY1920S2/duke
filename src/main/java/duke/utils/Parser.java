package duke.utils;

import duke.constants.Constants;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser to parse various stuff.
 */
public class Parser {

    /**
     * Parse string into date object.
     * @param text date in string format
     * @return date object from string
     */
    public static LocalDate stringToDate(String text) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_1);
        date = LocalDate.parse(text, formatter);
        return date;
    }

    /**
     * Check date format to see if it is in the form of yyyy-MM-dd.
     * @param text date in string format
     * @return boolean
     */
    public static boolean checkDateFormat(String text) {
        String[] tokens = text.split("-");
        if (tokens[0].length() != 4) {
            return false;
        }
        if (tokens[1].length() != 2 || tokens[1].compareTo("00") <= 0 || tokens[1].compareTo("12") > 0) {
            return false;
        }
        if (tokens[2].length() != 2 || tokens[2].compareTo("00") <= 0 || tokens[2].compareTo("31") > 0) {
            return false;
        }
        return true;
    }

    /**
     * Convert date object to string in the format MMM dd yyyy.
     * @param date date in date format
     * @return string
     */
    public static String dateToString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_2);
        String text = date.format(formatter);
        return text;
    }

    /**
     * convert list of task to string to be stored.
     * @param tasks tasks in list of tasks format
     * @return string to be stored
     */
    public static String tasksToStorage(List<Task> tasks) {
        String data = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT_1);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String doneString = task.isDone() ? "1" : "0";
            data = data + task.getTaskType() + "&" + doneString + "&" + task.getTaskName();
            if (task.getTaskType() == "E" || task.getTaskType() == "D") {
                data = data + "&" + formatter.format(task.getTaskTime());
            }
            if (i < tasks.size() - 1) {
                data = data + "\n";
            }
        }
        return data;
    }

    /**
     * Convert string data loaded to list of tasks.
     * @param data tasks in string format
     * @return list of tasks
     * @throws ParseException parsing exception
     */
    public static List<Task> storageToTask(String data) throws ParseException {
        List<Task> tasks = new ArrayList<>();
        String[] lines = data.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] tokens = line.split("&");
            Task task = null;
            switch (tokens[0]) {
            case "T":
                task = new Todo(tokens[2]);
                if (tokens[1].equals("1")) {
                    task.markAsDone();
                }
                break;
            case "E":
                task = new Event(tokens[2], stringToDate(tokens[3]));
                break;
            case "D":
                task = new Deadline(tokens[2], stringToDate(tokens[3]));
                break;
            default:
                break;
            }
            if (task != null && tokens[1].equals("1")) {
                task.markAsDone();
            }
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
