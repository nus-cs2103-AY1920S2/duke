import java.util.Scanner;

public class Duke {
    private static final String LINE = "\t__________________________________________________________";
    private static void response(String s){
        System.out.println(LINE);
        System.out.println("\t" + s);
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input;
        while (true){
            input = sc.nextLine().trim();
            if (input.equals("bye")){
                response("Goodbye! Hope to hear from you soon :)");
                break;
            } else {
                response(input);
            }
        }


    }
}
