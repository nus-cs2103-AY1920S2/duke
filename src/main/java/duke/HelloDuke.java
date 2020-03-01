package duke;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class HelloDuke {
    private static  String  WELCOME_LOGO = " Hello! This is Duke.";

    private static  String EXIT_LOGO = "Bye! See you again. ";

    private  static String ERROR_INFORM = "It seems sth is going wrong... ";

    public static void Start(){
        System.out.println(WELCOME_LOGO);
    }

    public static void  Exit(){
        System.out.println(EXIT_LOGO);
    }

    public static void HandleError(){
        System.out.println((ERROR_INFORM));
    }

    public static void Print_Out(String s){
        String output_string = " ";
        System.out.println(output_string);
    }

    public  static void Print_List(List<String> strs){
        AtomicInteger ctr = new AtomicInteger();
        StringBuilder strBldr = new StringBuilder();

        System.out.println(strBldr.toString());
    }


}
