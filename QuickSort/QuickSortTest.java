import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class QuickSortTest {
	private Quicksort QS;

	@Before
	public void setUp() throws Exception {
		QS = new Quicksort();
		QS.reset();
	} // setUp()

	/*
	 * Method to test the Sorting of an empty List
	 */
	@Test
	public void testEmpty() {
		int[] array1 = new int[0];
		int[] array2 = new int[0]; // correct sorted array

		array1 = QS.qs1(array1, 0, array1.length - 1);
		assertArrayEquals(array1, array2);

		array1 = QS.qs2(array1, 0, array1.length - 1);
		assertArrayEquals(array1, array2);

		array1 = QS.qs3(array1, 0, array1.length - 1);
		assertArrayEquals(array1, array2);
	}

	@Test
	public void testMedianOfMedians() {
		int[] array1 = { 1, 3, 1, 34, 43, 434, 411, 23, 323, 2, 33, 1, 5 };
		assertEquals(3, array1[QS.MedianOfMedians(array1, 0, 4)]);
		assertEquals(43, array1[QS.MedianOfMedians(array1, 2, 6)]);
		assertEquals(3, array1[QS.MedianOfMedians(array1, 0, 8)]);
		assertEquals(34, array1[QS.MedianOfMedians(array1, 1, 9)]);
		assertEquals(3, array1[QS.MedianOfMedians(array1, 0, 4)]);
		assertEquals(323, array1[QS.MedianOfMedians(array1, 5, 9)]);
		assertEquals(3, array1[QS.MedianOfMedians(array1, 1, 12)]);

	}

	/*
	 * Method to test the Sorting of an already sorted list:
	 */
	@Test
	public void testSorted() {
		int[] array1 = new int[20];
		int[] array2 = new int[20];
		int[] array3 = new int[20];

		for (int i = 0; i < 10; i++) {
			array1[i] = i * 2;
			array2[i] = i * 2;
			array3[i] = i * 2;

		}
		// sort using Java's inbuilt sorting method
		Arrays.sort(array3);

		// run QS1()
		array1 = QS.qs1(array1, 0, array1.length - 1);
		assertArrayEquals(array1, array3);

		// run QS2()
		array1 = QS.qs2(array2, 0, array2.length - 1);
		assertArrayEquals(array1, array3);

		// run QS3()
		array1 = QS.qs3(array2, 0, array2.length - 1);
		assertArrayEquals(array1, array3);
	}

	/*
	 * Method to test the Sorting of a reverse sorted list:
	 */
	@Test
	public void testReverseSorted() {
		int[] array1 = new int[10];
		int[] array2 = new int[10];

		int[] array3 = new int[10];

		for (int i = 0; i < 10; i++) {
			array1[i] = (100 - i);
			array2[i] = (100 - i);
			array3[i] = (100 - i);
		}
		// sort array3
		Arrays.sort(array3);

		// run QS1()
		array1 = QS.qs1(array1, 0, array1.length - 1);
		assertArrayEquals(array1, array3);

		// run QS2()
		array1 = QS.qs2(array2, 0, array2.length - 1);
		assertArrayEquals(array1, array3);

		// run QS3()
		array1 = QS.qs3(array2, 0, array2.length - 1);
		assertArrayEquals(array1, array3);
	}

	/*
	 * Method to test the select method
	 */
	@Test
	public void testSelect() {
		int[] array1 = new int[100];

		for (int i = 0; i < 100; i++) {
			array1[i] = i;
		}
		// median is 49
		int median = QS.select(array1, 0, array1.length - 1, array1.length / 2);
		System.out.println("median:" + QS.select(array1, 0, array1.length - 1, array1.length / 2));
		assertEquals(median, 49);
	}

	/*
	 * Method to test the randomness to the tests:
	 */
	@Test
	public void testRandom() {
		int[] array1 = new int[10];

		for (int i = 0; i < 10; i++) {
			array1[i] = (int) Math.random() * 10;
		}

		// copy arrays
		int[] array2 = Arrays.copyOf(array1, array1.length);
		int[] array3 = Arrays.copyOf(array1, array1.length); // correct sorted
		// array
		Arrays.sort(array3);

		// run QS1()
		array1 = QS.qs1(array1, 0, array1.length - 1);
		assertArrayEquals(array1, array3);

		// run QS2()
		array1 = QS.qs2(array2, 0, array2.length - 1);
		assertArrayEquals(array1, array3);

		// run QS3()
		array1 = QS.qs3(array2, 0, array2.length - 1);
		assertArrayEquals(array1, array3);

	}

	/*
	 * Method to test the timing of QS1
	 *
	 */
	@Test
	public void testQS1Timing() {
		// create an array and a sorted backup
		int[] array1 = QS.populate(10000);
		int[] array2 = QS.populate(1000000);
		int[] array3 = QS.populate(10000000);

		long start = System.currentTimeMillis();
		array1 = QS.qs1(array1, 0, array1.length - 1);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("QS1 time to sort 10000 elements in ms:" + elapsed);

		start = System.currentTimeMillis();
		array2 = QS.qs1(array2, 0, array2.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS1 time to sort 1000000 elements in ms:" + elapsed);

		start = System.currentTimeMillis();
		array3 = QS.qs1(array3, 0, array3.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS1 time to sort 100000000 elements in ms:" + elapsed);
	}

	/*
	 * Method to test the timing of QS2
	 *
	 */
	@Test
	public void testQS2Timing() {
		// create an array and a sorted backup
		int[] array1 = QS.populate(10000);
		int[] array2 = QS.populate(1000000);
		int[] array3 = QS.populate(10000000);

		long start = System.currentTimeMillis();
		array1 = QS.qs2(array1, 0, array1.length - 1);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("QS2 time to sort 10000 elements in ms:" + elapsed);

		start = System.currentTimeMillis();
		array2 = QS.qs2(array2, 0, array2.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS2 time to sort 1000000 elements in ms:" + elapsed);

		start = System.currentTimeMillis();
		array3 = QS.qs2(array3, 0, array3.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS2 time to sort 100000000 elements in ms:" + elapsed);
	}

	/*
	 * Method to test the timing of QS2
	 *
	 */
	@Test
	public void testQS3Timing() {
		// create an array and a sorted backup
		int[] array1 = QS.populate(10000);
		int[] array2 = QS.populate(1000000);
		int[] array3 = QS.populate(10000000);

		long start = System.currentTimeMillis();
		array1 = QS.qs2(array1, 0, array1.length - 1);
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("QS3 time to sort 10000 elements in ms:" + elapsed);

		start = System.currentTimeMillis();
		array2 = QS.qs3(array2, 0, array2.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS3 time to sort 1000000 elements in ms:" + elapsed);

		start = System.currentTimeMillis();
		array3 = QS.qs3(array3, 0, array3.length - 1);
		end = System.currentTimeMillis();
		elapsed = end - start;
		System.out.println("QS3 time to sort 100000000 elements in ms:" + elapsed);
	}

	/*
	 * Method to test the number of comparisons in sorting an already sorted
	 * array of 10 numbers. Number of comparisons should be 45
	 */
	@Test
	public void testgetPartCount() {
		int[] array1 = new int[10];

		// int[] result1 = new int[10];

		for (int i = 0; i < 10; i++) {
			array1[i] = i * 20;
		}

		array1 = QS.qs1(array1, 0, array1.length - 1);
		System.out.println("comparisons in already sorted:" + QS.getPartCount());
		long compare = QS.getPartCount();
		assertEquals(compare, 45);
	}

	/*
	 * Method to test the number of comparisons in reverse sorted array of 10
	 * numbers. Number of comparisons should be 45.
	 */
	@Test
	public void testgetPartCountA() {
		int[] array1 = new int[10];

		for (int i = 0; i < 10; i++) {
			array1[i] = (100 - i);
		}

		array1 = QS.qs1(array1, 0, array1.length - 1);
		System.out.println("comparisons in reverse sorted:" + QS.getPartCount());
		long compare = QS.getPartCount();
		assertEquals(compare, 45);

	} // getPartCount()

	@Test
	public void testTimeComparison() {
		int currentSize = 10000;
		System.out.println(" # of elements\t\tTime in ms\t# of Comparison");
		for (int i = 0; i < 5; i++) {
			// all four arrays have same size and values
			int[] solution1 = QS.populate(currentSize);
			int[] solution2 = Arrays.copyOf(solution1, solution1.length);
			int[] solution3 = Arrays.copyOf(solution1, solution1.length);
			int[] solution4 = Arrays.copyOf(solution1, solution1.length); //sorted by Java sort()

			QS.reset(); // reset the count

			long start = System.currentTimeMillis();
			solution1 = QS.qs1(solution1, 0, solution1.length - 1);
			long end = System.currentTimeMillis();
			long qs1Elapsed = end - start;
			String qs1Comparison = Long.toUnsignedString(QS.getPartCount());
			System.out.println("[QS1] " + currentSize + "\t\t" + qs1Elapsed + "\t\t" + qs1Comparison);

			QS.reset(); // reset the count

			start = System.currentTimeMillis();
			solution2 = QS.qs2(solution2, 0, solution2.length - 1);
			end = System.currentTimeMillis();
			long qs2Elapsed = end - start;
			String qs2Comparison = Long.toUnsignedString(QS.getPartCount());

			System.out.println("[QS2] " + currentSize + "\t\t" + qs2Elapsed + "\t\t" + qs2Comparison);

			QS.reset(); // reset the count

			start = System.currentTimeMillis();
			solution3 = QS.qs3(solution3, 0, solution3.length - 1);
			end = System.currentTimeMillis();
			long qs3Elapsed = end - start;
			String qs3Comparison = Long.toUnsignedString(QS.getPartCount());

			System.out.println("[QS3] " + currentSize + "\t\t" + qs3Elapsed + "\t\t" + qs3Comparison);
			
			Arrays.sort(solution4);

			assertArrayEquals(solution1, solution4);
			assertArrayEquals(solution2, solution4);
			assertArrayEquals(solution3, solution4);
			
			currentSize *= 10;
		}
	}
	
	@Test
	public void testInsertionSort() {
		int currentSize = 10;
		for (int i = 0; i < 5; i++, currentSize*=5) {
			int[] array1 = QS.populate(currentSize);
			int[] array2 = Arrays.copyOf(array1, array1.length);
			Arrays.sort(array1);
			QS.InsertionSort(array2, 0, array2.length-1);
		}
	}
}
