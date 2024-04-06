package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_2665_최승현 {
    static int N, room[][];
    static boolean visited[][];
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static class XY implements Comparable<XY>{
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
        N = Integer.parseInt(br.readLine());
        room = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                room[i][j] = tmp.charAt(j) - '0';
            }
        }
        bfs();
    }
    private static void bfs() {
        PriorityQueue<XY> pq = new PriorityQueue<>();
        visited[0][0] = true;
        pq.offer(new XY(0, 0, 0));
        while (!pq.isEmpty()) {
            XY cur = pq.poll();
            int curX = cur.x;
            int curY = cur.y;
            visited[curX][curY] = true;
            if (curX == N - 1 && curY == N - 1) {
                System.out.println(cur.cost);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nx = curX + dx[d];
                int ny = curY + dy[d];
                if (!isIn(nx,ny) || visited[nx][ny]) continue;
                if (room[nx][ny] == 1) {
                    pq.offer(new XY(nx, ny, cur.cost));
                } else {
                    pq.offer(new XY(nx, ny, cur.cost + 1));
                }
            }
        }
    }
    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < N && ny < N;
    }
}