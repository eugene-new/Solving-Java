package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d3_5607_조합 {
	static final long MOD = 1234567891;
	static int n, r;
	static long factorial[] = new long[1000000 + 5];
	static long pow(long d, long p) {
		if (p==0) return 1;
		if (p%2!=0) return (pow(d, p-1)*d) % MOD;
		long tmp = pow(d, p/2)%MOD;
		return (tmp*tmp)%MOD;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// 전처리
		factorial[0] = 1;
		for (int i = 1; i <= 1000000; i++) {
			factorial[i] = (factorial[i - 1] * i) % MOD;
			
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			// ((n-r)! x r!)%MOD
			long devider = (factorial[n-r]*factorial[r])%MOD;
			
			devider = pow(devider, MOD-2);
			
			long answer = (factorial[n] * devider) % MOD;
			System.out.print("#" + tc + " " + answer+"\n");
		}
	}

}
