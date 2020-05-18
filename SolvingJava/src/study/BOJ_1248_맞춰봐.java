package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BOJ_1248_맞춰봐 {
	public static int n;
	public static char[][] input = new char[11][11];
	public static int pick[] = new int[11];
	public static boolean flag = false;
	public static void dfs(int depth) {
		if (depth == n) {
			for (int i = 0; i < n; i++) {
				System.out.print(pick[i]+" ");
			}
			System.exit(0);
			return;
		}
		
		for (int i = -10; i <= 10; i++) {
			pick[depth] = i;
			if (check(depth)) {
				dfs(depth + 1);
			}

		}
	}

	public static boolean check(int now) {
		int sum = 0;
		for (int i = now; i >= 0; i--) {
			sum = sum + pick[i];
			if(input[i][now]=='+' && sum<=0) return false;
			if(input[i][now]=='-' && sum>=0) return false;
			if(input[i][now]=='0' && sum!=0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		int idx = 0;
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				input[i][j] = line[idx];
				idx++;
			}
		}
		dfs(0);
	}

	
}
