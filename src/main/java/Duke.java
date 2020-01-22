import java.util.*;

public class Duke {
    public static void main(String[] args) {


        System.out.println("Hello ! I'm Ashley\nOi What u want");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();

            if (str.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                return;

            } else {
                System.out.println(str + "\n");
            }
        }

    }
}
