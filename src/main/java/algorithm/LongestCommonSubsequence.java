package algorithm;

public class LongestCommonSubsequence {
	public static void main(String[] args) {
		String x = "acdefg";
		String y = "waceg";
		int M = x.length();
		int N = y.length();

		// opt[i][j] = length of LCS of x[i..M] and y[j..N]
		int[][] opt = new int[M + 1][N + 1];
		int max = 0;

		// compute length of Longest common substring
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (x.charAt(i) == y.charAt(j))
					opt[i][j] = opt[i + 1][j + 1] + 1;
				else
					opt[i][j] = 0;
				if(opt[i][j]>max)max=opt[i][j];
			}
		}
		System.out.println(max);

		// compute length of Longest common subsequence
		for (int i = M - 1; i >= 0; i--) {
			for (int j = N - 1; j >= 0; j--) {
				if (x.charAt(i) == y.charAt(j))
					opt[i][j] = opt[i + 1][j + 1] + 1;
				else
					opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
			}
		}
		System.out.println(opt[0][0]);

		// recover LCS itself and print it to standard output
		int i = 0, j = 0;
		while (i < M && j < N) {
			if (x.charAt(i) == y.charAt(j)) {
				System.out.print(x.charAt(i));
				i++;
				j++;
			} else if (opt[i + 1][j] >= opt[i][j + 1])
				i++;
			else
				j++;
		}
		System.out.println();

	}
}
