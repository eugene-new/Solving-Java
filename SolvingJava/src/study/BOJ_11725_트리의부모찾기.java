package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11725_트리의부모찾기 {
	static final int INF = 100000+5;
	static int n;
	static ArrayList<ArrayList<Integer>> tree;
	static boolean visit[];
	static int parent[];
	static void init() {
		tree = new ArrayList<ArrayList<Integer>>();
		for(int i=0; i<=n; i++) {
			tree.add(new ArrayList<Integer>());
		}
		visit = new boolean[n+1];
		parent = new int[n+1];
	}
	static void dfs(int now, int p) {
		visit[now]= true;
		parent[now] = p;
		for(int i=0; i<tree.get(now).size(); i++) {
			int next = tree.get(now).get(i);
			if (!visit[next]) {
				dfs(next, now);
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		init();
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree.get(a).add(b);
			tree.get(b).add(a);
		}
		
		
		dfs(1, 0);
		for(int i=2; i<=n; i++) {
			System.out.print(parent[i]+"\n");
		}
	}

}
