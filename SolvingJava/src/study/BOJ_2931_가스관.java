package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2931_가스관 {
	static final int dx[] = { -1, 0, 1, 0 };
	static final int dy[] = { 0, 1, 0, -1 };
	static int h, w;
	static char map[][] = new char[26][26];
	static int mx, my, md;
	static int zx, zy, zd;
	
	static int ax, ay;
	static int sx, sy;
	static int ex, ey;
	static int ans = '+';
	static int blocks[][] = { { 1, 0, 1, 0 }, { 0, 1, 0, 1 }, { 1, 1, 1, 1 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 },
			{ 1, 0, 0, 1 }, { 0, 0, 1, 1 }, {1, 1, 1, 1}, {1, 1, 1, 1 }};
	static boolean visit[][][] = new boolean[26][26][4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		for (int i = 0; i < h; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]=='M') {
					sx = i; sy = j;
				} 
				if(map[i][j]=='Z') {
					ex = i; ey = j;
				}
			}
		}
		
		int sd = -1;
		// 시작 방향 정하기 
		for(int i=0; i<4; i++) {
			int nx = sx+dx[i];
			int ny = sy+dy[i];
			if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
			if(map[nx][ny]!='.') {
				sd = i;
				break;
			}
		}
		dfs(sx, sy, sd, 'M');
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				for(int k=0; k<4; k++) {
					visit[i][j][k] = false;
				}
			}
		}
		
		int ed = -1;
		for(int i=0; i<4; i++) {
			int nx = ex+dx[i];
			int ny = ey+dy[i];
			if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
			if(map[nx][ny]!='.') {
				ed = i;
				break;
			}
		}
		dfs(ex, ey, ed, 'Z');
		
		ax = mx+dx[md]; ay = my+dy[md];
		for(int i=0; i<7; i++) {
			if(i==2) continue;
			int nd = nextDir(i, md);
			if(blocks[i][nd]==1) {
				int nx = ax+dx[nd]; int ny = ay+dy[nd];
				if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
				if(nx==zx && ny==zy) {
					ans = i;
					break;
				}
			}
		}
		
		
		System.out.print((ax+1)+" "+(ay+1)+" ");
		char answer = '0';
		switch(ans) {
		case 0:
			answer = '|';
			break;
		case 1:
			answer = '-';
			break;
		case 3:
			answer = '1';
			break;
		case 4:
			answer = '2';
			break;
		case 5:
			answer = '3';
			break;
		case 6:
			answer = '4';
			break;
		}
		System.out.println(answer);
	}
	
	static void dfs(int x, int y, int dir, char start) {
		//System.out.println(x+", "+y+" : "+ dir);
		visit[x][y][dir] = true;
		int shape = convert(map[x][y]);
		int nd = nextDir(shape, dir);
		int nx = x+dx[nd]; int ny = y+dy[nd];
		if(nx<0 || nx>h-1 || ny<0 || ny>w-1) return;
		if(visit[nx][ny][nd]) return;
		visit[nx][ny][nd] = true;
		if(map[nx][ny]=='.') {
			if(start=='M') {
				mx = x; my = y; md = nd;
			}
			else {
				zx = x; zy = y; zd = nd;
			}
			return;
		}
		dfs(nx, ny, nd, start);
		
	}
	
	static int nextDir(int shape, int dir) {
		if(shape==0 || shape==1 || shape==2 || shape==7 || shape==8) {
			return dir;
		}
		else if(shape==3) {
			if(dir==3) return 2;
			else return 1;
		}
		else if(shape==4) {
			if(dir==2) return 1;
			else return 0;
		}
		else if(shape==5) {
			if(dir==2) return 3;
			else return 0;
		}
		else if(shape==6){
			if(dir==1) return 2;
			else return 3;
		} 
		else {
			return -1;
		}
	}

	static int convert(char c) {
		switch (c) {
		case '|':
			return 0;
		case '-':
			return 1;
		case '+':
			return 2;
		case '1':
			return 3;
		case '2':
			return 4;
		case '3':
			return 5;
		case '4':
			return 6;
		case 'M':
			return 7;
		case 'Z':
			return 8;
		}
		
		return -1;
	}
}
