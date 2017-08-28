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
  
  // 1.1
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

  // 1.1 no extra space
  public boolean isUniqueNoExtraSpace(String input) {
    char[] chars = input.toCharArray();
    Arrays.sort(chars);
    for (int i = 1; i < chars.length; i++) {
      if (chars[i - 1] == chars[i]) {
        return false;
      }
    }
    return true;
  }
  
  // 1.2
  public boolean checkPermutations(String input1, String input2) {
    Hashtable<Character, Integer> firstString = new HashTable<Character, Integer>();
    Hashtable<Character, Integer> secondString = new HashTable<Character, Integer>();
    for (int i = 0; i < input1.length(); i++) {
      char current = input1.charAt(i);
      if (firstString.containsKey(current)) {
        firstString.put(current, firstString.get(current) + 1);
      } else {
        firstString.put(current, 1);
      }
    }

    for (int i = 0; i < input2.length(); i++) {
      char current = input2.charAt(i);
      if (secondString.containsKey(current)) {
        secondString.put(current, secondString.get(current) + 1);
      } else {
        secondString.put(current, 1);
      }
    }

    Set<Character> keys = firstString.keySet();
    for (Character c : keys) {
      if (firstString.get(c) != secondString.get(c)) {
        return false;
      }
    }
    return true;
  }
  
}
