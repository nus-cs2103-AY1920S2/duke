import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        String greeting = "Hey there! I'm DingDing!\n"
                + "    What's up today? ;D";

        printWithBorder(greeting);
        output();
    }

    public static void output() {
        String reply = SCANNER.nextLine();
        ArrayList<String> taskList = new ArrayList<>();


        while(!reply.equals("bye")) {
            if(reply.equals("list")) {
                String completeList = "";
                for(String task :taskList) {
                    completeList += ((taskList.indexOf(task) + 1) + ". " + task) + "\n    ";
                }
                printWithBorder(completeList);
                reply = SCANNER.nextLine();
            } else {
                taskList.add(reply);
                printWithBorder("Added: " + reply);
                reply = SCANNER.nextLine();
            }
        }

        printWithBorder("Bye! See ya later, alligator!");
    }


    public static void printWithBorder(String message) {
        System.out.println("    #############################\n"
                + "    " + message +"\n"
                + "    #############################\n");

    }

}
