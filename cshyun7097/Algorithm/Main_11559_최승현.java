package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11559_최승현 {
    static final int N = 12, M = 6;
    static char[][] board = new char[N][M];
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static Queue<XY> able = new LinkedList<>();

    static class XY {
        int x, y;

        public XY(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        for (int i = 0; i < 12; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = tmp.charAt(j);
            }
        }
        int ans = 0;
        // 4개 이상 붙어있는 블록 찾기
        while (true) {
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (!visited[i][j] && board[i][j] != '.') {
                        bfs(i, j);
                    }
                }
            }
            if (able.isEmpty()) break;
            //터뜨리기
            visited = new boolean[N][M];
            while (!able.isEmpty()) {
                broke(able.poll());
            }
            //내리기
            blockDown();
            ans++;
        }
        //정답 출력하기
        System.out.println(ans);
    }

    private static void broke(XY xy) {
        Queue<XY> queue = new LinkedList<>();
        char tmp = board[xy.x][xy.y];
        queue.offer(new XY(xy.x, xy.y));
        visited[xy.x][xy.y] = true;
        board[xy.x][xy.y] = '.';
        while (!queue.isEmpty()) {
            XY cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx, ny) || visited[nx][ny] || board[nx][ny] != tmp) continue;
                visited[nx][ny] = true;
                queue.offer(new XY(nx, ny));
                board[nx][ny] = '.';
            }
        }
    }

    private static void bfs(int x, int y) {
        int cnt = 1;
        Queue<XY> queue = new LinkedList<>();
        queue.offer(new XY(x, y));
        visited[x][y] = true;
        while (!queue.isEmpty()) {
            XY cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx, ny) || visited[nx][ny] || board[nx][ny] != board[x][y]) continue;
                cnt++;
                visited[nx][ny] = true;
                queue.offer(new XY(nx, ny));
            }
        }
        if (cnt >= 4) {
            able.offer(new XY(x, y));
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    //블록 내리기 함수
    private static void blockDown() {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (board[j][i] != '.') {
                    stack.push(board[j][i]);
                    board[j][i] = '.';
                }
            }
            for (int j = N - 1; j >= 0; j--) {
                if (!stack.isEmpty()) {
                    board[j][i] = stack.pop();
                } else {
                    break;
                }
            }
        }
    }
}
