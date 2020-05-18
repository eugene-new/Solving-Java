package study;

import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1405_미친로봇 {
	static int n;
	static boolean visit[][] = new boolean[30][30];
	static double prob[] = new double[4];
	static double ans = 0.0;
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, -1};
	static void dfs(int depth, int x, int y, double p) {
		if(depth==n) {
			ans += p;
			return;
		}
		
		visit[x][y] = true;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(visit[nx][ny]) continue;
			dfs(depth+1, nx, ny, p * prob[i]);
		}
		visit[x][y] = false;	
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for(int i=0; i<4; i++) {
			prob[i] = sc.nextDouble()*0.01;
		}
		dfs(0, 15, 15, 1.0);
		System.out.println(ans);
	}

}
