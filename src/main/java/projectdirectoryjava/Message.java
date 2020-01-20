package projectdirectoryjava;

public class Message {
    public static String lines = "--------------------------------------------------\n";
    private String message;

    public Message(){
        this.message = "";
    }

    public Message(String message) {
        this.message = message;
    }

    public void add(String msg) {
        this.message = msg;
    }

    public String getMsg() {
        return message;
    }

    public void added() {
        String output = lines + "added: " + message + "\n" + lines;
        System.out.println(output);
    }

    public static void welcome() {
        System.out.println(lines + "Hello Edmond! How can i help you?\n"
                + "What can I do for you?\n" + lines);
    }

    public static void end() {
        System.out.println(lines + "Bye. See you next time!\n" + lines);
    }

    @Override
    public String toString() {
        return lines + message + "\n" + lines;
    }
}