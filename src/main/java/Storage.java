import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 * A class to write to and read from a text file containing Strings of the Tasks.
 */
class Storage {
    private static final File filePath = new File("myTasks.txt");

    /**
     * Writes Tasks to a text file.
     *
     * @param dl A TaskList containing the current Tasks.
     * @throws IOException if the file cannot be found.
     */
    static void writeTasks(TaskList dl) throws IOException {
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
    static TaskList retrieveTasks() throws FileNotFoundException, ParseException {
        TaskList dl = new TaskList();
        Scanner sc = new Scanner(Storage.filePath);

        while (sc.hasNextLine()) {
            String[] details = sc.nextLine().split("\\|");
            boolean isTaskDone = Integer.parseInt(details[1].trim()) == 1;

            if (details[0].trim().equals("T")) {
                dl.addToList(new ToDoTask(details[2].trim(), isTaskDone));
            } else {
                String dateTime = stringToDate(details[3]);

                if (details[0].trim().equals("E")) {
                    dl.addToList(EventTask.createEventTask(details[2].trim(), dateTime));
                } else {
                    dl.addToList(DeadlineTask.createDeadlineTask(details[2].trim(), dateTime));
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
    private static String stringToDate(String detail) {
        assert detail != null;

        String[] arr = detail.trim().split(", ");
        String time = arr[0].split(" ")[1];
        String year = arr[2].substring(0, 4);

        String[] dayMonth = arr[1].split(" ");
        String day = dayMonth[1].length() == 3 ? "0" + dayMonth[1].substring(0, 1) : dayMonth[1].substring(0, 2);
        String month = dayMonth[0];

        switch (month) {
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
        }

        return day + "/" + month + "/" + year + " " + time;
    }
}