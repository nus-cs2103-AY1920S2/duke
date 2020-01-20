import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, Luke! I'm Your Father \nWhat can I do for you today?");
        String word = sc.nextLine();

        while(!word.equals("bye")){
            System.out.println(word);
            word = sc.nextLine();
        }
        System.out.println("Noooo Join the Dark Side, Luke, don't leave me");

    }
}
