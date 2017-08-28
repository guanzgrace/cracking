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
  
  // 1.3
  public char[] urlify(String input1, int length) {
    int spaces = 0;
    for (int i = 0; i < length; i++) {
      char current = input1.charAt(i);
      if (current == ' ') {
        spaces++;
      }
    }

    char[] url = new char[length + 2 * spaces];
    urlIndex = 0;
    for (int i = 0; i < length; i++) {
      char current = input1.charAt(i);
      if (current == ' ') {
        url[urlIndex] = '%'; 
        url[urlIndex + 1] = '2'; 
        url[urlIndex + 2] = '0';
        urlIndex = urlIndex + 3; 
      } else {
        url[urlIndex] = current;
        urlIndex++;
      }
    }
    return url;
  }

  // 1.4
  public boolean palindromePermutation(String s) {
    // to be a palindrome permutation, all or all but one letters in s need to 
    // occur an even number of times
    char[] input = s.replaceAll(" ", "").toCharArray();
    Hashtable<String, Integer> t = new Hashtable<String, Integer>();
    for (int i = 0; i < input.length; i++) {
      char current = input[i];
      if (t.containsKey(current)) {
        t.put(current, t.get(current) + 1);
      } else {
        t.put(current, 1);
      }
    }

    Set<Character> keys = t.keySet();
    int odd = 0;
    for (Character c : keys) {
      if (firstString.get(c) % 2 == 1) {
        odd++;
      }
    }
    return odd == 0 || odd == 1;
  }

  // 1.5 copied from leetcode 
  public boolean isOneEditDistance(String s, String t) {
    for (int i = 0; i < Math.min(s.length(), t.length()); i++) { 
      if (s.charAt(i) != t.charAt(i)) {
        if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
          return s.substring(i + 1).equals(t.substring(i + 1));
      else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
        return s.substring(i).equals(t.substring(i + 1));           
      else // s is longer than t, so the only possibility is deleting one char from s
        return t.substring(i).equals(s.substring(i + 1));
      }
    }       
    //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t 
    return Math.abs(s.length() - t.length()) == 1;        
  }

  // 1.6
  public String stringCompression(String s) {
    StringBuilder b = new StringBuilder();
    char prev = s.charAt(0);
    int count = 1;
    for (int i = 1; i < s.length; i++) {
      char current = s.charAt(i);
      if (prev != current) {
        b.append(prev);
        b.append(count);
        count = 1;
      } else {
        count++;
      }
    }
    if (b.length() >= s.length()) {
      return s;
    }
    return b.toString();
  }

  // 1.7
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    for (int i = 0; i < n / 2; i++) {
      for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[n-1-j][i];
        matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
        matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
        matrix[j][n-1-i] = temp;
      }
    }
  }

  // 1.8
  public void zeroMatrix(int[][] matrix) {
    
  } 
}
