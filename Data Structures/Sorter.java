public class Sorter {
	public int indexOfMin(Comparable[] a, int startIndex) {
		int min = startIndex;
		for (int i = startIndex; i < a.length - 1; i++) {
			if (a[i + 1].compareTo(a[min]) < 0) min = i + 1;
		}
		return min;
	}

	public void selectionSort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			Comparable current = a[i];
			int indexOfMin = indexOfMin(a, i);
			a[i] = a[indexOfMin];
			a[indexOfMin] = current;
		}
	}

	public void insert(Comparable[] a, int nextIndex) {
		Comparable move = a[nextIndex];
		int where = 0;
		for (int i = 0; i < nextIndex; i++) { //find where to put the object
			if (a[i].compareTo(move) < 0) where++; 
		}
		for (int j = nextIndex; j > where; j--) { //move the objects after it
			a[j] = a[j - 1];
		}
		a[where] = move; //place the object
	}

	public void insertionSort(Comparable[] a) {
		for (int i = 1; i < a.length; i++) {
			insert(a, i);
		}
	}

	public void mergesort(Comparable[] a) { mergesortHelp(a, 0, a.length - 1); }

	private void mergesortHelp(Comparable[] a, int lowIndex, int highIndex) {
		if (highIndex - lowIndex >= 1) {
			int midIndex = (lowIndex + highIndex) / 2;
			mergesortHelp(a, lowIndex, midIndex);
			mergesortHelp(a, midIndex + 1, highIndex);
			merge(a, lowIndex, midIndex, highIndex);
		}
	}
	
	private void merge(Comparable[] a, int lowIndex, int midIndex, int highIndex)  {
		Comparable[] copy = new Comparable[a.length];
		for (int i = lowIndex; i <= highIndex; i++)
			copy[i] = a[i];
		int left = lowIndex;
		int right = midIndex + 1;
		for (int i = lowIndex; i <= highIndex; i++)  {
			if (right > highIndex || (left <= midIndex && copy[left].compareTo(copy[right]) < 0)) {
				a[i] = copy[left];
				left++;
			}
			else {
				a[i] = copy[right];
				right++;
			}
		}
	}

	public void quicksort(Comparable[] a) { quicksortHelp(a, 0, a.length - 1); }

	private void quicksortHelp(Comparable[] a, int lowIndex, int highIndex) {
		if(lowIndex < highIndex) {
			int pivot = partition(a, lowIndex, highIndex);
			quicksortHelp(a, lowIndex, pivot);
			quicksortHelp(a, pivot + 1, highIndex);
		}
	}

	private int partition(Comparable[] a, int lowIndex, int highIndex) {
		int pivot = lowIndex;
		for (int unsorted = lowIndex + 1; unsorted <= highIndex; unsorted++) {
			if (a[unsorted].compareTo(a[pivot]) < 0) {
				Comparable temp = a[pivot];
				a[pivot] = a[unsorted];
				a[unsorted] = a[pivot + 1];
				a[pivot + 1] = temp;
				pivot++;
			}
		}
		return pivot;
	}
}