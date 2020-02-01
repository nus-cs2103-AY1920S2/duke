import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static Date stringToDate(String text) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_1);
        try {
            date = formatter.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

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

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_2);
        String text = formatter.format(date);
        return text;
    }

    public static String tasksToStorage(List<Task> tasks) {
        String data = "";
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_1);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String doneString = task.isDone()? "1" : "0";
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

    public static List<Task> storageToTask(String data) {
        List<Task> tasks= new ArrayList<>();
        String[] lines = data.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] tokens = line.split("&");
            Task task = null;
            if (tokens[0].equals("T")) {
                task = new Todos(tokens[2]);
                if (tokens[1].equals("1")) {
                    task.markAsDone();
                }
            } else if (tokens[0].equals("E")) {
                task = new Events(tokens[2], stringToDate(tokens[3]));
            } else if (tokens[0].equals("D")) {
                task = new Deadlines(tokens[2], stringToDate(tokens[3]));
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
