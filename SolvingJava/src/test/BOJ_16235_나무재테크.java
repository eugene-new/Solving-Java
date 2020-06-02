package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_16235_나무재테크 {
	static int h, w, m, k;
	static List<List<Map<Integer, Integer>>> dict; // 각 칸마다 트리맵 사용 
	static int[][] map;
	static int[][] food;

	static void init() {
		food = new int[h][w];
		map = new int[h][w];
		dict = new ArrayList<List<Map<Integer, Integer>>>();
		for (int i = 0; i < h; i++) {
			dict.add(new ArrayList<Map<Integer, Integer>>());
			for (int j = 0; j < w; j++) {
				dict.get(i).add(new TreeMap<Integer, Integer>());
			}
		}
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				map[i][j] = 5;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		h = w = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		init();
		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				food[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 나무 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			Map<Integer, Integer> hmap = dict.get(x).get(y);
			if (hmap.containsKey(age)) {
				hmap.put(age, hmap.get(age) + 1);
			} else {
				hmap.put(age, 1);
			}
		}
		// System.out.println(dict.get(0).get(0).get(1));
		
		for(int i=0; i<k; i++) {
			simul();
		}
		
		int ans = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				Map<Integer, Integer> hmap = dict.get(i).get(j);
				for(Integer key:hmap.keySet()) {
					ans += hmap.get(key);
				}
			}
		}
		System.out.println(ans);
	}

	static void simul() {
		// 봄 : 나이먹기
		spring();
		// 가을 : 번식
		fall();
		// 겨울 : 양분 추가
		winter();
	}

	static void spring() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int remain = map[i][j];
				Map<Integer, Integer> hmap = dict.get(i).get(j);
				Map<Integer, Integer> newKids = new TreeMap<Integer, Integer>();
				int extra = 0;
				// 양분 자기나이만큼 먹을 수 있는가
				for(Integer age:hmap.keySet()) {
					int cnt = hmap.get(age);
					int dead = 0;
					if((remain/age)<cnt) {
						dead = cnt - (remain/age);
					}
					int survive = cnt-dead;
					remain -= age * (survive);
					if(dead>0) {
						extra += dead*(age/2);
					}
					if(survive>0) {
						newKids.put(age+1, survive);
					}
				}
				map[i][j] = remain+extra;
				dict.get(i).get(j).clear();
				for(Integer age:newKids.keySet()) {
					dict.get(i).get(j).put(age, newKids.get(age));
				}
			}
		}
	}
	static int dx[] = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int dy[] = {0, 1, 0, -1, -1, 1, -1, 1};
	private static void fall() {
		int[][] newKids = new int[h][w];
		// 번식 (나이 % 5 ==0)
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Map<Integer, Integer> hmap = dict.get(i).get(j);
				for (Integer age : hmap.keySet()) {
					if(age%5==0) {
						for(int d=0; d<8; d++) {
							int nx = i+dx[d]; int ny = j+dy[d];
							if(nx<0 || nx>h-1 || ny<0 || ny>w-1) continue;
							newKids[nx][ny]+=hmap.get(age);
						}
					}
				}
			}
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Map<Integer, Integer> hmap = dict.get(i).get(j);
				hmap.put(1, newKids[i][j]);
			}
		}
	}

	private static void winter() {
		// 양분 뿌리기
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				map[i][j] += food[i][j];
			}
		}
	}

}
