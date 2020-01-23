import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> userInputList = new ArrayList<>();
        String logo = "  ____\n" + " (.__.)\n" + "<|>\n" + " /\\" + "\n_  _";
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("☛ Dude, what do you want? \n☛ Give me a command!");
        System.out.print("dude, ");
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("You asked to:");
                for (String item : userInputList) {
                    System.out.println("☛ " + item);
                }
            }
            else {
                userInputList.add(userInput);
                System.out.println("☛ fine, I will: " + userInput);
            }
            System.out.print("dude, ");
            userInput = sc.nextLine();
        }
        System.out.println("☛ dude, stop wasting my time! shoo!");

    }
}

