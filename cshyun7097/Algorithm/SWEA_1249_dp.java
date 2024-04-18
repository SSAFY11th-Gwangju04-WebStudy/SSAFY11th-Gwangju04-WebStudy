import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SWEA_1249_dp {
    static int N, map[][], dp[][];
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};

    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dp = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for (int i = 0; i < N; i++) {
                String tmp = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = tmp.charAt(j) - '0';
                }
            }
            sb.append(bfs()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int bfs() {
        int min = Integer.MAX_VALUE;
        Queue<XY> queue = new LinkedList<>();
        dp[0][0] = map[0][0];
        queue.offer(new XY(0, 0));
        while (!queue.isEmpty()) {
            XY cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;

            if (curX == N - 1 && curY == N - 1) {
                min = Math.min(min, dp[curX][curY]);
            }

            if (min <= dp[curX][curY]) continue;

            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx, ny)) continue;
                if (dp[nx][ny] > dp[curX][curY] + map[nx][ny]) {
                    dp[nx][ny] = dp[curX][curY] + map[nx][ny];
                    queue.offer(new XY(nx, ny));
                }
            }
        }
        return min;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static void print(int[][] map) {
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
