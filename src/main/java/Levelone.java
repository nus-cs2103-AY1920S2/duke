public class Levelone {

    public Levelone(){}

    public String reply(String s) {
        String out = null;
        if (s.equals("list")) {
            out = "list";
        } else if (s.equals("bye")) {
            out = "Bye. Hope to see you again soon!";
        } else {
            out = s;
        }
        return out;
    }


}
