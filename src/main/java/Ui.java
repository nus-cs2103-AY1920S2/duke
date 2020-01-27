import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Ui {

    private static final Scanner SC = new Scanner(System.in);
    private static final String HORIZONTAL_LINE = "------------------------------------------------------------";
    private static final String OUTPUT_INDENTATION = "    ";
    private static final String FORMAT_STRING_FOR_H_LINE =
            OUTPUT_INDENTATION +  "|%-" + HORIZONTAL_LINE.length() + "s|";
    private static final String FORMAT_STRING_FOR_CONTENT =
            OUTPUT_INDENTATION +  "|  %-" + (HORIZONTAL_LINE.length() - 2) + "s|";

    void print(String s) {
        List<String> temp = new ArrayList<>();
        temp.add(s);
        print(temp);
    }

    void print(List<String> stringList) {

        System.out.println(String.format(FORMAT_STRING_FOR_H_LINE, HORIZONTAL_LINE));
        for (String s : stringList) {
            System.out.println(String.format(FORMAT_STRING_FOR_CONTENT, s));
        }
        System.out.println(String.format(FORMAT_STRING_FOR_H_LINE, HORIZONTAL_LINE));
    }

    void greet() {
        List<String> outputStreamBuffer = new ArrayList<>();
        outputStreamBuffer.add("Hello! I'm Alfred!");
        outputStreamBuffer.add("How may I help you today?");
        print(outputStreamBuffer);
    }

    void bye() {
        print("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    void printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            print("List is empty");
            return;
        }

        List<String> outputStreamBuffer = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            String str = tasks.get(i).toString();
            String newStr = String.format("%d.%s", i + 1, str);
            outputStreamBuffer.add(newStr);
        }

        print(outputStreamBuffer);
    }

    String readCommand() {
        return SC.nextLine();
    }
}
