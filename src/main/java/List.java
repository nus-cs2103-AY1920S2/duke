import java.util.ArrayList;

public class List {
    ArrayList<Item> items = new ArrayList<Item>();
    static String space = "     ";
    static String line = space + "____________________________________________________________";
    int count = 0;
    List() {
        this.items = items;
        this.count = count;
    }

    public void addItem(Item item) {
        this.items.add(item);
        this.count ++;
        String response = line + "\n" + space + " Got it. I've added this task:\n" + space + this.items.get(items.size()-1)
            + space + " Now you have " + this.count + " task";
        if (this.count > 1) {
            response += "s";
        }
            response += " in the list.\n";
        response += line;
        System.out.println(response);
    }

    public List delete(int index) {
        count--;
        String response = line + "\n" + "Noted. I've removed this task:\n" + this.items.get(index) + "Now you have " + count + " task";
        if (this.count > 1) {
            response += "s";
        }
        response += " in the list.\n";
        response += line;
        System.out.println(response);
        this.items.remove(index);
        return this;
    }

    @Override
    public String toString() {
        String temp = space + " Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            temp += space;
            temp += " ";
            temp += Integer.toString(i+1);
            temp += this.items.get(i);
        }
        temp += line;
        return temp;
    }
}
