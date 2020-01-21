import java.util.Scanner;
import java.io.PrintStream;

public class Duke {
    private static final int indentLevels = 4;
    
    private static String indentString(String s) {
        //https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java
        return s.replaceAll("(?m)^", " ".repeat(indentLevels));
    }
    
    private static void indentedPrintln(String s) {
        System.out.println(indentString(s));
    }
    
    private static void indentedPrintf(String format, Object... args) {
        System.out.print(indentString(String.format(format, args)));
    }
    
    private static void printHeaderLine() {
        indentedPrintln("____________________________________________________________");
    }
    
    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        indentedPrintln("Hello from\n" + logo);
    }
    
    private static boolean runCommand(String command) {
        if (command.equals("bye")) {
            indentedPrintln("Goodbye");
            return false;
        } else {
            indentedPrintln(command);
        }
        return true;
    }
    
    public static void main(String[] args) {
        printHeaderLine();
        greet();
        printHeaderLine();
        
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            
            String command = sc.nextLine();
            
            printHeaderLine();
            running = runCommand(command);
            printHeaderLine();
        }
    }
}
