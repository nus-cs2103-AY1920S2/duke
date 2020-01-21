import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static void print(String s) {
        List<String> temp = new ArrayList<>();
        temp.add(s);
        print(temp);
    }

    private static void print(List<String> stringList) {
        final String HORIZONTAL_LINE = "------------------------------------------------------------";
        final String OUTPUT_INDENTATION = "    ";
        final String FORMAT_STRING = OUTPUT_INDENTATION +  "|%-" + HORIZONTAL_LINE.length() + "s|";

        System.out.println(String.format(FORMAT_STRING, HORIZONTAL_LINE));
        for (String s : stringList) {
            System.out.println(String.format(FORMAT_STRING, s));
        }
        System.out.println(String.format(FORMAT_STRING, HORIZONTAL_LINE));
    }

    public static void main(String[] args) {
        final String END_COMMAND = "bye";

        Scanner sc = new Scanner(System.in);
        String input;
        List<String> outputStreamBuffer;

        // greeting message
        outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Hello! I'm Alfred!");
        outputStreamBuffer.add("How may I help you today?");
        print(outputStreamBuffer);

        while (true) {
            input = sc.nextLine();
            if (input.equals(END_COMMAND)) {
                break;
            }
            print(input);
        }

        print("Bye. Hope to see you again soon!");
    }
}
