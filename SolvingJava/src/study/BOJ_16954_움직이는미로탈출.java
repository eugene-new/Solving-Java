package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16954_움직이는미로탈출 {
	static class Pos{
		int y, x, t;
		Pos(int y, int x, int t) {
			this.y=y;
			this.x=x;
			this.t=t;
		}
	}
	static int n = 8;
	static char[][] origin = new char[8][8];
	static char[][][] map = new char[8][8][8];
	static int dy[] = {0, -1, 0, 1, 0, -1, -1, 1, 1};
	static int dx[] = {0, 0, 1, 0, -1, -1, 1, -1, 1};
	static boolean visit[][][] = new boolean[8][8][8];
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			origin[i] = line.toCharArray();
		}
		wall();
		bfs();
		System.out.println(ans);
		
	}
	static void bfs() {
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(n-1,0,0));
		visit[0][n-1][0] = true;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int y = now.y; int x = now.x; int t = now.t;
			if(map[t][y][x]=='#') continue;
			if(y==0 && x==n-1) {
				ans = 1;
				return;
			}
			for(int i=0; i<9; i++) {
				int ny = y+dy[i];
				int nx = x+dx[i];
				int nt = Math.min(t+1, 7);
				if(ny<0 || ny>n-1 || nx<0 || nx>n-1) continue;
				if(visit[nt][ny][nx]==true) continue;
				if(nt<8 && map[t][ny][nx]=='#') continue;
				
				q.offer(new Pos(ny, nx, nt));
				visit[nt][ny][nx] = true;
			}
		}
	}
	static void wall() {
		for(int t=0; t<8; t++) {
			for(int j=0; j<8; j++) {
				for(int i=0; i<8; i++) {
					if(i-t>=0) {
						map[t][i][j] = origin[i-t][j];
					}
					else {
						map[t][i][j] = '.';
					}
				}
			}
		}
	}
}
