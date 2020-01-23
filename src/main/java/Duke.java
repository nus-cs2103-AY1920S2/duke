import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> list=new ArrayList<Task>(100);
    static int pos_in_list=0;

    public static void main(String args[]){
        System.out.println("____________________________________________________________\n Hello! I'm Duke\n What can I do for you?\n____________________________________________________________");
        Scanner sc=new Scanner(System.in);
        boolean flag=true;

        while(flag)
        {

            String sentence=sc.nextLine();
            int loc_of_space=sentence.indexOf(" ");
            String first_word=(loc_of_space==-1)?sentence:sentence.substring(0,loc_of_space);

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
                case "deadline":
                    if(sentence.equals("deadline")){
                        DukeException ex=new DukeException();
                        ex.emptyCommandException("deadline");
                    }
                    else {
                        formatter(sentence, loc_of_space, "deadline");
                    }
                    break;
                case "todo":
                    if(sentence.equals("todo")){
                        DukeException ex=new DukeException();
                        ex.emptyCommandException("todo");
                    }
                    else {
                        Todo todo_obj = new Todo(sentence.substring(loc_of_space + 1));
                        Add add_objec = new Add();
                        add_objec.addToList(todo_obj);
                    }
                    break;
                case "event":
                    if(sentence.equals("event")){
                        DukeException ex=new DukeException();
                        ex.emptyCommandException("event");
                    }
                    else {
                        formatter(sentence, loc_of_space, "event");
                    }
                    break;

                default:
                    DukeException ex=new DukeException();
                    ex.incorrectCommand();
            }
        }
    }

    public static void formatter(String sentence, int loc_of_space, String type){
        int loc_of_slash=sentence.indexOf("/");
        String description=sentence.substring(loc_of_space+1,loc_of_slash-1);
        String time=sentence.substring(loc_of_slash+4);
        Task obj;
        if(type.equals("deadline")) {
            obj = new Deadline(description, time);
        }
        else{
            obj=new Event(description,time);
        }
        Add add_object=new Add();
        add_object.addToList(obj);
    }
}