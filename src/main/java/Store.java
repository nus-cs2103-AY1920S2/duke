import java.util.ArrayList;

public class Store {

    private String line = "____________________________________________________________";
    private String cmd;
    private Integer counter;
    private ArrayList<Task> Storage;

    public Store(){
        counter = 1;
        Storage = new ArrayList<>();
    }
    public void AddNewAction(String S) {
        this.cmd = S;
        System.out.println(line);
        System.out.println("added: " + cmd);
        System.out.println(line);
        Task T = new Task(cmd, counter);
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
        for(Task act:Storage) {
            System.out.println(String.format("%d.",act.index) + act.toString());
        }
        System.out.println(line);
    }
    public void done(int index){
        Task UpdateCurrAction = Storage.get(index-1);
        UpdateCurrAction.isDone = true;
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(UpdateCurrAction.toString());
        System.out.println(line);
    }
    public void todo(String S){
        this.cmd = S;
        Task T = new Todo(cmd, counter);
        Storage.add(T);
        T.Output();
        counter = counter + 1;
    }
    public void deadline(String[] ActionTime){
        this.cmd = ActionTime[0];
        Task T = new Deadline(cmd, counter, ActionTime[1]);
        Storage.add(T);
        T.Output();
        counter = counter + 1;
    }
    public void event(String[] ActionTime){
        this.cmd = ActionTime[0];
        Task T = new Event(cmd, counter, ActionTime[1]);
        Storage.add(T);
        T.Output();
        counter = counter + 1;
    }
}

