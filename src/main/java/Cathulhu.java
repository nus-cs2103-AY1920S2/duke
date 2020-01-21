import java.util.Scanner;

public class Cathulhu {

    private void interact() {
        Scanner sc = new Scanner(System.in);
        String cmd = "";
        while (true) {
            System.out.println("\t-------------C-A-T-H-U-L-H-U-------------");
            cmd = sc.nextLine();
            if (cmd.equalsIgnoreCase("bye")) {
                System.out.println("\tBe gone then, mortal.");
                break;
            }
            System.out.println("\t" + cmd);
            System.out.println("\t----------------M-E-O-W-S----------------\n\n");
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = "         _..._                                                                          \n"
        +"      .-'_..._''.                                         .---.                         \n"
        +"    .' .'      '.\\                    .                   |   |   .                     \n"
        +"   / .'                             .'|                   |   | .'|                     \n"
        +"  . '                           .| <  |                   |   |<  |                     \n"
        +"  | |                 __      .' |_ | |                   |   | | |                     \n"
        +"  | |              .:--.'.  .'     || | .'''-.     _    _ |   | | | .'''-.     _    _   \n"
        +"  . '             / |   \\ |'--.  .-'| |/.'''. \\   | '  / ||   | | |/.'''. \\   | '  / |  \n"
        +"   \\ '.          .`\" __ | |   |  |  |  /    | |  .' | .' ||   | |  /    | |  .' | .' |  \n"
        +"    '. `._____.-'/ .'.''| |   |  |  | |     | |  /  | /  ||   | | |     | |  /  | /  |  \n"
        +"      `-.______ / / /   | |_  |  '.'| |     | | |   `'.  |'---' | |     | | |   `'.  |  \n"
        +"               `  \\ \\._,\\ '/  |   / | '.    | '.'   .'|  '/     | '.    | '.'   .'|  '/ \n"
        +"                  `--'  `\"   `'-'  '---'   '---'`-'  `--'      '---'   '---'`-'  `--'  \n";
        System.out.println(logo + "\n\n\tIt's you again, mortal. \n\tWhy have you summoned meow this time?");
        Cathulhu newCathulhu = new Cathulhu();
        newCathulhu.interact();
    }
}
