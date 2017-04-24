
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
	
	private Matrix A, B; //input matrices
	private Matrix productRegularResult, productStrassenResult; // Matrices for storing the results
	private int N; // size of the NXN matrix
	private int [] sizeArray = {4, 16, 256, 1024};
	@Before
	public void setUp() throws Exception
	{
		N = 4; // size of the matrix
		double[][] array1 = new double[N][N];
		double[][] array2 = new double[N][N];
		A = new Matrix(array1);
		B = new Matrix(array2);
		productRegularResult = new Matrix(N);
		productStrassenResult = new Matrix(N);
	} // setUp()
	
	/* Testing two constructor
	 * Matrix(double [][]) 	: get 2D array as param
	 * Matrix(int n) 		: get size n of nXn matrix
	 */
	
	@Test
	public void testConstructor() {
		for(int i = 0; i < 1; i++) {
			/*
			 * testing constructor Matrix(int n) 
			 */
			N = sizeArray[i];
			// creating NxN matrix
			Matrix m1 = new Matrix(N);
			// size of matrix should be N
			assertEquals(m1.data.length, N);
			
			/*
			 * testing constructor Matrix(double [][]) 
			 */
			//creating NxN array;
			double [][] array2D = new double [N][N];
			// Instantiating a matrix
			Matrix m2 = new Matrix(array2D);
			// since Java's array is default to 0.0 for data type double
			// check if all the element in array2D is 0.0
			for (int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++){
			    	assertEquals(m2.data[j][k], 0.0, 0.0001); 					
				}
		    }
		}
	}
	
	/* compare result matrices of regular multiplication method and Strassen multiplication method:
	 * Testing productRegular productStrassen
	 */
	@Test
	public void testProductCompare() {
		//run user defined random() method to generate the matrices
	     A = A.random();
	     B = B.random();
	      
	     // run productRegular()
	     productRegularResult = A.productRegular(B);
	     
	     // run productStrassen()
		 productStrassenResult = A.productStrassen(B);
		 
	     for (int i = 0; i < N; i++) {
	    	assertArrayEquals(productRegularResult.data[i], productStrassenResult.data[i], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
		}
		
	    /*
	     * My test
	     */
		for(int i = 0; i < 4; i++) {
			N = sizeArray[i];
			A = new Matrix(N);
			B = new Matrix(N);
			//run user defined random() method to generate the matrices
			A = A.random();
		    B = B.random();
		     
		    // run productRegular()
		    productRegularResult = A.productRegular(B);
		    // run productStrassen()
		    productStrassenResult = A.productStrassen(B);
		    for (int j = 0; j < N; j++) {
		    	assertArrayEquals(productRegularResult.data[j], productStrassenResult.data[j], 0.0001 ); // data[][] is a data member for storing matrix values in class Matrix.
		    }
		}
	}
	
	@Test
	public void testRandom() {
		for(int i = 0; i < 4; i++) {
			N = sizeArray[i];
			//Fill with -100
			Matrix RandomMatrix = new Matrix(N);
			for(int j = 0; j < RandomMatrix.data.length; j++){
				Arrays.fill(RandomMatrix.data[j], -100);
			}
			System.out.println("Rondomizing... size:" + N);
			RandomMatrix = RandomMatrix.random();

			//run user defined random() method to generate the matrices
			// According to the documentation
			// random() return 0.0 <= random() <1.0
			// it should not get -100
		    for (int j = 0; j < N; j++) {
		    	for(int k = 0; k < N; k++){
		    		assertNotEquals(RandomMatrix.data[j][k], -100);
		    	}
		    }
		}
	}
	
	/* multiplying a 2D array using the regular method:
	 */
	@Test
	public void testProductRegular() {
	    
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
	    N = expected.length;
		// input 2D arrain
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	      
	    // run productRegular()
		productRegularResult = m1.productRegular(m2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],productRegularResult.data[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	    
	}
	
	/* multiplying a 2D array using the Strassen method:
	 */
	@Test
	public void testProductStrassen() {
	    //expected output
		double[][] expected = {{96.0,94.0,81.0,128.0},{144.0,117.0,112.0,162.0},{132.0,112.0,101.0,152.0},{112.0,86.0,87.0,130.0}};
		N = expected.length;
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 		
	    
		Matrix m1 = new Matrix(array1);
		Matrix m2 = new Matrix(array2);
	      
	    // run productRegular()
		productStrassenResult= m1.productStrassen(m2);
	     
	    for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],productStrassenResult.data[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	}
	
	@Test
	public void testAdd() {
		double[][] expected = {{8.0,8.0,10.0,15.0},{14.0,14.0,10.0,16.0},{6.0,12.0,8.0,18.0},{14.0,8.0,3.0,10.0}};
		N = expected.length;
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 
		
		// output 2D array
		double[][] output = Strassen.add(array1, array2);
		for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],output[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	}
	
	@Test
	public void testSubtract() {
		double[][] expected = {{-4.0,0.0,0.0,-1.0},{-2.0,0.0,-6.0,0.0},{2.0,0.0,-2.0,0.0},{2.0,0.0,-1.0,0.0}};
		N = expected.length;
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		double[][] array2 = {{6.0,4.0,5.0,8.0},{8.0,7.0,8.0,8.0},{2.0,6.0,5.0,9.0},{6.0,4.0,2.0,5.0}}; 
		
		// output 2D array
		double[][] output = Strassen.subtract(array1, array2);
		for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],output[i], 0.0); // data[][] is a data member for storing matrix values in class Matrix.
		}
	}
	
	@Test
	public void testCreateSubMatrix() {
		double[][] expected11 = {{2.0,4.0},{6.0,7.0}};
		double[][] expected12 = {{5.0,7.0},{2.0,8.0}};
		double[][] expected21 = {{4.0,6.0},{8.0,4.0}};
		double[][] expected22 = {{3.0,9.0},{1.0,5.0}};
		
		N = expected11.length;
		
		// input 2D array
		double[][] array1 = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		
		// output 2D array
		double[][] A11 = Strassen.createSubMatrix(array1, 0, 0);
		double[][] A12 = Strassen.createSubMatrix(array1, 0, array1.length/2);
		double[][] A21 = Strassen.createSubMatrix(array1, array1.length/2, 0);
		double[][] A22 = Strassen.createSubMatrix(array1, array1.length/2, array1.length/2);
		
		for (int i = 0; i < N; i++) {
			assertArrayEquals(expected11[i],A11[i], 0.0);
			assertArrayEquals(expected12[i],A12[i], 0.0);
			assertArrayEquals(expected21[i],A21[i], 0.0);
			assertArrayEquals(expected22[i],A22[i], 0.0);
		}
	}
	
	@Test
	public void testMergeSubMatrixes() {
		double[][] expected = {{2.0,4.0,5.0,7.0},{6.0,7.0,2.0,8.0},{4.0,6.0,3.0,9.0},{8.0,4.0,1.0,5.0}};
		N = expected.length;
		
		// input 2D array
		double[][] array11 = {{2.0,4.0},{6.0,7.0}};
		double[][] array12 = {{5.0,7.0},{2.0,8.0}};
		double[][] array21 = {{4.0,6.0},{8.0,4.0}};
		double[][] array22 = {{3.0,9.0},{1.0,5.0}};
		
		double[][] result = Strassen.mergeSubMatrixes(array11, array12, array21, array22);
		for (int i = 0; i < N; i++) {
			assertArrayEquals(expected[i],result[i], 0.0);
		}
	}
}