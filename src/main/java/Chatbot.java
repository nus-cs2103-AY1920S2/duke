import java.util.ArrayList;

public class Chatbot implements Chat {
    private String name;
    private String greeting;
    private String goodbye;

    public Chatbot(String name) {
        this.name = name;
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
}
