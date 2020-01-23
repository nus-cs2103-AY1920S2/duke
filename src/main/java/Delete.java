public class Delete extends Command {
    public void deleteFromList(String s){
        int num=Integer.valueOf(s);
        Print ob=new Print();
        Duke.pos_in_list--;
        System.out.println("____________________________________________________________\nNoted. I've removed this task:\n"+ob.printTask(num)+"\nNow you have "+Duke.pos_in_list+" tasks in the list.\n____________________________________________________________");
        Duke.list.remove(num-1);

    }
}