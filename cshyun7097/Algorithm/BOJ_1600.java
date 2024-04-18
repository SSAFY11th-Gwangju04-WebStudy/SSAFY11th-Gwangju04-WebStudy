import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {
    static int K, W, H;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0}, hx = {-1, -2, -2, -1, 1, 1, 2, 2}, hy = {-2, -1, 1, 2, -2, 2, -1, 1};
    static boolean[][][] visited;

    static class XY {
        int x, y, k;

        public XY(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[K + 1][H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    private static void bfs() {
        Queue<XY> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.offer(new XY(0, 0, 0));
        int cnt = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            while (size-- > 0) {
                XY cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                int curK = cur.k;
                if (curX == H - 1 && curY == W - 1) {
                    System.out.println(cnt - 1);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx, ny) || visited[curK][nx][ny] || map[nx][ny] == 1) continue;
                    visited[curK][nx][ny] = true;
                    queue.offer(new XY(nx, ny, curK));
                }
                if (curK + 1 > K) continue;
                for (int d = 0; d < 8; d++) {
                    int nx = curX + hx[d];
                    int ny = curY + hy[d];
                    if (!isIn(nx, ny) || visited[curK + 1][nx][ny] || map[nx][ny] == 1) continue;
                    visited[curK + 1][nx][ny] = true;
                    queue.offer(new XY(nx, ny, curK + 1));
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W;
    }
}
