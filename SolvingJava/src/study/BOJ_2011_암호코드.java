package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2011_암호코드 {
	static final int MOD = 1000000;
	static int memo[];
	static char code[];
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		n = input.length();
		memo = new int[n + 2];
		code = new char[n+2];
		for(int i=1; i<=n; i++) {
			code[i] = input.charAt(i-1);
		}

		memo[0] = 1;

		for (int i = 1; i <= n; i++) {
			if (code[i] != '0') {
				memo[i] = (memo[i - 1] + memo[i]) % MOD;
			}
			// 두 자리수 가능
			if (i > 1) {
				int x = (code[i - 1] - '0') * 10 + code[i] - '0';
				if (10 <= x && x <= 26) {
					memo[i] = (memo[i - 2] + memo[i]) % MOD;
				}
			}
		}
		System.out.println(memo[n]);
	}

}
