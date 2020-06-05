package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 17822 원판돌리기 
public class BOJ_17822_원판돌리기 {
	static int h, w, order;
	static int[][] map;
	static void init() {
		map = new int[h][w];
		visit = new boolean[h][w];
	}
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		order = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<order; i++) {
			st = new StringTokenizer(br.readLine());
			int which, d, cnt;
			which = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			cnt = Integer.parseInt(st.nextToken());
			run(which, d, cnt);
		}
		int ans = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]==-1) continue;
				ans += map[i][j];
			}
		}
		System.out.println(ans);
		
	}
	static void run(int idx, int d, int cnt) {
		if(d==0) {
			cnt = cnt%w;
		} else {
			cnt = (cnt%w)*(w-1);
		}
		// 돌리기 
		for(int k=1; k<=h; k++) {
			if(k%idx!=0) continue;
			for(int i=0; i<cnt; i++) {
				map[k-1] = rotate(map[k-1]);
			}
		}
		
		sum = 0;
		for(int i=0; i<h; i++) {
			Arrays.fill(visit[i], false);
		}
		// 인접하고 같은 수가 있는가 
		int res = check();
		if(res==0) {
			// 보정 
			modify((double)sum/((h*w)-erased));
		} else {
			// 지우기 
			erase();
		}
	}
	// i 번째 원판 돌리기 
	static int[] rotate(int[] line) {
		int len = line.length;
		int last = line[len-1];
		for(int j=len-1; j>0; j--) {
			line[j] = line[j-1];
		}
		line[0] = last;
		return line;
	}
	static boolean visit[][];
	static int sum;
	static int erased = 0;
	static int check() {
		int cnt = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]==-1) continue;
				int num = map[i][j];
				sum += num;
				// 원판안
				if(map[i][(j+1)%w]==num) {
					visit[i][j] = true;
					visit[i][(j+1)%w] = true;
					cnt++;
				}
				// 원판밖 
				
				if(i+1<h && map[(i+1)][j]==num) {
					visit[i][j] = true;
					visit[(i+1)][j] = true;
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	static void modify(double num) {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]==-1) continue;
				if(map[i][j]>num) map[i][j]--;
				else if(map[i][j]<num) map[i][j]++;
			}
		}
	}
	static void erase() {
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				if(map[i][j]==-1) continue;
				if(visit[i][j]==true) {
					erased++;
					map[i][j] = -1;
				}
			}
		}
	}
	
	static void print() {
		System.out.println("=========");
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	
}
