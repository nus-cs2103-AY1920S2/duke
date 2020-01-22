public class Task {
    private String str;
    private String status;

    public Task (String str) {
        this.str = str;
        status = "[✗]";
    }

    public void markDone() {
        status = "[✓]";
    }

    @Override
    public String toString() {
        return status + " " + str;
    }
}
