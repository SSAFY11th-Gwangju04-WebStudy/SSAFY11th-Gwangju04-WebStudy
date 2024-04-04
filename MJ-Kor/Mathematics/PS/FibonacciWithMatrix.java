package training.boj.day_04_02;

// BOJ 2086

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FibonacciWithMatrix {

	private static long mod = 1_000_000_000;
	private static long[][] origin = {{1, 1}, {1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		long B = pow(origin, b + 1)[0][0];
		long A = pow(origin, a)[0][0];

		// Modular 연산 적용
		long result = ((B - A) + mod) % mod;
		
		System.out.println(result);
		
	}

	// 분할 정복을 활용한 거듭 제곱
	private static long[][] pow(long[][] a, long exp) {
		
		if(exp == 1 || exp == 0) {
			return a;
		}
		
		long[][] res = pow(a, exp / 2);

		// exp가 짝수일 경우
		res = matmul(res, res);

		// exp가 홀수일 경우
		if(exp % 2 == 1L) {
			res = matmul(res, origin);
		}
		
		return res;
	}

	// 행렬 곱 계산
	private static long[][] matmul(long[][] o1, long[][] o2) {
		
		long res[][] = new long[2][2];
		
		res[0][0] = ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % mod;
		res[0][1] = ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % mod;
		res[1][0] = ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % mod;
		res[1][1] = ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % mod;
		
		return res;
	}
}
