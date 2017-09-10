/**
 * Cracking the Coding Interview Chapter 10
 * Grace Guan 9/8/17
 * 
 * Sorting and Searching
 */
import java.util.*;
import java.io.*;
public class Solution{
	// 10.1 go from back to front
	public static void merge(int[] a, int[] b, int lastA, int lastB) {
		int indexMerged = lastB + lastA - 1; /* Index of last location of merged array */
		int indexA = lastA - 1; /* Index of last element in array b */
		int indexB = lastB - 1; /* Index of last element in array a */
	
		/* Merge a and b, starting from the last element in each */
		while (indexB >= 0) {
			if (indexA >= 0 && a[indexA] > b[indexB]) { /* end of a is bigger than end of b */
				a[indexMerged] = a[indexA]; // copy element
				indexA--; 
			} else {
				a[indexMerged] = b[indexB]; // copy element
				indexB--;
			}
			indexMerged--; // move indices			
		}
	}

	// 10.3
	public static int search(int a[], int x) {
		return search(a, 0, a.length - 1, x);
	}

	
	public static int search(int a[], int left, int right, int x) {
		int mid = (left + right) / 2;
		if (x == a[mid]) { // Found element
			return mid;
		}
		if (right < left) {
			return -1;
		}
		
		/* While there may be an inflection point due to the rotation, either the left or 
		 * right half must be normally ordered.  We can look at the normally ordered half
		 * to make a determination as to which half we should search. 
		 */
		if (a[left] < a[mid]) { // Left is normally ordered.
			if (x >= a[left] && x < a[mid]) { 
				return search(a, left, mid - 1, x);
			} else {
				return search(a, mid + 1, right, x);
			}
		} else if (a[mid] < a[left]) { // Right is normally ordered.
			if (x > a[mid] && x <= a[right]) {
				return search(a, mid + 1, right, x);
			} else {
				return search(a, left, mid - 1, x);
			}				
		} else if (a[left] == a[mid]) { // Left is either all repeats OR loops around (with the right half being all dups)
			if (a[mid] != a[right]) { // If right half is different, search there
				return search(a, mid + 1, right, x);
			} else { // Else, we have to search both halves
				int result = search(a, left, mid - 1, x); 
				if (result == -1) {
					return search(a, mid + 1, right, x); 
				} else {
					return result;
				}
			}
		}
		return -1;
	}
}
