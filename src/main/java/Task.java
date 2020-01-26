public abstract class Task {
    boolean isDone = false;

    String tick = "[" + (char) 10003 + "]";
    String cross = "[" + (char) 10060 + "]";


    abstract public String toString();

    public void setDone() {
        isDone = true;
    }

    abstract public String formatToStore();

}
