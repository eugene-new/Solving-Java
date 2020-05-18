package study;
// 1, 2 / /
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2151_거울설치 {
	static class Pos{
		int y, x, d;

		public Pos(int y, int x, int d) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
		}
		Pos() {}
	}
	static int n;
	static boolean flag = false;
	static int sy, sx, ey, ex;
	static char map[][];
	static int visit[][][];
	static int dy[] = {0, 0, 1, -1};
	static int dx[] = {1, -1, 0, 0};
	static int ans = Integer.MAX_VALUE;
	static int direction(int d, char c) {
		if(c=='/') {
			if(d==0) d=2;
			else if(d==1) d=2;
			else if(d==2) d=0;
			else if(d==3) d=0;
		}
		else {
			if(d==0) d=3;
			else if(d==1) d=3;
			else if(d==2) d=1;
			else if(d==3) d=1;
		}
		return d;
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		visit = new int[n][n][4];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<4; k++) {
					visit[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		for(int i=0; i<4; i++) {
			q.offer(new Pos(sy, sx, i));
			visit[sy][sx][i] = 0;
		}
		
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int y = now.y; int x = now.x; int d = now.d;
			
		
			int ny = y+dy[d]; int nx = x+dx[d];
			if(ny<0 || ny>n-1 || nx<0 || nx>n-1) continue;
			if(map[ny][nx]=='*') continue;
			
			else if(map[ny][nx]=='!') {
				if(visit[ny][nx][d]>visit[y][x][d]) {
					visit[ny][nx][d] = visit[y][x][d];
					q.offer(new Pos(ny, nx, d));
				}
				int nd = direction(d, '/');
				if(visit[ny][nx][nd] > visit[y][x][d]+1) {
					visit[ny][nx][nd] = visit[y][x][d]+1;
					q.offer(new Pos(ny, nx, nd));
				}
				nd = direction(d, '\\');
				if(visit[ny][nx][nd] > visit[y][x][d]+1 ) {
					visit[ny][nx][nd] = visit[y][x][d]+1;
					q.offer(new Pos(ny, nx, nd));
				}
			}
			else if(map[ny][nx]=='.') {
				if(visit[ny][nx][d]>visit[y][x][d]) {
					visit[ny][nx][d] = visit[y][x][d];
					q.offer(new Pos(ny, nx, d));
				}
			}
			else if(map[ny][nx]=='#') {
				if(visit[ny][nx][d] > visit[y][x][d]) {
					visit[ny][nx][d] = visit[y][x][d];
				}
			}
			
		}
		
		
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
			for(int j=0; j<n; j++) {
				if(map[i][j]=='#') {
					if(flag) {
						ey = i; ex = j;
					}
					else {
						sy = i; sx = j;
						flag = true;
					}
				}
			}
		}
		bfs();
		for(int i=0; i<4; i++) {
			ans = Math.min(ans, visit[ey][ex][i]);
		}
		System.out.println(ans);
	}

}
