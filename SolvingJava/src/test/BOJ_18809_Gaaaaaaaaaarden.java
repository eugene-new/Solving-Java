package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18809_Gaaaaaaaaaarden {
	static class Pos{
		int x, y, t;
		char color;
		public Pos(int x, int y, int t, char color) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
			this.color = color;
		}
		
	}
	static class Ground{
		int x, y;

		public Ground(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int h, w, g, r;
	static int[][] map;
	static List<Ground> ground;
	static void init() {
		map = new int[h][w];
		ground = new ArrayList<BOJ_18809_Gaaaaaaaaaarden.Ground>();
		pickedRed = new ArrayList<Integer>();
		pickedGreen = new ArrayList<Integer>();
	}
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					ground.add(new Ground(i, j));
				}
			}
		}
		visit = new boolean[ground.size()];
		pickGreen(0, 0);
		
		System.out.println(ans);
	}
	static int dx[]= {-1, 0, 1, 0};
	static int dy[]= {0, 1, 0, -1};
	static int[][] visitGreen;
	static int[][] visitRed;
	static int ans = 0;
	
	static int bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		visitGreen = new int[h][w];
		visitRed = new int[h][w];
		boolean[][] blossom = new boolean[h][w];
		int flower = 0;
		for(int i=0; i<g; i++) {
			int idx = pickedGreen.get(i);
			int x = ground.get(idx).x;
			int y = ground.get(idx).y;
			q.offer(new Pos(x, y, 1, 'g'));
			visitGreen[x][y] = 1;
		}
		for(int i=0; i<r; i++) {
			int idx = pickedRed.get(i);
			int x = ground.get(idx).x;
			int y = ground.get(idx).y;
			q.offer(new Pos(x, y, 1, 'r'));
			visitRed[x][y] = 1;
		}
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y; int t = now.t; char color = now.color;
			if(blossom[x][y]) continue; 
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
				if(map[nx][ny]==0) continue;
				if(blossom[nx][ny]) continue;
				if(color=='g') {
					if(visitGreen[nx][ny]!=0) continue;
					if(visitRed[nx][ny]!=0) continue;
				}
				else {
					if(visitRed[nx][ny]!=0) continue;
					
					if(visitGreen[nx][ny]==t+1) {
						blossom[nx][ny] = true;
						flower++;
					}
					if(visitGreen[nx][ny]!=0) continue;
 				}
				
				q.offer(new Pos(nx, ny, t+1, color));
				if(color=='g') 
					visitGreen[nx][ny] = t+1;
				else
					visitRed[nx][ny] = t+1;
			}
		}
		
//		for(int i=0; i<h; i++) {
//			for(int j=0; j<w; j++) {
//				if(visitGreen[i][j]==visitRed[i][j]) {
//					if(visitGreen[i][j]!=0) flower++;
//				}
//			}
//		}
		return flower;
	}
	
	
	static boolean visit[];
	static List<Integer> pickedGreen;
	static List<Integer> pickedRed;
	static void pickGreen(int depth, int start) {
		if(depth==g) {
			//bfsGreen();
			pickRed(0, 0);
			return;
		}
		for(int i=start; i<ground.size(); i++) {
			visit[i] = true;
			pickedGreen.add(i);
			pickGreen(depth+1, i+1);
			visit[i] = false;
			pickedGreen.remove(pickedGreen.size()-1);
		}
	}
	
	static void pickRed(int depth, int start) {
		if(depth==r) {
//			System.out.print("green: ");
//			for(int i=0; i<g; i++) {
//				System.out.print(pickedGreen.get(i)+" ");
//			}
//			System.out.print(" | red: ");
//			for(int i=0; i<r; i++) {
//				System.out.print(pickedRed.get(i)+" ");
//			}
//			System.out.println("=======");
			
			int res = bfs();
			//System.out.println(res);
			if(res>ans) {
				ans = res;
			}
			return;
		}
		for(int i=start; i<ground.size(); i++) {
			if(visit[i]) continue;
			visit[i] = true;
			pickedRed.add(i);
			pickRed(depth+1, i);
			pickedRed.remove(pickedRed.size()-1);
			visit[i] = false;
		}
	}
	
	static void printMap() {
		for(int i=0; i<h; i++) {
			Arrays.toString(map[i]);
		}
	}
}




/*
static void bfsGreen() {
	Queue<Pos> q = new LinkedList<Pos>();
	visitGreen = new int[h][w];
	for(int i=0; i<g; i++) {
		int idx = pickedGreen.get(i);
		int x = ground.get(idx).x;
		int y = ground.get(idx).y;
		q.offer(new Pos(x, y, 1));
		visitGreen[x][y] = 1;
	}
	while(!q.isEmpty()) {
		Pos now = q.poll();
		int x = now.x; int y = now.y; int t = now.t;
		for(int i=0; i<4; i++) {
			int nx = x+dx[i]; int ny = y+dy[i];
			if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
			if(visitGreen[nx][ny]!=0) continue;
			if(map[nx][ny]==0) continue;
			q.offer(new Pos(nx, ny, t+1));
			visitGreen[nx][ny] = t+1;
		}
	}
}

static int bfsRed() {
	Queue<Pos> q = new LinkedList<Pos>();
	visitRed = new int[h][w];
	int flower = 0;
	for(int i=0; i<r; i++) {
		int idx = pickedRed.get(i);
		int x = ground.get(idx).x;
		int y = ground.get(idx).y;
		q.offer(new Pos(x, y, 1));
		visitRed[x][y] = 1;
	}
	while(!q.isEmpty()) {
		Pos now = q.poll();
		int x = now.x; int y = now.y; int t = now.t;
		if(visitGreen[x][y]==t) {
			flower++;
			continue;
		}
		for(int i=0; i<4; i++) {
			int nx = x+dx[i]; int ny = y+dy[i];
			if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
			if(visitRed[nx][ny]!=0) continue;
			if(map[nx][ny]==0) continue;
			q.offer(new Pos(nx, ny, t+1));
			visitRed[nx][ny] = t+1;
		}
	}
	return flower;
}
*/