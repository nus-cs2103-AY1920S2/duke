import java.io.PrintStream;

public class PrintUtil {
    private static final int indentLevels = 4;
    
    private static String indentString(String s) {
        //https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java
        return s.replaceAll("(?m)^", " ".repeat(indentLevels));
    }
    
    public static void indentedPrintln(String s) {
        System.out.println(indentString(s));
    }
    
    public static void indentedPrintf(String format, Object... args) {
        System.out.print(indentString(String.format(format, args)));
    }
    
    public static void printHeaderLine() {
        indentedPrintln("____________________________________________________________");
    }
}

