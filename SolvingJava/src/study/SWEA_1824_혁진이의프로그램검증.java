package study;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 혁진이의 프로그래밍 검증 
public class SWEA_1824_혁진이의프로그램검증 {
	static class Pos{
		int x, y, dir, mem;

		public Pos(int x, int y, int dir, int mem) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.mem = mem;
		}

		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", dir=" + dir + ", mem=" + mem + "]";
		}
		
		
	}
	static int h, w;
	static char[][] map = new char[21][21];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		 
		 int T = Integer.parseInt(br.readLine());
		 for(int tc=1; tc<=T; tc++) {
			 st = new StringTokenizer(br.readLine());
			 h = Integer.parseInt(st.nextToken());
			 w = Integer.parseInt(st.nextToken());
			 for(int i=0; i<h; i++) {
				 map[i] = br.readLine().toCharArray();
			 }
			 System.out.println("#"+tc+" "+bfs());
		 }
	}
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static String bfs() {
		Queue<Pos> q = new LinkedList<SWEA_1824_혁진이의프로그램검증.Pos>();
		boolean[][][][] visit = new boolean[h][w][4][16];
		q.offer(new Pos(0, 0, 1, 0));
		while(!q.isEmpty()) {
			Pos now = q.poll();
			int x = now.x; int y = now.y; int dir = now.dir; int mem = now.mem;
			//System.out.println(x+" "+y+" "+dir+" "+mem);
			// 이미 방문
			if(visit[x][y][dir][mem]) {
				continue;
			}
			visit[x][y][dir][mem] = true;
			int nx = x; int ny = y; int nd = dir; int nmem = mem;
			
			
			char token = map[nx][ny];
			// @
			if(token=='@') return "YES";
			// 메모리 
			else if(token=='+') {
				nmem = (nmem+1)%16;
			}
			else if(token=='-') {
				if(nmem==0) nmem = 15;
				else nmem--;
			}
			else if(token=='_') {
				if(nmem==0) nd = 1;
				else nd = 3;
			}
			else if(token=='|') {
				if(nmem==0) nd = 2;
				else nd = 0;
			}
			else if(token=='?') {
				for(int i=0; i<4; i++) {
					nx = x+dx[i]; ny = y+dy[i];
					if(nx<0) nx = h-1;
					else if(nx>h-1) nx = 0;
					else if(ny<0) ny = w-1;
					else if(ny>w-1) ny = 0;
					//System.out.println(nx+","+ny+" "+i+" "+nmem);
					q.offer(new Pos(nx, ny, i, nmem));
				}
				continue;
			}
			else if('0'<=token && token<='9') {
				nmem = token-'0';
			}
			else if(token!='.'){
				nd = convertDir(token);
			}
			nx = x+dx[nd];
			ny = y+dy[nd];
			// 경계밖
			if(nx<0) nx = h-1;
			else if(nx>h-1) nx = 0;
			else if(ny<0) ny = w-1;
			else if(ny>w-1) ny = 0;
			q.offer(new Pos(nx, ny, nd, nmem));
			
		}
		return "NO";
	}
	static int convertDir(char c) {
		switch(c) {
		case '<':
			return 3;
		case '>':
			return 1;
		case '^':
			return 0;
		case 'v':
			return 2;
		}
		return -1;
	}
}
