package test;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class SWEA_2383_점심식사시간 {
	static class Pos {
		int idx, x, y;
		int d1, d2;

		public Pos(int idx, int x, int y) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pos [idx=" + idx + ", x=" + x + ", y=" + y + ", d1=" + d1 + ", d2=" + d2 + "]";
		}

	}

	static int h, w;
	static List<Pos> ppl = new ArrayList<SWEA_2383_점심식사시간.Pos>();
	static int total;
	static Pos s1, s2;
	static int ans;

	static void init() {
		ppl.clear();
		total = 0;
		s1 = null;
		s2 = null;
		ans = Integer.MAX_VALUE;
	}

	public static void main(String args[]) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			h = w = Integer.parseInt(br.readLine());
			init();
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					int now = Integer.parseInt(st.nextToken());
					if (now == 1) {
						ppl.add(new Pos(total++, i, j));
					} else if (now >= 2) {
						if (s1 == null) {
							s1 = new Pos(now, i, j);
						} else {
							s2 = new Pos(now, i, j);
						}
					}
				}
			}

			// 각 계단과의 거리 구하기
			for (Pos p : ppl) {
				int px = p.x;
				int py = p.y;
				p.d1 = Math.abs(px - s1.x) + Math.abs(py - s1.y);
				p.d2 = Math.abs(px - s2.x) + Math.abs(py - s2.y);
			}

			// 조합
			for (int i = 0; i <= total; i++) {
				combi(0, 0, i);
			}

			System.out.println("#" + test_case + " " + ans);
		}

	}

	static boolean[] visit = new boolean[10];

	static void combi(int depth, int start, int r) {
		if (depth == r) {
			//System.out.println(Arrays.toString(visit));
			for (int i = 0; i < total; i++) {
				if (visit[i] == true) {
					q1.offer(ppl.get(i));
				} else {
					q2.offer(ppl.get(i));
				}
			}
			int res = simul();
			//System.out.println(res);
			if (ans > res) {
				ans = res;
			}
			return;
		}
		for (int i = start; i < total; i++) {
			visit[i] = true;
			combi(depth + 1, i + 1, r);
			visit[i] = false;
		}
	}

	static PriorityQueue<Pos> q1 = new PriorityQueue<SWEA_2383_점심식사시간.Pos>(new Comparator<Pos>() {

		@Override
		public int compare(Pos o1, Pos o2) {
			return o1.d1 - o2.d1;
		}

	});
	static PriorityQueue<Pos> q2 = new PriorityQueue<SWEA_2383_점심식사시간.Pos>(new Comparator<Pos>() {

		@Override
		public int compare(Pos o1, Pos o2) {
			if (o1.d2 > o2.d2)
				return 1;
			else
				return -1;
		}

	});

	static int simul() {
		Queue<Integer> tmp = new LinkedList<Integer>();
		int t1 = 0; int t2=0;
		// q1부터, d1 쓰기
		if (!q1.isEmpty()) {
			t1 = q1.peek().d1;
			int k1 = s1.idx;
			while (!q1.isEmpty() || !tmp.isEmpty()) {
				while (!tmp.isEmpty()) {
					int start = tmp.peek();
					if (t1 - start == k1) {
						tmp.poll();
					} else {
						break;
					}
				}
				while (!q1.isEmpty()) {
					int arrive = q1.peek().d1;
					if (tmp.size()<3 && t1 >= arrive) {
						tmp.offer(t1);
						q1.poll();
					} else {
						break;
					}
				}
				t1++;
			}
		}
		// d2
		if (!q2.isEmpty()) {
			t2 = q2.peek().d2;
			int k2 = s2.idx;
			while (!q2.isEmpty() || !tmp.isEmpty()) {
				while (!tmp.isEmpty()) {
					int start = tmp.peek();
					if (t2 - start == k2) {
						tmp.poll();
					} else {
						break;
					}
				}
				while (!q2.isEmpty()) {
					int arrive = q2.peek().d2;
					if (tmp.size()<3 && t2 >= arrive) {
						tmp.offer(t2);
						q2.poll();
					} else {
						break;
					}
				}
				t2++;
			}
		}
		return Math.max(t1, t2);
	}

}