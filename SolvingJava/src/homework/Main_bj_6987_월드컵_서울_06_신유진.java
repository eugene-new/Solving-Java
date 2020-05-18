package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_bj_6987_월드컵_서울_06_신유진 {
	static int[] group1 = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4 };
	static int[] group2 = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };
	static int[][] input = new int[6][3];
	static int[][] res = new int[6][3];
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int tc = 1; tc <= 4; tc++) {
			ans = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0);
			System.out.print(ans + " ");

		}
	}

	static void dfs(int depth) {
		if (ans == 1)
			return;
		if (depth == 15) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					if (input[i][j] != res[i][j])
						return;
				}
			}
			ans = 1;
			return;
		}

		int c1 = group1[depth];
		int c2 = group2[depth];

		// 승 패
		res[c1][0]++;
		res[c2][2]++;
		dfs(depth + 1);
		res[c1][0]--;
		res[c2][2]--;

		// 무 무
		res[c1][1]++;
		res[c2][1]++;
		dfs(depth + 1);
		res[c1][1]--;
		res[c2][1]--;

		// 패 승
		res[c1][2]++;
		res[c2][0]++;
		dfs(depth + 1);
		res[c1][2]--;
		res[c2][0]--;

	}
}
