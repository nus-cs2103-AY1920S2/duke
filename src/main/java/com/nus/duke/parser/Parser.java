package com.nus.duke.parser;

import javafx.util.Pair;

public class Parser {
    public static Pair<String, String> tokenize(String input) {
        /**
         By default splits the string by the first whitespace character
         */
        return tokenize(input, " ");
    }

    public static Pair<String, String> tokenize(String input, String expr) {
        /**
         Splits a string into a (command, condition) pair

         @param input
         input text string, such as "list books"

         @return Pair of (command, condition)
         */
        String[] res        = input.split(expr, 2);
        String command      = res[0];
        String condition    = res.length > 1 ? res[1] : null;
        return new Pair<>(command, condition);
    }
}
