package numbers;

import java.math.BigInteger;

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

    private BigInteger gcd(BigInteger a, BigInteger b) {
      while (!b.equals(BigInteger.ZERO)) {
         BigInteger temp = b;
         b = a.mod(b);
         a = temp;
      }
      return a.abs();
   }
    //two input constructor
    public Rational(int a, int b) {
      if (b == 0 || b == Integer.MIN_VALUE) {
         throw new IllegalArgumentException("Invalid denominator");
       }

      if(a == 0){
         this.numerator = a;
         this.denominator = 1;
         return;
      }
       //if both negative, you can simplify by making both positive
       if (a < 0 && b < 0) {
         if(a == Integer.MIN_VALUE){
            throw new IllegalArgumentException("Overflow will occur");
         }
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

      return new Rational(this.numerator * -1, this.denominator);
    }

    public Rational reciprocal(){
      if(this.numerator == 0){
         throw new IllegalArgumentException("Can not find the reciprocal of zero");
      }
      if(this.numerator == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
      }
      return new Rational(this.denominator, this.numerator);
    }
   
   public Rational times(Rational r){ //might have to change

      BigInteger numerator1 = BigInteger.valueOf(this.numerator);
      BigInteger numerator2 = BigInteger.valueOf(r.numerator);
      BigInteger denominator1 = BigInteger.valueOf(this.denominator);
      BigInteger denominator2 = BigInteger.valueOf(r.denominator);

      BigInteger newNum = numerator1.multiply(numerator2);
      BigInteger newDenom = denominator1.multiply(denominator2);
      BigInteger gcd2 = gcd(newNum, newDenom);


      newNum = newNum.divide(gcd2);
      newDenom = newDenom.divide(gcd2);

      BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);
      BigInteger min = BigInteger.valueOf(Integer.MIN_VALUE);

      if(newNum.compareTo(max) > 0 /*|| newDenom.compareTo(max) > 0*/){
         throw new IllegalArgumentException("Overflow will occur");
      }

      if(newNum.compareTo(min) < 0 /*|| newDenom.compareTo(min) < 0*/){
         throw new IllegalArgumentException("Overflow will occur");
      }

      //takes care of negatives and simplifying the fraction if needed
      int newNum2 = newNum.intValue();
      int newDenom2 = newDenom.intValue();
      return new Rational(newNum2, newDenom2);
   }

   public Rational plus(Rational r){
      //testing overflow
      //need to use BigInteger
      int gcd = gcd(this.denominator, r.denominator);
      if(this.denominator / gcd > Integer.MAX_VALUE / r.denominator){
         throw new IllegalArgumentException("Overflow will occur");
      }

      BigInteger numerator1 = BigInteger.valueOf(this.numerator);
      BigInteger numerator2 = BigInteger.valueOf(r.numerator);
      BigInteger denominator1 = BigInteger.valueOf(this.denominator);
      BigInteger denominator2 = BigInteger.valueOf(r.denominator);

      BigInteger x = numerator1.multiply(denominator2);
      BigInteger y = numerator2.multiply(denominator1);
      BigInteger newNum = x.add(y);

      BigInteger newDenom = denominator1.multiply(denominator2);
      BigInteger gcd2 = gcd(newNum, newDenom);
      newNum = newNum.divide(gcd2);
      newDenom = newDenom.divide(gcd2);

      BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);
      BigInteger min = BigInteger.valueOf(Integer.MIN_VALUE);

      //checking if the multiplication will overflow
      if(newNum.compareTo(max) > 0 /*|| newDenom.compareTo(max) > 0*/){
         throw new IllegalArgumentException("Overflow will occur");
      }

      if(newNum.compareTo(min) < 0 /*|| newDenom.compareTo(min) < 0*/){
         throw new IllegalArgumentException("Overflow will occur");
      }
      int newNum2 = newNum.intValue();
      int newDenom2 = newDenom.intValue();

      return new Rational(newNum2, newDenom2);
   }

   public Rational minus(Rational r){ //might have to change
      if(this == r){
         return new Rational();
      }
      return this.plus(r.opposite());
   }

   public Rational dividedBy(Rational r){
      if(r.numerator == 0){
         throw new IllegalArgumentException("Can not divide by zero");
      }
      return this.times(r.reciprocal());
   }

   public Rational raisedToThePowerOf(int n){
      if(n == 0){
         return new Rational(1);
      }
      if(n < 0 && this.numerator == 0){
         throw new IllegalArgumentException("Can not raise a zero to a negative power");
      }
      if(n == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Overflow will occur");
      }
      int x = 1;
      int y = 1;

      try{
         for(int i=0; i < Math.abs(n); i++){
            x = Math.multiplyExact(x, this.numerator);
            y = Math.multiplyExact(y, this.denominator);
         }
      }
      catch(ArithmeticException overflowArithmeticException){
         throw new IllegalArgumentException("Overflow will occur");
      }
      
      int x1 = x;
      int y1 = y;
      if(n < 0){
         return new Rational(y1,x1);
      }
      return new Rational(x1,y1);
   }

   public boolean equals(Object o){
      if(this == o){
         return true;
      }
      if(o instanceof Rational){
         return (this.numerator == ((Rational)o).numerator && this.denominator == ((Rational)o).denominator);
      }
      if (o instanceof Number){
         int x = this.compareTo((Number)o);
         if(x == 0){
            return true;
         }
         else{
            return false;
         }
      }
      return false;
   }

   public boolean greaterThan(Number n){
      if(!(n instanceof Rational)){
         int x = this.compareTo(n);
         if(x == 1){
            return true;
         }
         else{
            return false;
         }
      }
      Rational value = (Rational) n;
      return this.numerator * value.denominator > value.numerator * this.denominator;
   }

   public boolean lessThan(Number n){
      if(!(n instanceof Rational)){
         int x = this.compareTo(n);
         if(x == -1){
            return true;
         }
         else{
            return false;
         }
      }
      Rational value = (Rational) n;
      return this.numerator * value.denominator < value.numerator * this.denominator;
   }

   public boolean isZero(){
      return this.numerator == 0;
   }

   public boolean isOne(){
      return this.numerator == this.denominator;
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
      if(o instanceof Float){
         float value = o.floatValue();
         float difference = Math.abs(this.floatValue() - value);
         if(difference < Math.pow(2, -20)){
            return 0;
         }
         else if(this.floatValue() < value){
            return -1;
         }
         else{
            return 1;
         }
      }
      double value = o.doubleValue();
      double difference = Math.abs(this.doubleValue() - value);
      if(difference < Math.pow(2,-40)){
         return 0;
      }
      else if(this.doubleValue() < value){
         return -1;
      }
      else{
         return 1;
      }
   }

   @Override
   public int intValue() {
      return this.numerator / this.denominator;
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