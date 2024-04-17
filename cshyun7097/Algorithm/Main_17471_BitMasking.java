package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17471_BitMasking {
    static int N, people[];
    static int ans = Integer.MAX_VALUE;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }
        people = new int[N];

        st = new StringTokenizer(br.readLine());
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
        comb(0, 0);
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    //비트마스킹을 사용한 서브셋은 int형으로~
    private static void comb(int cnt, int subset) {
        if (cnt == N) {
            //subset을 통한 a,b 지역 나누기
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if ((1 << i & subset) != 0) {
                    aList.add(i);
                } else {
                    bList.add(i);
                }
            }
            //2개중 하나의 사이즈가 0이면 구역을 나누었다고 볼 수 없음
            if (aList.isEmpty() || bList.isEmpty()) {
                return;
            }

            //두 리스트가 모두 연결되었다고 판단되면 계산시작
            if (isLink(aList) && isLink(bList)) {
                getNum(aList, bList);
            }
            return;
        }

        // ??
        comb(cnt + 1, subset);
        comb(cnt + 1, 1 << cnt | subset);
    }

    private static void getNum(List<Integer> aList, List<Integer> bList) {
        int aSum = 0;               //a지역의 인구수
        int bSum = 0;               //b지역의 인구수
        for (Integer i : aList) {
            aSum += people[i];
        }
        for (Integer i : bList) {
            bSum += people[i];
        }

        ans = Math.min(ans, Math.abs(aSum - bSum));
    }

    private static boolean isLink(List<Integer> list) {
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();

        int x = list.get(0);
        queue.offer(x);
        visited[x] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < arr[cur].size(); i++) {
                int nx = arr[cur].get(i);
                if (!visited[nx] && list.contains(nx)) {
                    queue.offer(nx);
                    visited[nx] = true;
                    cnt++;
                }
            }
        }
        if (cnt == list.size()) return true;
        else return false;
    }
}
