package ar.com.problems.string;

/**
 * Sentence Smash
 * Write a function that takes an array of words and smashes them together into a sentence and returns the sentence.
 * You can ignore any need to sanitize words or add punctuation, but you should add spaces between each word.
 * Be careful, there shouldn't be a space at the beginning or the end of the sentence!
 *
 * Example
 * ['hello', 'world', 'this', 'is', 'great']  =>  'hello world this is great'
 */
public class SmashWords {

    public static String smash(String... words) {
        return String.join(" ", words);
    }


    public static String smashBuilder(String... words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words)
        {
            sb.append(word).append(" ");
        }
        return sb.toString().trim();
    }

}
