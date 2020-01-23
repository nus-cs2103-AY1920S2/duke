import java.util.ArrayList;

public class Functionality {

    private ArrayList<Task> tasks;
    private String linedivider = "____________________________________________________________\n";
    public Functionality() {
        tasks = new ArrayList<>();
    }

    public void enter(String s) {
        if (s.equals("list")) {
            list();
        } else if (s.length() > 5 && s.substring(0,4).equals("done")) {
            done(Integer.parseInt(s.substring(5)));
        } else {
            add(s);
        }
    }

    public void list() {
        System.out.println(linedivider + "Here are the tasks in your list:\n");
        for (Task ls : tasks) {
            System.out.println(ls);
        }
        System.out.println(linedivider);
    }

    public void add(String s) {
        Task t = new Task(s, tasks.size() + 1);
        tasks.add(t);
        System.out.println(linedivider + "Got it. I've added this task:\n" + t + "\n" + linedivider);
    }

    public void done(int n) {
        tasks.get(n-1).makeDone();
        System.out.println(linedivider + "Nice! I've marked this task as done:\n" + tasks.get(n-1)
                + "\n" + linedivider);
    }

}
