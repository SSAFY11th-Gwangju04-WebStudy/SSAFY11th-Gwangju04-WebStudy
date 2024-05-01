## 전날 복습

1. CDN 연결
2. Vue Instance 생성 → createApp, ref, 등등
    - ref 객체
    - method
    - computed
    - watch
    
    ⇒ 이러한 Vue 객체들을 return 해줘야 한다.
    
3. 화면(App)을 할당하고 Vue Instance와 연결 → mount

- ref()
    1. 사용법
        - ref()에 primitive type, reference type의 데이터를 넣어 선언한다.
            
            ```html
            import { ref } from 'vue';
            
            const count = ref(0);
            ```
            
    2. 반응성 데이터 제공
        - ref로 선언된 변수의 값이 변경이 되면 ,해당 값을 사용하는 Vue 컴포넌트의 view도 자동으로 업데이트 된다.
    3. ref 속성을 통한 변수 접근
        - ref에 선언한 수에 접근하기 위해 value 속성을 사용한다.
        - {{   }}를 사용하면 value의 속성을 사용하지 않고 접근할 수 있다.
        
        ```html
        import { ref } from 'vue';
        
        const count = ref(0);
        
        console.log(count.value);
        ```
        
    4. component reference 제공
        - component에 대한 reference를 제공할 수 있다.
            
            ```html
            <template>
            	<div ref="myDiv">
            		<p>Hello, World!</p>
            	</div>
            </template>
            
            <script>
            import { ref } from 'vue';
            
            export default {
              setup() {
                const myDiv = ref(null);
            
                // myDiv.value는 <div> 엘리먼트의 레퍼런스를 가리킵니다.
                console.log(myDiv.value);
            
                return {
                  myDiv,
                };
              },
            };
            </script>
            ```
            
- reactive()
    1. 사용법
        - reactive() 안에 obj(reference type)만 가능하다.
        - ref와 달리 value로 값에 접근하지 않는다.

## Template Syntax

- DOM을 기본 구성 요소 인스턴스의 데이터에 선언적으로 바인딩(Vue Instance와 DOM을 연결)할 수 있는 HTML 기반 템플릿 구문을 사용

## V-

- v- 라는 prefix를 사용하면 directive(지시자)이다.
1. v-text
    - {{ }}와 같은 기능
2. v-html
    - html의 형태로 표현
3. v-bind
4. v-model
5. v-once
    - 한 번만 값을 설정하고 바뀌는 값에 대해 반응하지 말 것
6. v-if v-elseif  v-else
    - 조건에 만족하지 않으면 렌더링이 아예 안됨
7. v-show
    - 일단 렌더링하고, 조건에 맞지 않으면 안보이게 만듦

## Template Syntax 종류

1. Text Interpolation
2. Raw HTML
3. Attribute Bindings
4. JavaScript Expressions

## Text Interpolation ( {{  }}, v-text)

- 데이터 바인딩의 가장 기본적인 형태
- 이중 중괄호 구문( {{   }} )을 사용
- 콧수염 구문은 해당 구성 요소 인스턴스의 msg 속성 값으로 대체
- msg 속성이 변경될 때마다 업데이트 됨

## Raw HTML

- {{   }}는 데이터를 일반 텍스트로 해석하기 때문에 실제 HTML을 출력하려면 v-html을 사용해야 한다.

```html
<div v-html="rawHtml"></div>

const rawHtml = ref('<span style="color:red">This should be red.</span>')
```

## Attribute Bindings

- {{   }}은 HTML 속성 내에서 사용할 수 없기 때문에 v-bind를 사용
- HTML의 id 속성 값을 vue의 dynamicId 속성과 동기화 되도록 함 - 단방향
- 바인딩 값이 null이나 undefind인 경우 렌더링 요소에서 제거됨

```html
<!-- dynamicId의 위치에 들어가는 값은 단순 문자열이 아닌 JS 변수 (ref)이다. -->
<div v-bind:class="dynamicId">안녕하세요</div>

const dynamicId = ref("my-id")

<!-- bind는 단방향 -->
```

## JavaScript Expressions

- Vue는 모든 데이터 바인딩 내에서 JS 표현식의 모든 기능을 지원
- Vue 템플릿에서 JS 표현식을 사용할 수 있는 위치
    1. {{  }} 구문 내부
    2. 모든 directive의 속성 값(v-로 시작하는 특수 속성)
- 주의 사항
    - 각 바인딩에는 하나의 단일 표현식만 포함될 수 있음
        - 표현식은 값으로 평가할 수 있는 코드 조각 (return 뒤에 사용할 수 있는 코드)
    - 작동하지 않는 경우
    
    ```html
    <!-- 표현식이 아닌 선언식 -->
    {{ const number = 1 }}
    <!-- 흐름제어도 작동하지 않음. 삼항 표현식을 사용 -->
    {{ if (ok) { return message } }}
    ```
