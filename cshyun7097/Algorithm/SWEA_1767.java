import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767 {
    static int N, board[][], total, min, max;
    static List<Processor> processors;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    static class Processor {
        int x, y;

        public Processor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            N = Integer.parseInt(br.readLine().trim());
            board = new int[N][N];
            processors = new ArrayList<>();
            total = 0;

            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) continue;
                        processors.add(new Processor(i, j));
                        total++;
                    }
                }
            }
            dfs(0, 0, 0);
            sb.append(min).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int cCnt, int lCnt) {
        //기저 조건
        if (depth == total) {
            if (cCnt > max) {
                max = cCnt;
                min = lCnt;
            } else if (cCnt == max) {
                min = Math.min(min, lCnt);
            }
            return;
        }
        int x = processors.get(depth).x;
        int y = processors.get(depth).y;
        //가지치기
        if (cCnt + (total - depth) < max) {
            return;
        }

        //프로세서를 놓았을때
        for (int d = 0; d < 4; d++) {
            //상 하 좌 우로 보냄
            if (isValid(x, y, d)) {
                //1로 변경
                int length = changeBaord(x, y, d, 1);
                //재귀
                dfs(depth + 1, cCnt + 1, lCnt + length);
                //0으로 변경
                changeBaord(x, y, d, 0);
            }
        }
        //프로세서를 놓지 않았을때
        dfs(depth + 1, cCnt, lCnt);
    }

    private static int changeBaord(int x, int y, int d, int num) {
        int nx = x;
        int ny = y;
        int cnt = 0;
        while (true) {
            nx += dx[d];
            ny += dy[d];
            if (!isIn(nx, ny)) break;
            board[nx][ny] = num;
            cnt++;
        }
        return cnt;
    }

    private static boolean isValid(int x, int y, int d) {
        int nx = x;
        int ny = y;
        while (true) {
            nx += dx[d];
            ny += dy[d];
            if (!isIn(nx, ny)) break;
            if (board[nx][ny] == 1) return false;
        }
        return true;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
