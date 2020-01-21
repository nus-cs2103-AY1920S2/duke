import java.util.Scanner;

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
        int counter = 0;
        String[] tasks = new String[101];
        while(scanner.hasNextLine()){
            String word = scanner.nextLine();
            if(word.equals("list")){
                System.out.print(line);
                for(int i = 1; i < counter + 1; i++){
                    System.out.println(fiveSpaces + Integer.toString(i) + ". " + tasks[i]);
                }
                System.out.println(line);
            }
            else if(word.equals("bye")){
                System.out.println(line + fiveSpaces + "See ya later alligator!\n"+ line);
                break;
            }
            else{
                String add = fiveSpaces + "added: ";
                counter++;
                tasks[counter] = word;
                System.out.println(line + add + word + "\n" + line);
            }
        }
    }
}


