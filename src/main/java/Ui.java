import java.util.List;

public class Ui {

    public static String charmander = "              _.--\"\"`-..\n" +
            "            ,'          `.\n" +
            "          ,'          __  `.\n" +
            "         /|          \" __   \\\n" +
            "        , |           / |.   .\n" +
            "        |,'          !_.'|   |\n" +
            "      ,'             '   |   |\n" +
            "     /              |`--'|   |\n" +
            "    |                `---'   |\n" +
            "     .   ,                   |                       ,\".\n" +
            "      ._     '           _'  |                    , ' \\ `\n" +
            "  `.. `.`-...___,...---\"\"    |       __,.        ,`\"   L,|\n" +
            "  |, `- .`._        _,-,.'   .  __.-'-. /        .   ,    \\\n" +
            "-:..     `. `-..--_.,.<       `\"      / `.        `-/ |   .\n" +
            "  `,         \"\"\"\"'     `.              ,'         |   |  ',,\n" +
            "    `.      '            '            /          '    |'. |/\n" +
            "      `.   |              \\       _,-'           |       ''\n" +
            "        `._'               \\   '\"\\                .      |\n" +
            "           |                '     \\                `._  ,'\n" +
            "           |                 '     \\                 .'|\n" +
            "           |                 .      \\                | |\n" +
            "           |                 |       L              ,' |\n" +
            "           `                 |       |             /   '\n" +
            "            \\                |       |           ,'   /\n" +
            "          ,' \\               |  _.._ ,-..___,..-'    ,'\n" +
            "         /     .             .      `!             ,j'\n" +
            "        /       `.          /        .           .'/\n" +
            "       .          `.       /         |        _.'.'\n" +
            "        `.          7`'---'          |------\"'_.'\n" +
            "       _,.`,_     _'                ,''-----\"'\n" +
            "   _,-_    '       `.     .'      ,\\\n" +
            "   -\" /`.         _,'     | _  _  _.|\n" +
            "    \"\"--'---\"\"\"\"\"'        `' '! |! /\n" +
            "                            `\" \" -'";


    public static void showStart() {
        System.out.println(charmander);
        System.out.println("Char! Charmander!");
    }
    public static void showExit() {
        System.out.println(charmander);
        System.out.println("Charmander! Char char!");
    }

    public static void showList(TaskList tasks) {
        int listNo = 1;

        System.out.println("Charmander presents the list to you:");
        System.out.println(tasks);
        System.out.println("Charmander hopes you liked it!");
    }

    public static void showDone(Task task) {
        System.out.println("Charmander ticks out the task.");
        System.out.println(task);
    }

    public static void showDelete(Task task) {
        System.out.println("Charmander used delete on the task");
        System.out.println(task);
        System.out.println("it's super effective!");
    }

    public static void showAdd(Task task) {
        System.out.println("Charmander writes a task. You peek over and it says:");
        System.out.println(task);
    }

    public static void showError(DukeException e) {
        System.out.println("Charmander encountered an error:");
        System.out.println(e);
    }
}
