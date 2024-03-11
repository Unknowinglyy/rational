package rationale;

public class Rational {

    private int numerator;
    private int denominator;
   //greatest common divisor
    private int gcd(int a, int b) {
      while (b != 0) {
          int temp = b;
          b = a % b;
          a = temp;
      }
      return Math.abs(a);
    }
    //two input constructor
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
   //single parameter constructor
    public Rational(int a) {
       this(a, 1);
    }
    //default constructor
    public Rational(){
       this(0);
    }
    //copy constuctor
    public Rational(Rational r){
      this.numerator = r.numerator;
      this.denominator = r.denominator;
    }
    
    public Rational opposite(){
      Rational newRational = new Rational(this.numerator * -1, this.denominator);
      return newRational;
    }

    public Rational reciprocal(){
      if(this.denominator != 0){
         Rational newRational = new Rational(this.denominator, this.numerator);
         return newRational;
      }
      else{
         throw new IllegalArgumentException("Can not find the reciprocal of zero");
      }
    }
   //  public Rational change(int x, int y){
   //    this.numerator = x;
   //    this.denominator = y;
   //    return this;
   //  }
 
    public int numerator() {
       return numerator;
    }
 
    public int denominator() {
       return denominator;
    }
 }