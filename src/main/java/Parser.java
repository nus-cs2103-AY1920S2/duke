import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Task formatter(String sentence, int loc_of_space, String type, int done){
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
        return obj;
    }

    static Command parse(int saved, String sentence, int done) throws IOException, DukeException {
        int loc_of_space=sentence.indexOf(" ");
        String first_word=(loc_of_space==-1)?sentence:sentence.substring(0,loc_of_space);
        switch (first_word) {
            case "delete":
                Delete del_obj=new Delete(sentence.substring(loc_of_space+1));
                return del_obj;

            case "done":
                Done done_object=new Done(sentence.substring(loc_of_space+1));
                return done_object;

            case "list":
                ListCommand list_obj=new ListCommand();
                return list_obj;

            case "bye":
                Exit exit_obj=new Exit();
                return exit_obj;

            case "deadline":
                if(sentence.equals("deadline")){
                    DukeException ex=new DukeException(":( OOPS!!! The description of a deadline cannot be empty."); //change
                    throw ex;
                }
                else {
                    Add add_obj=new Add(formatter( sentence, loc_of_space, "deadline",done),saved);
                    return add_obj;
                }

            case "todo":
                if(sentence.equals("todo")){
                    DukeException ex=new DukeException(":( OOPS!!! The description of a todo cannot be empty.");
                    throw ex;
                }
                else {
                    Todo todo_obj = new Todo(done,sentence.substring(loc_of_space+1));
                    Add add_objec = new Add(todo_obj,saved);
                    return add_objec;
                }

            case "event":
                if(sentence.equals("event")){
                    DukeException ex=new DukeException(":( OOPS!!! The description of an event cannot be empty.");
                    throw ex;
                }
                else {
                    Add add=new Add(formatter(sentence, loc_of_space, "event",done),saved);
                    return add;
                }


            default:
                DukeException ex=new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                throw ex;
        }

    }
}
