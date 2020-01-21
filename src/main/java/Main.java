import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List list = new List();

        System.out.println("Hello, Luke! I'm Your Father \nWhat can I do for you today?");

        String word = sc.nextLine();
        while(!word.equals("bye")){
            if(word.equals("list")){
                list.printList();
            } else {
                list.setListArray(word);
            }
            word = sc.nextLine();
        }
        System.out.println("Noooo Join the Dark Side, Luke, don't leave me");

        sc.close();

    }
}
