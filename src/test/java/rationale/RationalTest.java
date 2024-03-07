package rationale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class RationalTest
        extends TestCase {

    public RationalTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(RationalTest.class);
    }


    public void testRational() {
        //default constructor test
        Rational value = new Rational();
        assertThat("the numerator should be 0", value.numerator(), is(0));
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }

    public void test1argConstructor() {
        //test for single input constructor
        Rational value = new Rational(2);
        assertThat("the numerator should be 2", value.numerator(), is(2));
        assertThat("the denominator should be 1", value.denominator(), is(1));

        //test for negative single input
        Rational value2 = new Rational(-2);
        assertThat("the numerator should be -2", value2.numerator(), is(-2));
        assertThat("the denominaotr should be 1", value2.denominator(), is(1));

        //test for number zero as single input
        Rational value3 = new Rational(0);
        assertThat("the numerator should be 0", value3.numerator(), is(0));
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }

    public void test2argConsturctor() {
        //test for a and b positive
        Rational value = new Rational(2, 3);
        assertThat("the numerator should be 2", value.numerator(), is(2));
        assertThat("the denominator should be 3", value.denominator(), is(3));

        //test for ONLY a is negative
        Rational value2 = new Rational(-2,3);
        assertThat("the numerator should be -2", value2.numerator(), is(-2));
        assertThat("the denominator should be 3", value2.denominator(), is(3));

        //test for ONLY b is negative
        Rational value3 = new Rational(2,-3);
        assertThat("the numerator should be -2", value3.numerator(), is(-2));
        assertThat("the denominator should be 3", value3.denominator(), is(3));

        //test for both negative
        Rational value4 = new Rational(-2,-3);
        assertThat("the numerator should be 2", value4.numerator(), is(2));
        assertThat("the denominator should be 3", value4.denominator(), is(3));

        //test for zero denominator
        IllegalArgumentException except = assertThrows(IllegalArgumentException.class, () -> {
            new Rational(2, 0);
        });

        //testing reducing a < b (both positive)
        Rational value5 = new Rational(48, 72);
        assertThat("48 / 72 = 2 / 3", value5.numerator(), is(2));
        assertThat("48 / 72 = 2 / 3", value5.denominator(), is(3));

        //testing reducing a < b (only a is negative)
        Rational value6 = new Rational(-48, 72);
        assertThat("-48 / 72 = -2 / 3", value6.numerator(), is(-2));
        assertThat("-48 / 72 = -2 / 3", value6.denominator(), is(3));

        //testing reducing a < b (only b is negative)
        Rational value7 = new Rational(48, -72);
        assertThat("48 / -72 = -2 / 3", value7.numerator(), is(-2));
        assertThat("48 / -72 = -2 / 3", value7.denominator(), is(3));

        //testing reducing a < b (both are negative)
        Rational value8 = new Rational(-48, -72);
        assertThat("-48 / -72 = 2 / 3", value8.numerator(), is(2));
        assertThat("-48 / -72 = 2 / 3", value8.denominator(), is(3));

        //testing reducing a = b (both positive)
        Rational value9 = new Rational(10,10);
        assertThat("10 / 10 = 1 / 1", value9.numerator(), is(1));
        assertThat("10 / 10 = 1 / 1", value9.denominator(), is(1));

        //testing reducing a = b (a is negative)
        Rational value10 = new Rational(-10,10);
        assertThat("-10 / 10 = -1 / 1", value10.numerator(), is(-1));
        assertThat("-10 / 10 = -1 / 1", value10.denominator(), is(1));

        //testing reducing a = b (b is negative)
        Rational value11 = new Rational(10,-10);
        assertThat("10 / -10 = -1 / 1", value11.numerator(), is(-1));
        assertThat("10 / -10 = -1 / 1", value11.denominator(), is(1));

        //testing reducing a = b (both are negative)
        Rational value12 = new Rational(-10, -10);
        assertThat("-10 / -10 = 1 / 1", value12.numerator(), is(1));
        assertThat("-10 / -10 = 1 / 1", value12.denominator(), is(1));

        //testing reducing a > b (both positive)
        Rational value13 = new Rational(10, 2);
        assertThat("10 / 2 = 5 / 1", value13.numerator(), is(5));
        assertThat("10 / 2 = 5 / 1", value13.denominator(), is(1));

        //testing reducing a > b (a is negative)
        Rational value14 = new Rational(-10, 2);
        assertThat("-10 / 2 = -5 / 1", value14.numerator(), is(-5));
        assertThat("-10 / 2 = -5 / 1", value14.denominator(), is(1));
        
        //testing reducing a > b (b is negative)
        Rational value15 = new Rational(10, -2);
        assertThat("10 / -2 = -5 / 1", value15.numerator(), is(-5));
        assertThat("10 / -2 = -5 / 1", value15.denominator(), is(1));

        //testing reducing a > b (both are negative)
        Rational value16 = new Rational(-10, -2);
        assertThat("-10 / -2 = 5 / 1", value16.numerator(), is(5));
        assertThat("-10 / -2 = 5 / 1", value16.denominator(), is(1));
    }
}