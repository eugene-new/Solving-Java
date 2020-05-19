package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7682_틱택토 {
	static final int n = 3;
	static final int dx[] = {-1, 0, 1, 0};
	static final int dy[] = {0, 1, 0, 1};
	static char map[][] = new char[3][3];
	static int row(int h) {
		char now = map[h][0];
		for(int j=1; j<n; j++) {
			if(map[h][j]!=now) return 0;
		}
		return 1;
	}
	static int col(int w) {
		char now = map[0][w];
		for(int i=1; i<n; i++) {
			if(map[i][w]!=now) return 0;
		}
		return 1;
	}
	// 오른쪽 대각선 
	static int right() {
		char now = map[0][0];
		for(int i=1; i<n; i++) {
			if(map[i][i]!=now) return 0;
		}
		return 1;
	}
	static int left() {
		char now = map[0][2];
		if(map[1][1]!=now || map[2][0]!=now) return 0;
		return 1;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String input = br.readLine();
			if(input.contentEquals("end")) break;
			int tmp = 0;
			int blank = 0;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					map[i][j] = input.charAt(tmp++);
					if(map[i][j]=='.') blank++;
				}
			}
			int win = 0;
			for(int i=0; i<3; i++) {
				win += row(i) + col(i);
			}
			win += right() + left();
			
			if(win>1 || (win==0 && blank==0)) System.out.println("invalid");
			else System.out.println("valid");
		}
	}

}
