public class Parser{
    public Command parse(String input){
        String[] inputArr = input.split(" ");

        if (input.equals("bye")){    
            return Command.BYE;
        } else if (inputArr[0].equals("list")){
            return Command.LIST;
        } else if (inputArr[0].equals("done")){
            return Command.DONE;
        } else if (inputArr[0].equals("delete")){
            return Command.DELETE;
        } else if (inputArr[0].equals("todo")){
            return Command.CREATETODO;
        } else if (inputArr[0].equals("event")){
            return Command.CREATEEVENT;
        } else if (inputArr[0].equals("deadline")){
            return Command.CREATEDEADLINE;
        } else {
            return Command.INVALID;
        }
    }
           
}