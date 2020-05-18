package lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_줄기세포배양1 {
	static class Cell {
		// (처음 입력값 보관용)
		int i; // 세로 좌표 
		int j; // 가로 좌표 
		int x; // x 줄기세포의 생명력 
		
		int life; // 활성화까지의 시간 -> 살아있는 시간 (시간이 지나면서 감소됨)
		int time; // 배양 시간 (시간이 지나면서 감소됨)
		int flag; // 활성화상태(0: 비활성화, 1: 활성화)
		public Cell(int i, int j, int x, int life, int time, int flag) {
			super();
			this.i = i;
			this.j = j;
			this.x = x;
			this.life = life;
			this.time = time;
			this.flag = flag;
		}
		
	}
	static int N, M, K;
	static int[][] map;
	static List<ArrayList<Cell>> list; // 줄기세포생명력 (1<=X<=10)별 저장 (인덱스 0~9 사용)
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static void bfs() {
		Queue<Cell> q = new LinkedList<Cell>();
		for(int x=9; x>=0; x--) {
			for(int s=0; s<list.get(x).size(); s++) {
				q.offer(list.get(x).get(s));
			}
		}
		// 종료 처리 
		while(!q.isEmpty()) {
			Cell c = q.poll();
			// 비활성화고 
			if(c.life==0 && c.flag==1) {
				map[c.i][c.j]=-1;
				continue;
			}
			
			// 더 이상 볼 필요가 없음 
			if(c.time==0) continue;
			if(c.life!=0) {
				q.offer(new Cell(c.i, c.j, c.x, c.life-1, c.time-1, c.flag));
				continue;
			}
			// c.life == 0
			q.offer(new Cell(c.i, c.j, c.x, c.x, c.time, 1));
			for(int d=0; d<4; d++) {
				int ni=c.i+di[d];
				int nj=c.j+dj[d];
				if(0<=ni&&ni<N+K&&0<=nj&&nj<M+K&&map[ni][nj]==0) {
					map[ni][nj]=c.x;
					q.offer(new Cell(ni, nj, c.x, c.x, c.time-1, 0));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		list = new ArrayList<>();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			// 초기화
			list.clear();
			for(int x=0; x<=9; x++) { // 줄기세포생명력 1<=ㅌ<=10, 리스트작업시 0~9로 변경
				list.add(new ArrayList<>());
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N+K][M+K];
			
			for(int i=(K/2); i<N+(K/2); i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=(K/2); j<M+(K/2); j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]!=0) {
						int idx = map[i][j]-1; // 리스트 작업 시 0~9까지 (
						list.get(idx).add(new Cell(i, j, map[i][j], map[i][j], K, 0));
					}
				}
			}
			
			
			bfs();
			
			int cnt=0;
			for(int i=0; i<N+K; i++) {
				for(int j=0; j<M+K; j++) {
					if(map[i][j]!=0 && map[i][j]!=-1) cnt++;
				}
			}
			sb.append("#"+tc+" "+cnt+"\n");
		}
		System.out.println(sb);
	}

}
