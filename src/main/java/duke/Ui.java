package cathulhu;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    /**
     * Constructor for the UI class
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Retrieves the next line of user command via Scanner's nextLine() method
     * @return String containing the user command
     */
    public String getUserCmd() {
        return sc.nextLine();
    }

    /**
     * Speaks on behalf of Cathulhu
     * @param msg message to be printed out on the cli
     */
    public void cathulhuSays(String msg) {
        System.out.println(msg);
    }

    /**
     * Shows the welcome message for this program
     */
    public void showWelcome() {
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
    }


    /**
     * Shows the goodbye message for this program
     */
    public void showGoodbye() {
        sc.close();
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
        System.out.println("\tBe gone then, mortal.\n");
        System.out.println(catLeaves);
    }

    /**
     * Prints a line before each user command response
     */
    public void printLine1() {
        System.out.println("\t-------------C-A-T-H-U-L-H-U-------------");
    }

    /**
     * Prints a line after each user command response
     */
    public void printLine2() {
        System.out.println("\t----------------M-E-O-W-S----------------\n");
    }


    /**
     * Prints out the error message received
     * @param e Typically CathulhuException error with message to be printed out
     */
    public void printError(Exception e) {
        System.err.println(e);
    }

}
