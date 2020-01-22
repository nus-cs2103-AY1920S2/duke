import java.util.ArrayList;

public class Chatbot implements Chat {
    private String name;
    private String greeting;
    private String goodbye;
    private ArrayList<String> record;

    public Chatbot(String name) {
        this.name = name;
        this.record = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void greet() {
        System.out.println(this.greeting);
    }

    public void echo(String command) {
        String str = "--------------------------------------------------\n" +
                     command + "\n" +
                     "--------------------------------------------------\n";
        System.out.println(str);
    }

    public String getGoodbye() {
        return goodbye;
    }

    public void setGoodbye(String goodbye) {
        this.goodbye = goodbye;
    }

    public void goodbye() {
        System.out.println(this.goodbye);
    }

    public void addRecord(String record) {
        this.record.add(record);
    }

    public void listRecord() {
        for (int i = 0; i < record.size(); i++) {
            int j = i + 1;
            System.out.print(j + ". " + record.get(i) + "\n");
        }
    }
}
