import javax.management.DescriptorKey;

public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    @Override
    String format_tasks(String s) throws DukeException {
        // Needed the space after "to do" due to space formatting.

        String[] abcd = s.split(" ");
        System.out.println("Hello " + abcd[0]);



        if(super.format_tasks(s).length() <5) {
            throw new DukeException("You cannot leave the description empty ");
        } else {
           return super.format_tasks(s);
        }



    }

    @Override
    void setDescription(String s) throws DukeException {
        try {
            super.setDescription(s);
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for the TODO");
        }
    }


    @Override
    public String toString() {
        return " [" + Task_Codes.T + "]" + super.toString();
    }


}
