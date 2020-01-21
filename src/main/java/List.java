public class List {
    Item[] items = new Item[100];
    int count = 0;
    List() {
        this.items = items;
        this.count = count;
    }

    public void addItem(Item item) {
        this.items[this.count] = item;
        this.count ++;
        String response = "Got it. I've added this task:\n" + this.items[this.count-1]
            + "Now you have " + this.count + " task";
        if (this.count > 1) {
            response += "s";
        }
            response += " in the list";
        System.out.println(response);
    }

    @Override
    public String toString() {
        String temp = "Here are the tasks in your list:\n";
        for (int i = 0; i < count; i++) {
            temp += Integer.toString(i+1);
            temp += this.items[i];
        }
        return temp;
    }
}
