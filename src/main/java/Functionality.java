import java.util.ArrayList;

public class Functionality {

    private ArrayList<String> tasks;
    private String linedivider = "____________________________________________________________\n";
    public Functionality() {
        tasks = new ArrayList<>();
    }

    public String levelone(String s) {
        return s;
    }

    public void leveltwo(String s) {
        if (s.equals("list")) {
            list();
        } else {
            add(s);
        }
    }

    public void list() {
        System.out.println(linedivider);
        int num = 1;
        for (String ls : tasks) {
            System.out.println(num + ". " + ls);
            num++;
        }
        System.out.println(linedivider);
    }

    public void add(String s) {
        tasks.add(s);
        System.out.println(linedivider + "added: " + s + "\n" + linedivider);
    }

}
