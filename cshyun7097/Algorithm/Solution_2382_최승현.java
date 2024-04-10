package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_2382_최승현 {
    //우 상 좌 하
    static int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};
    static int N, M, K;
    static PriorityQueue<Microbe> pq;
    static Microbe[][] microbes;

    static class Microbe implements Comparable<Microbe> {
        @Override
        public String toString() {
            return "Microbe{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    ", dir=" + dir +
                    '}';
        }

        int x, y, cnt, dir;

        void move() {
            x += dx[dir];
            y += dy[dir];
            if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
                cnt /= 2;
                dir = (dir + 2) % 4;
            }
        }

        public Microbe(int x, int y, int cnt, int dir) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Microbe o) {
            return o.cnt - this.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            pq = new PriorityQueue<>();
            microbes = new Microbe[N][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                //방향 전환을 쉽게하기 위해 우 상 좌 하 로 변경
                if (dir == 4) {
                    dir = 0;
                } else if (dir == 2) {
                    dir = 3;
                } else if (dir == 3) {
                    dir = 2;
                }
                microbes[x][y] = new Microbe(x, y, cnt, dir);
            }
            for (int i = 0; i < M; i++) {
                //이동
                move();
                //먹기
                eat();
            }
            sb.append(getNum()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void eat() {
        Microbe[][] tmp = new Microbe[N][N];
        while (!pq.isEmpty()) {
            Microbe now = pq.poll();
            if (tmp[now.x][now.y] == null) {
                tmp[now.x][now.y] = now;
            } else {
                tmp[now.x][now.y].cnt += now.cnt;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                microbes[i][j] = tmp[i][j];
            }
        }
    }

    private static void move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (microbes[i][j] != null) {
                    microbes[i][j].move();
                    pq.offer(microbes[i][j]);
                }
            }
        }
    }

    private static int getNum() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (microbes[i][j] != null) {
                    ans += microbes[i][j].cnt;
                }
            }
        }
        return ans;
    }
}
