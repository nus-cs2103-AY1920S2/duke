import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> log = new ArrayList<String>();
        TaskList tasklist = Storage.load();

        Ui.firstRun();

        boolean hasNotExited = true;
        while (hasNotExited) {
            String thisResult = Ui.getNextCommand(scanner, tasklist);
            log.add(thisResult);

            if (thisResult.equals("exit")) {
                if (log.isEmpty() || log.get(log.size() - 1).equals("save")) {
                    hasNotExited = false;
                } else {
                    hasNotExited = Ui.askBeforeQuitting(scanner, tasklist);
                }
            } else {
                System.out.println(thisResult);
            }
            System.out.println("");
        }

        Ui.exitMessage();

        scanner.close();
    }
}
