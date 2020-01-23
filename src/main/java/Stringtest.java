import java.util.Scanner;

public class Stringtest {
    public static void main(String[] args) {
        while(true){
            Scanner sc = new Scanner(System.in);
            String curr = sc.nextLine();

            String[] test = curr.split(" ");
            System.out.println(test.length);
        }
    }
}
