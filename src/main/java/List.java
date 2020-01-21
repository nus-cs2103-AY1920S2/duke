public class List {
    Item[] items = new Item[100];
    int count = 0;
    List() {
        this.items = items;
        this.count = count;
    }

    public void addItem(String temp) {
        this.items[this.count] = new Item(temp);
        this.count++;
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
