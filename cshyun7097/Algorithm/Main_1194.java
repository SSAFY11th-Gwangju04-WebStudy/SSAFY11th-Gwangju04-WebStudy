import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1194 {
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dx = { 0, 1, 0, -1 }, dy = { 1, 0, -1, 0 };

	static class XY {
		int x, y, key;

		@Override
		public String toString() {
			return "XY [x=" + x + ", y=" + y + ", key=" + key + "]";
		}

		public XY(int x, int y, int key) {
			super();
			this.x = x;
			this.y = y;
			this.key = key;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[1 << 6][N][M];

		int startX = 0, startY = 0;
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String tmp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = tmp.charAt(j);
				if (map[i][j] == '0') {
					startX = i;
					startY = j;
				}
			}
		}
		int ans = bfs(startX, startY);
		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	private static int bfs(int x, int y) {
		Queue<XY> q = new LinkedList<>();
		visited[0][x][y] = true;
		q.offer(new XY(x, y, 0));
		int time = 0;

		while (!q.isEmpty()) {
			int size = q.size();
			time++;
			while (size-- > 0) {
				XY cur = q.poll();
				int curX = cur.x;
				int curY = cur.y;
				if (map[curX][curY] == '1') {
					return time - 1;
				}
				int curKey = cur.key;
				for (int d = 0; d < 4; d++) {
					int nx = curX + dx[d];
					int ny = curY + dy[d];
					if (!isIn(nx, ny) || visited[curKey][nx][ny] || map[nx][ny] == '#')
						continue;
					// 키가 있는 자리면
					if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') {
						int nextKey = ((1 << (map[nx][ny] - 'a')) | curKey);
						visited[nextKey][nx][ny] = true;
						q.offer(new XY(nx, ny, nextKey));
					} else if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') {		//문이면
						if (((1 << (map[nx][ny] - 'A')) & curKey) != 0) {
							visited[curKey][nx][ny] = true;
							q.offer(new XY(nx, ny, curKey));
						}
					}
					// 다른 케이스면
					else {
						visited[curKey][nx][ny] = true;
						q.offer(new XY(nx, ny, curKey));
					}
				}
			}
		}
		return -1;
	}

	private static boolean isIn(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && y >= 0 && x < N && y < M;
	}

}
