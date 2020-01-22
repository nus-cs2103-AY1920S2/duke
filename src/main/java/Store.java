import java.util.ArrayList;

public class Store {

    String line = "____________________________________________________________";
    String cmd;
    Integer counter;
    ArrayList<String> Storage;

    public Store(){
        counter = 1;
        Storage = new ArrayList<>();
    }
    public void AddNewAction(String S) {
        this.cmd = S;
        System.out.println(line);
        System.out.println("added: " + cmd);
        System.out.println(line);
        Storage.add(String.format("%d. ", counter) + cmd);
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
        for(String act:Storage) {
            System.out.println(act);
        }
        System.out.println(line);
    }
}

