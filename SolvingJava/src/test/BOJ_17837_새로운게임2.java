package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
// 새로운게임2 
public class BOJ_17837_새로운게임2 {
	static class Pos {
		int x, y, d, mh;

		public Pos(int x, int y, int d, int mh) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.mh = mh;
		}

		public Pos() {
		}

	}

	static int h, w, n;
	static List<Pos> mal = new ArrayList<BOJ_17837_새로운게임2.Pos>();
	static List[][] map; // 배치
	static int[][] color; // 각 칸의 색깔
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static void init() {
		map = new ArrayList[h][w];
		color = new int[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i][j] = new ArrayList<Integer>();
			}
		}

		mal.clear();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		h = w = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		init();
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());
			mal.add(new Pos(x, y, realDir(d), 0));
			map[x][y].add(i);
		}
		int turn = 1;
		loop: while (turn <= 1000) {
			// k개 말
			for (int i = 0; i < n; i++) {
				Pos now = mal.get(i);
				int x = now.x;
				int y = now.y;
				int d = now.d;
				int mh = now.mh;
				int nx = x + dx[d];
				int ny = y + dy[d];
				int flag = 0;
				// 경계 밖 or 블루
				if (nx < 0 || nx > h - 1 || ny < 0 || ny > w - 1 || color[nx][ny] == 2)
					flag = blue(i, x, y, d, mh);
				// 흰 칸
				else if (color[nx][ny] == 0)
					flag = white(x, y, mh, nx, ny);
				// 레드
				else
					flag = red(x, y, mh, nx, ny);
//				System.out.print(flag+" ");
				if (flag >= 4)
					break loop;
			}
			turn++;
		}
		if (turn > 1000)
			System.out.println(-1);
		else
			System.out.println(turn);
	}

	static int blue(int name, int x, int y, int d, int mh) {
		d = (d + 2) % 4;
		int nx = x + dx[d];
		int ny = y + dy[d];
		newMal(name, x, y, d, mh);
		if (nx < 0 || nx > h - 1 || ny < 0 || ny > w - 1) {
			return map[x][y].size();
		}
		int col = color[nx][ny];
		if (col == 2) {
			return map[x][y].size();
		} else if (col == 1) {
			return red(x, y, mh, nx, ny);
		} else if (col == 0) {
			return white(x, y, mh, nx, ny);
		}
		return map[nx][ny].size();
	}

	static int white(int x, int y, int mh, int nx, int ny) {
		moveAll(x, y, mh, nx, ny);
		delAll(x, y, mh);
		return map[nx][ny].size();
	}

	static int red(int x, int y, int mh, int nx, int ny) {
		// 반대로
		int nh = map[nx][ny].size();
		for (int i = map[x][y].size() - 1; i >= mh; i--) {
			int name = (int) map[x][y].get(i);
			Pos now = mal.get(name);
			map[nx][ny].add(name);
			newMal(name, nx, ny, now.d, nh);
			nh++;
		}
		delAll(x, y, mh);
		return map[nx][ny].size();
	}

	static void moveAll(int x, int y, int mh, int nx, int ny) {
		if (color[nx][ny] == 0) {
			int nh = map[nx][ny].size();
			for (int i = mh; i < map[x][y].size(); i++) {
				int name = (int) map[x][y].get(i);
				Pos now = mal.get(name);
				map[nx][ny].add(name); // 넣기
				newMal(name, nx, ny, now.d, nh);
				nh++;
			}
		}
	}

	static void delAll(int x, int y, int mh) {
		// 나부터 뒤에있는 애들 다 지우기
		int size = map[x][y].size();
		for (int i = mh; i < size; i++) {
			map[x][y].remove(map[x][y].size() - 1);
		}
	}

	static void newMal(int name, int x, int y, int d, int mh) {
		mal.get(name).x = x;
		mal.get(name).y = y;
		mal.get(name).d = d;
		mal.get(name).mh = mh;
	}

	static int realDir(int d) {
		if (d == 1)
			return 1;
		else if (d == 2)
			return 3;
		else if (d == 3)
			return 0;
		else
			return 2;
	}
}
