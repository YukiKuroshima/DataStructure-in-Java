
import java.util.Random;

public class Matrix {
	
	// double [][]  [row][col]
	//ex. [2][4] means
	/*   1 2 3 4
	 * 1 x x x x
	 * 2 x x x x
	 */
	public double [][] data;
	
	public Matrix(double [][] a) { //construct by 2D array from parameter
		//copying each 1D array from 2D array.
		/* ex. double a[0].length is 4, which means 4 arrays of double == 4 rows
		 * 
		 * r0 double[]
		 * r1 double[]
		 * r2 double[]
		 * r3 double[]
		 */
		this.data = new double [a.length][];
		for(int i = 0; i < a.length; i++) {
			this.data[i] = new double [a[i].length]; //setting the size of 4
			for(int j = 0; j < this.data[i].length; j ++) {
				this.data[i][j] = a[i][j];
			}
		}
	}
	
	public Matrix(int n) { //construct by size
		this.data = new double [n][n];
	}
	
	public Matrix random() {
		Random r = new Random();
		for(int i = 0; i < this.data.length; i++) {
			for(int j = 0; j < this.data[i].length; j ++) {
				this.data[i][j] = r.nextDouble();
			}
		}
		return new Matrix(data);
	}
	
	public Matrix productRegular(Matrix m){
		
		double sum = 0;
		double [][] result = new double[this.data.length][this.data[0].length];
		for(int n = 0; n < this.data.length; n++){ // n = 0 -> # of row in A
			for(int i = 0; i < m.data().length; i++){ // i = 0 -> # of col row in B
				for(int j = 0; j < this.data[i].length; j++){ // j = 0 -> # of col in A || # of row in B
					sum += this.data()[n][j] * m.data()[j][i];
				}
				result[n][i] = sum; //store the result of cell 
				sum = 0; //reset sum
			}
		}
		return new Matrix(result);
	}
	
	public Matrix productStrassen(Matrix m){	
		return new Matrix(Strassen.StrassenMatrix(this.data, m.data));
	}
	
	public double [][] data() {
		return data;
	}
	
//	public void printMatrix() {
//		for(double [] row : data) {
//			for(double col : row) {
//				System.out.print(col + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
 }