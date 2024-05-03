import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_5656 {
	static int T, N, W, H, min;
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };

	static class XY {
		int x, y, cnt;

		public XY(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
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

			min = Integer.MAX_VALUE;

			int[][] map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			game(0, map);
			sb.append(min).append("\n");
		}
		System.out.println(sb.toString());
	}// end main

	private static boolean game(int cnt, int[][] map) {
		int remainBrick = getNum(map);
		// 가지치기
		if (remainBrick == 0) {
			min = 0;
			return true;
		}

		// 기저조건
		if (cnt == N) {
			min = Math.min(min, remainBrick);
			return false;
		}

		// 떨어뜨리기
		int[][] newMap = new int[H][W];
		for (int i = 0; i < W; i++) {
			mapCopy(map, newMap);
			// 최상단 찾기
			int x = 0;
			int y = i;
			while (x < H && map[x][y] == 0)
				x++;
			if (x == H)
				continue;

			// 터뜨리기
			int brick = map[x][y];
			boom(x, y, newMap);

			// 떨어뜨리기
			if (brick > 1) {
				blockDown(newMap);
			}

			// 다음케이스
			game(cnt + 1, newMap);
		}
		return false;
	} // end boom

	private static int getNum(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (map[i][j] > 0)
					cnt++;
			}
		}
		return cnt;
	}// end getNum

	private static void blockDown(int[][] map) {
		Stack<Integer> stack = new Stack<>();
		for (int y = 0; y < W; y++) {
			for (int x = 0; x < H; x++) {
				if (map[x][y] == 0)
					continue;
				stack.push(map[x][y]);
				map[x][y] = 0;
			}

			int idx = H - 1;
			while (!stack.isEmpty())
				map[idx--][y] = stack.pop();
		}

	} // end BlockDown

	private static void boom(int x, int y, int[][] map) {
		boolean[][] visited = new boolean[H][W];
		Queue<XY> q = new LinkedList<>();
		int brick = map[x][y];
		if (brick > 1)
			q.offer(new XY(x, y, map[x][y]));
		map[x][y] = 0;

		while (!q.isEmpty()) {
			XY cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = cur.x;
				int ny = cur.y;
				for (int i = 1; i < cur.cnt; i++) {
					nx += dx[d];
					ny += dy[d];
					if (!isIn(nx, ny) || visited[nx][ny] || map[nx][ny] == 0)
						continue;
					if (map[nx][ny] > 1)
						q.offer(new XY(nx, ny, map[nx][ny]));
					visited[nx][ny] = true;
					map[nx][ny] = 0;
				}
			}
		}
	} // end boom

	private static void mapCopy(int[][] map, int[][] newMap) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	} // end mapCopy

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W;
	} // end isIn
}
