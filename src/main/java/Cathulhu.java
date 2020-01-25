import java.util.ArrayList;
import java.util.Scanner;

public class Cathulhu {

    private void interact() {
        Scanner sc = new Scanner(System.in);
        String cmd = "";
        ArrayList<Task> tasks = new ArrayList<>();
        String catLeaves = "......................................................................\n"
        +".......................................Iä! Iä! .......................\n"
        +"...........................................Cthul......................\n"
        +"............................................hu fh.....................\n"
        +".........','...............................tagn!......................\n"
        +"........'Ph'nglui mglw'nfah Cthulhu R'lyeh wgah.......................\n"
        +".......'nagl fhatgn! Iä! Iä! Cthulhu fhtagn! P........................\n"
        +".......h'nglui mglw'nfah Cthulhu R'lyeh wgah'n........................\n"
        +"........agl fhtagn! Iä! Iä! Cthulhu fhtagn! P.........................\n"
        +"...............h'nglui mglw'nfah Cthulhu R'ly;........................\n"
        +"................yeh wgah'nagl fhtagn! Iä! Iä!,........................\n"
        +"...............Cthul...hufht.....agn!Ph'nglui ........................\n"
        +"..............mglw.....'nfah.....Cthu.....lhu.........................\n"
        +".............R'ly........ehw....gah'.......nag........................\n"
        +".............lf...........ht....ag..........n!........................\n"
        +"......................................................................\n";

        while (true) {
            System.out.println("\t-------------C-A-T-H-U-L-H-U-------------");
            cmd = sc.nextLine().strip();
            if ( cmd.equalsIgnoreCase("bye") ) {
                System.out.println("\tBe gone then, mortal.\n");
                System.out.println(catLeaves);
                break;
            } else if ( cmd.equalsIgnoreCase("list") ) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("\t" + i + ". " + tasks.get(i-1) );
                }
            } else if (cmd.matches("^done[ ]+[0-9]+?")) {
                int taskIndex = Integer.parseInt(cmd.split("[ ]+")[1])-1;
                if (tasks.size() > taskIndex) {
                    tasks.get(taskIndex).markAsDone();
                    System.out.println("Mortal, thou have completed this task:");
                    System.out.println(tasks.get(taskIndex));
                } else {
                    System.out.println("Mortal, thou have no such task! ");
                }
            } else {
                tasks.add(new Task(cmd));
                System.out.println("\tadded: " + cmd);
            }
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
        System.out.println(logo + "\n\n\tIt's you again, mortal. \n\tWhy have thou summoned meow this time?");
        Cathulhu newCathulhu = new Cathulhu();
        newCathulhu.interact();
    }
}
