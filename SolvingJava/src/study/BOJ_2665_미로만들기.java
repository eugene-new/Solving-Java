package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2665_미로만들기 {
	static class Pos{
		int x, y, c;

		public Pos(int x, int y, int c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}
		
	}
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static char[][] map = new char[51][51];
	static int[][] visit = new int[51][51];
	static int h, w;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		h = w = Integer.parseInt(br.readLine());
		for(int i=0; i<h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		Queue<Pos> q = new LinkedList<Pos>();
		q.offer(new Pos(0, 0, 0));
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				visit[i][j] = -1;
			}
		}
		visit[0][0] = 0;
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y;
			int cost = now.c;
			
			if(x==h-1 && y==w-1) {
				continue;
			}
			for(int i=0; i<4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				int nc = cost;
				if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
				if(map[nx][ny]=='0') nc++;
				if(visit[nx][ny]==-1 || visit[nx][ny]>cost) {
					visit[nx][ny] = nc;
					q.offer(new Pos(nx, ny, nc));
				}
			}
		}
		
		System.out.println(visit[h-1][w-1]);
		
	}

}
