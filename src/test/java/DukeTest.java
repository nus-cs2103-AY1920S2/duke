import java.util.Scanner;

public class DukeTest {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke.\nWhat can i do for you?");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        while (!str.equals("bye")) {
            System.out.println(str);
            str = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}