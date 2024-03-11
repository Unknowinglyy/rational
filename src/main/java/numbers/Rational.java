package numbers;

public class Rational {

    private int numerator;
    private int denominator;
   //greatest common divisor

   public int numerator() {
      return numerator;
   }

   public int denominator() {
      return denominator;
   }

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
      if(this.numerator != 0){
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

   public Rational times(Rational r){
      int newNumerator = this.numerator * r.numerator;
      int newDenominator = this.denominator * r.denominator;
      //takes care of negatives and simplifying the fraction if needed
      Rational newRational = new Rational(newNumerator, newDenominator);
      return newRational;
   }

   public Rational plus(Rational r){
      int newNumerator = (this.numerator * r.denominator) + (r.numerator * this.denominator);
      int newDenominator = this.denominator * r.denominator;

      Rational newRational = new Rational(newNumerator, newDenominator);
      return newRational;
   }

   public Rational minus(Rational r){
      int newNumerator = (this.numerator * r.denominator) - (r.numerator * this.denominator);
      int newDenominator = this.denominator * r.denominator;

      Rational newRational = new Rational(newNumerator, newDenominator);
      return newRational;
   }

   public Rational dividedBy(Rational r){
      if(r.numerator != 0){
         Rational newFraction = new Rational(r.denominator, r.numerator);
         Rational newRational = this.times(newFraction);
         return newRational;
      }
      else{
         throw new IllegalArgumentException("Can not divide by zero");
      }
   }

   public Rational raisedToThePowerOf(int n){
      if(n < 0 && this.numerator == 0){
         throw new IllegalArgumentException("Can not raise a zero to a negative power");
      }
      else{
         if(n < 0){
            //if negative exponent, make it positive and flip the fraction
            n = -n;
            
            int newNumerator = (int) Math.pow(this.denominator, n);
            int newDenominator = (int) Math.pow(this.numerator, n);

            Rational newRational = new Rational(newNumerator, newDenominator);
            return newRational;
         }
         //if non-negative exponent, do normal operations
         int newNumerator = (int) Math.pow(this.numerator, n);
         int newDenominator = (int) Math.pow(this.denominator, n);
         Rational newRational = new Rational(newNumerator, newDenominator);
         return newRational;
      }
   }

   public 
}