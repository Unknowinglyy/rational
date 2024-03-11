package numbers;

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



        //TODO: test for different input values like strings, bools, etc.
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

        //TODO: test for different input values like strings, bools, etc.
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

        //TODO: test for different input values like strings, bools, etc.
    }

    public void testcopyConstructor(){
        Rational original = new Rational(2,3);
        Rational value = new Rational(original);
        assertThat("the numerator should be 2", value.numerator(), is(2));
        assertThat("the denominator should be 3", value.denominator(), is(3));

        // original.change(1,2);
        // assertThat("the numerator should be 1", original.numerator(), is(1));
        // assertThat("the denominoatr should be 2", original.denominator(), is(2));

        //TODO: test for different input values like strings, bools, etc.
    }

    public void testOpposite(){
        Rational original = new Rational(2,3);
        Rational value2 = original.opposite();
        assertThat("the numerator should be -2", value2.numerator(), is(-2));
        assertThat("the denominator should be 3", value2.denominator(), is(3));

        //original isnt changed
        assertThat("the numerator should be 2", original.numerator(),is(2));
        assertThat("the denominator should be 3", original.denominator(), is(3));

        //can I go back?
        Rational value3 = value2.opposite();
        assertThat("the numerator should be 2", value3.numerator(),is(2));
        assertThat("the denominator should be 3", value3.denominator(), is(3));

        //TODO: test for different input values like strings, bools, etc.
    }

    public void testReciprocal(){
        Rational value1 = new Rational(2,3);
        Rational reciprocal = value1.reciprocal();
        assertThat("the numerator should be 3", reciprocal.numerator(), is(3));
        assertThat("the denominator should be 2", reciprocal.denominator(), is(2));

        Rational value2 = new Rational(0);
        assertThrows(IllegalArgumentException.class, () -> value2.reciprocal());
    }

    public void testTimes(){
        Rational value1 = new Rational(2,5);
        Rational value2 = new Rational(5,4);
        Rational result = value1.times(value2);
        //regular multiplication with reductions
        assertThat("the numerator should be", result.numerator(), is(1));
        assertThat("the denominator should be", result.denominator(), is(2));

        Rational value3 = new Rational(-1,5);
        Rational value4 = new Rational(-5,4);
        Rational result2 = value3.times(value4);
        //negatives give positives?
        assertThat("the numerator should be", result2.numerator(), is(1));
        assertThat("the denominator should be", result2.denominator(), is(4));

        Rational value5 = new Rational(1,-5);
        Rational value6 = new Rational(5,4);
        Rational result3 = value5.times(value6);
        //negatives end up in right place
        assertThat("the numerator should be", result3.numerator(), is(-1));
        assertThat("the denominator should be", result3.denominator(), is(4));
    }
    
    public void testPlus(){
        Rational value1 = new Rational(2,5);
        Rational value2 = new Rational(-1,6);
        Rational result = value1.plus(value2);

        assertThat("the numerator should be", result.numerator(), is(7));
        assertThat("the denominator should be", result.denominator(), is(30));

        Rational value3 = new Rational(5,1);
        Rational value4 = new Rational();
        Rational result2 = value3.plus(value4);

        assertThat("the numerator should be", result2.numerator(), is(5));
        assertThat("the denominator should be", result2.denominator(), is(1));

        Rational value5 = new Rational(1,2);
        Rational value6 = new Rational(5,3);
        Rational result3 = value5.plus(value6);

        assertThat("the numerator should be", result3.numerator(), is(13));
        assertThat("the denominator should be", result3.denominator(), is(6));
    }

    public void testMinus(){
        Rational value1 = new Rational(-5,10);
        Rational value2 = new Rational(10,1);
        Rational result = value1.minus(value2);
        
        assertThat("the numerator should be", result.numerator(), is(-21));
        assertThat("the denominator should be", result.denominator(), is(2));

        Rational value3 = new Rational(1,3);
        Rational value4 = new Rational();
        Rational result2 = value3.minus(value4);

        assertThat("the numerator should be", result2.numerator(), is(1));
        assertThat("the denominator should be", result2.denominator(), is(3));

        Rational value5 = new Rational(5,6);
        Rational value6 = new Rational(1,6);
        Rational result3 = value5.minus(value6);

        assertThat("the numerator should be", result3.numerator(), is(2));
        assertThat("the denominator should be", result3.denominator(), is(3));

        Rational value7 = new Rational();
        Rational value8 = new Rational(8,9);
        Rational result4 = value7.minus(value8);

        assertThat("the numerator should be", result4.numerator(), is(-8));
        assertThat("the denominator should be", result4.denominator(), is(9));
    }

    public void testdividedBy(){
        Rational value1 = new Rational(1,3);
        Rational value2 = new Rational(5,6);
        Rational result = value1.dividedBy(value2);

        assertThat("the numerator should be", result.numerator(), is(2));
        assertThat("the denominator should be", result.denominator(), is(5));

        Rational value3 = new Rational(1,3);
        Rational value4 = new Rational();

        assertThrows(IllegalArgumentException.class, () -> value3.dividedBy(value4));

        Rational value5 = new Rational(10,1);
        Rational value6 = new Rational(5,1);
        Rational result3 = value5.dividedBy(value6);

        assertThat("the numerator should be", result3.numerator(), is(2));
        assertThat("the denominator should be", result3.denominator(), is(1));
    }

    public void testRaisedToThePowerOf(){
        Rational value1 = new Rational(1,3);
        Rational result = value1.raisedToThePowerOf(2);

        assertThat("the numerator should be", result.numerator(), is(1));
        assertThat("the denominator should be", result.denominator(), is(9));

        Rational value2 = new Rational(5,2);
        Rational result2 = value2.raisedToThePowerOf(3);

        assertThat("the numerator should be", result2.numerator(), is(125));
        assertThat("the denominator should be", result2.denominator(), is(8));

        Rational value3 = new Rational(5,2);
        Rational result3 = value3.raisedToThePowerOf(-1);

        assertThat("the numerator should be", result3.numerator(), is(2));
        assertThat("the denominator should be", result3.denominator(), is(5));

        Rational value4 = new Rational();
        assertThrows(IllegalArgumentException.class, () -> value4.raisedToThePowerOf(-1));

        Rational result4 = value4.raisedToThePowerOf(5);
        assertThat("the numerator should be", result4.numerator(), is(0));
        assertThat("the denominator should be", result4.denominator(), is(1));
    }
    
    public void testEquals(){
        Rational value1 = new Rational(3,1);
        boolean result = value1.equals(3);
        assertThat("3 / 1 should be equal to 3", result, is(true));

        Rational value2 = new Rational(1,4);
        boolean result2 = value2.equals(0.25f);
        assertThat("1 / 4 should be equal to 0.25f", result2, is(true));

        Rational value3 = new Rational(1,5);
        boolean result3 = value3.equals(0.2d);
        assertThat("1 / 5 should be equal to 0.2d", result3, is(true));

        Rational value4 = new Rational();
        boolean result4 = value4.equals("test");
        assertThat("0 / 1 should not be equal to the string 'test'", result4, is(false));

        Rational value5 = new Rational(5,2);
        boolean result5 = value5.equals(2);
        assertThat("5 / 2 should not be equal to 2", result5, is(false));

        Rational value6 = new Rational(5,1);
        boolean result6 = value6.equals(4);
        assertThat("5 / 1 should not be equal to 4", result6, is(false));

        Rational value7 = new Rational(1,6);
        boolean result7 = value7.equals(0.16f);
        assertThat("1 / 6 should not be equal to 0.16f", result7, is(false));

        Rational value8 = new Rational(1,7);
        boolean result8 = value8.equals(0.14d);
        assertThat("1 / 7 should not be equal to 0.14d", result8, is(false));

        Rational value9 = new Rational();
        boolean result9 = value9.equals(null);
        assertThat("1 / 0 should not be equal to null", result9, is(false));
    }

    public void testGreaterThanN(){
        Rational value1 = new Rational(1,9);
        boolean result = value1.greaterThan(0.2f);
        assertThat("1 / 9 should not be greater than 0.2f", result, is(false));

        Rational value2 = new Rational(2,5);
        boolean result2 = value2.greaterThan(0.2d);
        assertThat("2 / 5 should be greater than 0.2d", result2, is(true));

        Rational value3 = new Rational(6, 1);
        boolean result3 = value3.greaterThan(8);
        assertThat("6 / 1 should not be greater than 8", result3, is(false));
    }

    public void testGreaterThanR(){
        Rational value1 = new Rational(1,7);
        Rational value2 = new Rational(1,6);
        boolean result = value1.greaterThan(value2);
        assertThat("1 / 7 should not be greater than 1 / 6", result, is(false));

        Rational value3 = new Rational(5,2);
        Rational value4 = new Rational(7,8);
        boolean result2 = value3.greaterThan(value4);
        assertThat("5 / 2 should be greater than 7 / 8", result2, is(true));

        Rational value5 = new Rational(10,-1);
        Rational value6 = new Rational(1,2);
        boolean result3 = value5.greaterThan(value6);
        assertThat("-10 / 1 should not be greater than 1 / 2", result3, is(false));
    }

    public void testLessThanN(){
        Rational value1 = new Rational(7,5);
        boolean result = value1.lessThan(1);
        assertThat("7 / 5 should not be less than 1", result, is(false));

        Rational value2 = new Rational(1,2);
        boolean result2 = value2.lessThan(0.51f);
        assertThat("1 / 2 should be less than 0.51f", result2, is(true));

        Rational value3 = new Rational(1,8);
        boolean result3 = value3.lessThan(0.125d);
        assertThat("1 / 8 should not be less than 0.125d", result3, is(false));
    }

    public void testLessThanR(){
        Rational value1 = new Rational(1,9);
        Rational value2 = new Rational(1,5);
        boolean result = value1.lessThan(value2);
        assertThat("1 / 9 should be less than 1 / 5", result, is(true));

        Rational value3 = new Rational();
        Rational value4 = new Rational(-9, 1);
        boolean result2 = value3.lessThan(value4);
        assertThat("0 / 1 should not be less than -9 / 1", result2, is(false));

        Rational value5 = new Rational(-5, 2);
        Rational value6 = new Rational(-3 , 2);
        boolean result3 = value5.lessThan(value6);
        assertThat("-5 / 2 should be less than -3 / 2", result3, is(true));
    }

    public void testIsZero(){
        Rational value1 = new Rational(1,6);
        boolean result = value1.isZero();
        assertThat("1 / 6 is not zero", result, is(false));

        Rational value2 = new Rational();
        boolean result2 = value2.isZero();
        assertThat("0 / 1 is zero", result2, is(true));

        Rational value3 = new Rational(-0, 1);
        boolean result3 = value3.isZero();
        assertThat("-0 / 1 is zero", result3, is(true));

        Rational value4 = new Rational(0);
        boolean result4 = value4.isZero();
        assertThat("0 / 1 is zero", result4, is(true));
    }

    public void testIsOne(){
        Rational value1 = new Rational(1);
        boolean result = value1.isOne();
        assertThat("1 / 1 is 1", result, is(true));

        Rational value2 = new Rational(1,1);
        boolean result2 = value2.isOne();
        assertThat("1 / 1 is 1", result2, is(true));

        Rational value3 = new Rational(10,10);
        boolean result3 = value3.isOne();
        assertThat("10 / 10 is 1", result3, is(true));

        Rational value4 = new Rational(5,2);
        boolean result4 = value4.isOne();
        assertThat("5 / 2 is not 1", result4, is(false));

        Rational value5 = new Rational();
        boolean result5 = value5.isOne();
        assertThat("0 / 1 is not 1", result5, is(false));
    }

    public void testIsMinusOne(){
        Rational value1 = new Rational();
        boolean result = value1.isMinusOne();
        assertThat("0 / 1 is not -1", result, is(false));

        Rational value2 = new Rational(-10, 10);
        boolean result2 = value2.isMinusOne();
        assertThat("-10 / 10 is -1", result2, is(true));

        Rational value3 = new Rational(-10, -10);
        boolean result3 = value3.isMinusOne();
        assertThat("-10 / -10 is not -1", result3, is(false));

        Rational value4 = new Rational(-1, 1);
        boolean result4 = value4.isMinusOne();
        assertThat("-1 / 1 is -1", result4, is(true));

        Rational value5 = new Rational(-1);
        boolean result5 = value5.isMinusOne();
        assertThat("-1 / 1 is -1", result5, is(true));
    }

    public void testToString(){
        Rational value1 = new Rational(5,2);
        String result = value1.toString();
        assertThat("5 / 2 should be represented as 5/2", result, is("5/2"));

        Rational value2 = new Rational(5,1);
        String result2 = value2.toString();
        assertThat("5 / 1 should be represented as 5", result2, is("5"));

        Rational value3 = new Rational(10,10);
        String result3 = value3.toString();
        assertThat("10 / 10 should be represented as 1", result3, is("1"));

        Rational value4 = new Rational(7,-7);
        String result4 = value4.toString();
        assertThat("-7 / 7 should be represented as -1", result4, is("-1"));
    }

    public void testCompareTo(){
        Rational value1 = new Rational(-7,1);
        int result = value1.compareTo(7);
        assertThat("-7 / 1 is less than 7", result, is(-1));

        Rational value2 = new Rational(7, 7);
        int result2 = value2.compareTo(1);
        assertThat("7 / 7 is equal to 1", result2, is(0));

        Rational value3 = new Rational(5);
        int result3 = value3.compareTo(2);
        assertThat("5 / 1 is greater than 2", result3, is(1));

        Rational value4 = new Rational(1,4);
        int result4 = value4.compareTo(0.25f);
        assertThat("1 / 4 is equal to 0.25f", result4, is(0));

        Rational value5 = new Rational(1,2);
        int result5 = value5.compareTo(null);
        assertThat("1 / 2 has no relation to null", result5, is(-2));
    }
}