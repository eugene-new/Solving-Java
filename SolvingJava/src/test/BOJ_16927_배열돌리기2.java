package test;

import java.io.*;
import java.util.*;

public class BOJ_16927_배열돌리기2 {
	static int h, w, r;
	static int map[][];
	static int dy[]= {1, 0, -1, 0};
	static int dx[]= {0, 1, 0, -1};
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/배열돌리기.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[h+5][w+5];
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int j=0; j<Math.min(h, w)/2; j++) {
			int row = h-(j*2); int col = w-(j*2);
			int k = (row*2)+(col*2)-4;
			int depth = r%k;
			for(int i=0; i<depth; i++) {
				rotate(j);
			}
		}
		
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	//cnt번째사각형돌리기.
	private static void rotate(int cnt) {
		int y = cnt; int x = cnt;
		
		int row = h-(cnt*2); int col = w-(cnt*2);
		int rec = map[y][x+1];
		for(int i=0; i<row; i++) {
			int tmp = map[y][x];
			map[y][x] = rec;
			rec = tmp;
			y += dy[0]; x += dx[0];
		}
		y = cnt+row-1; x = cnt+1;
		for(int i=0; i<col-1; i++) {
			int tmp = map[y][x];
			map[y][x] = rec;
			rec = tmp;
			y+=dy[1]; x+=dx[1];
		}
		y = cnt+row-2; x = cnt+col-1;
		for(int i=0; i<row-1; i++) {
			int tmp = map[y][x];
			map[y][x] = rec;
			rec = tmp;
			y+=dy[2]; x+=dx[2]; 
		}
		y = cnt; x = cnt+col-2;
		for(int i=0; i<col-2; i++) {
			int tmp = map[y][x];
			map[y][x] = rec;
			rec = tmp;
			y+=dy[3]; x+=dx[3]; 
		}
	}
	
	

}
