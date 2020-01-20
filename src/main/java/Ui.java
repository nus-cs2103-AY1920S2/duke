import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui(){
        sc=new Scanner(System.in);
    }

    public String getInput(){
        return sc.nextLine();
    }

    public void preLog(){
        display("Hello! I'm Duke",
                "What can I do for you?");
    }

    public void endLog(){
        display("Bye.");
    }

    public void display(String... msg){
        System.out.println("\t________________________________________________________________________________________________________________________");
        for(String str:msg) {
            System.out.println("\t" + str);
        }
        System.out.println("\t________________________________________________________________________________________________________________________");
    }

}
