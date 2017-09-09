/**
 * Cracking the Coding Interview Chapter 8
 * Grace Guan 9/6/17
 * 
 * Recursion and DP
 */
import java.util.*;
import java.io.*;
public class Solution{

	// 8.1
	public int tripleStep(int n) {
		return tripleStepHelper(0, n);
	}

	public int tripleStepHelper(int ways, int n) {
		if (n == 0) return ways;
		if (n >= 3) { // 3, 21, 12, 111
			return ways + tripleStepHelper(ways + 4, n - 3);
		} else if (n >= 2) { // 2, 11
			return ways + tripleStepHelper(ways + 2, n - 2);
		} else if (n >= 1) { // 1
			return ways + tripleStepHelper(ways + 1, n - 1);
		}
		return ways;
	}

	// with dp
	public static int countWays(int n) {
		int[] map = new int[n + 1];
		Arrays.fill(map, -1);
		return countWays(n, map);
	}
	
	public static int countWays(int n, int[] memo) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			return memo[n];
		} else {
			memo[n] = countWays(n - 1, memo) + countWays(n - 2, memo) + countWays(n - 3, memo);
			return memo[n];
		}
	}

	// 8.2
	public static ArrayList<Point> getPath(boolean[][] maze) {
		if (maze == null || maze.length == 0) return null;
		ArrayList<Point> path = new ArrayList<Point>();
		HashSet<Point> failedPoints = new HashSet<Point>();
		if (getPath(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
			return path;
		}
		return null;
	}
	
	public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
		/* If out of bounds or not available, return.*/
		if (col < 0 || row < 0 || !maze[row][col]) {
			return false;
		}
		
		Point p = new Point(row, col);
		
		/* If we've already visited this cell, return. */
		if (failedPoints.contains(p)) { 
			return false;
		}	
		
		boolean isAtOrigin = (row == 0) && (col == 0);
		
		/* If there's a path from the start to my current location, add my location.*/
		if (isAtOrigin || getPath(maze, row, col - 1, path, failedPoints) || getPath(maze, row - 1, col, path, failedPoints)) {
			path.add(p);
			return true;
		}
		
		failedPoints.add(p); // Cache result
		return false;
	}

	// 8.3
	public static int magicFast(int[] array, int start, int end) {
		if (end < start) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (array[mid] == mid) {
			return mid;
		} else if (array[mid] > mid){
			return magicFast(array, start, mid - 1);
		} else {
			return magicFast(array, mid + 1, end);
		}
	}
	
	public static int magicFast(int[] array) {
		return magicFast(array, 0, array.length - 1);
	}

	// 8.4
	public static ArrayList<ArrayList<Integer>> getSubsets(ArrayList<Integer> set, int index) {
		ArrayList<ArrayList<Integer>> allsubsets;
		if (set.size() == index) { // Base case - add empty set
			allsubsets = new ArrayList<ArrayList<Integer>>();
			allsubsets.add(new ArrayList<Integer>()); 
		} else {
			allsubsets = getSubsets(set, index + 1);
			int item = set.get(index);
			ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> subset : allsubsets) {
				ArrayList<Integer> newsubset = new ArrayList<Integer>();
				newsubset.addAll(subset); 
				newsubset.add(item);
				moresubsets.add(newsubset);
			}
			allsubsets.addAll(moresubsets);
		}
		return allsubsets;
	}
}