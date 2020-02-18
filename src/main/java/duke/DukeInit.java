package duke;

import commands.CommandHub;
import database.Storage;

/**
 * Initialises the bot and holds strings pertaining to bot initialisation
 */
public class DukeInit {
    /*private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";*/
    //got it from some text generator
    private static String logo = "               _ _   _ _   ___   _ \n" +
            "              | | | | | | | . \\ | |\n" +
            "              \\   / | ' | |   / | |\n" +
            "               |_|  `___' |_\\_\\ |_|\n" +
            "                                   \n";
    /**
     * Initalises bot.
     */
    public static void initBot() {

        System.out.println("Hello from\n" + logo);
        System.out.println("I am a very angry bot and I hate you");
        CommandHub.initCreator(); //init commands
        Storage.memoryInit(); //init memory
        System.out.println("Now what you want?");
    }

    /**
     * Returns the init line (welcome)
     *
     * @return A string with the welcome speech
     */
    public static String getInitLine() {
        return "Hello.. I am \n" + logo + "\nI am a very angry bot and I hate you";
    }

    /**
     * Returns the Instructions
     *
     * @return the String instr
     */
    public static String getInstrAdd() {
        String inst = "Here are my list of Commands:\n" +
                     "========Add Tasks=======\n" +
                    "1. todo <yourtasknamehere>\n" +
                   "2. event <youreventnamehere> /at (date or Time)\n" +
                   "3. deadline <youreventnamehere> /by (date or Time)\n\n";
        return inst;
    }

    public static String getInstrOther() {
        String inst = "======= Other commands =======\n" +
                "1. Find <keyword>\n" +
                "2. Delete <tasknumber>\n" +
                "3. Done <tasknumber>\n" +
                "4. List\n" +
                "5. stats";
        return inst;
    }


}
