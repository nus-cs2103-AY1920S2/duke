package main;

import java.util.Scanner;
import exception.*;

public class Ui{
    private final Scanner s = new Scanner(System.in);

    public String getInput() {
        return s.nextLine();
    }

    public String getCommand(){
        return s.nextLine();
    }

    public void showDukeError(DukeException e){
        System.err.println(e);
    }

    public void showDateTimeError() {
        System.err.println(Constant.ERROR_LINE + "\n    ☹ DATE FORMAT is yyyy/mm/dd!\n" + Constant.SPACE
            + "  TIME FORMAT is HHmm!\n" + Constant.ERROR_LINE);
    }

    public void showIOError(){
        System.err.println(Constant.ERROR_LINE+ "\n    ☹ You have an IO Error!\n" + Constant.ERROR_LINE);
    }

    public void reply(String reply){
        System.out.println(Constant.LINE);
        System.out.println(Constant.SPACE + reply);
        System.out.println(Constant.LINE);
    }

    public void goodBye() {
        System.out.println(Constant.LINE);
        System.out.println(Constant.SPACE + "Yes. FINALLY. Hope never to see you again!");
        System.out.println(Constant.LINE);
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
        System.out.println(Constant.LINE);
        System.out.println(Constant.SPACE + "Arghhhh... It's you again.");
        System.out.println(Constant.LINE);
    }
}  