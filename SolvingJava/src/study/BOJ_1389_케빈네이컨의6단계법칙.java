package study;

import java.io.*;
import java.util.*;

public class BOJ_1389_케빈네이컨의6단계법칙 {
	static int n, m;
	static boolean[][] adjMat;
	static ArrayList<Integer>[] adjList;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/케빈베이컨의6단계법칙.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adjMat = new boolean[n+5][n+5];
		adjList = new ArrayList[n+5];
		for(int i=1; i<=n; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(adjMat[a][b]) continue;
			adjMat[a][b] = true; adjMat[b][a] = true;
			adjList[a].add(b);
			adjList[b].add(a);
		}
		int Min = Integer.MAX_VALUE;
		int ans = 1;
		int res[] = new int[n+5];
		for(int i=1; i<=n; i++) {
			for(int j=i+1; j<=n; j++) {
				int ret = bfs(i, j);
				res[i] += ret;
				res[j] += ret;
			}
			if(res[i]<Min) {
				Min = res[i];
				ans = i;
			}
		}
		System.out.println(ans);
		
		
	}
	static class Info{
		int idx, dist;

		public Info(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}
		Info() {}
	}
	static int bfs(int start, int end) {
		Queue<Info> q = new LinkedList<Info>();
		q.offer(new Info(start, 0));
		boolean visit[] = new boolean[n+5];
		visit[start] = true;
		
		while(!q.isEmpty()) {
			Info now = q.poll(); int idx = now.idx; int dist = now.dist;
			if(idx==end) return dist;
			for(int i=0; i<adjList[idx].size(); i++) {
				int next = adjList[idx].get(i);
				if(visit[next]) continue;
				visit[next] = true;
				q.offer(new Info(next, dist+1));
			}
			
		}
		return 0;
		
	}

}
