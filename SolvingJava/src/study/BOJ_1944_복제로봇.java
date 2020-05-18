package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1944_복제로봇 {
	static class Pos {
		int x, y, idx;

		public Pos(int x, int y, int idx) {
			super();
			this.x = x;
			this.y = y;
			this.idx = idx;
		}
	}
	static class Edge implements Comparable<Edge>{
		int from, to, d;

		public Edge(int from, int to, int d) {
			super();
			this.from = from;
			this.to = to;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.d>o.d) return 1;
			return 0;
		}
		
	}
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	static int h, w, m, Sx, Sy;
	static int ans;
	static char[][] map = new char[52][52];
	static List<Edge> edge;
	static ArrayList<Pos> vertex;
	static int idx[][] = new int[52][52];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = w = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
		edge = new ArrayList<Edge>();
		vertex = new ArrayList<Pos>();
		for(int i=0; i<h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		int cnt = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]=='S') {
					Sx = i; Sy = j;
					idx[i][j] = 0;
					vertex.add(new Pos(i, j, idx[i][j]));
				}
				if(map[i][j]=='K') {
					idx[i][j] = ++cnt;
					vertex.add(new Pos(i, j, idx[i][j]));
				}
			}
		}
		
		// 각 정점마다 간선 넣어주기 
		loop : for(int i=0; i<vertex.size(); i++) {
			int cn = 0;
			for(int j=i+1; j<vertex.size(); j++) {
				int res = bfs(vertex.get(i), vertex.get(j));
				if(i==0) {
					cnt+=res;
				}
			}
			if(i==0 && cn!=m) {
				ans = -1;
				break loop;
			}
		}
		kruskal();
		System.out.println(ans);
	}
	
	static int bfs(Pos start, Pos end) {
		
		int sx = start.x; int sy = start.y; int ex = end.x; int ey = end.y;
		int sdx = idx[sx][sy]; int edx = idx[ex][ey];
		Queue<Pos> q = new LinkedList<Pos>();
		boolean[][] visit = new boolean[h][w];
		visit[sx][sy] = true;
		q.offer(new Pos(sx, sy, 0));
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y; int d = now.idx;
			if(x==ex && y==ey) {
				edge.add(new Edge(sdx, edx, d));
				return 1;
			}
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>h-1 || ny<0 || nx>w-1) continue;
				if(visit[nx][ny]) continue;
				if(map[nx][ny]=='1') continue;
				visit[nx][ny] = true;
				q.offer(new Pos(nx, ny, d+1));
			}
		}
		return 0;
	}
	static int parent[] = new int[m+5];
	static void kruskal() {
		for(int i=0; i<vertex.size(); i++) parent[i] = i;
		Collections.sort(edge);
		int cnt = 0;
		for(Edge e : edge) {
			int a = e.from; int b = e.to; int d = e.d;
			if(!sameParent(a, b)) {
				union(a, b);
				ans += d;
				cnt++;
			}
		}
	}
	
	static boolean sameParent(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a==b) return true;
		else return false;
	}
	static int findSet(int a) {
		if(parent[a]==a) return a;
		else return parent[a] = findSet(parent[a]);
	}
	static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		parent[b] = a;
	}
	
}
