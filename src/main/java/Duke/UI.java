package Duke;

/**
 * Class Duke.UI handles all Duke.UI interaction with user
 * Includes normal replies and exception replies
 */
public class UI {

    /**
     * Returns a reply string according to id
     *
     * @param id the str
     * @return the string
     */
    public static String getReply(String id){
        switch(id){
        case "delete":
            return "Erasing your tracks are you?";
        case "emptyList":
            return "Eto..(Twindle thumbs).. You have nothing inside your list";
        case "bye":
            return "Bye. Have a nice day lol you shit...";
        case "add":
            return "Sighhssss...I am your slave again?";
        case "markedDone":
            return "Okay whatever.. so you have completed this item.. so what?";
        default:
            return getErrorMsg(id);
        }
    }

    /**
     * Returns a error string according to id
     *
     * @param id the str
     * @return the string
     */
    private static String getErrorMsg(String id){
        switch(id){
        case "imaginary":
            return "You want to finish something imaginary??? Please.. are you stupid?";
        case "emptyList":
            return "Eto..(Twindle thumbs).. You have nothing inside your list";
        case "deleteEmpty":
            return "Lol.. how about you delete yourself first!";
        case "doneAlready":
            return "Its done already!";
        case "memParsedError":
            return "Invalid file parsed error! Please delete the memory file..";
        case "doneFieldEmpty":
            return "Done with what? Your life is it?";
        case "wrongCommand":
            return "Hello? You stupid? Wrong command lah please!";
        case "deleteFieldEmpty":
            return "Delete yourself!";
        case "wrongDate":
            return "Please input a proper date/time!";
        case "wrongDateFormat":
            return "Invalid date/time format";
        default:
            return "What you want?";
        }
    }
}
