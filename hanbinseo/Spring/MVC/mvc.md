# Spring MVC에 대한 공부를 기록하는 공간입니다

<details>
  <summary>Section 0, 1</summary>  
  
  - 웹 서버
    - HTTP 기반으로 동작
    - Client → Server : Request
    - Server → Client : Response
  - 서블릿
    - urlPatterns(url)로 정의한 url을 호출 시 서블릿 코드 실행
    - HTTP 요청 정보를 편리하게 사용할 수 있는 HttpServletRequest
    - HTTP 응답 정보를 편리하게 제공할 수 있는 HttpServletResponse
    - 서블릿 컨테이너
        - 서블릿 객체를 싱글톤으로 관리
        - 동시 요청을 위한 멀티 쓰레드 처리 지원
- 쓰레드
    - Application 코드를 하나하나 순차적으로 실행하는 것 → 쓰레드
    - 동시 처리가 필요하면 쓰레드 추가로 생성
    - 요청 마다 쓰레드 생성
        - 장점
            1. 동시 요청 처리 가능
            2. 리소스(CPU, 메모리)가 허용할 때 까지 처리 가능
            3. 하나의 쓰레드가 지연 되어도, 나머지 쓰레드는 정상 동작
        - 단점
            1. 생성 비용이 매우 비쌈 → 요청시마다 생성하면 응답 속도가 늦어짐
            2. 컨텍스트 스위칭 비용 발생
            3. 생성에 제한이 없어서 요청이 너무 많아질 시 서버가 죽을 수 있음
    - 쓰레드 풀
        - 특징
            - 필요한 쓰레드를 쓰레드 풀에 보관, 관리
            - 쓰레드 풀에 생성 가능한 쓰레드의 최대치를 관리
                - 톰캣은 최대 200개 기본 설정 (변경 가능)
        - 사용
            - 쓰레드 필요 시, 생성되어 있는 쓰레드를 풀에서 꺼내서 사용
            - 사용 종료 시 풀에 반납
            - 모두 사용중으로 풀에 쓰레드가 없을 시 기다리는 요청 거절하거나 특정 수만큼 대기하도록 설정 가능                
        - 장점
            - 쓰레드가 미리 생성되어 있으므로, 생성하고 종료하는 비용 절감, 응답 빠름
            - 생성 가능한 쓰레드의 최대치가 있으므로 요청 과다시 기존 요청은 안전하게 처리 가능
        - 실무 팁
            - WAS의 주요 튜닝 포인트 → 최대 쓰레드 수
                - 낮을 시 → 동시 요청이 많으면 서버 리소스는 여유롭지만 클라이언트는 금방 응답 지연
                - 높을 시 → 동시 요청이 많으면 CPU, 메모리 리소스 임계점 초과로 서버 다운
            - 장애 발생 시
                - 클라우드면 일단 서버부터 늘리고 이후에 튜닝
                - 아니면 열심히 튜닝
</details>

<details>
  <summary>Section 2</summary>
  
  > 인증
    ![section2](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/2b27f4e9-a01e-4014-93b2-3ffb32559439)
</details>

<details>
  <summary>Section 3</summary>
  
  > 인증
    ![section3](https://github.com/hanbinseo/SSAFY11th-Gwangju04-WebStudy/assets/79882952/0e6ad273-a577-493b-8827-1a8c42ac2048)
    
  - WEB-INF 안에 생성된 jsp는 주소창에 직접 입력해도 실행되지 않고 controller를 통해야만 접근이 가능함
</details>

<details>
  <summary>Section 4</summary>
</details>

<details>
  <summary>Section 5</summary>
</details>

<details>
  <summary>Section 6</summary>
</details>

<details>
  <summary>Section 7</summary>
</details>
