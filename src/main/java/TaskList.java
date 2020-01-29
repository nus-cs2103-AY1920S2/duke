import java.util.ArrayList;

public class TaskList {
    //contains the task list
    public static ArrayList<Task> newList;

    public TaskList() {
        this.newList = new ArrayList<>();
    }

    public static void list() {
        if (newList.size() == 0) {
            Duke.dukePrint("You currently have no tasks in your list\n");
        } else {
            System.out.print(Duke.horizontalLines() + "Here are the tasks in your list:\n");
            for (int i = 0; i < newList.size(); i += 1) {
                System.out.print((i + 1)+". "+ newList.get(i).toString()+"\n");
            }
            System.out.print(Duke.horizontalLines());
        }
    }

    public static void done(int i) {
        newList.get(i).markAsDone();
        Duke.dukePrint("Nice! I've marked this task as done: \n"+ newList.get(i).toString()+"\n");
    }

    public static void delete(int i) {
        Task task = newList.get(i);
        newList.remove(i);
        Duke.dukePrint("Noted. I've removed this task:\n"+task.toString()+"\n" +
                "Now you have " + newList.size() + " tasks in the list.\n");
    }

    public static void add (Task newTask) {
        newList.add(newTask);
        Duke.dukePrint("Got it. I've added this task:\n"+
                newList.get(newList.size() - 1).toString()+"\n" +
                "Now you have "+ newList.size() +" tasks in the list.\n");
    }

    public static int size() {
        return newList.size();
    }

    public static Task get(int index) {
        return newList.get(index);
    }
}