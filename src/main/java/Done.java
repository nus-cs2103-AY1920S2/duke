public class Done extends Command {
    public void markAsDone(String num){
        System.out.println("____________________________________________________________");
        int number=Integer.valueOf(num);
        Task ob= Duke.list.get(number-1);
        ob.setDone();
        System.out.println("Nice! I've marked this task as done:");
        Print print_obj=new Print();
        print_obj.printTask(number);
        System.out.println("____________________________________________________________");
    }
}