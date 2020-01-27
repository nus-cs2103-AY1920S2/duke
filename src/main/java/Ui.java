import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui{
    private static final String space = "    ";
    private static final String line = "   " + "<------------------------------------------------------------>";
    private static final String errorLine = "   " + "**************************************************************";
    private final Scanner s = new Scanner(System.in);

    public String getCommand(){
        return s.nextLine();
    }

    public void showDukeError(DukeException e){
        System.err.println(e);
    }

    public void showDateTimeError(DateTimeParseException e) {
        System.err.println(errorLine + "\n    ☹ DATE FORMAT is yyyy/mm/dd!\n" + space
            + "  TIME FORMAT is HHmm!\n" + errorLine);
    }

    public void showIOError(IOException e){
        System.err.println(errorLine + "\n    ☹ You have an IO Error!\n" + errorLine);
    }

    public void reply(String reply){
        System.out.println(line);
        System.out.println(space + reply);
        System.out.println(line);
    }

    public void goodBye() {
        System.out.println(line);
        System.out.println(space + "Yes. FINALLY. Hope never to see you again!");
        System.out.println(line);
    }

    public void sayHi() {
        String logo = "\n\n                    ¶¶¶¶¶¶¶¶¶¶¶ \n" 
            + "               ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶ \n"
            + "            ¶¶¶¶¶¶¶¶1111111111¶¶¶¶¶¶¶¶ \n" 
            + "          ¶¶¶¶¶11111111111111111111¶¶¶¶¶¶ \n"
            + "        ¶¶¶¶¶1111111111111111111111111¶¶¶¶¶ \n" 
            + "       ¶¶¶¶11111111111111111111111111111¶¶¶¶ \n"
            + "     ¶¶¶¶1111¶¶¶1111111111111111111¶¶¶111¶¶¶¶ \n"
            + "    ¶¶¶¶11111¶¶¶1111111111111111111¶¶¶11111¶¶¶ \n"
            + "   ¶¶¶1111111¶¶¶1111111111111111111¶¶¶111111¶¶¶ \n"
            + "  ¶¶¶¶1111¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶1111¶¶¶ \n"
            + "  ¶¶¶111¶¶¶¶¶¶¶¶1111111111111111111¶¶¶¶¶¶¶¶111¶¶¶ \n"
            + " ¶¶¶111¶¶¶¶   ¶¶¶11111111111111111¶¶¶   ¶¶¶¶11¶¶¶ \n"
            + " ¶¶¶11¶¶¶    ¶¶¶¶¶1111111111111111¶¶¶¶    ¶¶¶11¶¶ \n"
            + " ¶¶11¶¶¶     ¶¶¶¶¶¶11111111111111¶¶¶¶¶     ¶¶11¶¶¶ \n"
            + "¶¶¶11¶¶¶     ¶¶¶¶¶¶¶¶11111111111¶¶¶¶¶¶     ¶¶¶1¶¶¶ \n"
            + "¶¶¶11¶¶¶     ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶     ¶¶111¶¶ \n"
            + "¶¶¶111¶¶¶    ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶    ¶¶¶111¶¶ \n"
            + "¶¶¶111¶¶¶¶   ¶¶¶¶¶¶¶¶¶¶11111¶¶¶¶¶¶¶¶¶¶   ¶¶¶111¶¶¶ \n"
            + "¶¶¶1111¶¶¶¶   ¶¶¶¶¶¶¶¶111111¶¶¶¶¶¶¶¶¶   ¶¶¶¶111¶¶¶ \n"
            + " ¶¶111111¶¶¶¶¶¶¶¶¶¶¶¶111111111¶¶¶¶¶¶¶¶¶¶¶¶11111¶¶¶ \n"
            + " ¶¶¶1111111¶¶¶¶¶¶¶¶1111111111111¶¶¶¶¶¶¶¶1111111¶¶ \n"
            + " ¶¶¶111111111111111111111111111111111111111111¶¶¶ \n"
            + "  ¶¶¶1111111111111111¶¶¶¶¶¶¶¶¶111111111111111¶¶¶ \n"
            + "   ¶¶¶11111111111111¶¶¶¶¶¶¶¶¶¶¶11111111111111¶¶¶ \n"
            + "    ¶¶¶11111111111¶¶¶¶1111111¶¶¶¶11111111111¶¶¶ \n"
            + "    ¶¶¶¶1111111111¶¶¶111111111¶¶¶111111111¶¶¶¶ \n"
            + "      ¶¶¶¶1111111111111111111111111111111¶¶¶¶ \n" 
            + "       ¶¶¶¶1111111111111111111111111111¶¶¶¶ \n"
            + "         ¶¶¶¶¶11111111111111111111111¶¶¶¶¶ \n" 
            + "           ¶¶¶¶¶¶11111111111111111¶¶¶¶¶¶ \n"
            + "             ¶¶¶¶¶¶¶¶¶¶1111¶¶¶¶¶¶¶¶¶¶ \n" 
            + "                 ¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶\n\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println(space + "Arghhhh... It's you again.");
        System.out.println(line);
    }
}  