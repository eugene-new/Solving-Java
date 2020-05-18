package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17404_RGB거리2 {
	static int n;
	static int cost[][];
	static int memo[][]; // i집을 j로 칠하는 비용의 값
	static int ans = 1000*1000+1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		cost = new int[n][3];
		memo = new int[n][3];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 0번째 집의 색 
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j)
					memo[0][j] = cost[0][j];
				else
					memo[0][j] = 1000*1000+1;

			}

			for (int j = 1; j < n; j++) {
				memo[j][0] = Math.min(memo[j - 1][1], memo[j - 1][2]) + cost[j][0];
				memo[j][1] = Math.min(memo[j - 1][0], memo[j - 1][2]) + cost[j][1];
				memo[j][2] = Math.min(memo[j - 1][0], memo[j - 1][1]) + cost[j][2];
			}
			// 최솟값 찾기
			for (int j = 0; j < 3; j++) {
				if (i != j) {
					ans = Math.min(ans, memo[n - 1][j]);
				}
			}
		}

		System.out.println(ans);

	}

}
