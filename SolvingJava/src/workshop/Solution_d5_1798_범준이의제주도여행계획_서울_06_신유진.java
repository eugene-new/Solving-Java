package workshop;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_d5_1798_범준이의제주도여행계획_서울_06_신유진 {
	static class Point {
		int idx, play, satisfy;

		public Point(int idx, int play, int satisfy) {
			super();
			this.idx = idx;
			this.play = play;
			this.satisfy = satisfy;
		}

	}
	static int n, m;
	static int[][] weight = new int[40][40];
	static List<Integer> hotel = new ArrayList<Integer>();
	static List<Point> point = new ArrayList<Point>();
	static int airport = 0;
	static int ans = 0;
	static int total = 0;
	static void init() {
		hotel.clear();
		point.clear();
		book = new int[m-1];
		ans = 0;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			init();
			for (int i = 0; i < n - 1; i++) {
				st = new StringTokenizer(br.readLine());

				for (int j = i + 1; j < n; j++) {
					int w = Integer.parseInt(st.nextToken());
					weight[i][j] = w;
					weight[j][i] = w;
				}
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				if (c == 'P') {
					int playtime = Integer.parseInt(st.nextToken());
					int sati = Integer.parseInt(st.nextToken());
					point.add(new Point(i, playtime, sati));
				} else if (c == 'H') {
					hotel.add(i);
				} else if (c == 'A') {
					airport = i;
				}
			}
			
			permu(0);
			
			
			if(ans==0)
				System.out.println("#"+tc+" "+ans);
			else {
				System.out.print("#"+tc+" "+ans+" ");
				for(int i=0; i<total; i++) {
					System.out.print((ansPath[i]+1)+" ");
				}
				System.out.println();
			}
		}
	}
	
	static int[] book;
	static int[] visit;
	static int[] path = new int[35];
	static int[] ansPath = new int[35];
	
	// 호텔 고르기 
	static void permu(int depth) {
		if(depth==m-1) {
			//System.out.println(Arrays.toString(book));
			visit = new int[n];
			dfs(0, airport, 0, 0, 0);
			
			return;
		}
		for(int i=0; i<hotel.size(); i++) {
			book[depth] = hotel.get(i);
			permu(depth+1);
		}
	}
	
	static void dfs(int day, int now, int time, int score, int cnt) {
		
		//if(time>540) return;
		if(day==m) {
			if(ans<score) {
				ans = score;
				ansPath = path.clone();
				total = cnt;
			}
			
			return;
		}
		
		// 관광지 방문
		for(int i=0; i<point.size(); i++) {
			if(visit[i]==0) {
				
				int next = point.get(i).idx;
				int move = weight[now][next];
				int play = point.get(i).play;
				int sati = point.get(i).satisfy;
				
				if(time+move+play<=540-10) {
					visit[i] = 1;
					path[cnt] = next;
					dfs(day, next, time+move+play, score+sati, cnt+1);
					visit[i] = 0;
				}
				
			}
		}
		// 호텔 이동
		if(day<m-1) {
			int next = book[day];
			int move = weight[now][next];
			if(time+move<=540) {
				path[cnt] = next;
				dfs(day+1, next, 0, score, cnt+1);
			}
			else return;
		}
		// 마지막날이면 공항으로 이동 
		else {
			int next = airport;
			int move = weight[now][next];
			if(time+move<=540) {
				path[cnt] = next;
				dfs(day+1, next, time+move, score, cnt+1);
			}
		}
		
		
	}
}
