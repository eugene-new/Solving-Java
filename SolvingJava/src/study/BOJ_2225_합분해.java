package study;

import java.util.Scanner;

public class BOJ_2225_합분해 {
	static final int MOD = 1000000000;
	static int n;
	static int k;
	static long memo[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		memo = new long[n+1][k+1];
		for(int i=1; i<=k; i++) {
			memo[0][i] = 1;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=k; j++) {
				memo[i][j] = (memo[i][j-1]+memo[i-1][j])%MOD;
			}
		}
		
		System.out.println(memo[n][k]);
	}

}
