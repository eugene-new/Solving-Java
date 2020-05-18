package homework;

import java.io.*;
import java.util.*;

public class Solution_9659_다항식계산 {
	public static final int MOD = 998244353;
	public static int n;
	public static int m;
	public static long[] f;
	public static int[] t;
	public static int[] a;
	public static int[] b;

	public static long func(long x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		if(f[n] != 0) return f[n];
		if (t[n] == 1) {
			return f[n] = (func(x, a[n]) + func(x, b[n])) % MOD;
		} else if (t[n] == 2) {
			return f[n] = (a[n] * func(x, b[n])) % MOD;
		} else {
			return f[n] = (func(x, a[n]) * func(x, b[n])) % MOD;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#"+tc+" ");
			n = Integer.parseInt(br.readLine());

			t = new int[n + 1];
			a = new int[n + 1];
			b = new int[n + 1];

			for (int i = 2; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = Integer.parseInt(st.nextToken());
				a[i] = Integer.parseInt(st.nextToken());
				b[i] = Integer.parseInt(st.nextToken());
			}

			m = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				long x = Long.parseLong(st.nextToken());
				f = new long[n + 1];
				f[0] = 1;
				f[1] = x;
				sb.append(func(x, n) + " ");
			}
			System.out.println(sb);
		}
	}
}