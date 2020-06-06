package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2 {
	static class Info {
		int bx, by, rx, ry;
		boolean red, blue;

		public Info(int bx, int by, int rx, int ry, boolean red, boolean blue) {
			super();
			this.bx = bx;
			this.by = by;
			this.rx = rx;
			this.ry = ry;
			this.red = red;
			this.blue = blue;
		}

	}

	static int h, w;
	static char[][] map;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int ans;
	static void init() {
		map = new char[h][w];
		ans = Integer.MAX_VALUE;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("11".toString());
//		////////// 지우기 /////
//		System.setIn(new FileInputStream("res/input.txt"));
//		/////
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		init();
		int bx = 0;
		int by = 0;
		int rx = 0;
		int ry = 0;
		for (int i = 0; i < h; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 'B') {
					bx = i;
					by = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'R') {
					rx = i;
					ry = j;
					map[i][j] = '.';
				}
			}
		}

		dfs(1, -1, bx, by, rx, ry);
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	static int[] pick = new int[10];
	static void dfs(int depth, int pDir, int bx, int by, int rx, int ry) {
//		System.out.println(Arrays.toString(pick));
		if(depth>=ans) return;
		if (depth == 11) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (depth!=1 &&(i == pDir || (pDir + 2) % 4 == i))
				continue;
			Info result;
			// 실패가 아니라면 갱신
			// 상
			if (i == 0) {
				if (bx <= rx) {
					result = blueFirst(bx, by, rx, ry, i);
				} else {
					result = redFirst(bx, by, rx, ry, i);
				}
			}
			// 우
			else if (i == 1) {
				if(by>=ry) {
					result = blueFirst(bx, by, rx, ry, i);
				} else {
					result = redFirst(bx, by, rx, ry, i);
				}
			}
			// 하
			else if (i == 2) {
				if (bx >= rx) {
					result = blueFirst(bx, by, rx, ry, i);
				} else {
					result = redFirst(bx, by, rx, ry, i);
				}
			}
			// 좌
			else {
				if(by<=ry) {
					result = blueFirst(bx, by, rx, ry, i);
				} else {
					result = redFirst(bx, by, rx, ry, i);
				}
			}
			
			if(result.blue) continue;
			else if(result.red) {
				if(ans>depth) {
					ans = depth;
				}
				return;
			}
			// 재귀
			if(!result.blue && !result.red) {
				int nbx = result.bx; int nby = result.by; int nrx = result.rx; int nry = result.ry;
				dfs(depth+1, i, nbx, nby, nrx, nry);
			}
			
		}
	}

	static Info redFirst(int bx, int by, int rx, int ry, int dir) {
		boolean blue = false;
		boolean red = false;
		// 빨간공 쭉
		while (true) {
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			if (map[nx][ny] == '#')
				break;
			if (map[nx][ny] == 'O') {
				red = true;
				break;
			}
			if (map[nx][ny] == '.') {
				rx = nx;
				ry = ny;
			}
		}
		// 파랑공
		// 앞에 빨강공이면 벽과 동일
		while (true) {
			int nx = bx + dx[dir];
			int ny = by + dy[dir];
			if (map[nx][ny] == '#')
				break;
			if (map[nx][ny] == 'O') {
				blue = true;
				break;
			}
			if (!red &&(nx == rx && ny == ry))
				break;
			if (map[nx][ny] == '.') {
				bx = nx;
				by = ny;
			}
		}
		// 파랑이가 빠지거나 동시에 빠지면 실패 => 이동 x
		return new Info(bx, by, rx, ry, red, blue);
	}

	static Info blueFirst(int bx, int by, int rx, int ry, int dir) {
		boolean blue = false;
		boolean red = false;
		// 파랑 쭉
		while (true) {
			int nx = bx + dx[dir];
			int ny = by + dy[dir];
			if (map[nx][ny] == '#')
				break;
			if (map[nx][ny] == 'O') {
				blue = true;
				break;
			}
			if (map[nx][ny] == '.') {
				bx = nx;
				by = ny;
			}
		}
		// 빨강 공
		// 앞에 파랑 공이면 벽과 동일
		while (true) {
			int nx = rx + dx[dir];
			int ny = ry + dy[dir];
			if (map[nx][ny] == '#')
				break;
			if (map[nx][ny] == 'O') {
				red = true;
				break;
			}
			if (nx == bx && ny == by)
				break;
			if (map[nx][ny] == '.') {
				rx = nx;
				ry = ny;
			}
		}
		// 파랑이가 빠지거나 동시에 빠지면 실패 => 이동 x
		return new Info(bx, by, rx, ry, red, blue);
	}


}
