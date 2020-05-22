package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_18808_스티커붙이기 {
	static class Pos{
		int x, y;

		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static class Sticker{
		int h, w;
		int[][] shape;
		public Sticker(int r, int c) {
			super();
			this.h = r;
			this.w = c;
			this.shape = new int[r][c];
		}
		public void print() {
			System.out.println("===================");
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					System.out.print(shape[i][j]+" ");
				}
				System.out.println();
			}
		}
	}
	static int h, w, k;
	static int[][] map;
	static List<Sticker> list;
	static void init() {
		map = new int[h][w];
		list = new ArrayList<BOJ_18808_스티커붙이기.Sticker>();
	}
	public static void main(String[] args) throws IOException {
		//TEST
		//
		
		//System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		init();
		for(int idx=0; idx<k; idx++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.add(new Sticker(r, c));
			for(int i=0; i<r; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<c; j++) {
					list.get(idx).shape[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		loop: for(Sticker s: list) {
			int r = s.h; int c = s.w; 
			int[][] sticker = s.shape;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(i+r-1>h-1 || j+c-1>w-1) continue;
					// 붙일 수 있다. 
					if(check(i, j, sticker))  {
						continue loop;
					}
				}
			}
			
			
			// 회전
			for(int rot=0; rot<3; rot++) {
				sticker = rotate(sticker);
				r = sticker.length; c = sticker[0].length;
				for(int i=0; i<h; i++) {
					for(int j=0; j<w; j++) {
						if(i+r-1>h-1 || j+c-1>w-1) continue;
						// 붙일 수 있다. 
						if(check(i, j, sticker))  {
							continue loop;
						}
					}
				}
			}
		}
		int ans = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]==1) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}
	static boolean check(int sx, int sy, int[][] sticker) {
		int r = sticker.length;
		int c = sticker[0].length;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				int x = sx+i; int y = sy+j;
				if(x<0 || x>h-1 || y<0 || y>w-1) return false;
				if(sticker[i][j]==1 && map[x][y]!=0) return false;
			}
		}
		fill(sx, sy, sticker);
		return true;
	}
	
	static void fill(int sx, int sy, int[][] sticker) {
		int r = sticker.length;
		int c = sticker[0].length;
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				if(sticker[i][j]==0) continue;
				int x = sx+i; int y = sy+j;
				map[x][y] = 1;
			}
		}
	}
	static int[][] rotate(int[][] target) {
		int r = target.length;
		int c = target[0].length;
		int[][] ret = new int[c][r];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				ret[j][r-i-1] = target[i][j];
			}
		}
//		for(int i=0; i<c; i++) {
//			for(int j=0; j<r; j++) {
//				System.out.print(ret[i][j]+" ");
//			}
//			System.out.println();
//		}
		return ret;
	}
}
