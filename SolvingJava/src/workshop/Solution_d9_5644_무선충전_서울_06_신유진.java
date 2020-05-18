package workshop;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_d9_5644_무선충전_서울_06_신유진 {
	static class BC {
		int x, y, c, p;

		public BC(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

	}

	static int h = 10;
	static int w = 10;
	static int ax, ay, bx, by;
	static int time, n;

	static void init() {
		bc.clear();
		rec[0][0] = 0;
		rec[0][1] = 0;
		ax = ay = 0;
		bx = by = 9;
	}

	static List<BC> bc = new ArrayList<Solution_d9_5644_무선충전_서울_06_신유진.BC>();
	static int dx[] = { 0, -1, 0, 1, 0 };
	static int dy[] = { 0, 0, 1, 0, -1 };
	static int[][] rec = new int[2][102];

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			time = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			init();
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= time; j++) {
					rec[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bc.add(new BC(x, y, c, p));
			}
			int ans = 0;
			List<Integer> a = new ArrayList<Integer>();
			List<Integer> b = new ArrayList<Integer>();
			for (int t = 0; t <= time; t++) {
				ax += dx[rec[0][t]];
				ay += dy[rec[0][t]];
				bx += dx[rec[1][t]];
				by += dy[rec[1][t]];
				for (int i = 0; i < n; i++) {
					int x = bc.get(i).x;
					int y = bc.get(i).y;
					int c = bc.get(i).c;
					if (Math.abs(ax - x) + Math.abs(ay - y) <= c) {
						a.add(i);
					}
					if (Math.abs(bx - x) + Math.abs(by - y) <= c) {
						b.add(i);
					}
				}
				// A만 이용
				if(b.size()==0 && a.size()>0) {
					int Max = 0;
					for(int i=0; i<a.size(); i++) {
						int now = bc.get(a.get(i)).p;
						Max = Math.max(Max, now);
					}
					ans += Max;
				}
				// B만 이용 
				else if(a.size()==0 && b.size()>0) {
					int Max = 0;
					for(int i=0; i<b.size(); i++) {
						int now = bc.get(b.get(i)).p;
						Max = Math.max(Max, now);
					}
					ans += Max;
				}
				// 둘 다 이용 
				else {
					int Max = 0;
					for(int i=0; i<a.size(); i++) {
						for(int j=0; j<b.size(); j++) {
							if(a.get(i)==b.get(j)) {
								Max = Math.max(Max, bc.get(a.get(i)).p);
							}
							else {
								Max = Math.max(Max, bc.get(a.get(i)).p+bc.get(b.get(j)).p);
							}
						}
					}
					ans +=Max;
				}
				a.clear();
				b.clear();
			}
			
			System.out.println("#" + tc + " "+ans);
		}

	}
	
}
