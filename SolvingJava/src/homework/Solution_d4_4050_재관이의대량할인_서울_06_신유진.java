package homework;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution_d4_4050_재관이의대량할인_서울_06_신유진 {
	static int n;
	static int price[] = new int[100000+1];
	public static void main(String args[]) throws Exception {

		// System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int total = 0;
			for(int i=0; i<n; i++) {
				price[i] = -Integer.parseInt(st.nextToken());
				total += (-1)*price[i];
			}
			
			Arrays.sort(price);
			
			int discount = 0;
			for(int i=2; i<n; i+=3) {
				discount += (-1)*price[i];
			}
			System.out.println("#"+test_case+" "+(total-discount));
		}
	}
}