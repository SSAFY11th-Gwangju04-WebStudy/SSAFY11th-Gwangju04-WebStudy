유니온 파인드(Union-Find) 자료구조는 집합들의 합집합과 해당 집합에 속한 원소를 찾는 연산을 효율적으로 처리할 수 있도록 설계된 자료구조입니다. 주로 서로소 집합(Disjoint Sets)을 관리하고, 두 원소가 같은 집합에 속해 있는지 빠르게 판별하는 데 사용됩니다. 유니온 파인드 자료구조는 크루스칼 알고리즘과 같은 그래프 알고리즘에서 사이클을 검출하거나, 여러 네트워크 연결 문제를 해결하는 데 유용합니다.

유니온 파인드 자료구조의 주요 연산은 다음과 같습니다:

### 1. Find

- **목적**: 주어진 원소가 속한 집합의 대표값(루트 노드)를 찾는 연산입니다.
- **작동 원리**: 각 원소는 자신이 속한 집합의 대표값을 가리키는 포인터를 갖습니다. Find 연산은 이 포인터들을 따라가면서 최종적으로 대표값을 찾습니다. 경로 압축(Path Compression) 최적화를 통해, 한 번 Find 연산을 수행한 후에는 직접 또는 간접적으로 대표값을 가리키도록 포인터를 갱신함으로써, 후속 Find 연산의 속도를 높일 수 있습니다.

### 2. Union

- **목적**: 두 원소가 속한 집합을 합치는 연산입니다.
- **작동 원리**: 두 원소의 대표값(루트 노드)을 찾고, 하나의 대표값이 다른 집합의 대표값을 가리키도록 합니다. 이렇게 함으로써 두 집합을 하나의 집합으로 합칩니다. 랭크(Rank) 또는 크기(Size)에 기반한 최적화를 사용하여, 더 작은 집합을 더 큰 집합에 합치는 방식으로 Union 연산의 효율을 높일 수 있습니다.

### 유니온 파인드 자료구조 구현 예

```java
public class UnionFind {
    private int[] root;
    private int[] rank;

    public UnionFind(int size) {
        root = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]); // 경로 압축
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else {
                root[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
```

이 구현에서는 두 가지 최적화 기법, 경로 압축(Path Compression)과 랭크(Rank) 기반 합병,을 사용하여 연산의 효율성을 높입니다. 경로 압축은 Find 연산을 수행할 때, 검색 경로상의 모든 노드가 직접 대표값을 가리키도록 포인터를 갱신합니다. 랭크 기반 합

병은 두 트리를 합칠 때, 랭크(또는 깊이)가 더 낮은 트리를 더 높은 트리 아래에 붙이는 방식으로, 트리의 높이를 가능한 낮게 유지합니다.