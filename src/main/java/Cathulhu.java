import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Cathulhu {

    private static final File TASKS_FILE = new File("./data/tasksFile.txt");
    private ArrayList<Task> tasks;

    public Cathulhu() {
        try{
            this.tasks = loadTasksFile();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private ArrayList<Task> loadTasksFile() throws IOException {

        boolean isCreated = TASKS_FILE.createNewFile();

        if (isCreated) {
            return new ArrayList<>();
        }

        Scanner sc = new Scanner(TASKS_FILE);
        ArrayList<Task> taskData = new ArrayList<>();

        while (sc.hasNextLine()) {

            String[] taskString = sc.nextLine().split(":;:");
//            System.out.println(Arrays.asList(taskString));
            switch(taskString[0]) {
                case "T":
                    Task tdTask = new ToDo(taskString[2]);
                    if (taskString[1].equals("1")) {
                        tdTask.markAsDone();
                    }
                    taskData.add(tdTask);
                    break;

                case "D":
                    try {
                        Task dlTask = new Deadline(taskString[2], taskString[3]);
                        if (taskString[1].equals("1")) {
                            dlTask.markAsDone();
                        }
                        taskData.add(dlTask);
                    } catch (CathulhuException e) {
                        System.out.println("Error loading data from TASKS_FILE. Skipping the following line:");
                        System.out.println(Arrays.asList(taskString));
                    }

                    break;

                case "E":
                    Task evTask = new Event(taskString[2], taskString[3]);
                    if (taskString[1].equals("1")) {
                        evTask.markAsDone();
                    }
                    taskData.add(evTask);
                    break;

                default:
                    break;
            }
        }
        return taskData;
    }

    private void writeTasksFile() throws IOException {
        FileWriter fw = new FileWriter(TASKS_FILE);
        for (Task task : this.tasks) {
            fw.write(task.toDataString() + "\n");
        }
        fw.close();
    }

    private void interact() {

        Scanner sc = new Scanner(System.in);

        boolean byebye = false;

        while (!byebye) {

            String[] cmdString = sc.nextLine()
                    .strip()
                    .split(" ", 2);

            System.out.println("\t-------------C-A-T-H-U-L-H-U-------------");

            try {
                byebye = parse(this.tasks, cmdString);
                writeTasksFile();
            } catch (CathulhuException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.err.println(e);
                break;
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
            if (cmdString.length==1) {
                throw new CathulhuException("\tWhich task do you want to mark as done, mortal!");
            }
            int taskIndex = Integer.parseInt(cmdString[1])-1;
            if (tasks.size() <= taskIndex) {
                throw new CathulhuException("\tMortal, thou have no such task!");
            }
            tasks.get(taskIndex).markAsDone();
            System.out.println("\tMortal, thou have completed this task:");
            System.out.println("\t  " + tasks.get(taskIndex));

        } else if (cmdString[0].equalsIgnoreCase("delete")) { //delete
            if (cmdString.length==1) {
                throw new CathulhuException("\tWhich task do you want to delete, mortal!");
            }
            int taskIndex = Integer.parseInt(cmdString[1])-1;
            if (tasks.size() <= taskIndex) {
                throw new CathulhuException("\tMortal, thou have no such task!");
            }
            System.out.printf("\tMortal, the following task has been deleted:\n\t  %s\n\tYou have %d tasks left\n",
                    tasks.remove(taskIndex), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("todo")){ //todo
            if (cmdString.length==1) {
                throw new CathulhuException("\tA \"todo\" task must have a description, mortal!");
            }
            tasks.add(new ToDo(cmdString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.get(tasks.size()-1), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("deadline")){ //deadline
            if (cmdString.length==1) {
                throw new CathulhuException("\tA \"deadline\" task must have a description and a deadline, mortal!");
            }
            String[] byString = cmdString[1].split("/by ", 2);
            if (byString.length==1) {
                throw new CathulhuException("\tA \"deadline\" task must have a deadline, mortal!");
            }
            tasks.add(new Deadline(byString[0], byString[1]));
            System.out.printf("\tTask added: \n\t  %s\n\tYou have %d tasks now, mortal\n",
                    tasks.get(tasks.size()-1), tasks.size());

        } else if (cmdString[0].equalsIgnoreCase("event")){ //event
            if (cmdString.length==1) {
                throw new CathulhuException("\tAn \"event\" task must have a description and a time, mortal!");
            }
            String[] atString = cmdString[1].split("/at ", 2);
            if (atString.length==1) {
                throw new CathulhuException("\tAn \"event\" task must have a time, mortal!");
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
