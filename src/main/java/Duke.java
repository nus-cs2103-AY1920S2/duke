<<<<<<< HEAD
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
=======
import java.io.*;
>>>>>>> branch-level-7
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
    BYE,
    LIST,
    DONE,
    TODO,
    EVENT,
    DEADLINE,
    DELETE
}

public class Duke {

    public static final String SAVE_FILE = "save.txt";

    public static Task stringToTask(String string) {
        char type = string.charAt(1);
        boolean isDone = string.charAt(4) == '\u2713' ? true: false;
        Task task;
        String desc;
        String date;
        if (type == 'T') {
            desc = string.substring(7);

            task = new Todo(desc);
        } else if(type == 'D') {
            desc = string.substring(7);
            date = desc.split("\\(by: ")[1];
            date = date.substring(0, date.length() - 1); // remove ending bracket
            desc = desc.split("\\(by: ")[0];

            task = new Deadline(desc, date);
        } else {
            desc = string.substring(7);
            date = desc.split("\\(date: ")[1];
            date = date.substring(0, date.length() - 1); // remove ending bracket
            desc = desc.split("\\(date: ")[0];

            task = new Event(desc, date);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    public static List<Task> readList() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line = reader.readLine();
            while(line != null) {
                Task task = stringToTask(line);
                taskList.add(task);

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return taskList;
    }

    public static void writeList(List<Task> toWrite) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(SAVE_FILE));
        for (Task task: toWrite) {
            pw.println(task);
        }
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException{
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

        List<Task> charmanderList = readList();

        boolean isLooping = true;
        while (isLooping) {
            try {
                String input = sc.nextLine();
                String[] commandAndRest = input.split(" ", 2); //splits command and description
                String[] descAndDate; //splits description and date with (/by)
                String desc;
                String date;

                Command comm;

                try {
                    comm = Command.valueOf(commandAndRest[0].toUpperCase());
                } catch (IllegalArgumentException err) {
                    throw new DukeException("Charmander hurt itself in its confusion!");
                }

                switch (comm) {

                    case BYE:
                        isLooping = false;
                        break;
                    case LIST:
                        int listNo = 1;

                        System.out.println("Charmander presents the list to you:");
                        for (Task item : charmanderList) {
                            System.out.println(listNo + " " + item);
                            listNo++;
                        }
                        System.out.println("Charmander hopes you liked it!");
                        break;
                    case DONE:
                        try {       // check if value is given and correct
                            int listValue = Integer.parseInt(commandAndRest[1]) - 1;
                            charmanderList.get(listValue).markAsDone();
                            System.out.println("Charmander crosses out the task.");
                            System.out.println(charmanderList.get(listValue));
                        } catch (IndexOutOfBoundsException | NumberFormatException err) {
                            throw new DukeException("Charmander needs a valid number from the list!");
                        }

                        writeList(charmanderList);
                        break;
                    case DELETE:
                        try {       // check if value is given and correct
                            int listValue = Integer.parseInt(commandAndRest[1]) - 1;
                            System.out.println("Charmander used delete on the task");
                            System.out.println(charmanderList.remove(listValue));
                            System.out.println("it's super effective!");
                            System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");
                        } catch (IndexOutOfBoundsException | NumberFormatException err) {
                            throw new DukeException("Charmander needs a valid number from the list!");
                        }

                        writeList(charmanderList);
                        break;
                    case TODO:
                        try {       // check if desc is given
                            desc = commandAndRest[1];
                        } catch (ArrayIndexOutOfBoundsException err) {
                            throw new DukeException("Charmander needs a todo description to write it down!");
                        }
                        Todo newTodo = new Todo(desc);
                        charmanderList.add(newTodo);
                        System.out.println("Charmander writes a Todo. You peek over and it says:");
                        System.out.println(newTodo);
                        System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");

                        writeList(charmanderList);
                        break;
                    case DEADLINE:
                        if (commandAndRest.length < 2) {       // check if desc is given
                            throw new DukeException("Charmander needs a deadline description to write it down!");
                        }
                        try {       // check if desc and date is given
                            descAndDate = commandAndRest[1].split(" /by ", 2);
                            desc = descAndDate[0];
                            date = descAndDate[1];
                            Deadline newDeadline = new Deadline(desc, LocalDate.parse(date));
                            charmanderList.add(newDeadline);

                            System.out.println("Charmander writes a Deadline. You peek over and it says:");
                            System.out.println(newDeadline);
                            System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");
                        } catch (ArrayIndexOutOfBoundsException err) {
                            throw new DukeException("Charmander needs a description AND a date to write it down!");
                        } catch (DateTimeParseException err) {
                            throw new DukeException("Place date by yyyy-mm-dd format!");
                        }

                        writeList(charmanderList);
                        break;
                    case EVENT:
                        if (commandAndRest.length < 2) {       // check if desc is given
                            throw new DukeException("Charmander needs an event description to write it down!");
                        }
                        try {       // check if desc and date is given
                            descAndDate = commandAndRest[1].split(" /by ", 2);
                            desc = descAndDate[0];
                            date = descAndDate[1];
                            Event newEvent = new Event(desc, LocalDate.parse(date));
                            charmanderList.add(newEvent);

                            System.out.println("Charmander writes a Event. You peek over and it says:");
                            System.out.println(newEvent);
                            System.out.println("Charmander holds out " + charmanderList.size() + " finger(s).");
                        } catch (ArrayIndexOutOfBoundsException err) {
                            throw new DukeException("Charmander needs a description AND a date to write it down!");
                        } catch (DateTimeParseException err) {
                            throw new DukeException("Place date by yyyy-mm-dd format!");
                        }

                        writeList(charmanderList);
                        break;
                    default:
                        throw new DukeException("Charmander hurt itself in its confusion!");
                }

            } catch (DukeException err) {
                System.out.println(err);
            }
        }

        System.out.println("Charmander! Char char!"); //Goodbye
    }
}
