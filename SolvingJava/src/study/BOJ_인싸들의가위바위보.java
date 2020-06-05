package study;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_인싸들의가위바위보 {
	static int n, k;
	static int[][] map;
	static int[][] motion;
	static void init() {
		map = new int[n][n];
		win = new int[3];
		visit = new boolean[n];
		cnt = new int[3];
		motion = new int[3][20];
	}
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<20; i++) {
			motion[1][i] = Integer.parseInt(st.nextToken())-1;
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<20; i++) {
			motion[2][i] = Integer.parseInt(st.nextToken())-1;
		}
		permu(0);
		System.out.println(ans);
	}
	
	
	static boolean[] visit;
	static int ans = 0;
	
	static void permu(int depth) {
		if(ans==1) return;
		if(depth==n) {
			int res = game();
			if(res==1) {
				ans = 1;
			}
			
 			return;
		}
		for(int i=0; i<n; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			motion[0][depth] = i;
			permu(depth+1);
			visit[i] = false;
		}
	}
	
	static int[] win = new int[3];
	static int[] cnt = new int[3];
	static int game() {
		Arrays.fill(win, 0);
		Arrays.fill(cnt, 0);
		int A = 0; int B = 1;
		while(true) {
			// 승자 나옴 
			int ch = check();
			if(ch!=-1) {
				if(ch==0) {
					return 1;
				}
				else return 0;
			}
			// 지우가 n개 손동작 다 냄
			if(cnt[0]>=n) return 0;
			
			
			
			// 게임
			int m1 = motion[A][cnt[A]++];
			int m2 = motion[B][cnt[B]++];
			int winner;
			if(map[m1][m2]==2) {
				winner = A;
			}
			else if(map[m1][m2]==0){
				winner = B;
			}
			else {
				winner = Math.max(A, B);
			}
			win[winner]++;
//			System.out.println(3 - (A+B));
			int sum = A+B;
			A = winner; 
			B = 3-sum;
			
		}
	}
	
	static int check() {
		int winner = -1;
		for(int i=0; i<3; i++) {
			if(win[i]>=k) {
				winner = i;
				break;
			}
		}
		return winner;
	}
//	// 0 지우, 1 경희, 2 민호 
//	static void dfs(int A, int B) {
//		if(cnt[A]>=20 || cnt[B]>=20) return;
//		if(ans!=-1) return;
//		check();
//		// 지우 vs B
//		if(A==0 || B==0) {
//			for(int i=0; i<n; i++) {
//				if(visit[i]) continue;
//				visit[i] = true;
//				int winner = -1;
//				if(A==0) winner = whoWon(A, B, i, motion[B][cnt[B]]);
//				else winner = whoWon(A, B, motion[A][cnt[A]], i);
//				win[winner]++;
//				cnt[A]++; cnt[B]++;
//				int next = 3 - (A+B);
//				dfs(winner, next);
//				win[winner]--;
//				visit[i] = false;
//				cnt[A]--; cnt[B]--;
//			}
//		}
//		// 지우 없는 판 
//		else {
//			int winner = whoWon(A, B, motion[A][cnt[A]], motion[B][cnt[B]]);
//			win[winner]++;
//			cnt[A]++; cnt[B]++;
//			int next = 3 - (A+B);
//			dfs(winner, next);
//		}
//	}

	
}
