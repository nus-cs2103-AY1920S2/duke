public class Tokenizer {
    private Tokenizer() {}

    public static String[] tokenize(String message) {
        return message.split(" ");
    }
}