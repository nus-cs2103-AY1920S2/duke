import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Duke {
    public static ArrayList<Task> list=new ArrayList<Task>(100);
    static int pos_in_list=0;
    static boolean flag;
    static Save save_obj;

    public static void main(String args[])throws IOException,FileNotFoundException{
        System.out.println("____________________________________________________________\n Hello! I'm Duke\n What can I do for you?\n____________________________________________________________");
        Scanner sc=new Scanner(System.in);
        flag=true;
        save_obj=new Save();
        save_obj.load();

        while(flag)
        {
            String sentence=sc.nextLine();
            sort(0,sentence,0);
        }
    }

    public static void formatter(int saved, String sentence, int loc_of_space, String type, int done){
        int loc_of_slash=sentence.indexOf("/");
        String description=sentence.substring(loc_of_space+1,loc_of_slash-1);
        String time=sentence.substring(loc_of_slash+4);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(time, format);
        Task obj;
        if(type.equals("deadline")) {
            obj = new Deadline(description, dateTime,done);
        }
        else{
            obj=new Event(done,description,dateTime);
        }
        Add add_object=new Add();
        add_object.addToList(saved,obj);
    }

    public static void sort(int saved,String sentence,  int done)throws IOException{
        int loc_of_space=sentence.indexOf(" ");
        String first_word=(loc_of_space==-1)?sentence:sentence.substring(0,loc_of_space);
        switch (first_word) {
            case "delete":
                Delete del_obj=new Delete();
                del_obj.deleteFromList(sentence.substring(loc_of_space+1));
                break;
            case "done":
                Done done_object=new Done();
                done_object.markAsDone(sentence.substring(loc_of_space+1));
                break;
            case "list":
                Print print_object=new Print();
                print_object.printList();
                break;
            case "bye":
                save_obj.save();
                System.out.println("____________________________________________________________\n Bye. Hope to see you again soon!\n____________________________________________________________");
                flag=false;
                break;
            case "deadline":
                if(sentence.equals("deadline")){
                    DukeException ex=new DukeException();
                    ex.emptyCommandException("deadline");
                }
                else {
                    formatter(saved, sentence, loc_of_space, "deadline",done);
                }
                break;
            case "todo":
                if(sentence.equals("todo")){
                    DukeException ex=new DukeException();
                    ex.emptyCommandException("todo");
                }
                else {
                    Todo todo_obj = new Todo(done,sentence.substring(loc_of_space+1));
                    Add add_objec = new Add();
                    add_objec.addToList(saved, todo_obj);
                }
                break;
            case "event":
                if(sentence.equals("event")){
                    DukeException ex=new DukeException();
                    ex.emptyCommandException("event");
                }
                else {
                    formatter(saved, sentence, loc_of_space, "event",done);
                }
                break;

            default:
                DukeException ex=new DukeException();
                ex.incorrectCommand();
        }
        return;
    }
}