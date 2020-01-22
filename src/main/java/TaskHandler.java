import java.util.Scanner;

public class TaskHandler {


    public void handle(Boxer boxer) {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        do {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);

            /*
            At this moment switch block cannot catch invalid cases
            eg "done work" will be treated as done block rather than default (add)
             */
            try {
                switch (inputSplit[0]) {
                    case "todo":
                        boxer.todo(inputSplit[1]);
                        break;
                    case "deadline":
                        boxer.deadline(inputSplit[1]);
                        break;
                    case "event":
                        boxer.event(inputSplit[1]);
                        break;
                    case "done":
                        int index;
                        index = Integer.parseInt(inputSplit[1]);
                        boxer.done(index);
                        break;
                    case "list":
                        boxer.list();
                        break;
                    case "bye":
                        boxer.exit();
                        loop = false;
                        break;
                    default:
                        Boxer.print("Uhh... You're gonna have to say that again, Red.");
                        break;
                }
            } catch(Exception e) {

            }
        } while (loop);
    }
}
