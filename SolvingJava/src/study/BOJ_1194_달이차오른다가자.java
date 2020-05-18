package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194_달이차오른다가자 {
	static class Pos{
		int x, y, d, key;

		public Pos(int x, int y, int d, int key) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.key = key;
		}
		
	}
	static int h, w;
	static int sx, sy, ex, ey;
	static char[][] map = new char[50][50];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		for(int i=0; i<h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		// 민식 위치 및 출구 찾기
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]=='0') {
					sx = i; sy = j;
					break;
				}
			}
		}
		System.out.println(bfs());
		
	}
	static Queue<Pos> q = new LinkedList<Pos>();
	static boolean[][][] visit = new boolean[50][50][(1<<7)];
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int bfs() {
		q.add(new Pos(sx, sy, 0, (1<<6)));
		visit[sx][sy][(1<<6)] = true;
		
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y; int d = now.d; int key = now.key;
			if(map[x][y]=='1') {
				q.clear();
				return d;
			}
			//System.out.println(x+", "+y);
			for(int i=0; i<4; i++) {
				int nx = x+dx[i]; int ny = y+dy[i];
				if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
				if(visit[nx][ny][key]) continue;
				if(map[nx][ny]=='#') continue;
				int nkey = key;
				// 열쇠
				if('a'<=map[nx][ny]&&map[nx][ny]<='f') {
					int idx = map[nx][ny] - 'a';
					nkey = key | (1<<idx);
				}
				// 문 
				else if('A'<=map[nx][ny]&&map[nx][ny]<='F') {
					int door = map[nx][ny]-'A';
					int tmp = key;
					// 지나갈 수 없다 
					if ((tmp &= (1<<door))==0) {
						continue;
					}
				}
				visit[nx][ny][nkey] = true;
				q.offer(new Pos(nx, ny, d+1, nkey));
			}
		}
		
		return -1;
	}

}
