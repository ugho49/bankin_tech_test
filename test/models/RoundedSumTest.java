package models;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RoundedSumTest {

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 999.9998, 1000.00 }, { 1345.6578, 1345.658 }, { 1.1016, 1.102 }
        });
    }

    @Parameter
    public double fInput;

    @Parameter(1)
    public double fExpected;

    @Test
    public void should_round_to_the_upper_thousand() {
        final RoundedSum sum = new RoundedSum(fInput);
        assertEquals(fExpected, sum.getAmount(), 0D);
        System.out.println(fExpected + " expected, actual:" + sum.getAmount());
    }
}
