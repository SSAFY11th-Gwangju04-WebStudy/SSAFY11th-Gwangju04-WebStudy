import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17471 {
	static int N;
	static int[] people;
	static List<Integer>[] arr;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		people = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		// 배열생성
		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		// 인접리스트 생성
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 부분집합 생성
		subset(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void subset(int cnt, int mask) {
		if (cnt == N) {
			List<Integer> aList = new ArrayList<>();
			List<Integer> bList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (((1 << i) & mask) != 0) {
					aList.add(i + 1);
				} else {
					bList.add(i + 1);
				}
			}

			if (aList.isEmpty() || bList.isEmpty()) {
				return;
			}

			if (isLink(aList) && isLink(bList)) {
				min = Math.min(min, getNum(aList, bList));
			}
			return;
		}
		subset(cnt + 1, mask); // 포함X
		subset(cnt + 1, 1 << cnt | mask); // 포함
	}

	private static int getNum(List<Integer> aList, List<Integer> bList) {
		int aSum = 0;
		int bSum = 0;

		for (Integer integer : aList) {
			aSum += people[integer];
		}
		for (Integer integer : bList) {
			bSum += people[integer];
		}

		return Math.abs(aSum - bSum);
	}

	private static boolean isLink(List<Integer> list) {
		int start = list.get(0);
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		queue.offer(start);
		int cnt = 1;

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < arr[cur].size(); i++) {
				int next = arr[cur].get(i);
				if (!visited[next] && list.contains(next)) {
					queue.offer(next);
					visited[next] = true;
					cnt++;
				}
			}
		}
		if (cnt == list.size())
			return true;
		else
			return false;
	}
}
