import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_2115 {
    static int N, M, C, max;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());           //벌통 크기
            M = Integer.parseInt(st.nextToken());           //선택 가능 벌통 수
            C = Integer.parseInt(st.nextToken());           //채취 최대 양
            map = new int[N][N];
            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    dp[i][j] = getMax(i, j);
                }
            }
            int ans = 0;
            // 2차원 배열에서 좌표 2개를 정하는 방법
            for (int i = 0; i < (N * N) - M; i++) {
                int x = i / N, y = i % N;
                for (int j = i + M; j < N * N; j++) {
                    int nx = j / N, ny = j % N;
                    ans = Math.max(ans, dp[x][y] + dp[nx][ny]);
                }
            }
            sb.append(ans).append("\n");
        }//end tc
        System.out.println(sb.toString());
    }//end main

    private static int getMax(int x, int y) {
        max = 0;
        int[] tmp = new int[M];
        for (int i = 0; i < M; i++) {
            if (y + i == N) return 0;
            tmp[i] = map[x][y + i];
        }
        subset(0, 0, tmp);
        return max;
    }//end getMax

    private static void subset(int cnt, int sub, int[] tmp) {
        if (cnt == M) {
            int sum = 0;
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                if (((1 << i) & sub) != 0) {
                    arr.add(i);
                    sum += tmp[i];
                    if (sum > C) return;
                }
            }
            sum = 0;
            for (Integer i : arr) {
                sum += (int) Math.pow(tmp[i], 2);
            }
            max = Math.max(max, sum);
            return;
        }
        subset(cnt + 1, sub, tmp);
        subset(cnt + 1, 1 << cnt | sub, tmp);
    }

    private static void print() {
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }//end print()
}//end class
