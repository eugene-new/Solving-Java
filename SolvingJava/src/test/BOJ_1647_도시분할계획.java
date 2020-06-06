package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1647_도시분할계획 {
	static class Edge{
		int v1, v2, weight;

		public Edge(int v1, int v2, int weight) {
			super();
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [v1=" + v1 + ", v2=" + v2 + ", weight=" + weight + "]"+"\n";
		}
		
	}
	static List<Edge> edges = new ArrayList<BOJ_1647_도시분할계획.Edge>();
	static int V, E, ans;
	static void init() {
		parent = new int[V+1];
		rank = new int[V+1];
		ans = 0;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		init();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges.add(new Edge(v1, v2, weight));
		}
		Collections.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight-o2.weight;
			}
		});
//		System.out.println(edges);
		makeSet();
		int Max = 0;
		for(Edge e:edges) {
			int a = e.v1; int b = e.v2; int w = e.weight;
			int pa = findSet(a); int pb = findSet(b);
			if(pa!=pb) {
				union(pa, pb);
				ans += w;
				Max = Math.max(Max, w);
			}
		}
		System.out.println(ans-Max);
	}
	static int[] parent;
	static int[] rank;
	static void makeSet() {
		for(int i=1; i<=V; i++) {
			parent[i] = i;
		}
	}
	static int findSet(int a) {
		if(parent[a]==a) return a;
		else return findSet(parent[a]);
	}
	static void union(int a, int b) {
		if(rank[a]>=rank[b]) {
			parent[b] = a;
			rank[a]++;
		} else {
			parent[a] = b;
			rank[b]++;
		}
	}
	
	
	
}
