package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1504_최승현 {
    static int N, E, dist[];
    static ArrayList<Node>[] arr;
    static boolean[] visited;
    static final int INF = 500_000_000;

    static class Node implements Comparable<Node> {
        int idx, cost;

        public Node(int to, int cost) {
            this.idx = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[start].add(new Node(end, cost));
            arr[end].add(new Node(start, cost));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int method1 = 0;
        method1 += dijkstra(1, v1);
        method1 += dijkstra(v1, v2);
        method1 += dijkstra(v2, N);

        int method2 = 0;
        method2 += dijkstra(1, v2);
        method2 += dijkstra(v2, v1);
        method2 += dijkstra(v1, N);

        System.out.println((method1 >= INF && method2 >= INF) ? -1 : Math.min(method1, method2));
    }

    private static int dijkstra(int start, int end) {
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int idx = now.idx;

            if (visited[idx]) continue;
            visited[idx] = true;

            for (Node node : arr[idx]) {
                if (dist[node.idx] > dist[idx] + node.cost) {
                    dist[node.idx] = dist[idx] + node.cost;

                    pq.offer(new Node(node.idx, dist[node.idx]));
                }
            }
        }
        return dist[end];
    }
}
