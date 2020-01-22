import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String welcome = "OwO Hello! I am your neckbeard chatbot! \n" + "What can I do for you senpai? *sweats profusely*";
        System.out.println(welcome);

        ArrayList<String> storeList = new ArrayList<>();

        while(input.hasNextLine()) {
            String addition = input.nextLine();

            if (addition.equals("bye")) {

                System.out.println("UwU gone so fast? You're a fat bitch anyway.");
                break;

            } else if (addition.equals("list")) {

                for (int i = 0; i < storeList.size(); i++) {
                    int indexNumber = i + 1;
                    System.out.println(indexNumber + ". " + storeList.get(i));
                }

            } else {

                storeList.add(addition);
                System.out.println("added: " + addition);

            }
        }


    }
}
