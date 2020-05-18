package study;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5567_결혼식 {
	static int n, m;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean visit[];
	static int bfs() {
		int ret = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> depth = new LinkedList<Integer>();
		for(int i=0; i<adj.get(1).size(); i++) {
			int friend = adj.get(1).get(i);
			q.offer(friend);
			depth.offer(1);
			visit[friend] = true;
		}
		while(!q.isEmpty()) {
			int now = q.poll();
			int d = depth.poll();
			ret++;
			if(d>1) continue;
			
			
			for(int i=0; i<adj.get(now).size(); i++) {
				int next = adj.get(now).get(i);
				if(visit[next]) continue;
				q.offer(next);
				depth.offer(d+1);
				visit[next] = true;
			}
		}
		
		return ret;
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		adj = new ArrayList<ArrayList<Integer>>();
		visit = new boolean[n+5];
		for(int i=0; i<n+1; i++) {
			adj.add(new ArrayList<Integer>());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==1) {
				adj.get(a).add(b);
			}
			else if(b==1) {
				adj.get(b).add(a);
			}
			else {
				adj.get(a).add(b);
				adj.get(b).add(a);
			}
		}
		
		System.out.print(bfs());
	}

}
