import java.util.Scanner;

public class Akshay {
    public static void main(String[] args) {
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line);
        System.out.println("Hello from\n[A K S H A Y]\nHow may I help you?");
        System.out.println(line);

        Scanner sc =  new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = sc.nextLine();
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        System.out.println("Bye! Hope to see you again!");
    }
}
