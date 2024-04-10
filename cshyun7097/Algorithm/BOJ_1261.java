import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261 {
    static int N, M, map[][];
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j) - '0';
            }
        }
        bfs(0, 0);
    }

    private static void bfs(int x, int y) {
        PriorityQueue<XY> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        pq.offer(new XY(x, y, 0));

        int nx, ny;
        while (!pq.isEmpty()) {
            XY cur = pq.poll();
            if (cur.x == N - 1 && cur.y == M - 1) {
                System.out.println(cur.cost);
                return;
            }
            for (int d = 0; d < 4; d++) {
                nx = cur.x + dx[d];
                ny = cur.y + dy[d];
                if (!isIn(nx,ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                pq.offer(new XY(nx, ny, cur.cost + map[nx][ny]));
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
