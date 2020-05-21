package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_3860_할로윈묘지_F {
	static class Pos {
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static class Hole {
		Pos start, end;
		int dist;

		public Hole(Pos start, Pos end, int dist) {
			super();
			this.start = start;
			this.end = end;
			this.dist = dist;
		}

	}

	static final int INF = Integer.MAX_VALUE;
	static final int dx[] = { -1, 0, 1, 0 };
	static final int dy[] = { 0, 1, 0, -1 };
	static int h, w, g, e;
	static int total, flag;
	static int map[][];
	static int dist[][];
	static List<Hole> hole;
	static List<ArrayList<Pos>> adj;

	static void init() {
		hole = new ArrayList<Hole>();
		adj = new ArrayList<ArrayList<Pos>>();
		for (int i = 0; i < h * w; i++) {
			adj.add(new ArrayList<Pos>());
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w==0 && h==0) break;
			init();
			
			g = Integer.parseInt(br.readLine());
			for(int i=0; i<g; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1; 
			}
			
			e = Integer.parseInt(br.readLine());
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				map[x1][y1] = i;
				hole.add(new Hole(new Pos(x1, y1), new Pos(x2, y2), t));
			}
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 || map[i][j] == -1)
						continue;
					// 도착점 제외
					if (i == h - 1 && j == w - 1)
						continue;
					total++;
					for (int d = 0; d < 4; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if (ny<0 || ny>h - 1 || nx<0 || nx>w - 1)
							continue;
						if (map[ny][nx] == 1)
							continue;
						hole.add(new Hole(new Pos(i, j), new Pos(ny, nx), 1));
					}
				}
			}
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					dist[i][j] = INF;
				}
			}
			dist[0][0] = 0;
			
			bellman_ford();

			if (flag==1)
				System.out.println("Never");
			else if (dist[h - 1][w - 1] == INF)
				System.out.println("Impossible");
			else
				System.out.println(dist[h-1][w-1]);
		}
	}

	static void bellman_ford() {
		for (int i = 0; i < total - 1; i++) {
			for (int j = 0; j < hole.size(); j++) {
				if (dist[hole.get(j).start.y][hole.get(j).start.x] == INF)
					continue;
				if (dist[hole.get(j).end.y][hole.get(j).end.x] > dist[hole.get(j).start.y][hole.get(j).start.x]
						+ hole.get(j).dist) {
					dist[hole.get(j).end.y][hole.get(j).end.x] = dist[hole.get(j).start.y][hole.get(j).start.x]
							+ hole.get(j).dist;
				}
			}

		}

		for (int j = 0; j < hole.size(); j++) {
			if (dist[hole.get(j).start.y][hole.get(j).start.x] == INF)
				continue;
			if (dist[hole.get(j).end.y][hole.get(j).end.x] > dist[hole.get(j).start.y][hole.get(j).start.x]
					+ hole.get(j).dist) {
				flag = 1;
			}
		}
	}
}
