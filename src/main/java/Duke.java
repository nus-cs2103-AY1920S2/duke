import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        List<String> usrInputs = new LinkedList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while(true) {
            String command = sc.next();
            boolean wantToQuit = false;
            boolean wantToList = false;
            switch (command) {
                case "bye":
                    wantToQuit = true;
                    System.out.println("Goodbye!! See you some time soon.");
                    break;
                case "list":
                    wantToList = true;
                    int count = 1;
                    for (String usrInput : usrInputs) {
                        System.out.println(count + ". " + usrInput);
                        count++;
                    }
                    break;
            }

            if(wantToQuit) {
                break;
            } else if(wantToList) {
                continue;
            }

            usrInputs.add(command);
            System.out.println("added: " + command);
        }




    }
}
