package homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_2468_안전영역_서울_06_신유진 {
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int h, w;
	static int[][] map;
	static int[][] flood;
	static int[][] visit;
	static int Min = Integer.MAX_VALUE;
	static int Max = Integer.MIN_VALUE;
	static int ans = 0;
	static void init() {
		map =  new int[h][w];
		flood = new int[h][w];
		visit = new int[h][w];
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		h = w = Integer.parseInt(br.readLine());
		init();
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				Min = Math.min(map[i][j], Min);
				Max = Math.max(map[i][j], Max);
			}
		}
		
		for(int rain=0; rain<=Max; rain++) {
			memset();
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j]<=rain && flood[i][j]==0) {
						bfs(i, j, rain);
					}
				}
			}
			
			int safe = 0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(flood[i][j]==0 && visit[i][j]==0) {
						safe++;
						dfs(i, j);
					}
				}
			}
			ans = Math.max(safe, ans);
			// 다 잠기면 컷팅
			if(safe==0) break;
		}
		
		System.out.println(ans);
	}
	static void bfs(int sx, int sy, int rain) {
		Queue<Pos> q = new LinkedList<Main_bj_2468_안전영역_서울_06_신유진.Pos>();
		q.offer(new Pos(sx, sy));
		flood[sx][sy] = 1;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y;
			
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
				if(flood[nx][ny]==1) continue;
				if(map[nx][ny]>rain) continue;
				q.offer(new Pos(nx, ny));
				flood[nx][ny] = 1;
			}
		}
	}
	static void dfs(int x, int y) {
		visit[x][y] = 1;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i]; int ny = y+dy[i];
			if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
			if(visit[nx][ny]==1) continue;
			if(flood[nx][ny]==1) continue;
			dfs(nx, ny);
		}
	}
	static void memset() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				flood[i][j] = 0;
				visit[i][j] = 0;
			}
		}
			
	}
}
