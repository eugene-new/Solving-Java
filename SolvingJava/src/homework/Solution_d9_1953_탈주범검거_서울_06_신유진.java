package homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_d9_1953_탈주범검거_서울_06_신유진 {
	static class Pos{
		int x, y, t;
		public Pos(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
		}
	};
	
	
	static int[][]  go = {
			{0,0,0,0},
			{1,1,1,1},
			{1,0,1,0},
			{0,1,0,1},
			{1,1,0,0},
			{0,1,1,0},
			{0,0,1,1},
			{1,0,0,1}
		};
		static int[][]  check = {
			{ 0, 0, 0, 0 },
			{ 1, 1, 1, 1 },
			{ 1, 0, 1, 0 },
			{0,1,0,1},
			{0,0,1,1},
			{1,0,0,1},
			{1,1,0,0},
			{0,1,1,0}
		};
	static int ans;
	static int h, w;
	static int r, c, l;
	static int[][] map = new int[55][55];
	static boolean[][] visit = new boolean[55][55];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static void init() {
		ans = 0;
		memset();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc<= T; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			bfs();
			System.out.println("#"+tc+" "+ans);
			init();
		}
		bfs();
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		visit[r][c] = true;
		q.offer(new Pos(r, c, 1));

		while (!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y; int t = now.t;
			ans++;
			if (t == l) continue;
			for (int i = 0; i < 4; i++) {
				if (go[map[x][y]][i] == 0) continue;
				int nx = x + dx[i]; int ny = y + dy[i]; 
				if (nx<0 || nx>h - 1 || ny<0 || ny>w - 1) continue;
				if (visit[nx][ny]) continue;
				if (map[nx][ny] == 0) continue;
				if (check[map[nx][ny]][i]==1) {
					visit[nx][ny] = true;
					q.offer(new Pos(nx, ny, t + 1));
				}
			}
		}
	}
	static void memset() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				visit[i][j] = false;
			}
		}
	}
}
