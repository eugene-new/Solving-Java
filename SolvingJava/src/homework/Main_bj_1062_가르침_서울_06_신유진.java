package homework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bj_1062_가르침_서울_06_신유진 {
	static int n, k;
	static List<Character> candi = new ArrayList<Character>();
	static List<char[]> words =  new ArrayList<char[]>();
	static List<Character> origin = new ArrayList<Character>();
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		// 뽑을 알파벳들 넣기 
		for(int i=0; i<26; i++) {
			char now = (char) ('a'+i);
			if(now!='a' && now!='c' && now!='n' && now!='i' && now!='t') {
				candi.add(now);
			}
		}
		// 원래 있어야 하는 글자 고르기 
		origin.add('a'); origin.add('c'); origin.add('n'); origin.add('i'); origin.add('t');
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			char[] word = br.readLine().toCharArray();
			words.add(word);
		}
		combi(0, 0);
		System.out.println(ans);
	}
	static List<Character> pick = new ArrayList<Character>();
	
	static void combi(int depth, int now) {
		if(depth==k-5) {
			int cnt = 0;
			// 읽을 수 있는 단어 개수 
			for(int i=0; i<n; i++) {
				char[] word = words.get(i);
				if(check(word)) cnt++;
			}
			ans = Math.max(cnt, ans);
			return;
		}
		for(int i=now; i<candi.size(); i++) {
			pick.add(candi.get(i));
			combi(depth+1, i+1);
			//System.out.println(pick);
			pick.remove(pick.size()-1);
		}
	}
	static boolean check(char[] word) {
		for(int i=4; i<word.length-4; i++) {
			char now = word[i];
			if(origin.contains(now) || pick.contains(now)) continue;
			else return false;
		}
		return true;
	}
}
