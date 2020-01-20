package projectdirectoryjava;

import java.util.ArrayList;

public class Folder {
    private ArrayList<Message> listTasks;

    Folder() {
        listTasks = new ArrayList<>();
    }

    public void add(Message tasks) {
        listTasks.add(tasks);
    }

    public void show() {
        int i = 1;
        String output = Message.lines;
        for(Message x: listTasks) {
            output += i + ": " + x.getMsg() + "\n";
            i++;
        }
        output += Message.lines;
        System.out.println(output);
    }
}
