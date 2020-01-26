import java.util.ArrayList;
import java.util.Scanner;

public class Cathulhu {

    private void interact() {

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        boolean byebye = false;

        while (!byebye) {

            String[] cmdString = sc.nextLine()
                    .strip()
                    .split(" ", 2);

            System.out.println("\t-------------C-A-T-H-U-L-H-U-------------");

            try {
                byebye = parse(tasks, cmdString);
            } catch (CathulhuException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\t----------------M-E-O-W-S----------------\n\n");
        }

        sc.close();
    }

    /**
     * Parses the command user enters
     * @param tasks ArrayList<Task> that stores all the current tasks
     * @param cmdString String[] containing the user command, split by the first whitespace character
     * @return boolean. true indicates that the user has terminated the program (by typing "bye")
     * @throws CathulhuException for invalid user commands
     */
    private boolean parse(ArrayList<Task> tasks, String[] cmdString) throws CathulhuException {
        if ( cmdString[0].equalsIgnoreCase("bye") ) { //bye
            return true;

        } else if ( cmdString[0].equalsIgnoreCase("list") ) { //list

            if (tasks.size()==0) {
                System.out.println("\tYou are free now, mortal.");
            } else {
                System.out.println("\tHere are your tasks, mortal:\n");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("\t" + i + ". " + tasks.get(i - 1));
                }
            }

        } else if (cmdString[0].equalsIgnoreCase("done")) { //done
            int taskIndex = Integer.parseInt(cmdString[1])-1;
            if (tasks.size() <= taskIndex) {
                throw new CathulhuException("Mortal, thou have no such task!");
            }
            tasks.get(taskIndex).markAsDone();
            System.out.println("\tMortal, thou have completed this task:");
            System.out.println("\t  " + tasks.get(taskIndex));

        } else if (cmdString[0].equalsIgnoreCase("todo")){ //todo
            if (cmdString.length==1) {
                throw new CathulhuException("A \"todo\" task must have a description, mortal!");
            }
            tasks.add(new ToDo(cmdString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.get(tasks.size()-1), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("deadline")){ //deadline
            if (cmdString.length==1) {
                throw new CathulhuException("A \"deadline\" task must have a description and a deadline, mortal!");
            }
            String[] byString = cmdString[1].split("/by ", 2);
            if (byString.length==1) {
                throw new CathulhuException("A \"deadline\" task must have a deadline, mortal!");
            }
            tasks.add(new Deadline(byString[0], byString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.get(tasks.size()-1), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("event")){ //event
            if (cmdString.length==1) {
                throw new CathulhuException("An \"event\" task must have a description and a time, mortal!");
            }
            String[] atString = cmdString[1].split("/at ", 2);
            if (atString.length==1) {
                throw new CathulhuException("An \"event\" task must have a time, mortal!");
            }
            tasks.add(new Event(atString[0], atString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.get(tasks.size()-1), tasks.size());

        } else {
            throw new CathulhuException("\tMortal, that's an invalid Task!");

        }

        return false;
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


    public static void main(String[] args) {
        Cathulhu newCathulhu = new Cathulhu();
        newCathulhu.showWelcome();
        newCathulhu.interact();
        newCathulhu.showGoodbye();
    }
}
