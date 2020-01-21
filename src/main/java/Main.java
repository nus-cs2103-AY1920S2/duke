import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List list = new List();

        System.out.println("Hello Master\nWhat can I do for you today?");

        while(sc.hasNext()){

            String word = sc.nextLine();

            if(word.equals("bye")){
                System.out.println("Have a nice day sir!");
                break;
            }

            else if(word.equals("list")){
                list.printList();
            }

            else if(word.contains("done")){
                String[] split = word.split(" ");
                int num = Integer.parseInt(split[1]);
                Task toComplete = list.getTask(num);
                toComplete.setCompleted(true);
                System.out.println("I shall mark task " + num + " as completed");
                System.out.println(toComplete);
            }

            else {
                Task task = new Task(word);
                list.setListArray(task);
            }

        }

        sc.close();

    }
}
