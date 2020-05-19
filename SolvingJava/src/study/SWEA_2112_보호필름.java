package study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름 {
	static int h, w, success;
	static int[][] origin;
	static int[][] map;
	static int ans;

	static void init() {
		ans = -1;
		map = new int[h][w];
		origin = new int[h][w];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			success = Integer.parseInt(st.nextToken());
			init();
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					origin[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if(success==1) {
				System.out.println("#"+tc+" "+0); 
				continue;
			}
			// 0개 ~ h개까지 조합
			for (int i = 0; i < h; i++) {
				memcpy();
				dfs(0, i, 0);
				if(ans!=-1) break;
			}
			if(ans!=-1)
				System.out.println("#" + tc + " "+ans);
			else
				System.out.println("#"+tc+" "+h);
		}
	}
	static void dfs(int depth, int r, int now) {
		if (depth == r) {
			//System.out.println(Arrays.toString(pick));
			boolean flag = check();
			if(flag) ans = r;
			return;
		}

		for (int type = 0; type < 2; type++) {
			for (int i = now; i < h; i++) {
				insert(i, type);
				dfs(depth + 1, r, i + 1);
				delete(i);
			}
		}
	}
	static boolean check() {
		loop: for(int j=0; j<w; j++) {
			int cnt = 1;
			int type = map[0][j];
			for(int i=1; i<h; i++) {
				if(map[i][j]==type) {
					cnt++;
					// 이미 기준치 만족 
					if(cnt>=success) continue loop;
				} else {
					cnt = 1;
					type = map[i][j];
				}
			}
			// 한 줄이라도 틀림 
			return false;
		}
		return true;
	}
	static void insert(int height, int type) {
		for (int i = 0; i < w; i++) {
			map[height][i] = type;
		}
	}

	static void delete(int height) {
		for (int i = 0; i < w; i++) {
			map[height][i] = origin[height][i];
		}
	}

	static void memcpy() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				map[i][j] = origin[i][j];
			}
		}
	}
}