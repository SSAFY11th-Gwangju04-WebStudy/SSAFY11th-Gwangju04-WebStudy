# 다익스트라

---

## 정의

---

- 다이나믹 프로그래밍을 활용한 대표적인 _최단 경로(Shortest Path) 탐색 알고리즘_ 이며 특히 인공위성 GPS 소프트웨어 등에서 가장 많이 사용
- 특정한 하나의 정점에서 다른 모든 정점으로 가는 최단 경로르 알려줌 -> 음의 간선은 포함할 수 없음 => _현실 세계에 사용하기 매우 적절한 알고리즘 중 하나_ 이다.

![다익스트라](/pic/다익스트라_그래프1.png)

- 위의 그림에서 1에 당장 붙어 있는 노드인 2,3,4까지의 최단 거리를 각각 3, 6, 7로 산정할 수 있다. 이후 2번 노드를 처리하면 3까지의 최단 거리는 4로 갱신됨

## 작동 과정

---

1. 출발 노드를 설정
2. 출발 노드를 기준으로 각 노드의 최소 비용 저장
3. 방문하지 않은 노드 중에서 가장 비용이 적은 노드 선택
4. 해당 노드를 거쳐서 특정 노드로 가는 경우를 고려하여 최소 비용을 갱신
5. 위 과정에서 3~4번 반복

## 자바 코드

---

```java
class Node implements Comparable<Node>{
	int index;
	int cost;

	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}

public class Main {
	static ArrayList<Node>[] graph;

    //노드의 크기, 출발지
	public static void Dijkstra(int n, int start) {
		boolean[] check = new boolean[n + 1];
		int[] dist = new int[n + 1];
		int INF = Integer.MAX_VALUE;

		Arrays.fill(dist, INF);
		dist[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));

		while(!pq.isEmpty()) {
			int nowVertex = pq.poll().index;

			if(check[nowVertex]) continue;
			check[nowVertex] = true;

			//index의 연결된 정점 비교
			for(Node next : graph[nowVertex]) {
                //다음 노드의 값보다 현재 + 다음 노드의 비용이 적으면 초기화
				if(dist[next.index] > dist[nowVertex]+ next.cost) {
					dist[next.index] = dist[nowVertex] + next.cost;

					pq.offer(new Node(next.index, dist[next.index]));
				}
			}
		}

        //최소거리 출력
		for(int i : dist) {
			if(i == INF) System.out.print(0 + " ");
			else System.out.print(i+" ");
		}
	}

	public static void main(String[] args) throws IOException {

    //그래프 입력 받기
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		//정점의 개수, 간선의 개수
		int n = Integer.parseInt(bf.readLine());
		int m = Integer.parseInt(bf.readLine());

		graph = new ArrayList[n+1];
		for (int i = 0; i <= n; i++)  graph[i] = new ArrayList<>();

		StringTokenizer st;
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(bf.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph[v].add(new Node(w, cost));
		}

		int start = Integer.parseInt(bf.readLine());

		//다익스트라 알고리즘 수행
		Dijkstra(n, start);

	}
}
```

```plain text
입력
5
9
1 2 10
1 3 5
2 3 2
3 1 1
3 2 13
4 1 8
4 5 3
5 4 9
5 2 31
4 3

출력 결과
0 8 18 13 0 3 //인덱스 0은 사용 x
```
