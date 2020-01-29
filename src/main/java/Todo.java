import java.util.*;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public static String generateTodoDesc(char[] input) {
        String desc = "";
        for (int i = 5; i < input.length; i++) {
            desc += input[i];
        }
        return desc;
    }

    public static Todo createTodo(String desc){
        return new Todo(desc);
    }

//    public boolean checkToDo(String desc) throws DukeException {
//        if (desc.equals("") || desc.equals(" ")) {
//            System.out.println("OOPS!!! The description of a todo cannot be empty");
//            return false;
//        } else {
//            return true;
//        }
//    }

    @Override
    public String saveToText() {
        String output;
        if(this.isDone) {
            output = "T - 1 - " + this.getDescription();
        } else {
            output = "T - 0 - " + this.getDescription();
        }
        return output;
    }

    @Override
    public String toString(){
        return "[T][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}