# Day 4

- Single-File Components
    - Component
        - 재사용 가능한 코드 블록
    - Component 특징
        - UI를 독립적이고 재사용 가능한 일부분으로 분할하고 각 부분을 개별적으로 다룰 수 있음
        - 그러면 자연스럽게 앱은 중첩된 Component의 트리로 구성됨
    - Component 예시
        - 웹 서비스는 여러 개의 Component로 이루어져 있음
    
    ## SFC
    
    - Single-File Components
        - 컴포넌트의 템플릿,로직 및 스타일을 하나의 파일로 묶어낸 특수한 파일 형식(*.vue 파일)
    - SFC 파일 예시
        - Vue SFC는 HTML, CSS 및 JavaScript 3개를 하나로 합친 것
        - <template>, <script> 및 <style>블록은 하나의 파일에서 컴포넌트의 뷰, 로직 및 스타일을 캡슐화하고 배치
            
    - SFC 문법 개요
        - 각 *.vue 파일은 세 가지 유형의 최상위 언어 블록 <template>,<script>,<style>으로 구성됨
        - 언어 블록의 작성 순서는 상관 없으나 일반적으로 template → script → style 순서로 작성
            
    - 언어 블록 - <template>
        - 각 *.vue 파일은 최상위 <template> 블록을 하나만 포함할 수 있음
            
    - 언어 블록 - <script setup>
        - 각 *.vue 파일은 하나의 <script setup> 블록만 포함할 수 있음 (일반 <script> 제외)
        - 컴포넌트의 setup() 함수로 사용되며 컴포넌트의 각 인스턴스에 대해 실행
            
    - 언어 블록 - <style scope>
        - *.vue 파일에는 여러 <style> 태그가 포함될 수 있음
        - scoped가 지정되면 CSS는 현재 컴포넌트에만 적용
            
- SFC build tool
    - Vite
        - 프론트 엔드 개발 도구
            
            ⇒ 빠른 개발 환경을 위한 빌드 도구와 개발 서버를 제공
            
- NPM
    - Node Package Manager
        - Node.js의 기본 패키지 관리자
            
    - Node.js의 영향
        - 기존에 브라우저 안에서만 동작할 수 있었던 JavaScript를 브라우저가 아닌 서버 측에서도 실행할 수 있게 함
            
            ⇒ 프론트엔드와 백엔드에서 동일한 언어로 개발할 수 있게 됨
            
        - NPM을 활용해 수 많은 오픈 소스 패키지와 라이브러리를 제공하여 개발자들이 손쉽게 코드를 공유하고 재사용할 수 있게 함
- 모듈과 번들러
    - Module
        - 프로그램을 구성하는 독립적인 코드 블록 (*.js 파일)
        - 개발하는 애플리케이션의 크기가 커지고 복잡해지면서 파일 하나에 모든 기능을 담기가 어려워 짐
        - 따라서 자연스럽게 파일을 여러 개로 분리하여 관리를 하게 되었고, 이때 분리된 파일 각각이 모듈 즉, js 파일 하나가 하나의 모듈
        - 모듈의 수가 많아지고 라이브러리 혹은 모듈 간의 의존성(연결성)이 깊어지면서 특정한 곳에서 발생한 문제가 어떤 모듈 간의 문제인지 파악하기 어려워 짐
        - 복잡하고 깊은 모듈의 의존성 문제를 해결하기 위한 도구가 필요
            
            ⇒ Bundler
            
    - node_modules의 의존성 깊이
        
        
    - Bundler
        - 여러 모듈과 파일을 하나(혹은 여러 개)의 번들로 묶어 최적화하여 애플리케이션에서 사용할 수 있게 만들어주는 도구
    - Bundler의 역할
        - 의존성 관리, 코드 최적화, 리소스 관리 등
        - Bundler가 하는 작업 Bundling이라 함
        - Vite는 Rollup이라는 Bundler를 사용하며 개발자가 별도로 기타 환경설정에 신경 쓰지 않도록 모두 설정해두고 있음
- Vue Component
    
    ## Component 활용
    
    - 컴포넌트 사용 2단계
        1. 컴포넌트 파일 생성
        2. 컴포넌트 등록(import)
    - 사전 준비
        1. 초기에 생성된 모든 컴포넌트 삭제 (App.vue 제외)
        2. App.vue 코드 초기화
            
        - MyComponent.vue생성
            
        - App 컴포넌트에 MyComponent를 등록 - 1
        - App 컴포넌트에 MyComponent를 등록 - 2
            - App(부모) - Mycomponent(자식) 관계 형성
            - @ - ‘src/’경로를 뜻하는 약어
                
                
        - 결과 확인
            
            
        - MyComponentItem 컴포넌트 등록 후 활용 - 1
            - MyComponentItem은 MyComponent의 자식 컴포넌트
                
        - MyComponentItem 컴포넌트 등록 후 활용 - 2
            - 컴포넌트의 재사용성 확인
                
- Virtual DOM
    - Virtual DOM
        - 가상의 DOM을 메모리에 저장하고 실제 DOM과 동기화하는 프로그래밍 개념
        - 실제 DOM과의 변경 사항 비교를 통해 변경된 부분만 실제 DOM에 적용하는 방식
        - 웹 애플리케이션의 성능을 향상시키기 위한 Vue의 내부 렌더링 기술
            
    - 내부 렌더링 과정
        
    - Virtual DOM 패턴의 장점
        - 효율성
            - 실제 DOM 조작을 최소화하고, 변경된 부분만 업데이트하여 성능을 향상
        - 반응성
            - 데이터의 변경을 감지하고, Virtual DOM을 효율적으로 갱신하여 UI를 자동으로 업데이트
        - 추상화
            - 개발자는 실제 DOM 조작을 Vue에게 맡기고 컴포넌트와 템플릿을 활용하는 추상화된 프로그래밍 방식으로 원하는 UI 구조를 구성하고 관리할 수 있음
    - Virtual DOM 주의사항
        - 실제 DOM에 직접 접근하지 말 것
            - JavaScript에서 사용하는 DOM 접근 관련 메서드 사용 금지
            - querySelector, createElement, addEventListener 등
        
        ⇒ Vue의 ref와 Lifecycle Hooks 함수를 사용해 간접적으로 접근하여 조작할 
        
    - 직접 DOM 엘리먼트에 접근해야 하는 경우
        - ref 속성을 사용하여 특정 DOM 엘리먼트에 직접적인 참조를 얻을 수 있음
            
- Composition API & Option API
    - 2가지 API 스타일
        - Composition API
            - import해서 가져온 API 함수들을 사용하여 컴포넌트의 로직을 정의
            - Vue3 에서의 권장 방식
                
        - Option API
            - data, methods 및 mounted 같은 객체를 사용하여 컴포넌트의 로직을 정의
            - Vue2에서의 작성 방식
                
        
- 참고
    - 모든 컴포넌트에는 최상단 HTML 요소가 작성되는 것이 권장
        - 가독성, 스타일링, 명확한 컴포넌트 구조를 위해 각 컴포넌트에는 최상단 HTML 요소를 작성해야 함(Single Root Element)
            
    - “관심사항의 분리가 파일 유형의 분리와 동일한 것이 아니다”
        - HTML/CSS/JS를 한 파일에 혼합하는 게 괜찮을까?
        - 프론트-엔드 앱의 사용 목적이 점점 더 복잡해짐에 따라, 단순 파일 유형으로만 분리하게 될 경우 프로젝트의 목표를 달성 하는데 도움이 되지 않게 됨
            