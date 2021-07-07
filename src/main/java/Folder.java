package packagedirectory.test;

import packagedirectory.test.Tasks;
import packagedirectory.test.Message;
import java.util.ArrayList;

public class Folder {
    private ArrayList<Tasks> listTasks;

    Folder() {
        listTasks = new ArrayList<>();
    }

    public void add(Tasks tasks) {
        listTasks.add(tasks);
    }

    public String finishTasks(int i) {
        return listTasks.get(i-1).done();
    }

    public String finishAll() {
        String output = "Finished all items in list";
        listTasks.stream().forEach(x -> x.done());
        return output;
    }

    public String deleteTasks(int i) {
        return listTasks.remove(i-1).removed();
    }

    public String deleteAll() {
        String output = "Removed all items in list";
        listTasks.clear();
        return output;
    }

    public String getText() {
        int j = 1;
        String output = "";
        for(Tasks x: listTasks) {
            output = output + x.logo + "=" + x.status + "=" + x.msg.getMsg() + "\n";
            j++;
        }
        return output;
    }

    public String find(String word) {
        int i = 1;
        String output = Message.lines + "Here are the tasks in your list:\n";
        for(Tasks x: listTasks) {
            if(x.getMsg().getMsg().contains(word)) {
                output = output + i + ": " + x;
                i++;
            }
        }
        output += Message.lines;
        return output;
    }

    public String show() {
        int i = 1;
        String output = Message.lines + "Here are the tasks in your list:\n";
        for(Tasks x: listTasks) {
            output = output + i + ": " + x;
            i++;
        }
        output += Message.lines;
        return output;
    }
}
