import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_17471 {
    static int people[], N;
    static int min = Integer.MAX_VALUE;
    static List<Integer>[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        people = new int[N];
        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tmp; j++) {
                arr[i].add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        subset(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void subset(int cnt, int sub) {
        if (cnt == N) {
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();
            //subset별로 넣기
            for (int i = 0; i < N; i++) {
                if ((1 << i & sub) != 0) {
                    aList.add(i);
                } else {
                    bList.add(i);
                }
            }
            //둘중 하나가 0개인지 확인
            if (aList.isEmpty() || bList.isEmpty()) {
                return;
            }
            //연결됬는지 확인
            if (isLink(aList) && isLink(bList)) {
                getNum(aList, bList);
            }

            return;
        }
        subset(cnt + 1, sub);
        subset(cnt + 1, 1 << cnt | sub);
    }

    private static void getNum(List<Integer> aList, List<Integer> bList) {
        int aSum = 0, bSum = 0;
        for (Integer i : aList) {
            aSum += people[i];
        }
        for (Integer i : bList) {
            bSum += people[i];
        }
        int diff = Math.abs(aSum - bSum);
        min = Math.min(min, diff);
    }

    private static boolean isLink(List<Integer> list) {
        boolean[] visited = new boolean[N];
        int start = list.get(0);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < arr[cur].size(); i++) {
                int next = arr[cur].get(i);
                if (!visited[next] && list.contains(next)) {
                    cnt++;
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
        if (cnt == list.size()) {
            return true;
        }else {
            return false;
        }
    }
}
