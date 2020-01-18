import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        List<Task> usrInputs = new ArrayList<>();

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        while(true) {
            String command = sc.next();
            boolean wantToQuit = false;
            boolean nothingToAdd = false;
            switch (command) {
                case "bye":
                    wantToQuit = true;
                    System.out.println("Goodbye!! See you some time soon.");
                    break;

                case "list":
                    nothingToAdd = true;
                    int count = 1;
                    for (Task usrTask : usrInputs) {
                        System.out.println(count + ". " + usrTask);
                        count++;
                    }
                    break;
                    
                case "done":
                    int index = sc.nextInt();
                    Task completedTask = usrInputs.get(index - 1);
                    completedTask.setDone();
                    System.out.println("Good job, mate. I have marked the following task as done.\n" + completedTask);
                    nothingToAdd = true;
                    break;

            }

            if(wantToQuit) {
                break;
            } else if(nothingToAdd) {
                continue;
            }

            usrInputs.add(new Task(command));
            System.out.println("added: " + command);
        }




    }
}
