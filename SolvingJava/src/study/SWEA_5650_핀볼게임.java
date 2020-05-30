package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

class SWEA_5650_핀볼게임 {
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int[][] map;
	static int h, w;
	static int ans;
	static int[][] changeDir = {
			{2, 3, 1, 0},
			{1, 3, 0, 2},
			{3, 2, 0, 1},
			{2, 0, 3, 1},
			{2, 3, 0, 1}
	};
	static List<List<Pos>> worm;
	static void init() {
		map = new int[h][w];
		worm = new ArrayList<List<Pos>>();
		for(int i=0; i<5; i++) {
			worm.add(new ArrayList<SWEA_5650_핀볼게임.Pos>());
		}
		
		ans = 0;
	}
	public static void main(String args[]) throws Exception {
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			h = w = Integer.parseInt(br.readLine());
			init();
			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(6<=map[i][j]&& map[i][j]<=10) {
						makeWorm(i, j, map[i][j]);
					}
				}
			}
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j]!=0) continue; 
					for(int d=0; d<4; d++) {
						simulation(i, j, d);
					}
				}
			}
			
			System.out.println("#"+test_case+" "+ans);
		}
	}
	static int[] dx = {-1, 0, 1, 0}; static int[] dy = {0, 1, 0, -1};
	static void simulation(int sx, int sy, int d) {
		int x = sx; int y = sy;
		int score = 0;
		while(true) {
			x += dx[d]; y += dy[d];
			// 경계 밖 
			if(x<0 || x>h-1 || y<0 || y>w-1) {
				d = (d+2)%4;
				score += 1;
				continue;
			}
			int num = map[x][y];
			// 블랙홀
			if(num==-1) break;
			// 출발위치 
			else if(x==sx && y==sy) break;
			// 웜홀 
			else if(6<=num && num<=10) {
				Pos next = changeWorm(x, y, num);
				x = next.x; y = next.y;
			}
			// 블록 
			else if(1<=num && num<=5) {
				d = changeDir[num-1][d];
				score += 1;
			}
			// 빈칸
		}
		ans = Math.max(ans, score);
	}
	static Pos changeWorm(int x, int y, int num) {
		num -= 6;
		Pos first = worm.get(num).get(0);
		Pos second = worm.get(num).get(1);
		// 첫 번째 => 두 번째 리턴 
		if(x==first.x && y==first.y) return second;
		// 두 번
		else return first;
	}
	static void makeWorm(int x, int y, int num) {
		num -= 6;
		worm.get(num).add(new Pos(x, y));
	}
}