import java.util.Scanner;
import java.util.ArrayList;
public class Duke  {
    private ArrayList<String> textStorage;


    private Duke(ArrayList<String> textStorage){
        this.textStorage = textStorage;
    }
    public static void main(String[] args) {
        Duke chatbot = new Duke(new ArrayList<String>());
        chatbot.greet();
        chatbot.chat();
    }
    public void greet() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public void chat() {
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while(!message.equals("bye")) {
            if(message.equals("list")){
                this.printText();
            }
            else {
                System.out.println("added: " + message);
                this.textStorage.add(message);
            }
            message = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printText() {
        int counter = 1;
        for(String text : this.textStorage){
            System.out.println(counter + ". " + text);
            counter++;
    }
    }

}