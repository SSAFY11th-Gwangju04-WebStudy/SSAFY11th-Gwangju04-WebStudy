package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1194 {
    /*
    . : 빈칸
    # : 벽
    a~f : 열쇠
    A ~ F : 문(열쇠 있어야함)
    0 : 민식이 위치
    1 : 출구
     */
    static int N, M;
    static boolean[][][] visited;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    static class XY {
        int x, y, key;

        public XY(int x, int y, int key) {
            this.x = x;
            this.y = y;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if (N == 1 && M == 1) {
            System.out.println(1);
            System.exit(0);
        }
        map = new char[N][M];
        visited = new boolean[1 << 6][N][M];

        int nowI = 0, nowJ = 0;
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == '0') {
                    nowI = i;
                    nowJ = j;
                }
            }
        }
        bfs(nowI, nowJ);
    }

    private static void bfs(int x, int y) {
        Queue<XY> queue = new LinkedList<>();
        visited[0][x][y] = true;
        queue.offer(new XY(x, y, 0));
        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            cnt++;
            while (size-- > 0) {
                XY cur = queue.poll();
                int curX = cur.x;
                int curY = cur.y;
                int key = cur.key;
                if (map[curX][curY] == '1') {
                    System.out.println(cnt - 1);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nx = curX + dx[d];
                    int ny = curY + dy[d];
                    if (!isIn(nx,ny) || visited[key][nx][ny] || map[nx][ny] == '#') continue;
                    if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
                        int nextKey = (1 << (map[nx][ny] - 'a') | key);
                        visited[nextKey][nx][ny] = true;
                        queue.offer(new XY(nx, ny, nextKey));
                    } else if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') {
                        if (((1 << (map[nx][ny] - 'A')) & key) != 0) {
                            visited[key][nx][ny] = true;
                            queue.offer(new XY(nx, ny, key));
                        }
                    } else {
                        visited[key][nx][ny] = true;
                        queue.offer(new XY(nx, ny, key));
                    }
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}
