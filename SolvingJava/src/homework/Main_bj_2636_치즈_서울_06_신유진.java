package homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_bj_2636_치즈_서울_06_신유진 {
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int h, w; 
	static int total;
	static int map[][];
	static void init() {
		map = new int[h][w];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken()); 
		w = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					total++;
				}
			}
		}
		int time = 0;
		int ans = total;
		while(true) {
			
			int res = bfs();
			if(res==0) break;
			ans = total;
			total -= res;
			delCheeze();
			time++;
		}
		System.out.println(time);
		System.out.println(ans);
		
		
	}
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static Queue<Pos> q = new LinkedList<Pos>();
	static Queue<Pos> del = new LinkedList<Pos>();
	static int bfs() {
		q.offer(new Pos(0, 0));
		boolean[][] visit = new boolean[h][w];
		visit[0][0] = true;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y;
			// 치즈면 del에 넣고 컨티뉴 
			if(map[x][y]==1) {
				del.offer(new Pos(x, y));
				continue;
			}
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>h-1|| ny<0 || ny>w-1) continue;
				if(visit[nx][ny]) continue;
				q.offer(new Pos(nx, ny));
				visit[nx][ny] = true;
			}
		}
		
		if(del.size()==0) return 0;
		else return del.size();
	}
	
	static void delCheeze() {
		while(!del.isEmpty()) {
			Pos now = del.poll();
			int x = now.x; int y = now.y;
			map[x][y] = 0;
		}
	}
}
