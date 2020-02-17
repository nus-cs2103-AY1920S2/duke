package Duke;

import commands.CommandHub;
import database.Storage;

/**
 * Initialises the bot
 */
public class DukeInit {

    /**
     * Initalises bot.
     */
    public static void initBot(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am a very angry bot and I hate you");
        CommandHub.initCreator(); //init commands
        Storage.memory_Init(); //init memory
        System.out.println("Now what you want?");
    }
}
