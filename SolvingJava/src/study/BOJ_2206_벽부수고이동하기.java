package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206_벽부수고이동하기 {
	static class Pos{
		int y, x, t, c;

		public Pos(int y, int x, int t, int c) {
			super();
			this.y = y;
			this.x = x;
			this.t = t;
			this.c = c;
		}
		public Pos() {}
	}
	static final int INF = 1000*1000+5;
	static int dy[]= {-1, 0, 1, 0};
	static int dx[]= {0, 1, 0, -1};
	static int h, w;
	static char map[][];
	static int visit [][][];
	static int ans = INF;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/벽부수고이동하기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		visit = new int[h][w][2];
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(0, 0, 1, 0));
		visit[0][0][0] = 1;
		visit[0][0][1] = 1;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int y = now.y; int x = now.x; int t = now.t; int c = now.c;
			//System.out.println(y+", "+x+" "+t+" "+c);
			if(y==h-1 && x==w-1) {
				ans = t;
				break;
			}
			for(int i=0; i<4; i++) {
				int ny = y+dy[i]; int nx = x+dx[i]; int nt = t+1;
				if(ny<0 || ny>h-1 || nx<0 || nx>w-1) continue;
				if(map[ny][nx]=='1' && visit[ny][nx][1]==0) {
					if(c==0 && visit[ny][nx][1]==0) {
					q.offer(new Pos(ny, nx, nt, 1));
					visit[ny][nx][1] = 1;
					}
				}
				else if(visit[ny][nx][c]==0){
					q.offer(new Pos(ny, nx, nt, c));
					visit[ny][nx][c] = 1;
				}
			}
		}
		if(ans==INF) System.out.print(-1);
		else System.out.println(ans);
	}
	

}
