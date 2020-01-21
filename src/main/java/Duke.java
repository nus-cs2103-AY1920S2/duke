import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n I am at your service\n");
        String botReplyLine = "----------------------------------------------";


        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()){
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")){
                System.out.println(botReplyLine +"\n Duke: I'll say goodnight now \n" +botReplyLine);
                break;
            }
            else{
                System.out.println(botReplyLine +"\n Duke: "+ userInput + "\n" + botReplyLine);
            }
        }

    }
}

