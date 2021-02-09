import java.util.Scanner;

public class FkProceseStokastikeShumezuesMatrice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Jep numrin e rreshtave te vektorit page rank: ");
		int rreshtatPageRank = scanner.nextInt();
		System.out.println("Jep numrin e kolonave te vektorit page rank: ");
		int kolonatPageRank = scanner.nextInt();
		System.out.println("Jep numrin e rreshtave te matrices se probabiliteteve te kalimit: ");
		int rreshtatMatricaKalimit = scanner.nextInt();
		System.out.println("Jep numrin e kolonave te matrices se probabiliteteve te kalimit: ");
		int kolonatMatricaKalimit = scanner.nextInt();

		double[][] vektoriPageRank = new double[rreshtatPageRank][kolonatMatricaKalimit];
		double[][] matricaEProbabiliteteve = new double[rreshtatMatricaKalimit][kolonatMatricaKalimit];

		for (int i = 0; i < rreshtatPageRank; i++) {
			for (int j = 0; j < kolonatPageRank; j++) {
				vektoriPageRank[i][j] = (double) 1 / (rreshtatPageRank * kolonatPageRank);
			}
		}

		for (int i = 0; i < rreshtatPageRank; i++) {
			for (int j = 0; j < kolonatPageRank; j++) {
				System.out.print(vektoriPageRank[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("Jep vlerat e matrices se probabiliteteve te kalimit: ");
		for (int i = 0; i < rreshtatMatricaKalimit; i++) {
			for (int j = 0; j < kolonatMatricaKalimit; j++) {
				System.out.println("Jep vleren ne indeksin: [" + i + "][" + j + "]");
				matricaEProbabiliteteve[i][j] = scanner.nextDouble();
			}
		}
		
		for (int i = 0; i < matricaEProbabiliteteve.length; i++) {
			for (int j = 0; j < matricaEProbabiliteteve[i].length; j++) {
				System.out.print(matricaEProbabiliteteve[i][j] + " ");
			}
			System.out.println();
		}
		
		while(true) {
			double[][] matricaRezultat = multiplyMatrices(vektoriPageRank, matricaEProbabiliteteve);
			if(checkIfTwoMatricesAreEqual(matricaRezultat, vektoriPageRank)) {
				break;
			} else {
				vektoriPageRank = matricaRezultat;
				
				for (int i = 0; i < matricaRezultat.length; i++) {
					for (int j = 0; j < matricaRezultat[i].length; j++) {
						System.out.print(matricaRezultat[i][j] + " ");
					}
					System.out.println();
				}
			}
			System.out.println();
			System.out.println();
			System.out.println();

		} 
		

		for (int i = 0; i < vektoriPageRank.length; i++) {
			for (int j = 0; j < vektoriPageRank[i].length; j++) {
				System.out.print(vektoriPageRank[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
		double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result[row].length; col++) {
				result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
			}
		}

		return result;
	}

	private static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
		double cell = 0;
		for (int i = 0; i < secondMatrix.length; i++) {
			cell += firstMatrix[row][i] * secondMatrix[i][col];
			cell = round(cell, 5);
		}
		return cell;
	}

	private static boolean checkIfTwoMatricesAreEqual(double[][] firstMatrix, double[][] secondMatrix) {
		boolean flag = true;

		int row1 = firstMatrix.length;
		int col1 = firstMatrix[0].length;
		int row2 = secondMatrix.length;
		int col2 = secondMatrix[0].length;

		if (row1 != row2 || col1 != col2) {
		} else {
			for (int i = 0; i < row1; i++) {
				for (int j = 0; j < col1; j++) {
					if (firstMatrix[i][j] != secondMatrix[i][j]) {
						flag = false;
						break;
					}
				}
			}

			if (flag)
				System.out.println("Matrices are equal");
			
		}
		return flag;

	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
