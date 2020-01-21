public class List {
    Item[] items = new Item[100];
    static String space = "     ";
    static String line = space + "____________________________________________________________";
    int count = 0;
    List() {
        this.items = items;
        this.count = count;
    }

    public void addItem(Item item) {
        this.items[this.count] = item;
        this.count ++;
        String response = line + "\n" + space + " Got it. I've added this task:\n" + space + this.items[this.count-1]
            + space + " Now you have " + this.count + " task";
        if (this.count > 1) {
            response += "s";
        }
            response += " in the list.\n";
        response += line;
        System.out.println(response);
    }

    @Override
    public String toString() {
        String temp = space + " Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            temp += space;
            temp += " ";
            temp += Integer.toString(i+1);
            temp += this.items[i];
        }
        temp += line;
        return temp;
    }
}
