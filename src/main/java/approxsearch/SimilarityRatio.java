package approxsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import java.util.ArrayList;

class SimilarityRatio {

    // Reference: https://en.wikipedia.org/wiki/Levenshtein_distance#Iterative_with_full_matrix
    private static int levenshteinDist(String a, String b, int i, int j, int[][] memo) {
        if (memo[i][j] != -1) {
            // return from memo straight
        } else if (Math.min(i, j) == 0) {
            memo[i][j] = Math.max(i, j);
        } else {
            memo[i][j] = Math.min(
                    levenshteinDist(a, b, i - 1, j, memo) + 1,
                    Math.min(
                            levenshteinDist(a, b, i, j - 1, memo) + 1,
                            levenshteinDist(a, b, i - 1, j - 1, memo)
                                    + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1)
                    )
            );
        }
        return memo[i][j];
    }

    private static double getSimilarityRatio(String a, String b) {
        int i = a.length();
        int j = b.length();
        int[][] memo = new int[i + 1][j + 1];
        for (int m = 0; m <= i; m++) {
            for (int n = 0; n <= j; n++) {
                memo[m][n] = -1;
            }
        }

        return ((double) (i + j) - levenshteinDist(a.toLowerCase(), b.toLowerCase(), i, j, memo))
                / (i + j);
    }

    private static Stream<String> getPhraseStream(int phraseLength, String sentence) {
        ArrayList<String> phrases = new ArrayList<>();
        String[] words = sentence.split(" ");
        int lastIndex = 0;

        for (int i = 0; i <= words.length - phraseLength; i += phraseLength) {
            String phrase = "";
            for (int j = 0; j < phraseLength; j++) {
                phrase += " " + words[i + j];
                lastIndex = i + j + 1;
            }
            phrases.add(phrase.trim());
        }

        String lastPhrase = "";
        for (int i = lastIndex; i < words.length; i++) {
            lastPhrase += " " + words[i];
        }

        phrases.add(lastPhrase.trim());

        return phrases.stream();
    }

    /**
     * Get highest Levenshtein similarity ratio for a given word compared to a sentence.
     * Reference: https://www.datacamp.com/community/tutorials/fuzzy-string-python
     *
     * @param a        string that may be in sentence.
     * @param sentence sentence to compare to.
     * @return Levenshtein similarity ratio.
     */
    static double getHighestSimilarityRatio(String a, String sentence) {
        return getPhraseStream(a.split(" ").length, sentence)
                .map((word -> getSimilarityRatio(a, word)))
                .reduce(-1.0, (acc, curr) -> Math.max(acc, curr));
    }

    public static void main(String[] args) {
        System.out.println(getHighestSimilarityRatio(
                "Even though the example above is a valid way",
                "event hought the example above s a valid way"));
        System.out.println(getHighestSimilarityRatio(
                "flexibilty",
                "All more flexibility in search"));
        System.out.println(getHighestSimilarityRatio(
                "all",
                "All more flexibility in search"));
    }
}
