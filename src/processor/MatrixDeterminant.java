package processor;

import java.util.Scanner;

public class MatrixDeterminant {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		int m;
		double [][] matrixA;
		double [][] matrixB;
		
		while(true) {
			System.out.println("1. Add matrices\r\n" + 
					"2. Multiply a matrix to a constant\r\n" + 
					"3. Multiply matrices\r\n" +
					"4. Transpose a matrix\r\n" + 
					"5. Calculate a determinant\r\n" + 
					"0. Exit\r\n Your choice: ");
			switch (sc.nextInt()) {
			case 0:
				return;
			case 1:
				System.out.println("Enter the size of the first matrix: ");
				n = sc.nextInt();
				m = sc.nextInt();
				matrixA = new double [n][m];
				System.out.println("Enter the first matrix:");
				for(int i = 0; i<n; i++) {
					for(int j = 0; j<m; j++) {
						matrixA[i][j] = sc.nextDouble();
					}
				}
				System.out.println("Enter the size of the second matrix: ");
				if(n != sc.nextInt() ||	m != sc.nextInt()) {
					System.out.println("ERROR");
					System.out.println();
					sc.nextInt();
					break;
				}
				System.out.println("Enter the second matrix:");
				matrixB = new double [n][m];
				for(int i = 0; i<n; i++) {
					for(int j = 0; j<m; j++) {
						matrixB[i][j] = sc.nextDouble();
					}
				}
				System.out.println("The addition result is: ");
				addMatrices (matrixA, matrixB);
				System.out.println();
				break;
			case 2:
				System.out.println("Enter the size of a matrix: ");
				n = sc.nextInt();
				m = sc.nextInt();
				matrixA = new double [n][m];
				System.out.println("Enter a matrix:");
				for(int i = 0; i<n; i++) {
					for(int j = 0; j<m; j++) {
						matrixA[i][j] = sc.nextDouble();
					}
				}
				System.out.println("Enter a constant:");
				double c = sc.nextInt();
				System.out.println("The multiplication result is:");
				multiplyMatrixConstant(matrixA,c);
				System.out.println();
				break;
			case 3:
				System.out.println("Enter the size of the first matrix: ");
				n = sc.nextInt();
				m = sc.nextInt();
				matrixA = new double [n][m];
				System.out.println("Enter the first matrix:");
				for(int i = 0; i<n; i++) {
					for(int j = 0; j<m; j++) {
						matrixA[i][j] = sc.nextDouble();
					}
				}
				System.out.println("Enter the size of the second matrix: ");
				if(m != sc.nextInt()) {
					System.out.println("ERROR");
					System.out.println();
					sc.nextInt();
					break;
				}
				n = sc.nextInt();
				System.out.println("Enter the second matrix:");
				matrixB = new double [n][m];
				for(int i = 0; i<m; i++) {
					for(int j = 0; j<n; j++) {
						matrixB[j][i] = sc.nextDouble();
					}
				}
				System.out.println("The multiplication result is: ");
				multiplyMatrices (matrixA, matrixB);
				System.out.println();
				break;
			case 4:
				System.out.println("1. Main diagonal\r\n" + 
						"2. Side diagonal\r\n" + 
						"3. Vertical line\r\n" + 
						"4. Horizontal line\r\n Your choice: ");
				int choice = sc.nextInt();
				if(choice<1 || choice>4) {
					System.out.println();
					break;
				}
				System.out.println("Enter the size of the matrix: ");
				n = sc.nextInt();
				m = sc.nextInt();
				if(choice<3) {
					if(n!=m) {
						System.out.println("ERROR: The matrix mast be square!");
						System.out.println();
						break;
					}
				}
				matrixA = new double [n][m];
				System.out.println("Enter the matrix:");
				for(int i = 0; i<n; i++) {
					for(int j = 0; j<m; j++) {
						matrixA[i][j] = sc.nextDouble();
					}
				};
				System.out.println("The matrix transposition is: ");
				switch (choice) {
				case 1:
					matrixTranspositionMain(matrixA);
					System.out.println();
					break;
				case 2:
					matrixTranspositionSide(matrixA);
					break;
				case 3:
					matrixTranspositionVertical(matrixA);
					break;
				case 4:
					matrixTranspositionHorisontal(matrixA);
					break;
				default:
					break;
				}
				break;
			case 5:
				
				System.out.println("Enter the size of the matrix: ");
				n = sc.nextInt();
				if(n!=sc.nextInt()) {
					System.out.println("ERROR: The matrix mast be square!");
					System.out.println();
					break;
				}
				matrixA = new double [n][n];
				System.out.println("Enter the matrix:");
				for(int i = 0; i<n; i++) {
					for(int j = 0; j<n; j++) {
						matrixA[i][j] = sc.nextDouble();
					}
				};
				System.out.println("The determinant of the matrix is: ");
				System.out.println(matrixDeterminant(matrixA));
				System.out.println();
				break;
			default:
				break;
			}
			
		}
	}
	
	private static double matrixDeterminant(double[][] matrixA) {
		int n = matrixA.length;
		if (n == 1 ) {return matrixA[0][0];}
		if (n == 2 ) {
			return matrixA[0][0]*matrixA[1][1]-matrixA[0][1]*matrixA[1][0];
		}
		double det = 0;
		double[][] matrixB = new double [n-1][n-1];
		for(int j = 0; j<n; j++) {
			matrixB = MinorMatrixBy1row(matrixA, j);
			det+= (j%2==0?1:-1)*matrixA[0][j]*matrixDeterminant(matrixB);
		}
		
		return det;
	}
	
	private static double[][] MinorMatrixBy1row(double[][] matrixA, int jIndex) {
		int n = matrixA.length;
		double[][] matrixB = new double [n-1][n-1];
		for(int i = 1; i<n; i++) {
			for(int j = 0, k = 0; j<n; j++, k++) {
				if(jIndex == j) {
					k--;
					continue;
				}
				matrixB[i-1][k] = matrixA[i][j];
			}
		};
		return matrixB;
	}
	

	private static void matrixTranspositionVertical(double[][] matrix) {
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][matrix[0].length-1-j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	private static void matrixTranspositionHorisontal(double[][] matrix) {
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				System.out.print(matrix[matrix.length-1-i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	static void matrixTranspositionSide(double[][] matrix) {
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix.length; j++) {
				System.out.print(matrix[matrix.length-1-j][matrix.length-1-i]);
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	static void addMatrices(double[][] matrixA, double[][] matrixB) {
		for(int i = 0; i<matrixA.length; i++) {
			for(int j = 0; j<matrixA[0].length; j++) {
				System.out.print(matrixA[i][j]+matrixB[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	static void multiplyMatrixConstant(double[][] matrix, double c) {
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][j]*c);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	static void multiplyMatrices(double[][] matrixA, double[][] matrixB) {
		double resMatrix = 0;
		for(int i = 0; i<matrixA.length; i++) {
			for(int j = 0; j<matrixB.length; j++) {
				for (int k = 0; k < matrixA[0].length; k++) {
					resMatrix += matrixA[i][k]*matrixB[j][k];
				}
				System.out.print(resMatrix);
				resMatrix = 0;
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	static void matrixTranspositionMain(double[][] matrix) {
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix.length; j++) {
				System.out.print(matrix[j][i]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

}
