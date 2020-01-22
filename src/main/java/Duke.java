import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "OwO Hello! Watashi Duke desu! \n" + "What can I do for you senpai? *sweats profusely*";
        System.out.println(welcome);

        Scanner input = new Scanner(System.in);
        while(input.hasNextLine()) {
            String echoInput = input.next();

            if(echoInput.equals("bye")) {

                System.out.println("UwU gone so fast? You're a fat bitch anyway.");
                break;
            } else {

                System.out.println(echoInput);

            }
        }


    }
}
