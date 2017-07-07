/**
 * Cracking the Coding Interview Chapter 1
 * Grace Guan 5/31/17
 * 
 * Arrays and Strings
 * Important things to note:
 * - For Hashtables, two different keys can have the same hashcode since there are an infinte # of keys and a finite #
 * of ints. If the # of collisions is really high, then O(N), otherwise generally O(1).
 * - For ArrayLists, the doubling of the array is amortized making it O(1) since the doubling function itself is O(N).
 * - StringBuilder prevents the for loop manipulation of strings, which is O(xN^2). StringBuilder works by creating a
 * resizable array of strings and copying them back to a string only when necessary.
 */

import java.util.*;
import java.io.*;
public class Chapter1 {
  
  /**
   * Question 1. Correct but uses extra space. More accurate solution would be to use
   * key-index counting. If we aren't allowed to use extra space, sort the array and then
   * compare each term to previous terms.
   */ 
  public boolean isUnique(String input) {
    Hashtable<Character, Integer> dups = new Hashtable<Character, Integer>();
    for (int i = 0; i < input.length(); i++) {
      char current = input.charAt(i);
      if (dups.containsKey(current)) {
        return false;
      }
      else {
        dups.put(current, 1);
      }
    }
    return true;
  }
  
  
  
}
