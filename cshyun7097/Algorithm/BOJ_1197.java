import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197 {
    static int V, E, parents[];
    static Edge[] edges;
    static class Edge implements Comparable<Edge>{
        int from ,to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);

        parents = new int[V + 1];
        make();

        int cnt = 0;
        int weight = 0;

        for (Edge edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            weight += edge.weight;
            if (++cnt == V - 1) {
                break;
            }
        }
        System.out.println(weight);
    }

    private static void make() {
        for (int i = 1; i < V+1; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) {
            return false;
        }
        parents[aRoot] = bRoot;
        return true;
    }
}
