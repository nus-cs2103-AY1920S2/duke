import javax.management.DescriptorKey;

public class Todo extends Task {

    Todo(String description) {
        super(description);
    }

    @Override
    String format_tasks(String s) throws DukeException {
        // Needed the space after "to do" due to space formatting.

        String[] splited_string = getDescription().split("todo ");

        try{
            if(splited_string[1].length()<1) {
                // Checks if the to-do is empty a not
            }
            return super.format_tasks(s);
        }catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("You cannot leave the description empty");
        } catch(Exception e) {
            throw new DukeException("Please enter the correct format for the TODO");
        }

    }

    @Override
    void setDescription(String s) throws DukeException {
//        try {
//            super.setDescription(s);
//        } catch (Exception e) {
//            throw new DukeException("Please enter the correct format for the TODO");
//        }
       super.setDescription(s);
    }


    @Override
    Task_Codes getTaskCodes() {
        return Task_Codes.T;
    }

    @Override
    public String toString() {
        return " [" + Task_Codes.T + "]" + super.toString();
    }


}
