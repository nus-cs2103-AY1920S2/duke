public class Task {
    String name;
    boolean done = false;
    public Task(String name){
        this.name = name;
    }
    public void markAsDone(){
        done = true;
    }
    @Override
    public String toString(){
        return "[" + (done ? "✓" : "✗") + "] " + name;
    }
}
