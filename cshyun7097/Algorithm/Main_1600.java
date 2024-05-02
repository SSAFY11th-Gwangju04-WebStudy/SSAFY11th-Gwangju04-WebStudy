import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600 {
	static int K, W, H;
	static int[][] map;

	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 }, hx = { -1, -2, -2, -1, 1, 2, 2, 1 },
			hy = { -2, -1, 1, 2, -2, -1, 1, 2 };

	static class XY {
		int x, y, cnt;

		public XY(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 넘을 수 있는 수
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bw.write(bfs() + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int bfs() {
		boolean[][][] visited = new boolean[K + 1][H][W];
		Queue<XY> q = new LinkedList<>();
		visited[0][0][0] = true;
		q.offer(new XY(0, 0, 0));

		int time = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			time++;
			while (size-- > 0) {
				XY cur = q.poll();
				int curX = cur.x;
				int curY = cur.y;
				int curK = cur.cnt;
				
				if (curX == H - 1 && curY == W - 1) {
					return time - 1;
				}
				
				// 일반으로 갔을때
				for (int d = 0; d < 4; d++) {
					int nx = curX + dx[d];
					int ny = curY + dy[d];
					if (!isIn(nx, ny) || visited[curK][nx][ny] || map[nx][ny] == 1)
						continue;
					visited[curK][nx][ny] = true;
					q.offer(new XY(nx, ny, curK));
				}
				if (curK == K)
					continue;
				for (int d = 0; d < 8; d++) {
					int nx = curX + hx[d];
					int ny = curY + hy[d];
					if (!isIn(nx, ny) || visited[curK + 1][nx][ny] || map[nx][ny] == 1)
						continue;
					visited[curK + 1][nx][ny] = true;
					q.offer(new XY(nx, ny, curK + 1));
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W;
	}
}
