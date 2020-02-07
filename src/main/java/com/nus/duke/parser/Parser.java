package com.nus.duke.parser;

import javafx.util.Pair;

public class Parser {
    public static Pair<String, String> tokenize(String input) {
        /**
         Splits a string into a (command, condition) pair

         @param input
                input text string, such as "list books"

         @return Pair of (command, condition)
         */
        String[] res        = input.split(" ", 2);
        String command      = res[0];
        String condition    = res.length > 1 ? res[1] : null;
        return new Pair<>(command, condition);
    }
}
