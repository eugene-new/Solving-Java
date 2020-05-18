package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503_로봇청소기 {
	static final int dy[]= {-1, 0, 1, 0};
	static final int dx[]= {0, 1, 0, -1};
	static int map[][];
	static int h, w;
	static int y, x, d;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/로봇청소기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		map[y][x] = 2; ans++;
		clean : while(true) {
			for(int i=3; i>=0; i--) {
				int ny = y+dy[(d+i)%4]; int nx = x+dx[(d+i)%4];
				if(map[ny][nx]==1 ||map[ny][nx]==2) continue;
				y = ny; x = nx; d = (d+i)%4;
				map[y][x] = 2; // 청소.
				ans++;
				continue clean;
			}
			// 모두 청소거나 벽.
			int ny = y+dy[(d+2)%4]; int nx = x+dx[(d+2)%4];
			// 후진 불가.
			if(map[ny][nx]==1) break clean;
			// 후진 가능.
			y = ny; x = nx; 
		}
		
		System.out.println(ans);
	}
	static void change() {
		
	}

}
