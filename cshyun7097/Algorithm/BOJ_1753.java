import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1753 {
    static List<Node>[] arr;
    static int V, E;
    static final int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node> {
        int idx, weight;

        public Node(int idx, int wight) {
            this.idx = idx;
            this.weight = wight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        int start = Integer.parseInt(br.readLine());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[from].add(new Node(to, weight));
        }

        dijkstra(start);
    }

    private static void dijkstra(int start) {
        int[] dist = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int idx = cur.idx;

            if (visited[idx]) continue;
            visited[idx] = true;

            for (Node node : arr[idx]) {
                if (dist[node.idx] > dist[idx] + node.weight) {
                    dist[node.idx] = dist[idx] + node.weight;
                    pq.offer(new Node(node.idx, dist[node.idx]));
                }
            }
        }
        for (int i = 1; i < V+1; i++) {
            System.out.println(dist[i] == INF ? "INF" : dist[i]);
        }
    }
}
