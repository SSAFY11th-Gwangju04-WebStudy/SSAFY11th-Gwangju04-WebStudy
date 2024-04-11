[프로그래머스 의상](https://school.programmers.co.kr/learn/courses/30/lessons/42578)

```python
def solution(clothes):
    answer = 1
    clothes_dict = dict()
    
    for n, t in clothes:
        if t not in clothes_dict:
            clothes_dict[t] = []
        clothes_dict[t].append(n)

    for cloth in clothes_dict.keys():
        answer *= (len(clothes_dict[cloth])+1)
        
    return answer-1
```
[프로그래머스 피로도](https://school.programmers.co.kr/learn/courses/30/lessons/87946)

```python
rst = 0

def dfs(visit, state, dungeons, k):
    global rst
    rst = max(rst, state)
    
    for i in range(len(dungeons)):
        if visit[i]:
            continue
        if dungeons[i][0]>k:
            continue
        visit[i] = True
        dfs(visit, state+1, dungeons,k- dungeons[i][1])    
        visit[i] = False
    
def solution(k, dungeons):
    global rst
    
    dfs([False]*len(dungeons), 0, dungeons, k)
    
    return rst
```

[프로그래머스 기능개발](https://school.programmers.co.kr/learn/courses/30/lessons/42586)

```python
from collections import deque

def solution(progresses, speeds):
    answer = []
    progresses = deque(progresses)
    speeds = deque(speeds)
    while progresses:
        cnt = 0
        while progresses and progresses[0]>=100:
            progresses.popleft()
            speeds.popleft()
            cnt+=1
        if cnt > 0:
            answer.append(cnt)
        if not progresses:
            break
        N = len(progresses)
        for i in range(N):
            progresses[i] += (speeds[i])
        
        
    return answer
```

### 개선된 풀이

```python
from collections import deque
import math

def solution(progresses, speeds):
    answer = []
    progresses = deque(progresses)
    speeds = deque(speeds)
    while progresses:
        cnt = 0
        while progresses and progresses[0]>=100:
            progresses.popleft()
            speeds.popleft()
            cnt+=1
        if cnt > 0:
            answer.append(cnt)
        if not progresses:
            break
        N = len(progresses)
        l = math.ceil((100 - progresses[0]) / speeds[0])
        for i in range(N):
            progresses[i] += l*(speeds[i])
        
        
    return answer
```
