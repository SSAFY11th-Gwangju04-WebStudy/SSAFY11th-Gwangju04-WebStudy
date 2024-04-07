package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14938_최승현 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());           //지역의 개수
        int M = Integer.parseInt(st.nextToken());           //수색 범위
        int R = Integer.parseInt(st.nextToken());           //길의 개수
        final int INF = 10000000;
        int[] items = new int[N + 1];
        int[][] dp = new int[N + 1][N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            dp[a][b] = l;
            dp[b][a] = l;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (dp[i][j] == 0) {
                    dp[i][j] = INF;
                }
            }
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                if (k == i) continue;
                for (int j = 1; j < N + 1; j++) {
                    if (j == k || j == i) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[k][j] + dp[i][k]);
                }
            }
        }
        int max = Integer.MIN_VALUE;

        for (int i = 1; i < N + 1; i++) {
            int tmp = 0;
            for (int j = 1; j < N + 1; j++) {
                if (dp[i][j] >= INF) continue;
                if (dp[i][j] <= M) {
                    tmp += items[j];
                }
            }
            max = Math.max(max, tmp);
        }

        System.out.println(max);
    }
}
