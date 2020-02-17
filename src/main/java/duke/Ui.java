package duke;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    private String cathulhuSaysString;

    /**
     * Constructs the Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
        this.cathulhuSaysString = "";
    }


    /**
     * Concatenates Strings Cathulhu intends to say,
     * so that Cathulhu can say everything in one go in cathulhuSaysItOut().
     *
     * @param msg message to be printed out on the cli
     */
    public void cathulhuSays(String msg) {
        if (this.cathulhuSaysString.equals("")) {
            cathulhuSaysString = msg;
        } else {
            cathulhuSaysString += "\n" + msg;
        }
    }


    /**
     * Returns everything Cathulhu wanted to say and clears them from Cathulhu's memory.
     *
     * @return String to be shown to the user
     */
    public String cathulhuSaysItOut() {
        String retStr = this.cathulhuSaysString;
        flush();
        return retStr;
    }


    /**
     * Clears what Cathulhu want to say from his memory.
     */
    public void flush() {
        this.cathulhuSaysString = "";
    }


    /**
     * Retrieves the next line of user command via Scanner's nextLine() method.
     *
     * @return String containing the user command
     */
    public String getUserCmd() {
        return sc.nextLine();
    }


    /**
     * Prints out the error message received.
     *
     * @param e Typically CathulhuException error with message to be printed out
     */
    public void printError(Exception e) {
        cathulhuSays(e.getMessage());
    }


    /**
     * Prints a line before each user command response.
     */
    public void printLine1() {
        cathulhuSays("\t-------------C-A-T-H-U-L-H-U-------------");
    }


    /**
     * Prints a line after each user command response.
     */
    public void printLine2() {
        cathulhuSays("\t----------------M-E-O-W-S----------------\n");
    }


    /**
     * Shows the goodbye message for this program.
     */
    public void showGoodbye() {
        sc.close();
        String catLeaves = ".......................................Iä! Iä! .......................\n"
                + "...........................................Cthul......................\n"
                + "............................................hu fh.....................\n"
                + ".........','...............................tagn!......................\n"
                + "........'Ph'nglui mglw'nfah Cthulhu R'lyeh wgah.......................\n"
                + ".......'nagl fhatgn! Iä! Iä! Cthulhu fhtagn! P........................\n"
                + ".......h'nglui mglw'nfah Cthulhu R'lyeh wgah'n........................\n"
                + "........agl fhtagn! Iä! Iä! Cthulhu fhtagn! P.........................\n"
                + "...............h'nglui mglw'nfah Cthulhu R'ly;........................\n"
                + "................yeh wgah'nagl fhtagn! Iä! Iä!,........................\n"
                + "...............Cthul...hufht.....agn!Ph'nglui ........................\n"
                + "..............mglw.....'nfah.....Cthu.....lhu.........................\n"
                + ".............R'ly........ehw....gah'.......nag........................\n"
                + ".............lf...........ht....ag..........n!........................\n";
        cathulhuSays("Be gone then, mortal.\n" + catLeaves);
    }


    /**
     * Shows the welcome message for this program.
     */
    public void showWelcome() {
        String logo = "         _..._                                                                          \n"
                + "      .-'_..._''.                                         .---.                         \n"
                + "    .' .'      '.\\                    .                   |   |   .                     \n"
                + "   / .'                             .'|                   |   | .'|                     \n"
                + "  . '                           .| <  |                   |   |<  |                     \n"
                + "  | |                 __      .' |_ | |                   |   | | |                     \n"
                + "  | |              .:--.'.  .'     || | .'''-.     _    _ |   | | | .'''-.     _    _   \n"
                + "  . '             / |   \\ |'--.  .-'| |/.'''. \\   | '  / ||   | | |/.'''. \\   | '  / |  \n"
                + "   \\ '.          .`\" __ | |   |  |  |  /    | |  .' | .' ||   | |  /    | |  .' | .' |  \n"
                + "    '. `._____.-'/ .'.''| |   |  |  | |     | |  /  | /  ||   | | |     | |  /  | /  |  \n"
                + "      `-.______ / / /   | |_  |  '.'| |     | | |   `'.  |'---' | |     | | |   `'.  |  \n"
                + "               `  \\ \\._,\\ '/  |   / | '.    | '.'   .'|  '/     | '.    | '.'   .'|  '/ \n"
                + "                  `--'  `\"   `'-'  '---'   '---'`-'  `--'      '---'   '---'`-'  `--'  \n";
        cathulhuSays(logo + "\tIt's you again, mortal. \n\tWhy have thou summoned meow this time?\n");
    }
}
