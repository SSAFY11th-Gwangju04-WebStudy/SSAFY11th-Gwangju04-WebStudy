## 개념

- Set 인터페이스를 구현한 클래스
- Set의 성질을 그대로 가짐
- Binary Search Tree의 구조
    - Binary Search Tree는 추가와 삭제에는 시간이 조금 더 걸리지만 정렬, 검색에 높은 성능을 보이는 자료구조
- 기본적으로 nature ordering을 지원하며 생성자의 매개변수로 Comparator 객체를 입력하여 정렬 방법을 임의로 지정해 줄 수 있음

## 구현

- Binary Search Tree 중에서도 성능을 향상시킨 Red-Black Tree로 구현
- Red-Black Tree
    - 부모노드보다 작은 값을 가지는 노드는 왼쪽 자식으로, 큰 값을 가지는 노드는 오른쪽 자식으로 배치하여 데이터의 추가나 삭제 시 Tree가 한쪽으로 치우쳐지지 않도록 균형을 맞추는 Tree

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/10e189df-f5b5-49a6-a187-d962b314cfe7/Untitled.png)

## Tree 선언

```java
import java.util.TreeSet;
import java.util.Arrays;

TreeSet<Integer> ts1 = new TreeSet<Integer>();
TreeSet<Integer> ts1 = new TreeSet<>();
TreeSet<Integer> ts1 = new TreeSet<Integer>(Arrays.asList(1, 2 ,3);
```

## TreeSet 값 추가 - add(value)

```java
TreeSet<Integer> ts1 = new TreeSet<>();
ts1.add(5);
ts1.add(3);
ts1.add(9);
ts1.add(7);
```

## TreeSet 값(전체) 삭제 - remove(value), clear()

```java
ts1.remove(5);
ts1.clear();
```

## TreeSet 크기 구하기 - size()

```java
System.out.println(ts1.size());
```

## TreeSet 내림차순 정렬

```java
TreeSet<Integer> ts1 = new TreeSet<>(Comparator.reverseOrder()); // Comparator 입력하여 임의로 내림차순 정렬
ts1.add(5);
ts1.add(3);
ts1.add(9);

Iterator iter = ts1.iterator();
while(iter.hasNext())
    System.out.print(iter.next() + " "); // 출력결과 : 9 5 3
```

## Tip

> 중복을 허용하는 TreeSet
> 
- 본래 TreeSet은 중복을 허용하지 않는 자료구조이지만, 아래와 같이 Comparator를 정의해주면 중복을 허용한다.
    
    ```java
    TreeSet<Integer> ts1 = new TreeSet<>(new Comparator<Integer>(){
    			
    			@override
    			public int compare(Integer o1, Integer o2){
    					return o1 > o2 ? 1 : -1;
    			}
    });
    ```
