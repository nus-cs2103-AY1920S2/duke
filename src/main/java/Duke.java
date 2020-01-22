import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Konnichiwa! Watashi no namaeha Duke desu! What can I do for you? (^.___.^)");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> strArr = new ArrayList<>();
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println("Sayonara~ （＾・ω・＾✿）");
                break;
            } else if (input.equals("list")) {
                for(int i=0; i< strArr.size(); i++) {
                    String text = strArr.get(i);
                    System.out.println(i+1 +". "+text);
                }
            } else {
               strArr.add(input);
               System.out.println("(=´∇｀=) added:" + input +"!");
            }
        }
    }
}
