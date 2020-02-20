import java.util.*;

public class TaskList {
    public static ArrayList <Task> arr = new ArrayList <Task>();

    public TaskList(ArrayList <Task> al) {
        this.arr = al;
    }

    public ArrayList <Task> getArraylist() {
        return arr;
    }

    public String print() {
        String str = "";
        for (int i = 1; i <= arr.size(); i++) {
            if (i == 0) {
                str += (i + "." + arr.get(i - 1).getIcon() + arr.get(i - 1).status + " " + arr.get(i - 1).getDescription());
            } else {
                str += "\n" + (i + "." + arr.get(i - 1).getIcon() + arr.get(i - 1).status + " " + arr.get(i - 1).getDescription());
            }
        }
        return str;
    }
    public void clear() {
        arr.clear();
    }

    public ArrayList <String> getNames(ArrayList <Task> arr) {
        ArrayList <String> namesAL = new ArrayList <String> ();
        for (int i = 0; i < arr.size(); i++) {
            namesAL.add(arr.get(i).name);
        }
        return namesAL;
    }
}
