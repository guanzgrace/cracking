import java.util.*;
public class SumDigitsInFactorial {
  private ArrayList<Integer> digits;
  private int n;
  
  public SumDigitsInFactorial(int n) {
    this.n = n;
    this.digits = new ArrayList<Integer>();
    digits.add(1);
  }
 
  public int digitsInFactorial() {
    for (int i = 2; i <= n; i++) {
      if (i % 10 > 0) {
        digitMultiplier(i);
      }
    }
    int sum = 0;
    for (int i = 0; i < digits.size(); i++) {
      sum += digits.get(i);
    }
    return sum;
  }
  
  public void digitMultiplier(int n) {
    ArrayList<Integer> oldDigits = new ArrayList<Integer>();
    for (int i = 0; i < digits.size(); i++) {
      oldDigits.add(digits.get(i));
    }
    int offset = 0;
    while (n > 0) {
      int currentDigit = n % 10;
      int size = digits.size();
      for (int i = size - 1; i >= 0; i--) {
        if (offset == 0) { digits.set(i, (currentDigit * oldDigits.get(i))); }
        else if (offset > 0) { 
          if (i + offset >= size) { digits.add(currentDigit * oldDigits.get(i)); }
          else { 
            digits.set(i + offset,
                       digits.get(i + offset) + (currentDigit * oldDigits.get(i)));
          }
        }
      } 
      n = n / 10;
      offset++;
    }
    refactor();
    System.out.println(digits);
  }
  
  public void refactor() {
      for (int i = 0; i < digits.size() - 1; i++) {
      int carryOver = digits.get(i) / 10;
      digits.set(i + 1, digits.get(i + 1) + carryOver);
      digits.set(i, digits.get(i) % 10);
    }
    while (digits.get(digits.size() - 1) > 9) {
      digits.add(digits.get(digits.size() - 1) / 10);
      digits.set(digits.size() - 2, digits.get(digits.size() - 2) % 10);
    }
  }
 
  public static void main(String[]args) {
    SumDigitsInFactorial s = new SumDigitsInFactorial(14);
    System.out.println(s.digitsInFactorial());
  }
}