import java.util.Scanner;
public class Duke {
    public static Task list[]=new Task[100];
    static int pos_in_list=0;
    public static void main(String args[]){
        System.out.println("____________________________________________________________\n Hello! I'm Duke\n What can I do for you?\n____________________________________________________________");
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        while(flag)
        {

            String sentence=sc.nextLine();
            int loc_of_space=sentence.indexOf(" ");
            String first_word="";
            if(loc_of_space==-1){
                first_word=sentence;
            }
            else {
                first_word = sentence.substring(0, loc_of_space);
            }

            switch (first_word) {
                case "done":
                    Done done_object=new Done();
                    done_object.markAsDone(sentence.substring(loc_of_space+1));
                    break;
                case "list":
                    Print print_object=new Print();
                    print_object.printList();
                    break;
                case "bye":
                    System.out.println("____________________________________________________________\n Bye. Hope to see you again soon!\n____________________________________________________________");
                    flag=false;
                    break;
                default:
                    Add add_object=new Add();
                    add_object.addToList(sentence);
            }
        }
    }
}