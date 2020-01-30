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
        } else if (s.length() > 6 && s.substring(0,6).equals("delete")) {
            delete(Integer.parseInt(s.substring(7)));
        } else if (s.length() < 5 || !s.contains(" ") || s.stripTrailing().length() < 9) {
            error(s);
        } else {
            add(s);
        }
    }


    public void list() {
        System.out.println(linedivider + "Here are the tasks in your list:");
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
            } else if (taskType.equals("event")) {
                Event t = new Event(theTask, date, tasks.size() + 1);
                tasks.add(t);
                System.out.println(linedivider + "Got it. I've added this task:\n " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);
            }

        }
    }

    public void done(int n) {
        tasks.get(n-1).makeDone();
        System.out.println(linedivider + "Nice! I've marked this task as done:\n" + tasks.get(n-1)
                + "\n" + linedivider);
    }

    public void delete(int n) {
        Task rm = tasks.remove(n - 1);
        System.out.println(linedivider + "Noted. I've removed this task:\n" + rm + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n" + linedivider);
    }

    public void error(String s) {
        System.out.println(linedivider);
        if (s.length() >= 4 && (s.stripTrailing().equals("todo")
                || s.stripTrailing().equals("deadline") || s.stripTrailing().equals("event"))) {
            if (s.stripTrailing().equals("todo")) {
                System.out.println(":( OOPS!!! The description of a todo cannot be empty.");
            } else if (s.stripTrailing().equals("deadline")) {
                System.out.println(":( OOPS!!! The description of a deadline cannot be empty.");
            } else if (s.stripTrailing().equals("event")) {
                System.out.println(":( OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            System.out.println(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println(linedivider);
    }

}
