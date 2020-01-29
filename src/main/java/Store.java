import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Store {

    private String line = "____________________________________________________________";
    private String cmd;
    private Integer counter;
    private ArrayList<Task> Storage;
    private DukeException DE;
    private LocalDate LD;
    private LocalTime LT;

    public Store(){
        this.counter = 1;
        this.Storage = new ArrayList<>();
        this.DE = new DukeException();
    }
    public void AddNewAction(String S) {
        this.cmd = S;
        System.out.println(line);
        System.out.println("added: " + cmd);
        System.out.println(line);
        Task T = new Task(cmd);
        Storage.add(T);
        counter = counter + 1;
    }
    public void bye() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(1);
    }
    public void list() {
        System.out.println(line);
        for(int i = 0; i < Storage.size(); i++) {
            System.out.println(String.format("%d.",i+1) + Storage.get(i).toString());
        }
        System.out.println(line);
    }
    public void done(int index){
        if(index > Storage.size() || index <= 0){
            DE.ExceedList();
        } else {
            Task UpdateCurrAction = Storage.get(index - 1);
            UpdateCurrAction.isDone = true;
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(UpdateCurrAction.toString());
            System.out.println(line);
        }
    }
    public void todo(String S){
        this.cmd = S;
        Task T = new Todo(cmd);
        T.Output();
        System.out.println(String.format("Now you have %d tasks in the list.", counter));
        System.out.println(line);
        Storage.add(T);
        counter = counter + 1;
    }
    public void deadline(String[] ActionTime){
        String Timing;
        this.cmd = ActionTime[0];
        String details = ActionTime[1].substring(3).strip();
        String[] DateTime = details.split(" ");
        this.LD = LocalDate.parse(DateTime[0]);
        String Date = LD.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if(DateTime.length == 2){
            this.LT = LocalTime.parse(DateTime[1]); //accept time of 10:15 format
            String Time = LT.format(DateTimeFormatter.ofPattern("hh:mm a"));
            Timing = Date + " " + Time;
        } else {
            Timing = Date;
        }
        Task T = new Deadline(cmd, Timing);
        T.Output();
        System.out.println(String.format("Now you have %d tasks in the list.", counter));
        System.out.println(line);
        Storage.add(T);
        counter = counter + 1;
    }
    public void event(String[] ActionTime){
        String Timing;
        this.cmd = ActionTime[0];
        String details = ActionTime[1].substring(3).strip();
        String[] DateTime = details.split(" ");
        this.LD = LocalDate.parse(DateTime[0]);
        String Date = LD.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        if(DateTime.length == 2){
            this.LT = LocalTime.parse(DateTime[1]); //accept time of 10:15 format
            String Time = LT.format(DateTimeFormatter.ofPattern("hh:mm a"));
            Timing = Date + " " + Time;
        } else {
            Timing = Date;
        }
        Task T = new Event(cmd, Timing);
        T.Output();
        System.out.println(String.format("Now you have %d tasks in the list.", counter));
        System.out.println(line);
        Storage.add(T);
        counter = counter + 1;
    }
    public void delete(int index){
        if(index > Storage.size() || index <= 0){
            DE.ExceedList();
        } else {
            Task UpdateCurrAction = Storage.get(index - 1);
            System.out.println(line);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(UpdateCurrAction.toString());
            Storage.remove(index - 1);
            System.out.println(String.format("Now you have %d tasks in the list", Storage.size()));
            System.out.println(line);

        }
    }
}

