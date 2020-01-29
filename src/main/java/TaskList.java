import java.util.ArrayList;

public class TaskList {
    //contains the task list
    public static ArrayList<Task> newList;
    public static Ui ui;

    public TaskList() {
        this.newList = new ArrayList<>();
        this.ui = Duke.ui;
    }

    public void list() {
        if (newList.size() == 0) {
            ui.dukePrint("You currently have no tasks in your list\n");
        } else {
            System.out.print(ui.horizontalLines() + "Here are the tasks in your list:\n");
            for (int i = 0; i < newList.size(); i += 1) {
                System.out.print((i + 1)+". "+ newList.get(i).toString()+"\n");
            }
            System.out.print(ui.horizontalLines());
        }
    }

    public void done(int i) {
        newList.get(i).markAsDone();
        ui.dukePrint("Nice! I've marked this task as done: \n"+ newList.get(i).toString()+"\n");
    }

    public void delete(int i) {
        Task task = newList.get(i);
        newList.remove(i);
        ui.dukePrint("Noted. I've removed this task:\n"+task.toString()+"\n" +
                "Now you have " + newList.size() + " tasks in the list.\n");
    }

    public void add (Task newTask) {
        newList.add(newTask);
        ui.dukePrint("Got it. I've added this task:\n"+
                newList.get(newList.size() - 1).toString()+"\n" +
                "Now you have "+ newList.size() +" tasks in the list.\n");
    }

    public int size() {
        return newList.size();
    }

    public Task get(int index) {
        return newList.get(index);
    }
}