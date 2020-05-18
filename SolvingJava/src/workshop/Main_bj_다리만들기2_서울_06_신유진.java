package workshop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_bj_다리만들기2_서울_06_신유진 {
	static class Info implements Comparable<Info> {
		int to;
		int weight;

		public Info(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Info o) {
			if(this.weight>o.weight) return 1;
			else return 0;
		};

	};

	static int h, w;
	static int map[][];
	static int visit_make[][];
	static int dy[] = { -1, 0, 1, 0 };
	static int dx[] = { 0, 1, 0, -1 };
	static int total;
	static int cost[][];
	static List<ArrayList<Info>> adj; // 인접 리스트

	static int prim(int start) {
	    int len = 0;
	    int visit[]=new int[10];
	    Arrays.fill(visit, 0);
	    visit[start] = 1;
	    PriorityQueue<Info> pq = new PriorityQueue<Info>();
	    for(int i=0; i<adj.get(start).size(); i++) {
	        pq.offer(new Info(adj.get(start).get(i).to, adj.get(start).get(i).weight));
	    }

	    while(!pq.isEmpty()) {
	        Info now = pq.peek(); pq.poll();
	        int to = now.to; int weight = now.weight;
	        if(visit[to]==1) continue;

	        visit[to] = 1;
	        len += weight;
	        for(int i=0; i<adj.get(to).size(); i++) {
	            pq.offer(new Info(adj.get(to).get(i).to, adj.get(to).get(i).weight));
	        }
	    }
	    int flag = 0;
	    for(int i=1; i<=total; i++) {
	        if(visit[i]==1) flag++;
	    }

	    if(flag==total) return len;
	    else return -1;
	}

	static void make_island(int y, int x) {
		map[y][x] = total;
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny < 0 || ny > h-1 || nx < 0 || nx > w-1)
				continue;
			if (map[ny][nx] == 0)
				continue;
			if (visit_make[ny][nx]==1)
				continue;

			visit_make[ny][nx] = 1;
			make_island(ny, nx);
		}
	}

	static void find_bridge(int y, int x, int dir, int id, int depth) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		if (ny < 0 || ny > h -1|| nx < 0 || nx > w-1)
			return;
		if (map[ny][nx] == id)
			return;
		else if (map[ny][nx]==1) {
			int from = id;
			int to = map[ny][nx];
			if ((cost[from][to] == -1 || cost[from][to] > depth) && depth >= 2) {
				cost[from][to] = depth;
				cost[to][from] = depth;
			}
			return; // 리턴을 갱신할 때만 해줘서 오류
		}

		find_bridge(ny, nx, dir, id, depth + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		visit_make = new int[h][w];
		cost = new int[h][w];
		
	    for(int i=0; i<h; i++) {
	    	st = new StringTokenizer(br.readLine());
	        for(int j=0; j<w; j++) {
	           map[i][j] = Integer.parseInt(st.nextToken());
	        }
	    }

	    for(int i=0; i<h; i++) {
	        for(int j=0; j<w; j++) {
	            if(map[i][j]!=0 && visit_make[i][j]==0) {
	                total++;
	                make_island(i, j);
	            }
	            cost[i][j] = -1;
	        }
	    }
	    adj = new ArrayList<ArrayList<Info>>();
		for(int i=0; i<total; i++) {
			adj.add(new ArrayList<Info>());
		}
	    for(int i=0; i<h; i++) {
	        for(int j=0; j<w; j++) {
	            if(map[i][j]==1) {
	                for(int d=0; d<4; d++) {
	                    find_bridge(i, j, d, map[i][j], 0);
	                }
	            }
	        }
	    }


	    for(int i=1; i<=total; i++) {
	        for(int j=1; j<=total; j++) {
	            if(cost[i][j]!=-1) {
	                adj.get(i).add(new Info(j, cost[i][j]));
	            }
	        }
	    }
	    System.out.println(prim(1));
	}

}
