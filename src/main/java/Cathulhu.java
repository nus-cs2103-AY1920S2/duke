import java.util.Scanner;

public class Cathulhu {

    private void interact() {
        Scanner sc = new Scanner(System.in);
        String cmd = "";
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
            cmd = sc.nextLine();
            if (cmd.equalsIgnoreCase("bye")) {
                System.out.println("\tBe gone then, mortal.\n");
                System.out.println(catLeaves);
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
