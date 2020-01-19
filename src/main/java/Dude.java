import java.util.Scanner;

public class Dude {
    public static void main(String[] args) {
        respond("Wassup dude!");

        Scanner sc = new Scanner(System.in);
        String input;

        while (!(input = sc.nextLine()).equals("bye")) {
            respond(input);
        }

        respond("See ya!");
        sc.close();
    }

    public static void respond(String response) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        System.out.println("     " + response);
        System.out.println(line);
    }
}
