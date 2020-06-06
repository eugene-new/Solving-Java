package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class SWEA_5658_비밀상자보물번호 {
	
	
	static int N, K;
	static int[] map;
	static List<Integer> numbers = new ArrayList<Integer>();
	public static void init() {
		numbers.clear();
		map = new int[N];
	}
	public static void main(String args[]) throws Exception {

		// System.setIn(new FileInputStream("res/input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			init();
			String line = br.readLine();
			for(int i=0; i<N; i++) {
				char c = line.charAt(i);
				int x = c-'0';
				if(c=='A') x=10;
				else if(c=='B') x=11;
				else if(c=='C') x=12;
				else if(c=='D') x=13;
				else if(c=='E') x=14;
				else if(c=='F') x=15;
				
				map[i] = x;
			}
			int cnt = N/4;
			for(int i=0; i<cnt; i++) {
				makeNum(cnt);
				rotate();
			}
			
			Collections.sort(numbers, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if (o1<o2) return 1;
					else return -1;
				}
				
			});
			
			int count = 0;
			int ans = -1;
			for(int i=0; i<numbers.size(); i++) {
				if(ans!=numbers.get(i)) count++;
				ans = numbers.get(i);
				if(count==K) {
					break;
				}
			}
			System.out.println("#"+test_case+" "+ans);
		}
	}
	private static void rotate() {
		int tmp = map[N-1];
		for(int i=N-1; i>0; i--) {
			map[i] = map[i-1];
		}
		map[0] = tmp;
	}
	private static void makeNum(int cnt) {
		for(int i=0; i<N; i+=cnt) {
			int num = 0;
			for(int j=0; j<cnt; j++) {
				int now = map[i+j];
				num += Math.pow(16, cnt-j-1)*now;
			}
			numbers.add(num);
		}
	}
}