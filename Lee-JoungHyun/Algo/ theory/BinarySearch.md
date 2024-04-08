# BinarySearch

- 정렬이 되있는 것을 LogN 의 시간으로 탐색하는 매우 빠른 검색 알고리즘
- Arrays.BinarySearch(), Collections.BinarySearch() 메서드를 지원한다
- Lower and Upper Bound 가 있다


## Upper Bound

- 범위 [begin, end) 안의 원소들 중, **특정 target보다 큰 첫번째 원소의 인덱스**를 리턴한다. 만약 그런 원소가 없다면 end 인덱스를 리턴한다.
    - max를 마지막 인덱스 + 1 로 지정해야 함!
- 가능한 값중 최댓값

```java
private static int upperBound(List<Integer> data, int target) {
    int begin = 0;
    int end = data.size();
    
    while(begin < end) {
    	int mid = (begin + end) / 2;
        
        if(data.get(mid) <= target) {
        	begin = mid + 1;
        }
        else {
        	end = mid;
        }
    }
    return end;
    // return end - 1; 로 많이 사용함
}
```


## Lower Bound

- 범위 [begin, end) 안의 원소들 중, 특정 target보다 크거나 같은 **첫번째 원소의 인덱스**를 리턴한다. 만약 그런 원소가 없다면 end 인덱스를 리턴
- 가능한 값중 최솟값!

```java
private static int lowerBound(List<Integer> data, int target) {
    int begin = 0;
    int end = data.size();
    
    while(begin < end) {
    	int mid = (begin + end) / 2;
        
        if(data.get(mid) >= target) {
        	end = mid;
        }
        else {
        	begin = mid + 1;
        }
    }
    return end;
}
```


### Collection.binarySearch(List<T>, T key)

- 해당 요소를 찾게 되면 그 `idx`를 반환
- 해당 요소를 찾지 못하면 `-(upper bound idx)` 를 반환! → ( 사용시 -1 * (idx + 1) )
