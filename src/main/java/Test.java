import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        PrintStream fileOut = new PrintStream(new File("/Users/joshua/Desktop/Schoolwork/Year 2 Sem 2/CS2103T/Individual_Project_Duke/text-ui-test/ACTUAL.TXT"));
        PrintStream console = System.out;
        System.setOut(fileOut);
        System.out.println("Bye");
        System.setOut(console);
        System.out.println("this will be written on the console!");
        fileOut.close();

    }

}
