import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------");
        System.out.println("Hello! I am Duke");
        System.out.println("What can i do for you");
        System.out.println("------------");
        String mesInput = "";
        String [] arr = new String[100];
        int index = 0;
        while(!mesInput.equalsIgnoreCase("bye")){
            mesInput = sc.nextLine();

            if(!mesInput.equalsIgnoreCase("bye") && !mesInput.equalsIgnoreCase("list")){
                System.out.println("  ---------");
                System.out.println("   added: "  +mesInput);
                System.out.println("   ---------");
                arr[index] = mesInput ;
                index = index + 1;
            }

            if (mesInput.equalsIgnoreCase("list") && arr[0] != ""){
                System.out.println("  --------");
                for(int i = 0 ; i < index ; i ++){
                    System.out.println("   "+ (i+1)+ ". "+ arr[i]);
                }
                System.out.println("  --------");
            }
        }
        System.out.println("  ---------");
        System.out.println("     Bye. Hope to see you again");
        System.out.println("  ---------");
    }
}
