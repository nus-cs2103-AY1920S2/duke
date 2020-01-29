import java.io.*;
import java.util.ArrayList;

public class Store {

    private String line = "____________________________________________________________";
    private String cmd;
    private Integer counter;
    private ArrayList<Task> Storage;
    private DukeException DE;
    File file;
    private int Status;

    public Store(File file){
        this.counter = 1;
        this.Storage = new ArrayList<>();
        this.DE = new DukeException();
        this.file = file;
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
        WritetoFile();
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
        System.exit(1);
    }
    public void list() {
        System.out.println(line);
        for(int i = 0; i < Storage.size(); i++) {
            String data = String.format("%d.",i+1) + Storage.get(i).toString();
            System.out.println(data);
        }
        System.out.println(line);
//        WritetoFile();
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
        WritetoFile();
    }
    public void todo(String S){
        this.cmd = S;
        Task T = new Todo(cmd);
        T.Output();
        Storage.add(T);
        System.out.println(String.format("Now you have %d tasks in the list.", Storage.size()));
        System.out.println(line);
        WritetoFile();
//        counter = counter + 1;
    }
    public void deadline(String[] ActionTime){
        this.cmd = ActionTime[0];
        Task T = new Deadline(cmd, ActionTime[1]);
        T.Output();
        Storage.add(T);
        System.out.println(String.format("Now you have %d tasks in the list.", Storage.size()));
        System.out.println(line);
        WritetoFile();
//        counter = counter + 1;
    }
    public void event(String[] ActionTime){
        this.cmd = ActionTime[0];
        Task T = new Event(cmd, ActionTime[1]);
        T.Output();
        Storage.add(T);
        System.out.println(String.format("Now you have %d tasks in the list.", Storage.size()));
        System.out.println(line);
        WritetoFile();
//        counter = counter + 1;
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
        WritetoFile();
    }
    public void WritetoFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < Storage.size(); i++) {
                String data = String.format("%d.", i + 1) + Storage.get(i).toString();
                fileWriter.write(data);
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void load(String S){
        boolean R = CheckIfDone(S);
        if(S.contains("[T]")){
            String result = S.substring(9);
            Task T = new Todo(result);
            T.isDone = R;
            Storage.add(T);
            WritetoFile();
        } else if (S.contains("[D]")){
            String NewInput = S.substring(9);
            String[] AT = NewInput.split("\\|");
            Task T = new Deadline(AT[0], AT[1]);
            T.isDone = R;
            Storage.add(T);
            WritetoFile();
        } else if (S.contains("[E]")){
            String result = S.substring(9);
            String[] AT = result.split("\\|");
            Task T = new Event(AT[0], AT[1]);
            T.isDone = R;
            Storage.add(T);
            WritetoFile();
        }
    }
    public boolean CheckIfDone(String S){
        return S.contains("✓");
    }
}

