public class Task {
    public boolean done;
    public String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void toggleDone() {
        this.done = !this.done;
    }

    public String toFile() {
        int doneInt = done ? 1 : 0;
        return "Q , " + doneInt + " , " + name;
    }

    @Override
    public String toString() {
        String symbol;
        if (done) {
            symbol = "O";
        } else {
            symbol = "X";
        }
        return "[" + symbol + "] " + this.name;
    }
}
