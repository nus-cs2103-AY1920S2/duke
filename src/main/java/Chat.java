public interface Chat {

        String getName();
        void setName(String name);
        String getGreeting();
        void setGreeting(String greeting);
        void greet();
        String getGoodbye();
        void setGoodbye(String goodbye);
        void goodbye();
        void echo(String command);
        void addToDo(String record);
        void addDeadline(String record, String by);
        void addEvent(String record, String at);
        void listRecord();
        void setDone(int num);
        void delete(int num);

}