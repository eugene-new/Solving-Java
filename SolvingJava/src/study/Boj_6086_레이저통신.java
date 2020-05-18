package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_6086_레이저통신 {
	static class Pos implements Comparable<Pos> {
		int y, x, d, cnt;

		public Pos(int y, int x, int d, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
			this.cnt = cnt;
		}

		Pos() {
		}

		@Override
		public int compareTo(Pos p) {
			return this.cnt - p.cnt;
		}
	}

	static final int dy[] = { 0, 0, 1, -1 };
	static final int dx[] = { 1, -1, 0, 0 };
	static final int d1[] = { 3, 2, 1, 0 };
	static final int d2[] = { 2, 3, 0, 1 };
	static int h, w;
	static char map[][];
	static int visit[][][];

	static int sy, sx = -1;
	static int ey, ex;;

	static void init() {
		map = new char[h][w];
		visit = new int[h][w][4];
	}

	static void bfs() {
		PriorityQueue<Pos> q = new PriorityQueue<>();
		for (int i = 0; i < 4; i++) {
			q.offer(new Pos(sy, sx, i, 0));
			visit[sy][sx][i] = 1;
		}

		while (!q.isEmpty()) {
			Pos now = q.poll();
			int y = now.y;
			int x = now.x;
			int d = now.d;
			int cnt = now.cnt;
			if (y == ey && x == ex) {
				System.out.println(cnt);
				return;
			}
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny < 0 || ny > h - 1 || nx < 0 || nx > w - 1)
				continue;
			if (visit[ny][nx][d] == 1)
				continue;
			if (map[ny][nx] == '*')
				continue;

			visit[ny][nx][d] = 1;

			q.offer(new Pos(ny, nx, d, cnt));
			// 꺾어지기
			q.offer(new Pos(ny, nx, d1[d], cnt + 1));
			q.offer(new Pos(ny, nx, d2[d], cnt + 1));

		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		init();
		for (int i = 0; i < h; i++) {
			String line = br.readLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'C') {
					if (sx == -1) {
						sy = i;
						sx = j;
					} else {
						ey = i;
						ex = j;
					}
				}
			}
		}
		bfs();
	}

}
