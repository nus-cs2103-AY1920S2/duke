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
        void addRecord(String record);
        void listRecord();

}
