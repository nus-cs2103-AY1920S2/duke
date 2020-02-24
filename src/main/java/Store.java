import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;

import java.util.ArrayList;
import java.util.Date;

public class Store {

    private String cmd;
    private Integer counter;
    private ArrayList<Task> storage;
    private DukeException de;
    File file;
    private Ui ui = new Ui();
    private DateFormat inputDate = new SimpleDateFormat("dd/MM/yyyy");  //input date format of dd/mm/yyyy
    private DateFormat inputTime = new SimpleDateFormat("HHmm");        //input time in hhmm
    private DateFormat outputDate = new SimpleDateFormat("MMM dd yyyy");//output in MMM dd yyyy
    private DateFormat outputTime = new SimpleDateFormat("hh:mm a");    //output in hh:mm PM/AM
    private String[] dateTimes = new String[2];
    private DetectAnomalies detectA = new DetectAnomalies();

    /**
     * This method updates the existing variables.
     * @param file This is the absolute file for storing the Task onto the hard disk.
     */
    public Store(File file) {
        this.counter = 1;
        this.storage = new ArrayList<>();
        this.de = new DukeException();
        this.file = file;
    }

    /**
     * This method prints out message for "bye" action.
     * @return the String of the byeMessage
     */
    public String bye() {
        writeToFile();
        return ui.byeMessage();
    }

    /**
     * This method prints the elements in the {@link ArrayList} of {@link Task}.
     * @return String list of the elements in the Arraylist.
     */
    public String list() {
        String output = "";
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).toString().contains("[1]")) {
                storage.get(i).getStatusIcon();
            }
            String data = String.format("%d.",i + 1) + storage.get(i).toString();
            output = String.format("%s%s\n", output, data);
        }
        return output;
    }

    /**
     * This method updates the Task at the index to complete.
     * @param index Indicates which item on the ArrayList Storage.
     * @return String of done task.
     */
    public String done(int index) {
        assert index < storage.size() : "Index cannot be greater than list";
        assert index > 0 : "Index cannot be 0 or negative";
        if (index > storage.size() || index <= 0) {
            return de.exceedList();
        } else {
            Task updateCurrAction = storage.get(index - 1);
            updateCurrAction.isDone = true;
            updateCurrAction.getStatusIcon();
            String currAction = updateCurrAction.toString();
            writeToFile();
            return ui.doneMessage(currAction);
        }
    }

    /**
     * This method add a new Task to the {@link ArrayList} of {@link Task}.
     * @param cmd The action to be done.
     * @return String This returns the number of task currently.
     */
    public String todo(String cmd) {
        assert cmd != null : "input string cannot be null";
        this.cmd = cmd;
        Task task = new Todo(cmd);
        storage.add(task);
        String output = task.output()
                        + String.format("\nNow you have %d tasks in the list.\n", storage.size());
        writeToFile();
        return output;
    }

    /**
     * This method add a new Deadline Task to the {@link ArrayList} of {@link Task}.
     * @param actionTime Contains the Action and the Date and/or Time.
     * @return the String of the new deadline task.
     */
    public String deadline(String[] actionTime) {
        assert actionTime.length > 0 : "Deadline details cannot be empty";
        String timing;
        String detail;
        this.cmd = actionTime[0];
        if (actionTime[1].length() <= 1) {
            return de.deadlineMissingDate();
        } else {
            detail = actionTime[1].substring(3).strip();
            timing = formatDateTime(detail);
            String output = detectA.checkForClash(storage, dateTimes);
            if (output.contains("No")) {
                Task task = new Deadline(cmd, timing, dateTimes);
                storage.add(task);
                output = task.output()
                        + String.format("\nNow you have %d tasks in the list.\n", storage.size());
                writeToFile();
                return output;
            } else {
                return output;
            }
        }
    }

    /**
     * This method add a new Event Task to the {@link ArrayList} of {@link Task}.
     * @param actionTime Contains the Action and Date and/or Time.
     * @return String of the event created.
     */
    public String event(String[] actionTime) {
        String timing;
        String detail;
        this.cmd = actionTime[0];
        assert actionTime.length > 1 : "Event missing date";
        if (actionTime[1].length() <= 1) {
            return de.eventMissingDate();
        } else {
            detail = actionTime[1].substring(3).strip();
            timing = formatDateTime(detail);
            String output = detectA.checkForClash(storage, dateTimes);
            if (output.contains("No")) {
                Task task = new Event(cmd, timing, dateTimes);
                storage.add(task);
                output = task.output() + String.format("\nNow you have %d tasks in the list.", storage.size());
                writeToFile();
                return output;
            } else {
                return output;
            }
        }
    }

    /**
     * This method remove the Task at the index from the ArrayList.
     * @param index Indicates which item on the {@link ArrayList} of {@link Task} Storage.
     * @return the String of the task deleted.
     */
    public String delete(int index) {
        assert index < storage.size() : "index value greater than list";
        assert index > 0 : "index cannot be 0 or negative.";
        if (index > storage.size() || index <= 0) {
            return de.exceedList();
        } else {
            Task updateCurrAction = storage.get(index - 1);
            String currAction = updateCurrAction.toString();
            int amt = storage.size() - 1;
            storage.remove(index - 1);
            writeToFile();
            return ui.deleteMessage(currAction, amt);
        }
    }

    /**
     * This method writes the {@link ArrayList} of {@link Task} to the file on the hard disk.
     */
    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < storage.size(); i++) {
                if (storage.get(i).isDone) {
                    storage.get(i).setStatusIcon();
                }
                String data = String.format("%d.", i + 1) + storage.get(i).toString();
                fileWriter.write(data);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method load existing file onto the {@link ArrayList} of {@link Task} Storage.
     * @param str This is the Task from the file to be loaded from.
     */
    public void load(String str) {
        boolean alreadyDone = checkIfDone(str);
        if (str.contains("[T]")) {
            String[] results = str.split(" ", 2);
            Task task = new Todo(results[1]);
            task.isDone = alreadyDone;
            if (alreadyDone) {
                task.getStatusIcon();
            }
            storage.add(task);
            writeToFile();
        } else if (str.contains("[D]")) {
            String[] results = str.split(" ", 2);
            String[] actionTime = results[1].split("\\|");
            String time = actionTime[1].strip().substring(3).strip();
            formatDateTimeForLoad(time);
            Task task = new Deadline(actionTime[0], time, dateTimes);
            task.isDone = alreadyDone;
            if (alreadyDone) {
                task.getStatusIcon();
            }
            storage.add(task);
            writeToFile();
        } else if (str.contains("[E]")) {
            String[] results = str.split(" ", 2);
            String[] actionTime = results[1].split("\\|");
            String time = actionTime[1].strip().substring(3).strip();
            formatDateTimeForLoad(time);
            Task task = new Event(actionTime[0], time, dateTimes);
            task.isDone = alreadyDone;
            if (alreadyDone) {
                task.getStatusIcon();
            }
            storage.add(task);
            writeToFile();
        }
    }

    /**
     * This method checks if Tasks in the loaded file is completed.
     * @param str This is the Task.
     * @return boolean This return true if the task has been completed.
     */
    public boolean checkIfDone(String str) {
        return str.contains("[1]");
    }

    /**
     * This method finds Task that contains the Action.
     * @param action This is the Action to be located in the {@link ArrayList} of {@link Task} Storage.
     * @return the String of all the items in the list that match.
     */
    public String find(String action) {
        ArrayList<String> matches = new ArrayList<>();
        String output;
        int counter = 1;
        for (Task task : storage) {
            task.getStatusIcon();
            String data = task.toString();
            if (data.contains(action)) {
                matches.add(String.format("%d.", counter) + data);
                counter++;
            }
        }
        if (matches.isEmpty()) {
            output = de.noMatchesFound();
        } else {
            output = "Here are the matching tasks in your list:\n";
            for (String item : matches) {
                output = String.format("%s%s\n", output, item);
            }
        }
        return output;
    }

    /**
     * This method format the input detail to date and time.
     * @param detail This is the string to be formatted.
     * @return the string of correct format.
     */
    public String formatDateTime(String detail) {
        String timing;
        String[] details = detail.split(" ");
        try {
            Date date = inputDate.parse(details[0]);
            dateTimes[0] = outputDate.format(date);
            dateTimes[1] = " ";
            if (details.length == 2) {
                try {
                    Date time = inputTime.parse(details[1]);
                    dateTimes[1] = outputTime.format(time);
                } catch (DateTimeException | ParseException d) {
                    d.printStackTrace();
                    return de.invalidTimeFormat();
                }
            }
            timing = dateTimes[0] + " " + dateTimes[1];
            return timing;
        } catch (DateTimeException | ParseException d) {
            d.printStackTrace();
            return de.invalidDateFormat();
        }
    }

    /**
     * This method format the date and time from the loaded file.
     * @param detail String read from the file.
     */
    public void formatDateTimeForLoad(String detail) {
        SimpleDateFormat loadInputDate = new SimpleDateFormat("MMM dd yyyy");  //input date format of MMM dd yyyy
        SimpleDateFormat loadInputTime = new SimpleDateFormat("hh:mm a");        //input time in hh:mm AM/PM
        String[] details = detail.split(" ");

        try {
            Date date = loadInputDate.parse(details[0]);
            dateTimes[0] = outputDate.format(date);
            dateTimes[1] = " ";
            if (details.length == 2) {
                try {
                    Date time = loadInputTime.parse(details[1]);
                    dateTimes[1] = outputTime.format(time);
                } catch (DateTimeException d) {
                    d.printStackTrace();
                }
            }
        } catch (DateTimeException | ParseException d) {
            d.printStackTrace();
        }
    }


}

