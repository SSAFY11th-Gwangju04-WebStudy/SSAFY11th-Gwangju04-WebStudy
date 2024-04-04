
- 프림(Prim) 알고리즘은 그래프 내에서 최소 신장 트리(Minimum Spanning Tree, MST)를 찾는 데 사용되는 알고리즘입니다.
- 최소 신장 트리란 그래프의 모든 정점을 최소한의 비용으로 연결하는 부분 그래프를 의미합니다.
- 프림 알고리즘은 그리디 알고리즘의 일종으로, 임의의 시작 정점에서 출발하여 점진적으로 MST를 확장해 나가는 방식으로 동작합니다.

### 알고리즘의 동작 과정

1. **초기화**: MST를 구성할 때까지 사용할 정점 집합을 비워둡니다. 이 집합에는 알고리즘 실행 과정에서 선택된 정점들이 추가됩니다.
2. **시작 정점 선택**: 그래프의 임의의 정점을 선택하고 이 정점을 현재 MST의 일부로 간주합니다(시작 정점을 정점 집합에 추가).
3. **연결 간선 선택**: 현재 정점 집합에 속하지 않은 정점들 중에서, 집합에 속한 어떤 정점과도 연결된 간선들 중 가장 가중치가 낮은 간선을 찾습니다. 이 간선과 이 간선으로 연결된 정점을 현재 MST에 추가합니다.
4. **반복**: 모든 정점이 MST에 포함될 때까지 3번 과정을 반복합니다.

### 알고리즘의 특징

- 그래프의 모든 정점을 최소 비용으로 연결합니다.
- 비순환 연결 부분 그래프인 신장 트리를 만듭니다.
- 가중치가 있는 연결 그래프에 적용됩니다(가중치는 양수여야 함).
- 시간 복잡도는 사용하는 자료 구조에 따라 달라지며, 일반적으로 `O(E log V)`입니다(E는 간선의 수, V는 정점의 수).

### 프림 알고리즘 구현 (자바 예시)

```java
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class PrimAlgorithm {
    static class Edge implements Comparable<Edge> {
        int target, cost;

        Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        int V = 5; // 정점의 수
        LinkedList<Edge>[] graph = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new LinkedList<>();
        }

        // 그래프의 간선 정보를 추가 (예시)
        graph[0].add(new Edge(1, 2));
        graph[1].add(new Edge(0, 2));
        
        graph[1].add(new Edge(2, 3));
        graph[2].add(new Edge(1, 3));
        
        graph[2].add(new Edge(3, 6));
        graph[3].add(new Edge(2, 6));
        
        graph[3].add(new Edge(4, 9));
        graph[4].add(new Edge(3, 9));
        
        graph[4].add(new Edge(0, 7));
        graph[0].add(new Edge(4, 7));

        // 프림 알고리즘 실행
        prim(graph, V);
    }

    public static void prim(LinkedList<Edge>[] graph, int V) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        LinkedList<Edge> mst = new LinkedList<>();

        // 시작 정점으로부터 시작
        pq.addAll(graph[0]);
        visited[0] = true;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();



            if (visited[edge.target]) continue;

            visited[edge.target] = true;
            mst.add(edge);

            for (Edge nextEdge : graph[edge.target]) {
                if (!visited[nextEdge.target]) {
                    pq.add(nextEdge);
                }
            }
        }

        // 결과 출력
        int totalCost = 0;
        for (Edge edge : mst) {
            totalCost += edge.cost;
            System.out.println(edge.target + " 연결 비용: " + edge.cost);
        }
        System.out.println("총 연결 비용: " + totalCost);
    }
}
```

이 예시는 정점의 수가 5개인 간단한 그래프에 프림 알고리즘을 적용한 것입니다. 최소 신장 트리를 구성하는 간선들과 총 비용을 출력합니다. 실제 구현 시에는 그래프의 구조나 입력 방식에 따라 코드를 조정할 필요가 있습니다.