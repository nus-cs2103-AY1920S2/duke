package duke;

import java.util.Scanner;

/**
 * UI Class.
 * @author qiujingying
 * @version 1.0
 */
public class Eevee {

    /**
     * Initialises the scanner and Duke.
     * @param tasks task list
     * @param storage storage
     */
    public void echo(TaskList tasks, Storage storage) {
        Scanner sc = new Scanner(System.in);
        System.out.println("~Hello I am Eevee.~\n" + "~What can I do for you?~");
        System.out.println("                                      |\n"
                + "                                     /|\n"
                + "                                   ,' |\n"
                + "                                  .   |\n"
                + "                                    | |\n"
                + "                                 ' '| |\n"
                + "                                / / | |\n"
                + "       _,.-\"\"--._              / /  | |\n"
                + "     ,'          `.           j '   ' '\n"
                + "   ,'              `.         ||   / ,                         ___..--,\n"
                + "  /                  \\        ' `.'`.-.,-\".  .       _..---\"\"'' __, ,'\n"
                + " /                    \\        \\` .\"`      `\"'\\   ,'\"_..--''\"\"\"'.'.'\n"
                + ".                      .      .'-'             \\,' ,'         ,','\n"
                + "|                      |      ,`               ' .`         .' /\n"
                + "|                      |     /          ,\"`.  ' `-. _____.-' .'\n"
                + "'                      |..---.|,\".      | | .  .-'\"\"   __.,-'\n"
                + " .                   ,'       ||,|      |.' |    |\"\"`'\"\n"
                + "  `-._   `._.._____  |        || |      `._,'    |\n"
                + "      `.   .       `\".     ,'\"| \"  `'           ,+.\n"
                + "        \\  '         |    '   |   .....        .'  `.\n"
                + "         .'          '     7  \".              ,'     \\\n"
                + "                   ,'      |    `..        _,'      F\n"
                + "                  .        |,      `'----''         |\n"
                + "                  |      ,\"j  /                   | '\n"
                + "                  `     |  | .                 | `,'\n"
                + "                   .    |  `.|                 |/\n"
                + "                    `-..'   ,'                .'\n"
                + "                            | \\             ,''\n"
                + "                            |  `,'.      _,' /\n"
                + "                            |    | ^.  .'   /\n"
                + "                             `-'.' j` V    /\n"
                + "                                   |      /\n"
                + "                                   |     /\n"
                + "                                   |   ,'\n"
                + "                                    `\"\"");

        Lister lister = new Lister(tasks, storage);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                lister.record(command);
            }
        }
    }

    public void showLoadingError() {
        System.out.println("The text file is empty...");
    }

}
