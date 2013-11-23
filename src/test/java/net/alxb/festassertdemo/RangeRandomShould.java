package net.alxb.festassertdemo;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * @Author Alex Borisov
 */
@RunWith(JUnitParamsRunner.class)
public class RangeRandomShould {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private RangeRandom rangeRandom = new RangeRandom();

    @Parameters({"0, 100", "5, 25000", "5000, 5001", "1000, 5000000"})
    @Test
    public void generateNumberInARange(int lower, int upper) {
        int result = rangeRandom.next(lower, upper);
        assertThat(result)
                .isNotNegative()
                .isGreaterThanOrEqualTo(lower)
                .isLessThan(upper);
    }

    @Test
    public void throwIllegalArgumentExceptionIfLowerGreaterThanUpper() {
        try {
            rangeRandom.next(100, 90);
            fail("IllegalArgumentException should be thrown.");
        } catch (IllegalArgumentException ex) {
            assertThat(ex)
                    .hasMessageContaining("100 >= 90")
                    .hasNoCause();
        }
    }

    @Test
    public void throwIllegalArgumentExceptionIfLowerEqualsUpper() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(containsString("42 >= 42"));

        rangeRandom.next(42, 42);
    }
}
