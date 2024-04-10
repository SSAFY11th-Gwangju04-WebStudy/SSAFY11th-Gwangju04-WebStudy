import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SWEA_5656 {
    static int N, W, H, min;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    static class Point {
        int x, y, cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            game(0, map);
            sb.append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean game(int depth, int[][] map) {
        int remainBrick = getNum(map);
        if (remainBrick == 0) {
            min = 0;
            return true;
        }
        if (depth == N) {
            min = Math.min(min, remainBrick);
            return false;
        }
        int[][] newMap = new int[H][W];
        //0 ~ W-1열까지 떨구기
        for (int i = 0; i < W; i++) {
            //맨 위 찾기
            int x = 0;
            while (x < H && map[x][i] == 0) x++;
            if (x == H) continue;               //바닥까지 벽돌 없음

            copyMap(map, newMap);
            int brick = map[x][i];
            //구슬 떨어뜨리기
            boom(x, i, newMap);

            if (brick > 1) {
                //중력작용
                blockDown(newMap);
            }
            game(depth + 1, newMap);
        }
        return false;
    }

    private static int getNum(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j] > 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private static void blockDown(int[][] map) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (map[j][i] == 0) continue;
                stack.push(map[j][i]);
                map[j][i] = 0;
            }
            int r = H - 1;
            while (!stack.isEmpty()) {
                map[r--][i] = stack.pop();
            }
        }
    }

    private static void copyMap(int[][] map, int[][] newMap) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }

    private static void boom(int x, int y, int[][] map) {
        Queue<Point> queue = new LinkedList<>();
        if (map[x][y] > 1) queue.offer(new Point(x, y, map[x][y]));
        map[x][y] = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur.x;
                int ny = cur.y;
                for (int i = 1; i < cur.cnt; i++) {
                    nx += dx[d];
                    ny += dy[d];
                    if (!isIn(nx, ny) || map[nx][ny] == 0) continue;
                    if (map[nx][ny] > 1) queue.offer(new Point(nx, ny, map[nx][ny]));
                    map[nx][ny] = 0;
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < H && y < W;
    }
}
