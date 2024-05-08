package ar.com.problems.numbers;

import java.util.stream.IntStream;

/**
 * Objective
 * In this challenge, we're going to use loops to help us do some simple math.
 *
 * Task
 * Given an integer, , print its first  multiples. Each multiple  (where ) should be printed on a new line in the form: N x i = result.
 *
 * Input Format
 *
 * A single integer, .
 *
 * Constraints
 *
 * Output Format
 *
 * Print  lines of output; each line  (where ) contains the  of  in the form:
 * N x i = result.
 *
 * Sample Input
 *
 * 2
 * Sample Output
 *
 * 2 x 1 = 2
 * 2 x 2 = 4
 * 2 x 3 = 6
 * 2 x 4 = 8
 * 2 x 5 = 10
 * 2 x 6 = 12
 * 2 x 7 = 14
 * 2 x 8 = 16
 * 2 x 9 = 18
 * 2 x 10 = 20
 */
public class Multiplier {

    public static void main(String[] args) {
        int multiple = 2;

        IntStream
                .rangeClosed(1, 10)
                .forEach(
                        i -> System.out.println(multiple + " x " + i + " = " + (multiple * i)));

    }

}
