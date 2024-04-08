package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11779 {
    static ArrayList<Node>[] arr;
    static int N, M, dist[], tmp[];
    static final int INF = Integer.MAX_VALUE;
    static boolean[] visited;
    static class Node implements Comparable<Node>{
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        tmp = new int[N + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            arr[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());

        dijkstra(startNode);
        System.out.println(dist[endNode]);          //최단거리

        int cnt = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(endNode);

        while (tmp[endNode] != 0) {
            cnt += 1;
            stack.push(tmp[endNode]);
            endNode = tmp[endNode];
        }

        System.out.println(cnt + 1);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;

            if (visited[idx]) continue;
            visited[idx] = true;

            for (Node node : arr[idx]) {
                if (dist[node.idx] > dist[idx] + node.cost) {
                    dist[node.idx] = dist[idx] + node.cost;
                    pq.offer(new Node(node.idx, dist[node.idx]));
                    tmp[node.idx] = idx;
                }
            }
        }
    }
}
