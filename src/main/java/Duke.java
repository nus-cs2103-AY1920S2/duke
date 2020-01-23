import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "  ____\n" + " (.__.)\n" + "<|>\n" + " /\\" + "\n_  _";
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        System.out.println("Dude, what do you want? \nHurry, reply with 'dude, <command>'!");
        String userInput = sc.nextLine();
        while (!userInput.equals("dude, bye")) {
            if (!userInput.split(" ")[0].equals("dude,")) {
                System.out.println("dude, wrong command!");
            }
            else {
                System.out.println(userInput);
            }
            userInput = sc.nextLine();
        }
        System.out.println("dude, stop wasting my time! shoo!");

    }
}

