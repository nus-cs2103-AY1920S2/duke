package duke.core;

public class Message {
    public static final String INTRO = "I'm not Duke, I'm Cute!\n"
            + "I'm feeling a lil hungry... ooh!\n" + "Hi little fishy, what you up to?\n\n"
            + "Type 'help' if you need any!";

    public static final String EXIT = "Cute lunges forward and snaps you up in a gulp.\n" 
            + "She's grateful you swam by!";

    public static final String HELP = "Here's a list of Cute commands to try:\n";

    public static final String DO_TASK = "Mmm, Cute thinks your hardworkingness smells yummy!\n";

    public static final String ADD_TASK = "Wow, you add tasks faster than I eat fishes! Hmm...\n";

    public static final String DELETE_TASK = "Noted. I've eaten - uhh, I mean, removed this task:\n";

    public static final String FIND_KEYWORD = "Cute has fished up these results for you!\n";

    public static final String UPDATE_DESCRIPTION = "Updating a description? Forgetful fishy, are we?\n";

    public static final String UPDATE_TIME = "Ooh, you updated the time, but is it time for me to eat yet?\n";

    public static final String COMMAND_ERROR = "Sorry, invalid command.\n" 
            + "View a list of Cute commands by typing 'help'.";
        
    public static final String LIST_ERROR = "Sorry, that command isn't Cute. Try just 'list'?";

    public static final String DONE_ERROR = "Sorry, invalid command!\nTry: done <task index>";

    public static final String DESCRIPTION_ERROR = "Oops! The description of a task can't be empty!";

    public static final String INDEX_ERROR = "Sounds like you have the wrong task index, "
            + "I can't really hear you through your water bowl!";

    public static final String TIME_ERROR = "Your date time format is incorrect.\n"
            + "Try: yyyy-mm-dd HHmm";

    public static final String EVENT_FORMAT_ERROR = "Your event format is incorrect.\n"
            + "Try: /at <date and time>";
    
    public static final String DEADLINE_FORMAT_ERROR = "Your event format is incorrect.\n"
            + "Try: /by <date and time>";

    public static final String KEYWORD_ERROR = "Aww, Cute couldn't fish up any results from that keyword.";

    public static final String UPDATE_GENERAL_ERROR = "Wrong command format! Try:\n"
            + "'update description <index> <new description>' or 'update time <index> <new time>";

    public static final String UPDATE_DESC_INDEX_ERROR = "Try: update description <index> <date time>";

    public static final String UPDATE_TIME_INDEX_ERROR = "Try: update time <index> <date time>";

    public static final String UPDATE_TIME_ERROR = "Oops! Wrong command format.\n" 
            + "Try: update time <index> <date time>"; 

    public static final String TODO_TIME_ERROR = "Oops, a todo doesn't have a time! Try again.";
}