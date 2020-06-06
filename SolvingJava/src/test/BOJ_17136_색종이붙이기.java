package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BOJ_17136_색종이붙이기
public class BOJ_17136_색종이붙이기 {
	static int h = 10; static int w = 10;
	static int[][] map = new int[h][w];
	static List<int[]> points = new ArrayList<int[]>();
	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					int[] tmp = {i, j};
					points.add(tmp);
				}
			}
		}
		int[] cnt = {0, 5, 5, 5, 5, 5};
		dfs(0, cnt, 0);
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	static int ans = Integer.MAX_VALUE;
	static int[][] visit = new int[h][w];
//	static List<Integer> pick = new ArrayList<Integer>();
	static void dfs(int depth, int[] cnt, int sum) {
		if(sum>=ans) return;
		if(depth>=points.size()) {
//			System.out.println(pick);
			if(ans>sum) {
				ans = sum;
			}
			return;
		}
		
		int x = points.get(depth)[0];
		int y = points.get(depth)[1];
		
		if(visit[x][y]==1) {
			int[] ncnt = cnt.clone();
			dfs(depth+1, ncnt, sum);
		}
		
		else {
			int Max = 1;
			for(int i=5; i>=2; i--) {
				if(isPossible(x, y, i)) {
					Max = i;
					break;
				}
			}
			for(int i=Max; i>=1; i--) {
				if(cnt[i]<=0) continue;
				
				int[] ncnt = cnt.clone();
				ncnt[i]--;
				insert(x, y, i);
//				pick.add(i);
				dfs(depth+1, ncnt, sum+1);
				delete(x, y, i);
//				pick.remove(pick.size()-1);
			}
			
		}
		
	}
	
	// sx, sy에서 len짜리 색종이를 붙일 수 있는가 
	static boolean isPossible(int sx, int sy, int len) {
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				if(sx+i>h-1 || sy+j>w-1) return false;
				if(map[sx+i][sy+j]==0) return false;
				if(visit[sx+i][sy+j]==1) return false;
			}
		}
		return true;
	}
	
	// len짜리 색종이 붙이기 시작점 sx, sy 
	// visit체크 
	static void insert(int sx, int sy, int len) {
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				visit[sx+i][sy+j] = 1; 
			}
		}
	}
	// len짜리 색종이 지우기 시작점 sx, sy 
	static void delete(int sx, int sy, int len) {
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				visit[sx+i][sy+j] = 0; 
			}
		}
	}
}
