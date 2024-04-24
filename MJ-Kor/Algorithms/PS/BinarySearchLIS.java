// 백준 12015번

package training.boj.extra.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12015_김민주 {

	private static int[] dp;
	private static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		dp = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int idx = 0;
		int len = 0;

    // 해당 for문의 N, 이진 탐색의 logN으로 시간복잡도는 O(NlogN)
		for (int i = 0; i < N; i++) {
      // 현재 배열의 숫자가 가장 끝에 있는 메모한 숫자보다 크면
			if(arr[i] > dp[len]) {
        // 다음 위치에 저장
				len++;
				dp[len] = arr[i];
			}
      // 작으면
			else {
        // 이진 탐색으로 적절한 위치를 찾기
				idx = binarySearchLIS(arr[i], 0, len);
				dp[idx] = arr[i];
			}
		}
		System.out.println(len);
	}


  // 적절한 위치는 현재 value보다 큰 수들 중에서 가장 작은 수의 위치
	private static int binarySearchLIS(int value, int start, int end) {  
		if(start > end) return start;
		
		int mid = (start + end) / 2;
		
		if(dp[mid] < value) return binarySearchLIS(value, mid + 1, end);
		else return binarySearchLIS(value, start, mid - 1);
	}

}
