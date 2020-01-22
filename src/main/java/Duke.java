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
        System.out.println("Konnichiwa! Watashi no namaeha Duke desu! Whatchu can i duwu for yuwu? (^.___.^)");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> strArr = new ArrayList<>();
        ArrayList<String> tickArr = new ArrayList<>();
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")){
                System.out.println("Sayonara~ （＾・ω・＾✿）");
                break;
            } else if (input.equals("list")) {
                System.out.println("Hewe awu de tasku in ywour listu! uwu \n");
                for(int i=0; i< strArr.size(); i++) {
                    String text = strArr.get(i);
                    System.out.println(i + 1 + ". " + tickArr.get(i) +" "+ text);
                }
            } else {
                String[] splited = input.split("\\s+");
                if (splited[0].equals("done")) {
                    System.out.println("Nicesu! watashi hasu marku the tasku as done desu! uwu" + "\n");
                    int index = Integer.parseInt(splited[1]) -1;
                    tickArr.set(index, "[✓] ");
                    System.out.println(tickArr.get(index) + strArr.get(index));
                } else {
                    strArr.add(input);
                    tickArr.add("[✗] ");
                    System.out.println("(=´∇｀=) added:" + input +"!");
                }
            }
        }
    }
}
