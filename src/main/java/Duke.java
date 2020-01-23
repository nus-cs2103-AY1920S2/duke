import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        String userInput = myObj.nextLine();

        while(!userInput.equals("bye")){
            System.out.println("    ____________________________________________________________");
            System.out.println("     " +userInput);
            System.out.println("    ____________________________________________________________\n");
            userInput = myObj.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
