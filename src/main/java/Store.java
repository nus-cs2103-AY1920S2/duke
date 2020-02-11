import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Store {

    private String cmd;
    private Integer counter;
    private ArrayList<Task> Storage;
    private DukeException DE;
    private LocalDate LD;
    private LocalTime LT;
    File file;
    private Ui ui = new Ui();
    private DateFormat inputDate = new SimpleDateFormat("dd/MM/yyyy");  //input date format of dd/mm/yyyy
    private DateFormat inputTime = new SimpleDateFormat("HHmm");        //input time in hhmm
    private DateFormat outputDate = new SimpleDateFormat("MMM dd yyyy");//output in MMM dd yyyy
    private DateFormat outputTime = new SimpleDateFormat("hh:mm a");    //output in hh:mm PM/AM

    /**
     * This method updates the existing variables.
     * @param file This is the absolute file for storing the Task onto the hard disk.
     */
    public Store(File file){
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
        WritetoFile();
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
    public String done(int index){
        assert index < Storage.size() : "Index cannot be greater than list";
        assert index > 0 : "Index cannot be 0 or negative";
//        if(index > Storage.size() || index <= 0){
//            return DE.ExceedList();
//        } else {
            Task UpdateCurrAction = Storage.get(index - 1);
            UpdateCurrAction.isDone = true;
            UpdateCurrAction.getStatusIcon();
            String CurrAction = UpdateCurrAction.toString();
            WritetoFile();
            return ui.DoneMessage(CurrAction);
//        }
    }

    /**
     * This method add a new Task to the ArrayList<Task>.
     * @param S The action to be done.
     * @return String This returns the number of task currently.
     */
    public String todo(String S){
        assert S != null : "input string cannot be null";
        this.cmd = S;
        Task T = new Todo(cmd);
        Storage.add(T);
        String output = T.Output() + String.format("\nNow you have %d tasks in the list.\n", Storage.size());
        WritetoFile();
        return output;
    }

    /**
     * This method add a new Deadline Task to the ArrayList<Task>
     * @param ActionTime Contains the Action and the Date and/or Time.
     * @return the String of the new deadline task.
     */
    public String deadline(String[] ActionTime){
        assert ActionTime.length > 0 : "Deadline details cannot be empty";
        String Timing;
        String details;
        this.cmd = ActionTime[0];
        if(ActionTime[1].length() <=1){
            return DE.DeadlineMissingDate();
        } else {
            details = ActionTime[1].substring(3).strip();
            String[] DateTime = details.split(" ");
            try {
                Date date = inputDate.parse(DateTime[0]);
                Timing = outputDate.format(date);
                if (DateTime.length == 2) {
                    try {
                        Date time = inputTime.parse(DateTime[1]);
                        Timing = Timing + " " + outputTime.format(time);
                    } catch (DateTimeException d){
                        return DE.InvalidTimeFormat();
                    }
                }
                Task T = new Deadline(cmd, Timing);
                Storage.add(T);
                String output = T.Output() + String.format("\nNow you have %d tasks in the list.\n", Storage.size());
                WritetoFile();
                return output;
            } catch (DateTimeException | ParseException d){
                return DE.InvalidDateFormat();
            }
        }
    }

    /**
     * This method add a new Event Task to the ArrayList<Task>
     * @param ActionTime Contains the Action and Date and/or Time.
     * @return String of the event created.
     */
    public String event(String[] ActionTime){
        String Timing;
        String details;
        String Date;
        this.cmd = ActionTime[0];
        assert ActionTime.length > 1 : "Event missing date";
//        if(ActionTime[1].length() <=1){
//            return DE.EventMissingDate();
//        } else {
            details = ActionTime[1].substring(3).strip();
            String[] DateTime = details.split(" ");
            try {
                Date date = inputDate.parse(DateTime[0]);
                Timing = outputDate.format(date);
                if (DateTime.length == 2) {
                    try {
                        Date time = inputTime.parse(DateTime[1]);
                        Timing = date + " " + outputTime.format(time);

                    } catch (DateTimeException d) {
                        return DE.InvalidTimeFormat();
                    }
                }
                Task T = new Event(cmd, Timing);
                Storage.add(T);
                String output = T.Output() + String.format("\nNow you have %d tasks in the list.", Storage.size());
                WritetoFile();
                return output;
            } catch (DateTimeException | ParseException d) {
                return DE.InvalidDateFormat();
            }
//        }
    }

    /**
     * This method remove the Task at the index from the ArrayList.
     * @param index Indicates which item on the ArrayList<Task> Storage.
     * @return the String of the task deleted.
     */
    public String delete(int index){
        assert index < Storage.size() : "index value greater than list";
        assert index > 0 : "index cannot be 0 or negative.";
//        if(index > Storage.size() || index <= 0){
//            return DE.ExceedList();
//        } else {
            Task UpdateCurrAction = Storage.get(index - 1);
            String CurrAction = UpdateCurrAction.toString();
            int amt = Storage.size() - 1;
            Storage.remove(index - 1);
            WritetoFile();
            return ui.DeleteMessage(CurrAction, amt);
//        }
    }

    /**
     * This method writes the ArrayList<Task> to the file on the hard disk.
     */
    public void WritetoFile() {
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
    public void load(String S){
        boolean alreadyDone = CheckIfDone(S);
        if(S.contains("[T]")){
            String[] result = S.split(" ", 2);
            Task T = new Todo(result[1]);
            T.isDone = alreadyDone;
            Storage.add(T);
            WritetoFile();
        } else if (S.contains("[D]")){
            String[] result = S.split(" ", 2);
            String[] AT = result[1].split("\\|");
            String time = AT[1].strip().substring(3).strip();
            Task T = new Deadline(AT[0], time);
            T.isDone = alreadyDone;
            Storage.add(T);
            WritetoFile();
        } else if (S.contains("[E]")){
            String[] result = S.split(" ", 2);
            String[] AT = result[1].split("\\|");
            String time = AT[1].strip().substring(3).strip();
            Task T = new Event(AT[0], time);
            T.isDone = alreadyDone;
            Storage.add(T);
            WritetoFile();
        }
    }

    /**
     * This method checks if Tasks in the loaded file is completed.
     * @param S This is the Task.
     * @return boolean This return true if the task has been completed.
     */
    public boolean CheckIfDone(String S){
        return S.contains("[1]");
    }

    /**
     * This method finds Task that contains the Action.
     * @param Action This is the Action to be located in the ArrayList<Task> Storage.
     * @return the String of all the items in the list that match.
     */
    public String find(String Action){
        ArrayList<String> Match = new ArrayList<>();
        String output = "";
        int counter = 1;
        for (Task task : Storage) {
            String data = task.toString();
            if (data.contains(Action)) {
             Match.add(String.format("%d.", counter) + data);
                counter++;
            }
        }
        if(Match.isEmpty()){
            output = output + DE.NoMatchesFound();
        } else {
            output = output + "Here are the matching tasks in your list:\n";
            for (String item : Match){
                output = String.format("%s%s\n", output, item);
            }
        }
        return output;
    }
}

