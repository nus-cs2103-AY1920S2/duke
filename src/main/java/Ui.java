import java.util.ArrayList;

public class Ui {
    public Ui(){
    }

    public void topTwoLine (){
        System.out.println("-----------------");
        System.out.println("   Got it. I've added this task:");
    }

    public void bottomTwoLine (ArrayList<Task> tList){
        System.out.println("   Now you have "+ tList.size() +" tasks in the list.");
        System.out.println("-----------------  ");
    }

    public void exitsMessage (){
        System.out.println("  --------------");
        System.out.println("     Bye. Hope to see you again");
        System.out.println("  --------------");
    }

    public void markDone(){
        System.out.println("  -------------");
        System.out.println("    Nice! I've marked this task as done: ");
    }

    public void removeMes(){
        System.out.println("-----------------");
        System.out.println("   Got it. I've removed this task:");
    }

}
