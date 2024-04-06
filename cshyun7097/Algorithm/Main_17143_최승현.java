package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17143_최승현 {
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, -1, 0, 1};
    static int R, C, M;
    static Shark[][] sharks;

    static class Shark {
        int x, y, s, d, z;

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", s=" + s +
                    ", d=" + d +
                    ", z=" + z +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharks = new Shark[R][C];
        int ans = 0;
        //입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;   //행
            int c = Integer.parseInt(st.nextToken()) - 1;   //열
            int s = Integer.parseInt(st.nextToken());   //속력
            int d = Integer.parseInt(st.nextToken());   //이동방향
            int z = Integer.parseInt(st.nextToken());   //크기

            if(d == 1)
                d = 0;
            else if(d == 4)
                d = 1;

            sharks[r][c] = new Shark(r, c, s, d, z);
        }
        int fisher = -1;
        while (++fisher < C) {
            //상어 잡기
            ans += catchShark(fisher);
            //상어 이동 후 먹기
            moveShark();
        }
        System.out.println(ans);
    }

    private static void moveShark() {
        Shark[][] copyShark = new Shark[R][C];
        Queue<Shark> queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sharks[i][j] != null) {
                    queue.add(sharks[i][j]);
                }
            }
        }
        while (!queue.isEmpty()) {
            Shark cur = queue.poll();
            int curS = cur.s;
            if (cur.d == 0 || cur.d == 2) {
                curS %= (R - 1) * 2;
            } else if (cur.d == 1 || cur.d == 3) {
                curS %= (C - 1) * 2;
            }
            for (int i = 0; i < curS; i++) {
                int nx = cur.x + dx[cur.d];
                int ny = cur.y + dy[cur.d];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    cur.x -= dx[cur.d];
                    cur.y -= dy[cur.d];
                    cur.d = (cur.d + 2) % 4;
                    continue;
                }

                cur.x = nx;
                cur.y = ny;
            }
            if (copyShark[cur.x][cur.y] != null) {
                if (copyShark[cur.x][cur.y].z < cur.z) {
                    copyShark[cur.x][cur.y] = cur;
                }
            } else {
                copyShark[cur.x][cur.y] = cur;
            }
        }
        returnShark(copyShark);
    }

    private static void returnShark(Shark[][] copyShark) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sharks[i][j] = copyShark[i][j];
            }
        }
    }

    private static int catchShark(int fisher) {
        int tmp = 0;
        for (int i = 0; i < R; i++) {
            if (sharks[i][fisher] != null) {
                tmp = sharks[i][fisher].z;
                sharks[i][fisher] = null;
                break;
            }
        }
        return tmp;
    }
}
