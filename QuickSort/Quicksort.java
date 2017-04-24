import java.util.Random;

public class Quicksort {
	private int count;
	private Random rand;

	public Quicksort() {
		rand = new Random();
	}

	public int[] qs1(int a[], int p, int r) {
		sort1(a, p, r);
		return a;
	}

	public void sort1(int a[], int p, int r) {
		if (p < r) {
			int q = partition1(a, p, r);
			sort1(a, p, q - 1);
			sort1(a, q + 1, r);
		}
	}

	public int partition1(int a[], int p, int r) {
		int x = a[r]; // setting the pivot to the last element
		int i = p - 1;// [Correctly Positioned && < pivot] i [Correctly
						// Positioned && > pivot] j [Not yet checked] r
		for (int j = p; j <= r - 1; j++) {
			count++;
			if (a[j] <= x) {
				i++;
				swap(a, i, j);
			}
		}
		swap(a, i + 1, r); // put pivot in the correct position
		return i + 1;
	}

	public int[] qs2(int a[], int p, int r) {
		sort2(a, p, r);
		return a;
	}

	public void sort2(int[] a, int p, int r) {
		if (p < r) {
			int q = partition2(a, p, r);
			sort2(a, p, q - 1);
			sort2(a, q + 1, r);
		}
	}

	public int partition2(int[] a, int p, int r) {
		int i = this.rand.nextInt((r - p) + 1) + p;
		swap(a, r, i);
		return partition1(a, p, r);
	}

	public int[] qs3(int a[], int p, int r) {
		sort3(a, p, r);
		return a;
	}

	public void sort3(int[] a, int p, int r) {
		if (p < r) {
			int q = partition3(a, p, r);
			sort3(a, p, q - 1);
			sort3(a, q + 1, r);
		}
	}

	public int partition3(int[] a, int p, int r) {
		int i = MedianOfMedians(a, p, r);
		swap(a, r, i);
		return partition1(a, p, r);
	}

	public int MedianOfMedians(int[] a, int start, int end) {
		if ((end - start) < 5) {
			InsertionSort(a, start, end);
			return MedianPosition(start, end);
		} else {
			int count = 0;
			int newStart = start;
			while (count < (end-start) / 5) {	
				int temp =  MedianOfMedians(a, newStart, newStart + 4);
				swap(a, count + start, temp);
				count++;
				newStart+=5;
			}
			return MedianOfMedians(a, start, start + count - 1);
		}
	}

	public int MedianPosition(int start, int end) {
		return (end - start) / 2 + start;
	}

	public void InsertionSort(int[] a, int start, int end) {
        int key = a[start+1];
        for (int i = start+1; i <= end; i++) {
    		key = a[i];
  			int j = i - 1;
          	while ((j >= start) && (a[j] > key)){
            	a[j+1] = a[j]; 
                j = j - 1;
          	}
			a[j+1] = key;
        }
	}

	/**
	 * Create a randomized array with size from parameter.
	 * 
	 * @param SIZE
	 * @return Randomized array and size is SIZE
	 */
	int[] populate(final int SIZE) {
		int[] a = new int[SIZE];
		for (int i = 0; i < SIZE; i++) {
			a[i] = this.rand.nextInt();
		}
		return a;
	}

	public int select(int[] a, int p, int r, int index) {
		return a[index - 1];
	}

	public long getPartCount() {
		return this.count;
	}

	public void reset() {
		this.count = 0;
	}

	private void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}
