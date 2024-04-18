import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SWEA_1249 {
    static int N, map[][];
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
    static class XY implements Comparable<XY> {
        int x, y, cost;

        public XY(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(XY o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N][N];
            map = new int[N][N];
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
        PriorityQueue<XY> pq = new PriorityQueue<>();
        visited[0][0] = true;
        pq.offer(new XY(0, 0, 0));
        while (!pq.isEmpty()) {
            XY cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            visited[curX][curY] = true;
            if (curX == N - 1 && curY == N - 1) {
                return cur.cost;
            }
            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx,ny) || visited[nx][ny]) continue;
                pq.offer(new XY(nx, ny, cur.cost + map[nx][ny]));
            }
        }
        return -1;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    private static void print() {
        for (int[] ints : map) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
