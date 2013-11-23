package net.alxb.festassertdemo;

import java.util.Random;

import static java.lang.String.format;

/**
 * Generates random int number within a given range
 *
 * @Author Alex Borisov
 */
public class RangeRandom {

    public int next(int lower, int upper) {
        validateBounds(lower, upper);

        int difference = upper - lower;
        int delta = new Random().nextInt(difference);
        return lower + delta;
    }

    private void validateBounds(int lower, int upper) {
        if (lower >= upper) {
            throw new IllegalArgumentException(format("Lower should not less than upper. %s >= %s.", lower, upper));
        }
    }
}
