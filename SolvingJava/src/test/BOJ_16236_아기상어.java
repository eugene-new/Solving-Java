package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
// 아기상어 
public class BOJ_16236_아기상어 {
	static class Pos{
		int x, y, d;

		public Pos(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", d=" + d + "]";
		}
		
	}
	static int h, w;
	static int[][] map;
	static int sx, sy, size, cnt, time;
	static PriorityQueue<Pos> fish = new PriorityQueue<BOJ_16236_아기상어.Pos>(new Comparator<Pos>() {

		@Override
		public int compare(Pos o1, Pos o2) {
			if(o1.x>o2.x) return 1;
			else if(o1.x==o2.x) {
				return o1.y-o2.y;
			}
			else return -1;
		}
		
	});
	static void init() {
		map = new int[h][w];
		size = 2;
		cnt = 0;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		h = w = Integer.parseInt(br.readLine());
		init();
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==9) {
					map[i][j] = 0;
					sx = i; sy = j;
				}
			}
		}
		// 먹었으면 계속 
		while(true) {
			bfs();
			if(fish.size()==0) break;
			eat();
		}
		System.out.println(time);
	}
	static int[] dx = {-1, 0, 1, 0}; static int[] dy = {0, 1, 0, -1};
	static void bfs() {
		int Min = Integer.MAX_VALUE;
		Queue<Pos> q = new LinkedList<BOJ_16236_아기상어.Pos>();
		boolean[][] visit = new boolean[h][w];
		q.offer(new Pos(sx, sy, 0));
		visit[sx][sy] = true;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y; int d = now.d;
			if(d>Min) continue;
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
				if(visit[nx][ny]) continue;
				visit[nx][ny] = true;
				int num = map[nx][ny];
				// 못 지나감
				if(num>size) continue;
				// 지나감
				// 먹을 수 있음
				if(num!=0 && num<size) {
					Min = d;
					fish.offer(new Pos(nx, ny, d+1));
				}
				// 못 먹음 
				// 빈칸 
				
				
				q.offer(new Pos(nx, ny, d+1));
			}
		}
	}
	static void eat() {
		Pos target = fish.poll();
		fish.clear();
		int x = target.x; int y = target.y; int d = target.d;
//		System.out.println(map[x][y]+" "+x+", "+y);
		// 물고기자리로 이동
		sx = x; sy = y;
		map[x][y] = 0;
		// 크기 및 cnt 
		cnt++;
		if(size==cnt) {
			cnt = 0;
			size++;
		}
		// 시간 갱신 
		time += d;
	}
}
