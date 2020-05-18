package study;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_4727_견우와직녀 {
	static int h, w, m;
	static int[][] map = new int[10][110];

	static void init() {
		ans = Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			h = w = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			init();
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 깃 커밋용 
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}

	static class Pos {
		int x, y, t, made;

		public Pos(int x, int y, int t, int made) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
			this.made = made;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", t=" + t + "]";
		}

	}

	static Queue<Pos> q = new LinkedList<Pos>();
	static int[][][] visit = new int[10][10][2];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int ans = Integer.MAX_VALUE;

	static void bfs() {
		int sx = 0;
		int sy = 0;
		q.add(new Pos(sx, sy, 0, 0));
		memset(visit);
		visit[sx][sy][0] = 0;
		visit[sx][sy][1] = 0;
		while (!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x;
			int y = now.y;
			int t = now.t;
			int made = now.made;
			// System.out.println(x+", "+y);
			if (x == h - 1 && y == w - 1) {
				ans = Math.min(ans, t);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx > h - 1 || ny < 0 || ny > w - 1)
					continue;
				// 건너기 가능
				if (map[nx][ny] >= 1) {
					// 길인 경우
					if (map[nx][ny] == 1) {
						if (visit[nx][ny][made] == -1 || visit[nx][ny][made] >= t + 1) {
							q.offer(new Pos(nx, ny, t + 1, made));
							visit[nx][ny][made] = t + 1;
							continue;
						}
					}
					// 오작교인 경우
					else {
						if (map[x][y] <= 1) {
							if (visit[nx][ny][made] == -1 || visit[nx][ny][made] >= t + 1) {
								int nt = t + (map[nx][ny] - (t % map[nx][ny]));
								q.offer(new Pos(nx, ny, nt, made));
								visit[nx][ny][made] = t + 1;
								continue;
							}
						}
					}
				}
				// 절벽
				else {
					// 임시 오작교 만들 수 있음 : 아직 만든 적 없고, 이전 블록이 오작교가 아니었음
					if (made == 0 && map[x][y] <= 1) {
						if (visit[nx][ny][1] == -1 || visit[nx][ny][1] >= t + 1) {
							// 교차하는가
							int garo = 0;
							int sero = 0;
							for (int c = 0; c < 4; c++) {
								int cx = nx + dx[c];
								int cy = ny + dy[c];
								if (cx < 0 || cx > h - 1 || cy < 0 || cy > w - 1)
									continue;
								if (map[cx][cy] == 0) {
									if (i % 2 == 0)
										sero++;
									else
										garo++;
								}
							}
							if (garo > 0 && sero > 0)
								continue; // 건널 수 없
							int nt = t + (m - (t % m));
							q.offer(new Pos(nx, ny, nt, 1));
							visit[nx][ny][1] = t + 1;
							continue;
						}
					}
				}
			}
		}

	}

	static void memset(int[][][] arr) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				for (int k = 0; k < 2; k++) {
					visit[i][j][k] = -1;
				}
			}
		}
	}
}
