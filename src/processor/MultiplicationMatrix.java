package processor;

import java.util.Scanner;

public class MultiplicationMatrix {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int [][] matrixA = new int [n][m];
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<m; j++) {
				matrixA[i][j] = sc.nextInt();
			}
		}
		int c = sc.nextInt();
				
		for(int i = 0; i<n; i++) {
			for(int j = 0; j<m; j++) {
				System.out.print(matrixA[i][j]*c);
				System.out.print(" ");
			}
			System.out.println();
		}

	}

}
