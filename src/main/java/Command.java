public enum Command{
    TASK, TODO, DEADLINE, EVENT, DONE, LIST, DELETE, BYE;
    public static Command convert(String cmd){
        if(cmd.equals("bye")){
            return BYE;
        }else if(cmd.equals("list")){
            return LIST;
        }else if(cmd.equals("done")){
            return DONE;
        }else if(cmd.equals("todo")||cmd.equals("T")){
            return TODO;
        }else if(cmd.equals("deadline")||cmd.equals("D")){
            return DEADLINE;
        }else if(cmd.equals("event")||cmd.equals("E")){
            return EVENT;
        }else if(cmd.equals("delete")) {
            return DELETE;
        }else{
            return TASK;
        }
    }
}