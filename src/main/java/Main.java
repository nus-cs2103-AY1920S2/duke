import duchess.Duchess;

public class Main {
    public static void main(String[] args) {
        Duchess duchess = new Duchess("data/tasks.json");
        duchess.run();
    }
}
