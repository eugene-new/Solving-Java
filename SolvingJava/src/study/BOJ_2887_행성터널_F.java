package study;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2887_행성터널_F {
	static class Vertex{
		int x, y, z;
		
		public Vertex(int x, int y, int z) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
	static class Edge implements Comparable<Edge>{
		int a, b, w;

		public Edge(int a, int b, int w) {
			super();
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if(this.w>o.w) return 1;
			else if(this.w==o.w) return 0;
			return -1;
		}

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + ", w=" + w + "]";
		}
		
		
	}
	static int n;
	static List<Vertex> v = new ArrayList<Vertex>();
	static List<Edge> edges = new ArrayList<Edge>();
	static int root[] = new int[100000+1];
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			v.add(new Vertex(x, y, z));
		}
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				Vertex v1 = v.get(i); Vertex v2 = v.get(j);
				int x1 = v1.x; int y1 = v1.y; int z1 = v1.z;
				int x2 = v2.x; int y2 = v2.y; int z2 = v2.z;
				int dx = Math.abs(x1-x2);
				int dy = Math.abs(y1-y2);
				int dz = Math.abs(z1-z2);
				int Min = min(dx, dy, dz);
				edges.add(new Edge(i, j, Min));
			}
		}
		System.out.println(kruskal());
		
		
		
	}
	static int kruskal() {
		int ret = 0;
		for(int i=0; i<n; i++) {
			root[i] = i;
		}
		Collections.sort(edges);
		for(int i=0; i<edges.size(); i++) {
			Edge edge = edges.get(i);
			int a = edge.a; int b = edge.b; int w = edge.w;
			ret += union(a, b, w);
		}
		return ret;
	}
	static int findSet(int a) {
		if(root[a]==a) return a;
		else return root[a] = findSet(root[a]);
	}
	private static int union(int a, int b, int w) {
		a = findSet(a);
		b = findSet(b);
		if(a==b) return 0;
		else {
			root[b] = a;
			return w;
		}
	}
	static int min(int a, int b, int c) {
		if(a<b) {
			if(a<c) return a;
			else return c;
		}
		else {
			if(b<c) return b;
			else return c;
		}
	}
}
