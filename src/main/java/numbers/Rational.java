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
    //two input constructor
    public Rational(int a, int b) {
      if(a == 0 && b != 0){
         b = 1;
      }

       if (b == 0) {
         throw new IllegalArgumentException("Denominator cannot be zero");
       }
       if(b == Integer.MIN_VALUE){
         throw new IllegalArgumentException("Denominator cannot be MIN");
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
      if(this.numerator != 0){
         if(this.numerator == Integer.MIN_VALUE){
            throw new IllegalArgumentException("Overflow will occur");
         }
         return new Rational(this.denominator, this.numerator);
      }
      else{
         throw new IllegalArgumentException("Can not find the reciprocal of zero");
      }
    }
   
   public Rational times(Rational r){ //might have to change
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
      return new Rational(newNumerator, newDenominator);
   }

   public Rational plus(Rational r){//might have to change
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

      return new Rational(newNumerator, newDenominator);
   }

   public Rational minus(Rational r){ //might have to change
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

            return new Rational(newNumerator, newDenominator);
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

         return new Rational(newNumerator, newDenominator);
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
      if(n instanceof Float){
         if(this.floatValue() > n.floatValue()){
            return true;
         }
         else{
            return false;
         }
      }
      else{
         if(this.doubleValue() > n.doubleValue()){
            return true;
         }
         else{
            return false;
         }
      }
   }

   public boolean greaterThan(Rational r){
      //takes use of the previous function
      double comparison = (double) r.numerator / r.denominator;
      return this.greaterThan(comparison);
   }

   public boolean lessThan(Number n){
      if(n instanceof Float){
         if(this.floatValue() < n.floatValue()){
            return true;
         }
         else{
            return false;
         }
      }
      else{
         if(this.doubleValue() < n.doubleValue()){
            return true;
         }
         else{
            return false;
         }
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