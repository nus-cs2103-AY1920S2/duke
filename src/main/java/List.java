public class List {
    private String[] list = new String[100];
    int count = 0;
    List(String[] list) {
        this.list = list;
        this.count = count;
    }

    public void addList(String temp) {
        this.list[this.count] = temp;
        this.count++;
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < count; i++) {
            temp += Integer.toString(i+1);
            temp += this.list[i];
        }
        return temp;
    }
}
