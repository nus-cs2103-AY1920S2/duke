import java.util.List;

public class Ui {
    private static final String outputBorder = "===";

    public void output(String line) {
        System.out.println("    " + outputBorder);
        System.out.println("    " + line);
        System.out.println("    " + outputBorder);
    }

    public void output(List<String> lines) {
        System.out.println("    " + outputBorder);
        for (String line : lines) {
            System.out.println("    " + line);
        }
        System.out.println("    " + outputBorder);
    }
}