import java.util.*;


public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String mesInput = "";
        String [] arr = new String[100];
        Task [] tasks = new Task[100];
        int index = 0; // to store inside array

        while(!mesInput.equalsIgnoreCase("bye")){
            mesInput = sc.nextLine();

            // scenario where you add task ......
            if(!mesInput.equalsIgnoreCase("bye") && !mesInput.equalsIgnoreCase("list") && !mesInput.contains("done")){
                System.out.println("  ---------");
                System.out.println("   added: "  +mesInput);
                System.out.println("  ---------");
                arr[index] = mesInput ;
                tasks[index] = new Task(arr[index]);
                index = index + 1;
            }

            if (mesInput.equalsIgnoreCase("list") && arr[0] != ""){
                System.out.println("  --------");
                System.out.println("  Here are the task in your list");
                for(int i = 0 ; i < index ; i ++){
                    System.out.println("    "+ (i+1)+". ["+tasks[i].getStatusIcon()+"] "+arr[i]);
                }
                System.out.println("  --------");
            }

            // done task got error
            if(mesInput.contains("done")){
                String [] temp = mesInput.split(" ");
                int arrPos = Integer.parseInt(temp[1]);
                tasks[arrPos-1].setDone();
                System.out.println("  --------");
                System.out.println("    Nice! I've marked this task as done: ");
                System.out.println("    ["+tasks[arrPos-1].getStatusIcon()+"] "+tasks[arrPos-1].getDescription());
                System.out.println("  ---------");
            }
        }
    }
}
