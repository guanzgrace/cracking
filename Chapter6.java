/**
 * Cracking the Coding Interview Chapter 6
 * Grace Guan 9/3/17
 * 
 * Math and Logic Puzzles
 */

import java.util.*;
import java.io.*;
public class Chapter6 {

	boolean checkIfPrime(int n) {
		if (n < 2) return false;
		int sqrt = (int) Math.sqrt(n);
		for (int i = 2; i < sqrt; i++) {
			if (n % i == 0) return false;
		}
		return true;
	}

	// 6.1 
	// weigh 1 pill from bottle 1, 2 pills from bottle 2, etc.
	// formula: weight - 210 / 0.1 = bottle #

	// 6.2
	// game 1: p
	// game 2: 3p^2 - 2p^3
	// p^3 + 3p^2 - 3 p^3
	// compare

	// 6.3
	// impossible bc each domino must take up 1 black sq and 1 white sq
	// and there are 30 of one color and 32 of the other

	// 6.4
	// triangle case:
	// same direction: 2 * (1/2)^3 = 1/4
	// probability = 1 - same dir = 3/4
	// n case:
	// probability = 1 - p^ (n-1)

	// 6.5
	// 5 jug | 3 jug | turn #
	// 5, 0, 1
	// 2, 3, 2
	// 2, 0, 3
	// 0, 2, 4
	// 5, 2, 5
	// 4, 3, 6
	// 4, 0, 7

	// 6.6
	// # nights = # blue eyed people
	// if a person does not see any blue eyed people on the island, he should leave

	// 6.7
	// 0.5 (overall ratio not impacted by individual cases)

	// 6.8, 6.9, 6.10 - revisit
}