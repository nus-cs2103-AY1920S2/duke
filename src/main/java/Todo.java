import java.util.*;
import java.time.LocalDate;

public class Todo extends Task{
    LocalDate date = null;

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

    @Override
    public LocalDate getDate() {
        return this.date;
    }
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