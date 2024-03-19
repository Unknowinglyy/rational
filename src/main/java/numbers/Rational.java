package numbers;

public class Rational extends Number implements Comparable<Number>{
//TODO: do all the integer overflow checking for the functions that need this check. Function requirements are here: https://reimagined-train-wl296om.pages.github.io/client_complete_specification.html

//Notes about integer overflow when it comes to divison: https://stackoverflow.com/questions/30394086/integer-division-overflows
    private int numerator;
    private int denominator;

   public int numerator() {
      return numerator;
   }

   public int denominator() {
      return denominator;
   }
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
      if(a == 0 && b != 0){
         b = 1;
      }

       if (b == 0) {
           throw new IllegalArgumentException("Denominator cannot be zero");
       }
       //integer overflow: abs(INTEGER MIN) = INTEGER MAX + 1 
       //in other words, the absolute value of INTEGER MIN would result in overflow
       if(a == Integer.MIN_VALUE && b == -1){
         throw new IllegalArgumentException("Overflow will occur");
       }
       if(a == -1 && b == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
       }

       // MAX / MIN
       if(a == Integer.MAX_VALUE && b == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
       }

       // MIN / MIN
       if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
       }
       // 1 / MIN (negative in denom and can not change it, so overflow occurs)
       if(a == 1 && b == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
       }
       // POSITIVE / MIN (could have a negative in the denominator if no common divisor exists between the numerator and MIN)
       //note: Math.abs function does nothing to MIN 
       if(a > 0 && b == Integer.MIN_VALUE){
         int gcd = gcd(a, b);
         if(gcd != 1){
            this.numerator = -a / gcd;
            this.denominator = -(b / gcd);
         }
         else{
            this.numerator = a;
            this.denominator = b;
         }
         return;
       }
       // NEGATIVE / MIN (wont overflow if MIN can be "reduced", otherwise overflow will occur)
       if(a < 0 && b == Integer.MIN_VALUE){
         int gcd = gcd(Math.abs(a), b);
         if (gcd == 1){
            throw new IllegalArgumentException("Overflow will occur");
         }
         else{
            this.numerator = -(a / gcd);
            this.denominator = -(b / gcd);
         }
         return;
       }

       // MIN / NEGATIVE (might need the MIN to be divided somehow, if the gcd is 1, then we cant decrease the min and overflow happens)
       if(a == Integer.MIN_VALUE && b < 0){
         int gcd = gcd(a, Math.abs(b));
         if(gcd == 1){
            throw new IllegalArgumentException("Overflow will occur");
         }
         else{
            this.numerator = -(a / gcd);
            this.denominator = -(b / gcd);
         }
         return;
       }

       //MIN / POSITIVE
       //logic below handles this


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
      //testing overflow
      if(this.numerator == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
      }

      Rational newRational = new Rational(this.numerator * -1, this.denominator);
      return newRational;
    }

    public Rational reciprocal(){
      if(this.numerator != 0){
         //overflow testing happens in constructor
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
      long result1 = this.numerator * r.numerator;
      long result2 = this.denominator * r.denominator;

      //testing if multiplication will overflow
      if(this.numerator != result1 / r.numerator){
         throw new IllegalArgumentException("Overflow will occur");
      }
      if(this.denominator != result2 / r.denominator){
         throw new IllegalArgumentException("Overflow will occur");
      }

      int newNumerator = this.numerator * r.numerator;
      int newDenominator = this.denominator * r.denominator;
      //takes care of negatives and simplifying the fraction if needed
      Rational newRational = new Rational(newNumerator, newDenominator);
      return newRational;
   }

   public Rational plus(Rational r){
      //testing overflow
      long result1 = this.numerator * r.denominator;
      long result2 = r.numerator * this.denominator;
      long result3 = this.denominator * r.denominator;
      int result4 = (this.numerator * r.denominator) + (r.numerator * this.denominator);

      //checking if the multiplication will overflow
      if(this.numerator != result1 / r.denominator){
         throw new IllegalArgumentException("Overflow will occur");
      }

      if(r.numerator != result2 / this.denominator){
         throw new IllegalArgumentException("Overflow will occur");
      }

      if(this.denominator != result3 / r.denominator){
         throw new IllegalArgumentException("Overflow will occur");
      }

      //checking if the adding will overflow
      if((this.numerator * r.denominator) > 0 && (r.numerator * this.denominator) > 0 && result4 <= 0){
         throw new IllegalArgumentException("Overflow will occur");
      }

      if((this.numerator * r.denominator) < 0 && (r.numerator * this.denominator) < 0 && result4 >= 0){
         throw new IllegalArgumentException("Overflow will occur");
      }
      
      int newNumerator = (this.numerator * r.denominator) + (r.numerator * this.denominator);
      int newDenominator = this.denominator * r.denominator;

      Rational newRational = new Rational(newNumerator, newDenominator);
      return newRational;
   }

   public Rational minus(Rational r){
      if(this.numerator == r.numerator && this.denominator == r.denominator){
         return new Rational();
      }
      else if(r.numerator != 0){
         Rational minusOne = new Rational(-1);
         Rational newR = minusOne.times(r);
         return this.plus(newR);
      }
      else{
         return this;
      }
   }

   public Rational dividedBy(Rational r){
      Rational newFraction = r.reciprocal();
      Rational newRational = this.times(newFraction);
      return newRational;
   }

   public Rational raisedToThePowerOf(int n){
      if(n < 0 && this.numerator == 0){
         throw new IllegalArgumentException("Can not raise a zero to a negative power");
      }
      else{
         if(n < 0){
            //if negative exponent, make it positive and flip the fraction
            int newN = -n;
            
            double result1 = Math.pow(this.denominator, newN);
            double result2 = Math.pow(this.numerator, newN);

            int newNumerator = (int) Math.pow(this.denominator, newN);
            int newDenominator = (int) Math.pow(this.numerator, newN);

            //testing for overflow
            if(result1 != newNumerator){
               throw new IllegalArgumentException("Overflow will occur");
            }
            if(result2 != newDenominator){
               throw new IllegalArgumentException("Overflow will occur");
            }

            Rational newRational = new Rational(newNumerator, newDenominator);
            return newRational;
         }
         //if non-negative exponent, do normal operations
         double result1 = Math.pow(this.numerator,n);
         double result2 = Math.pow(this.denominator,n);

         int newNumerator = (int) Math.pow(this.numerator, n);
         int newDenominator = (int) Math.pow(this.denominator, n);

         //testing for overflow
         if(result1 != newNumerator){
            throw new IllegalArgumentException("Overflow will occur");
         }
         if(result2 != newDenominator){
            throw new IllegalArgumentException("Overflow will occur");
         }

         Rational newRational = new Rational(newNumerator, newDenominator);
         return newRational;
      }
   }

   public boolean equals(Object o){
      if(o instanceof Rational){
         return (this.numerator == ((Rational)o).numerator && this.denominator == ((Rational)o).denominator);
      }
      if (o != null && o instanceof Integer){
         //no integer is equal to a rational number that has a non-one denominator
         if(this.denominator != 1){
            return false;
         }
         else if (o.equals(this.numerator)){
            return true;
         }
         else{
            return false;
         }
      }
      else if(o != null && o instanceof Float){
         //Rational number represented as float
         float fRational = (float) this.numerator / this.denominator;
         float diff = Math.abs(fRational - ((Float)o).floatValue());
         float check = (float) Math.pow(2, -20);
         if(diff < check){
            return true;
         }
         else{
            return false;
         }
      }
      else if(o != null && o instanceof Double){
         //Rational number represented as double
         double dRational = (double) this.numerator / this.denominator;
         double diff = Math.abs(dRational - ((Double)o).doubleValue());
         double check = Math.pow(2,-40);
         if(diff < check){
            return true;
         }
         else{
            return false;
         }
      }
      else{
         return false;
      }
   }

   public boolean greaterThan(Number n){
      double dRational = (double) this.numerator / this.denominator;
      if(dRational > n.doubleValue()){
         return true;
      }
      else{
         return false;
      }
   }

   public boolean greaterThan(Rational r){
      //takes use of the previous function
      double comparison = (double) r.numerator / r.denominator;
      return this.greaterThan(comparison);
   }

   public boolean lessThan(Number n){
      double dRational = (double) this.numerator / this.denominator;
      if(dRational < n.doubleValue()){
         return true;
      }
      else{
         return false;
      }
   }

   public boolean lessThan(Rational r){
      double comparison = (double) r.numerator / r.denominator;
      return this.lessThan(comparison);
   }

   public boolean isZero(){
      return this.numerator == 0;
   }

   public boolean isOne(){
      return this.numerator == 1;
   }

   public boolean isMinusOne(){
      return this.numerator == -1;
   }

   @Override
   public String toString(){
      if(this.denominator == 1){
         return String.valueOf(this.numerator);
      }
      else{
         return String.valueOf(this.numerator) + "/" + String.valueOf(this.denominator);
      }
   }
   

   @Override
   public int compareTo(Number o) {
      if(o != null){
         if(this.greaterThan(o)){
            return 1;
         }
         else if(this.lessThan(o)){
            return -1;
         }
         else{
            return 0;
         }
      }
      else{
         //going to be the error code for this function
         return -2;
      }
   }

   @Override
   public int intValue() {
      if(this.denominator == 1){
         return this.numerator;
      }
      else{
         return this.numerator / this.denominator;
      }
   }

   @Override
   public long longValue() {
      return (long) this.numerator / this.denominator;
   }

   @Override
   public float floatValue() {
      return (float) this.numerator / this.denominator;
   }

   @Override
   public double doubleValue() {
      return (double) this.numerator / this.denominator;
   }
}