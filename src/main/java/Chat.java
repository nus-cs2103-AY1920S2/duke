public interface Chat {

        String getName();
        void setName(String name);
        String getGreeting();
        void setGreeting(String greeting);
        void echo(String command);

}
