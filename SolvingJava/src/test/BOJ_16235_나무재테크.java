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
	static int[][] map;  // 땅의 상태 (양분이 얼마나 있는지)
	static int[][] food; // 겨울에 뿌릴 양분 
	
	 // 각 칸마다 트리맵 사용해 나무를 관리 {나이 : 해당 나이 나무 수} 
	static List<List<Map<Integer, Integer>>> dict;
	

	static void init() {
		food = new int[h][w];
		map = new int[h][w];
		
		// 각 칸마다 트리맵을 넣어준다
		// 트리맵 => 어린 순으로 관리하기 위해 (어린 애들부터 양분을 먹기 때문)
		dict = new ArrayList<List<Map<Integer, Integer>>>(); // 일단 동적 할당 
		for (int i = 0; i < h; i++) { // 행마다 ArrayList 할당  
			dict.add(new ArrayList<Map<Integer, Integer>>());
			for (int j = 0; j < w; j++) { // 칸마다 트리맵 할당 
				dict.get(i).add(new TreeMap<Integer, Integer>());
			}
		}
		// 땅의 초기 상태 
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
		// 봄/여름 : 나이먹기 & 죽은 애들이 양분으로 
		SS();
		// 가을 : 번식
		fall();
		// 겨울 : 양분 추가
		winter();
	}

	static void SS() {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int remain = map[i][j]; // 땅에 남아 있는 양분 
				Map<Integer, Integer> hmap = dict.get(i).get(j);
				Map<Integer, Integer> agedTree = new TreeMap<Integer, Integer>();
				int extra = 0;
				// 맵 순회 : 양분 자기나이만큼 먹을 수 있는가
				// 먹을 수 있으면 agedTree에 넣기
				// 없으면 죽이고 extra 갱신 
				for(Integer age:hmap.keySet()) {
					int total = hmap.get(age); // 해당 칸의 age살 나무 수 
					int dead = 0; // 죽을 애들 
					// 모든 애들이 나이만큼 먹을 수 없으면 
					if((remain/age) < total) {
						// dead에 죽은 애들 수 갱신
						dead = total - (remain/age);
					}
					int survive = total-dead; // 살아 남은 나무 
					remain -= age * (survive); // 먹은 양분 빼주기 = 나이 * 살아남은 나무  
					// 죽은 애들은 양분이 된다 
					if(dead>0) { 
						extra += dead*(age/2);
					}
					// 살아 남은 애들은 1살 먹이고 기록 
					if(survive>0) { 
						agedTree.put(age+1, survive);
					}
				}
				map[i][j] = remain+extra; // 남은 양분+죽은 애들이 준 양분으로 갱신 
				hmap.clear(); // 일단 싹 비운다 
				// 나이 먹은 애들로 다시 맵 구성 
				for(Integer age:agedTree.keySet()) {
					hmap.put(age, agedTree.get(age));
				}
			}
		}
	}
	static int dx[] = {-1, 0, 1, 0, -1, -1, 1, 1};
	static int dy[] = {0, 1, 0, -1, -1, 1, -1, 1};
	private static void fall() {
		// 태어나는 애들을 따로 저장한 후 마지막에 한 번에 갱신 
		int[][] newKids = new int[h][w]; // (i, j)에 태어날 나무 수 저장 
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
