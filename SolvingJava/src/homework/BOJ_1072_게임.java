package homework;

import java.util.Scanner;

public class BOJ_1072_게임 {
	static final long MAX = 1000000000;
	static long x, y, z, low, high, mid, test_z;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		x = sc.nextLong();
		y = sc.nextLong();
		z = 100 * y / x;
		if (z >= 99) {
			System.out.println(-1);
			System.exit(0);
		}

		low = 0;
		high = MAX;
		while (low <= high) {
			mid = (low + high) / 2;
			test_z = 100 * (y + mid) / (x + mid);

			if (z < test_z) {
				high = mid - 1;
			}
			else {
				low = mid+1;
			}
		}
		System.out.println(low);
	}
}
