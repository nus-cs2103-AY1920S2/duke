import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String line = "    ____________________________________________________________" + "\n";
        String fiveSpaces = "      ";
        System.out.println(line + fiveSpaces + "Hello! I'm Duke\n" + fiveSpaces + "Whatcha wanna do?\n" + line);
        ArrayList<Task> tasks = new ArrayList<>();
        while(scanner.hasNextLine()){
            String word = scanner.nextLine();
            String[] command = word.split(" ");
            if(command[0].equals("list")){
                System.out.print(line);
                for(Task task : tasks){
                    System.out.println(task);
                }
                System.out.println(line);
            }
            else if(command[0].equals("bye")){
                System.out.println(line + fiveSpaces + "See ya later alligator!\n"+ line);
                break;
            }
            else if(command[0].equals("done")){
                int taskNum = Integer.parseInt(command[1]) - 1;
                tasks.get(taskNum).isDone = true;
                System.out.print(line);
                System.out.println("     Nice! I've marked this task as done:");
                tasks.get(taskNum).printDone();
                System.out.println(line);
            }
            else{
                String add = fiveSpaces + "added: ";
                Task task = new Task(word);
                tasks.add(task);
                System.out.println(line + add + word + "\n" + line);
            }
        }
    }
}


