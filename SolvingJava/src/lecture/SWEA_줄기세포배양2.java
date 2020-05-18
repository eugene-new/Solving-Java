package lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_줄기세포배양2 {
	static class Cell implements Comparable<Cell> {
		// (처음 입력값 보관용)
		int i; // 세로 좌표 
		int j; // 가로 좌표 
		int x; // x 줄기세포의 생명력 
		
		int t; // 활성화까지의 시간 -> 살아있는 시간 (시간이 지나면서 감소됨)
		public Cell(int i, int j, int x, int t) {
			super();
			this.i = i;
			this.j = j;
			this.x = x;
			this.t = t;
		}
		@Override
		public int compareTo(Cell o) {
			if(t!=o.t) return Integer.compare(t, o.t); // 활성화 시간이 빠른 것 (오름차순)
			return -Integer.compare(x, o.x); // 활성화 시간이 같으면 생명력이 높은 것(내림차순)
		}
		
	}
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Cell> pq = new PriorityQueue<>((o1, o2)-> {
			if(o1.t!=o2.t) return Integer.compare(o1.t, o2.t); // 활성화 시간이 빠른 것 (오름차순)
			return -Integer.compare(o1.x, o2.x); // 활성화 시간이 같으면 생명력이 높은 것(내림차순)
		});
		boolean[][] v = new boolean[700][700];
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			// 초기화
			pq.clear();
			for(int i=0; i<v.length; i++) { 
				Arrays.fill(v[i], false);
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int cnt = 0;
			for(int i=350; i<N+350; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=(K/2); j<M+(K/2); j++) {
					int X = Integer.parseInt(st.nextToken());
					if(X!=0) {
						v[i][j] = true;
						pq.offer(new Cell(i, j, X, X+1));
						if(X*2>K) cnt++;
					}
				}
			}
			
			int time = 0;
			while(time<=K) {
				Cell c=pq.poll();
				time = c.t;
				if(time>K) break;
				for(int d=0; d<4; d++) {
					int ni = c.i+di[d];
					int nj = c.j+dj[d];
					if(!v[ni][nj]) {
						v[ni][nj]=true;
						pq.offer(new Cell(ni, nj, c.x, time+c.x+1)); // 활성화 시간 
						if(time+c.x*2>K) cnt++;
					}
				}
			}
			sb.append("#"+tc+" "+cnt+"\n");
		}
		System.out.println(sb);
	}

}
