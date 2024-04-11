## 슬라이딩 윈도우

> 개념

- 고정 사이즈의 윈도우가 이동하며 윈도우 내에 있는 데이터를 이용해 문제를 푸는 것
    
   <img src = "https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/Algorithms/Concepts/imgs/sliding1.png">
    

> 사용 이유

- 위와 같은 배열에서 연속된 범위의 숫자 합을 구해보자
- 가장 직관적으로 생각할 수 있는 것은
    1. 연속된 숫자의 범위를 구한다
    2. 해당 범위의 숫자 합을 구한다.
    3. 범위를 바꾼다.
    4. 바꾼 범위의 숫자 합을 구하고 이전 숫자와 크기 비교한다.
    5. 3, 4번 반복
    
    이다.
    
    ⇒ 하지만 이는 이중 for문으로 시간 복잡도가 O(N*M)을 가지게 되며, 주어진 숫자 배열이 커지면 시간 초과가 난다.
    

> 방법

- 고정된 범위가 이동하는 부분에 집중해보자
    
   <img src = "https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/Algorithms/Concepts/imgs/sliding2.png">
    
- 위와 같이 겹치는 범위가 존재하는데, 범위를 바꿀때 실제로 바뀌는 값은 고정된 범위(현재 범위와 다음 범위가 겹치는 곳)를 맨 처음 값과 새로 추가한 값 두 개 뿐이다.
- 따라서 고정된 범위에서 바뀌는 값들만 빼고 더하는 형태로 연산해준다.
    
   <img src = "https://github.com/MJ-Kor/SSAFY11th-Gwangju04-WebStudy/blob/main/MJ-Kor/Algorithms/Concepts/imgs/sliding3.png">
    
    이 방법은 O(N)의 시간 복잡도를 가지게 된다.