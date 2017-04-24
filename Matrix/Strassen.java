

public class Strassen {
	public static double [][] StrassenMatrix(double [][] m1, double [][]m2) {
		double [][] result = new double [m1.length][m1.length];
		//Base when size of matrix is 1 return product of two matrixes
		if (m1.length == 1){
			result [0][0] =  m1[0][0] * m2[0][0];
		}
		else {
			// creating sub matrixes
			double[][] A11 = createSubMatrix(m1, 0, 0);
			double[][] A12 = createSubMatrix(m1, 0, m1.length/2);
			double[][] A21 = createSubMatrix(m1, m1.length/2, 0);
			double[][] A22 = createSubMatrix(m1, m1.length/2, m1.length/2);
			double[][] B11 = createSubMatrix(m2, 0, 0);
			double[][] B12 = createSubMatrix(m2, 0, m1.length/2);
			double[][] B21 = createSubMatrix(m2, m1.length/2, 0);
			double[][] B22 = createSubMatrix(m2, m1.length/2, m1.length/2);
			
			//A11*(B12-B22)
			double [][] P1 = StrassenMatrix(A11, subtract(B12, B22));
			//(A11+A12)*B22
			double [][] P2 = StrassenMatrix(add(A11, A12), B22);
			//(A21+A22)*B11
			double [][] P3 = StrassenMatrix(add(A21, A22), B11);
			//A22*(B21-B11)
			double [][] P4 = StrassenMatrix(A22, subtract(B21, B11));
			//(A11+A22)(B11+B22)
			double [][] P5 = StrassenMatrix(add(A11, A22), add(B11, B22));
			//(A12-A22)(B21+B22)
			double [][] P6 = StrassenMatrix(subtract(A12, A22), add(B21, B22));
			//(A11-A21)(B11+B12)
			double [][] P7 = StrassenMatrix(subtract(A11, A21), add(B11, B12));

			//construct four n/2 n/2 sub matrices of the product c
			//C11 = P5+P4-P2+P6
			double C11 [][] = add(P5, add(P6, subtract(P4, P2)));
			//C12 = P1+P2
			double C12 [][] = add(P1, P2);
			//C21 = P3 + P4
			double C21 [][] = add(P3, P4);
			//C22 = P5 + P1 - P3 -P7
			double C22 [][] = subtract(subtract(add(P5, P1), P3), P7);
			
			result = mergeSubMatrixes(C11, C12, C21, C22);

		}
		return result;
	}
	public static double [][] add(double [][] m1, double [][] m2){
		double [][] result = new double [m1.length][m1.length];
		for(int i = 0; i < m1.length; i++) {
			for(int j = 0; j < m1.length; j++) {
				result[i][j] = m1[i][j] + m2[i][j];
			}
		}
		return result;
	}
	public static double [][] subtract(double [][] m1, double [][] m2){
		double [][] result = new double[m1.length][m1.length];
		for(int i = 0; i < m1.length; i++) {
			for(int j = 0; j < m1.length; j++) {
				result[i][j] = m1[i][j] - m2[i][j];
			}
		}
		return result;
	}
	public static double [][] createSubMatrix(double [][] m, int beginRow, int beginCol){
		double [][] result = new double[m.length/2][m.length/2];
		//i, j keep track of index for main matrix
		//a, b keep track of index for sub matrix
		//a and b always start with 0 to begin - end
		for(int i = beginRow, a = 0; a < m.length/2; i++, a++) {
			for(int j = beginCol, b = 0; b < m.length/2; j++, b++) {
				result[a][b] = m[i][j];
			}
		}
		return result;
	}
	public static double [][] mergeSubMatrixes(double [][] m11, double [][] m12,
												double [][] m21, double [][] m22){
		double [][] result = new double[m11.length*2][m11.length*2];
		
		for(int i = 0; i < m11.length; i++) {
			for(int j = 0; j < m11.length; j++) {
				result[i][j] = m11[i][j];
				result[i][m11.length + j] = m12[i][j];
				result[m11.length+ i][j] = m21[i][j];
				result[m11.length + i][m11.length +j] = m22[i][j];
			}
		}
		return result;
	}
//	private static void printMatrix(double [][] m) {
//		for(double [] row : m) {
//			for(double col : row) {
//				System.out.print(col + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
}
