public class Add extends Command{

    void addToList(int saved,Task ob){
        Duke.list.add(ob);
        Duke.pos_in_list++;
        String k=ob.toString();
        if(saved==0) {
            System.out.println("____________________________________________________________\nGot it. I've added this task:\n  " + k + "\nNow you have " + Duke.pos_in_list + " tasks in the list.\n____________________________________________________________");
        }
        }

}