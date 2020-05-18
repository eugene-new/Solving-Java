package study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17825_주사위윷놀이 {
	static class Pos{
		int line = 0;
		int idx = 0;
		int used = 0;
		Pos(int line, int idx, int used) {
			this.line = line;
			this.idx = idx;
			this.used = used;
		}
		Pos() {
		}
	}
	static int dice[] = new int[10];
	static int sel[] = new int[10];
	static int pos[][] = new int[4][2];
	static final int map[][] = {
			{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40},
			{10, 13, 16, 19, 25, 30, 35, 40},
			{0,  20, 22, 24, 25, 30, 35, 40},
			{30, 28, 27, 26, 25, 30, 35, 40}
	};
	static int visit[][];
	static int ans = 0;
	static int simulation() {
		visit = new int[4][25];
		
		int score = 0;
		Pos[] pos = new Pos[4];
		for(int i=0; i<4; i++) {
			pos[i] = new Pos(0, 0, 0);
		}
		
		for(int i=0; i<10; i++) {
			int now = sel[i];
			if(pos[now].used==1) {
				return 0;
			}
			int idx = pos[now].idx;
			int line = pos[now].line;
				
			int next = idx+dice[i];
				
			if(map[line][idx]==40) {
				visit[0][20] = 0;
				visit[1][7] = 0;
				visit[2][7] = 0;
				visit[3][7] = 0;
			}
			else if(line>0 && idx>=4) {
				visit[1][idx] = 0;
				visit[2][idx] = 0;
				visit[3][idx] = 0;
			}
			else {
				visit[line][idx] = 0;
			}
			if(pos[now].line==0) {
				// 범위 
				if(next>map[0].length-1)  {
					pos[now].used = 1;
					continue;
				}					
				// 5, 10, 15 처리
				else if(next==5) {
					line = 1;
					idx = 0;	
				}
				else if(next==10) {
					line = 2;
					idx = 1;
				}					
				else if(next==15) {
					line = 3;
					idx = 0;
				}
				else {
					idx = next;
				}				
			}
				
			else {
				if(next>map[line].length-1) {
					pos[now].used = 1;
					continue;					
				}
				else {
					idx = next;
				}
				
				
			}
			if(visit[line][idx]==1) {
				return 0;			
			}
			
			if(map[line][idx]==40) {
				visit[0][20] = 1;
				visit[1][7] = 1;
				visit[2][7] = 1;
				visit[3][7] = 1;
			}
			else if(line>0 && idx>=4) {
				visit[1][idx] = 1;
				visit[2][idx] = 1;
				visit[3][idx] = 1;
			}
			else {
				visit[line][idx] = 1;
			}
			score += map[line][idx];
			pos[now].line = line;
			pos[now].idx = idx;
		}
		
		return score;
	}
	
	static void dfs(int start, int depth) {
		if(depth==10) {
			int res = simulation();
			if(res>ans) ans = res;
			return;
		}
		
		for(int i=start; i<4; i++) {
			sel[depth] = i;
			dfs(i, depth+1);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/주사위윷놀이.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		System.out.println(ans);
	}

}
