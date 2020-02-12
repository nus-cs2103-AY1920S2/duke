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
    public String echo(TaskList tasks, Storage storage, String command) {
        StringBuilder sb = new StringBuilder();
//        sb.append("~Hello I am Eevee.~\n" + "~What can I do for you?~\n");
//        sb.append("                                      |\n"
//                + "                                     /|\n"
//                + "                                   ,' |\n"
//                + "                                  .   |\n"
//                + "                                    | |\n"
//                + "                                 ' '| |\n"
//                + "                                / / | |\n"
//                + "       _,.-\"\"--._              / /  | |\n"
//                + "     ,'          `.           j '   ' '\n"
//                + "   ,'              `.         ||   / ,                         ___..--,\n"
//                + "  /                  \\        ' `.'`.-.,-\".  .       _..---\"\"'' __, ,'\n"
//                + " /                    \\        \\` .\"`      `\"'\\   ,'\"_..--''\"\"\"'.'.'\n"
//                + ".                      .      .'-'             \\,' ,'         ,','\n"
//                + "|                      |      ,`               ' .`         .' /\n"
//                + "|                      |     /          ,\"`.  ' `-. _____.-' .'\n"
//                + "'                      |..---.|,\".      | | .  .-'\"\"   __.,-'\n"
//                + " .                   ,'       ||,|      |.' |    |\"\"`'\"\n"
//                + "  `-._   `._.._____  |        || |      `._,'    |\n"
//                + "      `.   .       `\".     ,'\"| \"  `'           ,+.\n"
//                + "        \\  '         |    '   |   .....        .'  `.\n"
//                + "         .'          '     7  \".              ,'     \\\n"
//                + "                   ,'      |    `..        _,'      F\n"
//                + "                  .        |,      `'----''         |\n"
//                + "                  |      ,\"j  /                   | '\n"
//                + "                  `     |  | .                 | `,'\n"
//                + "                   .    |  `.|                 |/\n"
//                + "                    `-..'   ,'                .'\n"
//                + "                            | \\             ,''\n"
//                + "                            |  `,'.      _,' /\n"
//                + "                            |    | ^.  .'   /\n"
//                + "                             `-'.' j` V    /\n"
//                + "                                   |      /\n"
//                + "                                   |     /\n"
//                + "                                   |   ,'\n"
//                + "                                    `\"\"");

        Lister lister = new Lister(tasks, storage);
        if (command.equals("bye")) {
             sb.append("Bye! Hope to see you again soon!");
             return sb.toString();
        } else {
            sb.append(lister.record(command));
            return sb.toString();
        }

    }

    public void showLoadingError() {
        System.out.println("The text file is empty...");
    }

}
