import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String charmander = "              _.--\"\"`-..\n" +
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

        System.out.println(charmander);
        System.out.println("Char! Charmander!");

        List<Task> charmanderList = new ArrayList<>();

        while (true) {
            String command = sc.nextLine();
            String[] words = command.split(" ", 2);
            String[] dates; //splits between task and date with (/by)

            if (command.equals("bye")) break;

            String task = "";
            switch (words[0]) {

                case "list":
                    int listNo = 1;

                    System.out.println("Charmander presents the list to you:");
                    for (Task item : charmanderList) {
                        System.out.println(listNo + " " + item);
                        listNo++;
                    }
                    System.out.println("Charmander hopes you liked it!");
                    break;
                case "done":
                    int listValue = Integer.parseInt(words[1]) - 1;
                    charmanderList.get(listValue).markAsDone();
                    System.out.println("Charmander crosses out the task.");
                    System.out.println(charmanderList.get(listValue));
                    break;
                case "todo":
                    task = words[1];
                    Todo newTodo = new Todo(task);
                    charmanderList.add(newTodo);
                    System.out.println("Charmander writes a Todo. You peek over and it says:");
                    System.out.println(newTodo);
                    System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");
                    break;
                case "deadline":
                    dates = words[1].split(" /by ", 2);
                    Deadline newDeadline = new Deadline(dates[0], dates[1]);
                    charmanderList.add(newDeadline);
                    System.out.println("Charmander writes a Deadline. You peek over and it says:");
                    System.out.println(newDeadline);
                    System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");
                    break;
                case "event":
                    dates = words[1].split(" /by ", 2);
                    Event newEvent = new Event(dates[0], dates[1]);
                    charmanderList.add(newEvent);
                    System.out.println("Charmander writes a Event. You peek over and it says:");
                    System.out.println(newEvent);
                    System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");
            }
        }

        System.out.println("Charmander! Char char!"); //Goodbye
    }
}
