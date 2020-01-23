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
        int entryno = 1;
        for (Task ls : tasks) {
            System.out.println(entryno + ". " + ls);
            entryno++;
        }
        System.out.println(linedivider);
    }

    public void add(String s) {
        int whitespaceidx = s.indexOf(" ");
        String taskType = s.substring(0, whitespaceidx);
        if (taskType.equals("todo")) {
            String theTask = s.substring(whitespaceidx + 1);
            Todo t = new Todo(theTask, tasks.size() + 1);
            tasks.add(t);
            System.out.println(linedivider + "Got it. I've added this task:\n " + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);
        } else {
            int taskIdx = s.indexOf("/");
            String theTask = s.substring(whitespaceidx + 1, taskIdx - 1);
            String date = s.substring(taskIdx + 1);
            if (taskType.equals("deadline")) {
                Deadline t = new Deadline(theTask, date, tasks.size() + 1);
                tasks.add(t);
                System.out.println(linedivider + "Got it. I've added this task:\n " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);
            } else {
                Event t = new Event(theTask, date, tasks.size() + 1);
                tasks.add(t);
                System.out.println(linedivider + "Got it. I've added this task:\n " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);            }

        }
    }

    public void done(int n) {
        tasks.get(n-1).makeDone();
        System.out.println(linedivider + "Nice! I've marked this task as done:\n" + tasks.get(n-1)
                + "\n" + linedivider);
    }

}
