package Core;

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
        display(UiMessage.GREETING.getMsg());
    }

    public void endLog(){
        display(UiMessage.FAREWELL.getMsg());
    }

    public void errorLog(String error){
        display(error);
    }

    public void display(String... msg){
        System.out.println("\t________________________________________________________________________________________________________________________");
        for(String str:msg) {
            System.out.println("\t" + str);
        }
        System.out.println("\t________________________________________________________________________________________________________________________");
    }

}
