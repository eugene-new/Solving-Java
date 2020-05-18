package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_이상한피라미드탐험 {
	// 현재 행 구하기 
	static int row(int room) {
		int now = 1;
		while(map[now]<room) {
			now++;
		}
		return now;
	}
	// 현재 열 구하기 
	static int col(int room, int row) {
		return room - map[row-1]-1;
	}
	// 전처리 : 계차수열 계산 
	static void init() {
		int A1 = 1;
		int n = 1;
		while(n<142) {
			int An = (int) (( Math.pow(n, 2)+n)/2);
			map[n++] = An;
		}
	}
	static int map[] = new int[142];
	public static void main(String[] args) throws NumberFormatException, IOException {
		init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 보정 
			if (start > end) {
				int tmp = end;
				end = start;
				start = tmp;
			}
		
			int startRow = row(start); int endRow = row(end);
			int startCol = col(start, startRow); int endCol = col(end, endRow);
			
			int ans = 0;
			if (endRow - startRow <= Math.abs(endCol-startCol)) {
				ans = Math.abs(endCol-startCol);
				if(startCol-endCol>0) {
					ans += (endRow-startRow);
				}
			}
			else {
				ans = endRow-startRow;
				if(startCol-endCol>0) {
					ans += startCol-endCol;
				}
			}
			System.out.println("#"+tc+" "+ans);
			
			
			
		}
	}

}
