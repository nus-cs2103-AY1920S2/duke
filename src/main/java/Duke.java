import java.util.Scanner;
public class Duke {

    public static int count = 0;
    public static String[] listOfString = new String[100];
    public static void HorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greeting(){
        HorizontalLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        HorizontalLine();
    }

    public static void listcommand() {
        HorizontalLine();
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ". " + listOfString[i]);
        }
        HorizontalLine();
    }
    public static void exit(){
        HorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("");
        HorizontalLine();
    }

    public static void add(String line) {
        HorizontalLine();
        listOfString[count++] = line;
        System.out.println("added: " + line);
        HorizontalLine();
    }
    public static void main(String[] args) {

        // Greet the user
        greeting();

        Scanner sc = new Scanner(System.in);

        //Output the command the user types
        // Exits when the user types 'bye'
        String line = sc.nextLine();
        while (!line.equals("bye")) {
            // Output to the user
            if (line.equals("list")) {
                listcommand();
            } else {
                add(line);
            }
            line = sc.nextLine();
        }

        // Say 'bye' to the user
        exit();
    }
}
