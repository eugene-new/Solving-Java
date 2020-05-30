package study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1525_퍼즐 {
	static class Pos{
		char[] stat;
		int cnt;
		int idx;
		public Pos(char[] stat, int idx, int cnt) {
			super();
			this.stat = stat;
			this.idx = idx;
			this.cnt = cnt;
		}
	}
	static int[] delta = {-3, 1, 3, -1}; // 다음 좌표 
	static int h = 3; static int w = 3;
	static char[] map = new char[9];
	static String correct = "123456780";
	static int ans = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int p = 0;
		int where = 0;
		for(int i=0; i<h; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<w; j++) {
				map[p++] = st.nextToken().charAt(0);
				if(map[p-1]=='0') where = p-1;
			}
		}
		bfs(map, where);
		System.out.println(ans);
	}
	static Queue<Pos> q = new LinkedList<Pos>();
	static Set<String> set = new HashSet<String>(); // 중복 체크 
	static void bfs(char[] start, int p) {
		q.add(new Pos(start, p, 0));
		set.add(start.toString());
		while(!q.isEmpty()) {
			Pos now = q.poll();
			char[] stat = now.stat;
			int idx = now.idx;
			int cnt = now.cnt;
//			System.out.print(stat);
//			System.out.println(" : "+ cnt);
			if(String.valueOf(stat).equals(correct)) {
				ans = cnt;
				return;
			}
			
			for(int i=0; i<4; i++) {
				if(i==1 &&(idx==2 || idx==5)) continue;
				if(i==3 &&(idx==3 || idx==6)) continue;
				char[] nstat = stat.clone();
				int ndx = idx+delta[i];
				if(ndx<0 || ndx>8) continue;
				char num = nstat[ndx];
				nstat[idx] = num;
				nstat[ndx] = '0';
				if(set.contains(String.valueOf(nstat))) continue;
				else {
					q.offer(new Pos(nstat, ndx, cnt+1));
					set.add(String.valueOf(nstat));
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
