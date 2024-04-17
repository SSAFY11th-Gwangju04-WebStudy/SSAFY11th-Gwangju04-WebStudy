import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_2382 {
    static int N, M, K;
    static Virus[][] virus;
    static PriorityQueue<Virus> pq = new PriorityQueue<>();
    static class Virus implements Comparable<Virus> {
        int x, y, d, cnt;
        int[] dx = {0, -1, 0, 1}, dy = {1, 0, -1, 0};

        public Virus(int x, int y, int cnt, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Virus o) {
            return o.cnt - this.cnt;
        }

        public void move() {
            x += dx[d];
            y += dy[d];
            if (x == 0 || y == 0 || x == N - 1 || y == N - 1) {
                cnt /= 2;
                d = (d + 2) % 4;
            }
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
            N = Integer.parseInt(st.nextToken());               //셀의 개수
            M = Integer.parseInt(st.nextToken());               //격리 시간
            K = Integer.parseInt(st.nextToken());               //미생물 군집의 개수
            virus = new Virus[N][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                if (d == 4) {
                    d = 0;
                } else if (d == 2) {
                    d = 3;
                } else if (d == 3) {
                    d = 2;
                }
                virus[x][y] = new Virus(x, y, cnt, d);
            }
            for (int i = 0; i < M; i++) {
                //미생물 이동
                move();
                //미생물 먹기
                eat();
            }
            //개수 세기
            sb.append(getNum()).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int getNum() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (virus[i][j] != null) {
                    ans += virus[i][j].cnt;
                }
            }
        }
        return ans;
    }

    private static void eat() {
        Virus[][] tmp = new Virus[N][N];
        while (!pq.isEmpty()) {
            Virus cur = pq.poll();
            int x = cur.x;
            int y = cur.y;
            if (tmp[x][y] == null) {
                tmp[x][y] = cur;
            } else {
                tmp[x][y].cnt += cur.cnt;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                virus[i][j] = tmp[i][j];
            }
        }
    }

    private static void move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (virus[i][j] != null) {
                    virus[i][j].move();
                    pq.offer(virus[i][j]);
                }
            }
        }
    }
}
