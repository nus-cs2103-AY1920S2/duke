import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        File file = new File("/Users/jadetay/duke/data/tasks.txt");

        DukeManager manager = new DukeManager();
        manager.loadTasks();

        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String[] temp = input.split(" ");
                String command = temp[0];
                switch (command) {
                    case ("list") :
                    case ("done") :
                        manager.run(input, command);
                        break;
                    case ("delete") :
                        manager.delete(input);
                        break;
                    case ("todo") :
                    case ("deadline") :
                    case ("event") :
                        manager.runTask(input, temp, command);
                        break;
                    case ("search") :
                        String dt = input.substring(7);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate ld = LocalDate.parse(dt, formatter);
                        manager.taskSearch(ld);
                        break;
                    case ("bye") :
                        manager.run(input, command);
                        return;
                    default:
                        throw new DukeException("others");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        sc.close();

    }
}
