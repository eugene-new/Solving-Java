package workshop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bj_15683_감시_서울_06_신유진 {
	static class Pos{
		int num, x, y;
		public Pos(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + "]";
		}
		
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int ans = Integer.MAX_VALUE;
	static int h, w, wall;
	static int[][] map;
	static List<Pos> cam;
	static void init() {
		map = new int[h][w];
		tmp = new int[h][w];
		dir = new ArrayList<Integer>();
		cam = new ArrayList<Pos>();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]!=0 && map[i][j]!=6) {
					cam.add(new Pos(map[i][j], i, j));
				}
				if(map[i][j]==6) {
					wall++;
				}
			}
		}
		dfs(0);
		System.out.println(ans);
	}
	static List<Integer> dir; //i번째 카메라를 어떤 방향으로 돌릴지!
	static int[][][] all = {
			{},
			{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}},
			{{0, 1, 0, 1}, {1, 0, 1, 0}},
			{{1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 1}, {1, 0, 0, 1}},
			{{1, 1, 0, 1}, {1, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 1, 1}},
			{{1, 1, 1, 1}}
	};
	static int tmp[][];
	static void dfs(int depth) {
		if(depth==cam.size()) {
			memcpy();
			for(int i=0; i<cam.size(); i++) {
				Pos now = cam.get(i);
				int num = now.num; int x = now.x; int y = now.y; int d = dir.get(i);
				for(int j=0; j<4; j++) {
					if(all[num][d][j]==1) {
						run(num, x, y, j);
					}
				}
			}
			int ret = count();
			ans = Math.min(h*w-ret-wall, ans);
			return;
		}
		int num = cam.get(depth).num;
		for(int i=0; i<all[num].length; i++) {
			dir.add(i);
			dfs(depth+1);
			dir.remove(dir.size()-1);
		}
		
	}
	static void run(int num, int x, int y, int j) {
		tmp[x][y] = 1;
		while(true) {
			x += dx[j];
			y += dy[j];
			if(x<0 || x>h-1 || y<0 || y>w-1) break;
			if(map[x][y]==6) break;
			tmp[x][y] = 1;
		}
	}
	static int count() {
		int ret = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(tmp[i][j]==1) 
					ret++;
			}
		}
		return ret;
	}
	static void memcpy() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				tmp[i][j] = 0;
			}
		}
	}
}
