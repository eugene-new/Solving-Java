package workshop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class Main_bj_1941_소문난칠공주_서울_06_신유진 {
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = 0;
	static void init() {
		map = new char[5][5];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init();
		for(int i=0; i<5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		dfs(0, 0, 0);
		System.out.println(ans);
		
	}
	static int[] pick = new int[7];
	static void dfs(int depth, int now, int cnt) {
		if(depth==7) {
			//System.out.println(Arrays.toString(pick));
			if(cnt<4) return;
			// 인접한지 검사
			boolean ret = bfs(pick[0]);
			if(ret) ans++;
			return;
		}
		for(int i=now; i<25; i++) {
			pick[depth] = i;
			int x = i/5; int y = i%5;
			if(map[x][y]=='S')
				dfs(depth+1, i+1, cnt+1);
			else
				dfs(depth+1, i+1, cnt);
		}
		
	}
	static Queue<Integer> q = new LinkedList<Integer>();
	static boolean bfs(int start) {
		int[] visit = new int[25];
		int cnt = 0;
		q.offer(start);
		visit[start] = 1;
		while(!q.isEmpty()) {
			int now = q.poll();
			int x = now/5; int y = now%5;
			cnt++;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>5-1 || ny<0 ||ny>5-1) continue;
				int next = nx*5 + ny;
				if(visit[next]==1) continue;
				visit[next] = 1;
				for(int j=0; j<7; j++) {
					if (pick[j]==next) {
						q.offer(next);
					}
				}
				
			}
		}
		
		if(cnt==7) return true;
		else return false;
	}
}
