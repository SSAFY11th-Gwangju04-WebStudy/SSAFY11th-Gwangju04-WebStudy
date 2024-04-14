## Index

> Index란

- 테이블에 대한 동작(select)의 속도를 높여주는 자료 구조
- 책의 찾아보기와 같이 원하는 내용을 바로 찾을 수 있도록 지원
- 테이블의 데이터 조회 시 동작속도를 높여주는 자료구조
- 데이터의 위치를 빠르게 찾아주는 역할
- 컬럼의 값과 레코드가 저장된 주소를 키와 값의 쌍으로 인덱스를 만들어 둠
- MYI(MySQL Index) 파일에 인덱스 저장

> Index의 문제점

- 불필요한 인덱스를 만들면 DB가 차지하는 공간만 늘어나고 전체 테이블을 찾는 것보다 느려짐
- DB의 공간을 차지하므로 추가적인 공간 필요
- 처음 index를 생성하는데 많은 시간 소요
- 데이터 변경 작업이 자주 일어나는 경우 오히려 성능 저하가 일어날 수 있다.

> Index의 종류 - 클러스터형 인덱스

- 특정 나열된 데이터들을 일정 기준으로 정렬해주는 인덱스
- 클러스터형 인덱스 생성 시 데이터 페이지 전체가 다시 정렬 >> 이미 대용량의 데이터가 입력된 상태라면 클러스터형 인덱스 생성시 심각한 부하가 발생.
- 테이블당 하나만 생성 가능, 어느 열에 클러스터형 인덱스를 생성하는지에 따라 성능이 달라짐
- 보조 인덱스보다 검색 속도는 더 빠르다. 단, 입력/수정/삭제는 더 느림
- MySQL의 경우 Primary Key가 있다면 Primary Key를 클러스터형 인덱스로, 없다면 unique 하면서 Not Null인 컬럼을, 그것도 없으면 임의로 보이지 않는 컬럼을 만들어 클러스터형 인덱스로 지정

> Index의 종류 - 클러스터형 인덱스_보조 인덱스

- 개념적으로 후보키에만 부여 가능한 index.
- 보조 인덱스 생성시 데이터 페이지는 그냥 둔 상태에서 별도의 페이지에 인덱스를 구성
- 데이터가 위치하는 주소값
- 클러스터형 인덱스보다 검색 속도는 느리지만 데이터의 입력/수정/삭제 시 성능 부하가 적음
- 보조 인덱스는 테이블당 여러 개 생성 가능

> 제약 조건에 따른 index 결정

- 특정 테이블에 Primary Key가 존재하면서 Unique Key가 존재할 경우
    - Primary Key로 지정된 컬럼은 Clustered index가 됨
    - Unique Key로 지정된 컬럼들은 Secondary index가 됨
    - Unique Key로 지정된 컬럼이 NULL을 허용하던 허용하지 않던 상관없이 모두 Secondary index가 됨
- 특정 테이블에 Primary Key가 존재하지 않으며 특정 컬럼에 UNIQUE + NOT NULL 제약 조건이 지정될 경우
    - 해당 컬럼은 Clustered index가 됨
    - NOT NULL 조건이 없다면 Secondary index가 됨

> Index 생성 전략

- 인덱스는 열 단위에 생성
- where 절에서 사용되는 열에 생성
- where 절에 사용되는 열이라도 자주 사용해야 가치가 있음
- 데이터 중복도가 높은 열에는 인덱스를 만들어도 효과가 없음
- 외래키를 설정한 열에는 자동으로 외래키 인덱스가 생성됨
- 조인에 자주 사용되는 열에는 인덱스를 생성하는 것이 좋음
- 데이터 변경 작업이 얼마나 자주 일어나는지를 고려해야 함
- 클러스터형 인덱스는 테이블당 하나만 생성할 수 있음
- 사용하지 않는 인덱스는 제거

> 자동 생성 Index

- 자동으로 생성되는 index
    - 클러스터형 인덱스
        - Primary Key를 설정하면 자동으로 해당 열에 클러스터형 인덱스가 생성.
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/82fb530e-42c8-401a-85a3-9bf21277d6a1/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/10b4de2f-0df8-4164-bde7-b0932d71ca48/Untitled.png)
        

> 제약 조건으로 자동 생성되는 Index : index_test1.sql

- 테이블 만들고 자동으로 생성된 인덱스 확인
    - test_tbl1 테이블 생성하고 a열을 기본키로 설정
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/3a298938-60a1-4886-a866-5832ccffeedb/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/76f34b7e-adfc-4f6e-aab8-2b4e1c4dc7cc/Untitled.png)
    
    - 테이블 만들고 자동으로 생성된 인덱스 확인
        - test_tbl2 테이블 생성하고 기본키와 unique 제약 조건 설정
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/7971b7f2-2332-431c-a1b6-17c0b1c0b757/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/0faf44bf-2130-44e5-8cdc-fdd4e8b4cb95/Untitled.png)
        
    - 테이블 만들고 자동으로 생성된 인덱스 확인
        - test_tbl3 테이블 생성하고 기본키와 unique 제약 조건 설정
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/91ac3708-2d3e-4214-85fb-250f98a6ea07/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/f4b01cc2-b1dc-4b41-8d34-668dd3efbc60/Untitled.png)
        
    - 테이블 만들고 자동으로 생성된 인덱스 확인
        - test_tbl4 테이블 생성하고 기본키와 unique 제약 조건 설정
            
            ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/90e9b12d-71e9-4750-8ef7-fbc300391a36/Untitled.png)
            
            ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/b2dde88d-4c2c-4869-82df-8245a2bf8645/Untitled.png)
            
    - 테이블 만들고 자동으로 생성된 인덱스 확인
        - test_tbl5 테이블 생성하고 기본키와 unique 제약 조건 설정
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/0801f5b7-8ff1-4a26-a9dd-a97334837d3e/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/b42f570b-94ef-4f5d-a6b5-09999c1cda53/Untitled.png)
        

#> 제약 조건으로 자동 생성되는 Index : index_test2

- 클러스터형 인덱스의 정렬 확인
    - ssafy table을 생성하고 user_id를 Primary Key로 설정(클러스터형 인덱스 생성)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/ac978912-e360-40a4-bef1-e99020e4cd86/Untitled.png)
    
    - 데이터를 입력하고 기본 정렬 확인
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/719df7c6-b613-448b-9f31-5a7c04a6f6c9/Untitled.png)
    
    - user_id의 Primary Key를 제거하고 user_name을 PrimaryKey로 설정
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/9c56eb5d-96d9-41cb-be93-0238fb7958f1/Untitled.png)

## Index 생성

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/0f2885bd-ea53-403d-98a1-ca4205d45d3d/Untitled.png)

- create index 문으로 인덱스를 만들면 보조 인덱스가 생성
- create index 문의 unique 옵션은 고유한 인덱스를 만들 때 사용
- ASC, DESC로 정렬 방식 지정
- index_type은 생략 가능하며, 생략할 경우 기본 값인 B-Tree 형식 사용
- create index 문으로는 클러스터형 인덱스를 만들 수 없으며, 클러스터형 인덱스를 만들려면 alter table을 사용해야 함.
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/b5e49ab2-0f42-434f-9cdf-5fa9d1c3f3ea/Untitled.png)
    

> Index 생성

- index_test2.sql 실행하여 테이블 초기화
    - ssafy 테이블 내용확인
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/cb23e932-5552-4e4b-bfa3-9c6e3eefb774/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/536f8785-d4c5-4312-8f32-8608808779e1/Untitled.png)
    
- index_test2.sql 실행하여 테이블 초기화
    - ssafy 테이블의 index 설정 확인
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/f6f289aa-a049-4a70-adcd-a87edcb8dc3a/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/9e54916f-a743-4c67-a774-a446ea445d26/Untitled.png)
        
    - 주소지역 열에 단순 보조 인덱스 생성
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/c1c84ae8-2d1d-4fce-8c6f-812d27147996/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/98a769b7-0ba3-45ed-bba3-513aa3516eab/Untitled.png)
        
- index_test2.sql 실행하여 테이블 초기화
    - 출생 년도 열에 고유 보조 인덱스 생성 :: error
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/f9e04964-d66f-4966-a264-22d1f8951010/Untitled.png)
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/26a7f4eb-6db9-4238-ad0b-1a6c1dc80754/Untitled.png)
        
    - 이름 열에 고유 보조 인덱스 생성 :: 정상 실행
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/ac7a2637-e4eb-41e5-b803-aca10b65f060/Untitled.png)
        
    - 이름이 같고 아이디가 다른 사용자 등록 :: error
        
        ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/609d46e8-84fd-4b9e-9a41-bd47c7d63d8e/f48d7362-3e13-42af-829b-46e7bd8e96b3/Untitled.png)
