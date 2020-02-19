import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Store {

    private String cmd;
    private Integer counter;
    private ArrayList<Task> Storage;
    private DukeException DE;
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
        this.Storage = new ArrayList<>();
        this.DE = new DukeException();
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
     * This method prints the elements in the ArrayList<Task>.
     * @return the String of list
     */
    public String list() {
        String output = "";
        for(int i = 0; i < Storage.size(); i++) {
            if (Storage.get(i).toString().contains("[1]")){
                Storage.get(i).getStatusIcon();
            }
            String data = String.format("%d.",i+1) + Storage.get(i).toString();
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
        assert index < Storage.size() : "Index cannot be greater than list";
        assert index > 0 : "Index cannot be 0 or negative";
        if(index > Storage.size() || index <= 0){
            return DE.exceedList();
        } else {
            Task updateCurrAction = Storage.get(index - 1);
            updateCurrAction.isDone = true;
            updateCurrAction.getStatusIcon();
            String CurrAction = updateCurrAction.toString();
            writeToFile();
            return ui.DoneMessage(CurrAction);
        }
    }

    /**
     * This method add a new Task to the ArrayList<Task>.
     * @param S The action to be done.
     * @return String This returns the number of task currently.
     */
    public String todo(String S) {
        assert S != null : "input string cannot be null";
        this.cmd = S;
        Task task = new Todo(cmd);
        Storage.add(task);
        String output = task.output()
                        + String.format("\nNow you have %d tasks in the list.\n", Storage.size());
        writeToFile();
        return output;
    }

    /**
     * This method add a new Deadline Task to the ArrayList<Task>
     * @param actionTime Contains the Action and the Date and/or Time.
     * @return the String of the new deadline task.
     */
    public String deadline(String[] actionTime) {
        assert actionTime.length > 0 : "Deadline details cannot be empty";
        String timing;
        String detail;
        this.cmd = actionTime[0];
        if(actionTime[1].length() <=1) {
            return DE.deadlineMissingDate();
        } else {
            detail = actionTime[1].substring(3).strip();
            timing = formatDateTime(detail);
            String output = detectA.checkForClash(Storage, dateTimes);
            if (output.contains("No")) {
                Task task = new Deadline(cmd, timing, dateTimes);
                Storage.add(task);
                output = task.output()
                        + String.format("\nNow you have %d tasks in the list.\n", Storage.size());
                writeToFile();
                return output;
            } else {
                return output;
            }
        }
    }

    /**
     * This method add a new Event Task to the ArrayList<Task>
     * @param actionTime Contains the Action and Date and/or Time.
     * @return String of the event created.
     */
    public String event(String[] actionTime) {
        String timing;
        String detail;
        this.cmd = actionTime[0];
        assert actionTime.length > 1 : "Event missing date";
        if(actionTime[1].length() <=1){
            return DE.eventMissingDate();
        } else {
            detail = actionTime[1].substring(3).strip();
            timing = formatDateTime(detail);
            String output = detectA.checkForClash(Storage, dateTimes);
            if (output.contains("No")) {
                Task T = new Event(cmd, timing, dateTimes);
                Storage.add(T);
                output = T.output() + String.format("\nNow you have %d tasks in the list.", Storage.size());
                writeToFile();
                return output;
            } else {
                return output;
            }
        }
    }

    /**
     * This method remove the Task at the index from the ArrayList.
     * @param index Indicates which item on the ArrayList<Task> Storage.
     * @return the String of the task deleted.
     */
    public String delete(int index) {
        assert index < Storage.size() : "index value greater than list";
        assert index > 0 : "index cannot be 0 or negative.";
        if(index > Storage.size() || index <= 0){
            return DE.exceedList();
        } else {
            Task updateCurrAction = Storage.get(index - 1);
            String currAction = updateCurrAction.toString();
            int amt = Storage.size() - 1;
            Storage.remove(index - 1);
            writeToFile();
            return ui.DeleteMessage(currAction, amt);
        }
    }

    /**
     * This method writes the ArrayList<Task> to the file on the hard disk.
     */
    public void writeToFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < Storage.size(); i++) {
                if (Storage.get(i).isDone) {
                    Storage.get(i).setStatusIcon();
                }
                String data = String.format("%d.", i + 1) + Storage.get(i).toString();
                fileWriter.write(data);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This method load existing file onto the ArrayList<Task> Storage.
     * @param S This is the Task from the file to be loaded from.
     */
    public void load(String S) {
        boolean alreadyDone = CheckIfDone(S);
        if(S.contains("[T]")){
            String[] results = S.split(" ", 2);
            Task task = new Todo(results[1]);
            task.isDone = alreadyDone;
            if (alreadyDone){
                task.getStatusIcon();
            }
            Storage.add(task);
            writeToFile();
        } else if (S.contains("[D]")){
            String[] results = S.split(" ", 2);
            String[] actionTime = results[1].split("\\|");
            String time = actionTime[1].strip().substring(3).strip();
            formatDateTimeForLoad(time);
            Task task = new Deadline(actionTime[0], time, dateTimes);
            task.isDone = alreadyDone;
            if (alreadyDone){
                task.getStatusIcon();
            }
            Storage.add(task);
            writeToFile();
        } else if (S.contains("[E]")){
            String[] results = S.split(" ", 2);
            String[] actionTime = results[1].split("\\|");
            String time = actionTime[1].strip().substring(3).strip();
            formatDateTimeForLoad(time);
            Task task = new Event(actionTime[0], time, dateTimes);
            task.isDone = alreadyDone;
            if (alreadyDone){
                task.getStatusIcon();
            }
            Storage.add(task);
            writeToFile();
        }
    }

    /**
     * This method checks if Tasks in the loaded file is completed.
     * @param S This is the Task.
     * @return boolean This return true if the task has been completed.
     */
    public boolean CheckIfDone(String S) {
        return S.contains("[1]");
    }

    /**
     * This method finds Task that contains the Action.
     * @param Action This is the Action to be located in the ArrayList<Task> Storage.
     * @return the String of all the items in the list that match.
     */
    public String find(String Action){
        ArrayList<String> Matches = new ArrayList<>();
        String output;
        int counter = 1;
        for (Task task : Storage) {
            task.getStatusIcon();
            String data = task.toString();
            if (data.contains(Action)) {
                Matches.add(String.format("%d.", counter) + data);
                counter++;
            }
        }
        if(Matches.isEmpty()){
            output = DE.noMatchesFound();
        } else {
            output = "Here are the matching tasks in your list:\n";
            for (String item : Matches){
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
                } catch (DateTimeException | ParseException d){
                    d.printStackTrace();
                    return DE.invalidTimeFormat();
                }
            }
            timing = dateTimes[0] + " " + dateTimes[1];
            return timing;
        } catch (DateTimeException | ParseException d){
            d.printStackTrace();
            return DE.invalidDateFormat();
        }
    }

    public void formatDateTimeForLoad (String detail){
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
                } catch (DateTimeException d){
                    d.printStackTrace();
                }
            }
        } catch (DateTimeException | ParseException d){
            d.printStackTrace();
        }
    }


}

