package rationale;

public class Rational {

    private int numerator;
    private int denominator;

    private int gcd(int a, int b) {
      while (b != 0) {
          int temp = b;
          b = a % b;
          a = temp;
      }
      return Math.abs(a);
    }
 
    public Rational(int a, int b) {
       if (b == 0) {
           throw new IllegalArgumentException("Denominator cannot be zero");
       }
       //if both negative, you can simplify by making both positive
       if (a < 0 && b < 0) {
         a = -1 * a;
         b = -1 * b;
      }
       
 
       // Find the greatest common divisor
       int gcd = gcd(Math.abs(a), Math.abs(b));
 
       // negative sign -> numerator
       if (b < 0) {
           gcd = -gcd;
       }
 
       this.numerator = a / gcd;
       this.denominator = b / gcd;
   }
 
    public Rational(int a) {
       this(a, 1);
    }
 
    public Rational(){
       this(0);
    }
 
    public int numerator() {
       return numerator;
    }
 
    public int denominator() {
       return denominator;
    }
 }