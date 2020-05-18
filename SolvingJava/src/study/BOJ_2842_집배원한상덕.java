package study;
import java.util.*;
import java.io.*;
public class BOJ_2842_집배원한상덕 {
	static class Pos{
		int y, x;
		public Pos(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		Pos() {}
	}
	static int n;
	static char[][] map;
	static int[][] height;
	static int m;
	static ArrayList<Pos> house;
	static void init() {
		map = new char[n+5][n+5];
		height = new int[n+5][n+5];
		house = new ArrayList<Pos>();
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/집배원한상덕.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		init();
		for(int i=0; i<n; i++) {
			map[i] = br.readLine().toCharArray();
			
		}
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				height[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//System.out.println(k);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]=='K') {
					map[i][j] = (char) m;
					house.add(new Pos(i, j));
				}
			}
		}
		
		System.out.println("fd");
	}

}
