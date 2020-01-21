import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "    ____________________________________________________________" +  "\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     Whatcha wanna do?\n" + line);
        String fourSpace = "     ";
        while(scanner.hasNextLine()){
            String word = scanner.nextLine();
            if(word.equals("bye")){
                System.out.println(line + fourSpace + "See ya later alligator!\n"+ line);
                break;
            }
            else {
                System.out.println(line + fourSpace + word + "\n" + line);
            }
        }
    }

}

