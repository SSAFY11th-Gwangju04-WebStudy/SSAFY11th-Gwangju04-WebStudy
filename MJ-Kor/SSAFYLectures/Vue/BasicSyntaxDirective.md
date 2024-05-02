## Directive

- ‘v-’ 접두사가 있는 특수 속성
1. v-text
    - {{ }}와 같은 기능
2. v-html
    - html의 형태로 표현
3. v-bind
    - 참조하는 ref() 객체의 변수를 가지고 와라
4. v-model
5. v-once
    - 한 번만 값을 설정하고 바뀌는 값에 대해 반응하지 말 것
6. v-if v-elseif  v-else
    - 조건에 만족하지 않으면 렌더링이 아예 안됨
7. v-show
    - 일단 렌더링하고, 조건에 맞지 않으면 안보이게 만듦

## Directive 특징

- Directive의 속성 값은 단일 JS 표현식이어야 함 (v-for, v-on 제외)
- 표현식 값이 변경될 때 DOM에 반응적으로 업데이트를 적용
- 예시
    
    ```html
    <!--> v-if는 seen의 표현식 값의 T/F를 기반으로 <p> 요소를 제거/삽입
    <p v-if="seen"> Hi There </p>
    ```
    

## Directive 전체 구문

> v-on : submit.prevent=”onSubmit”
> 

- prevent - form 태그가 가지고 있는 submit을 끄는 기능. 끄지 않으면 submit이 form에서 한 번, onSubmit에서 한 번 총 2번이 일어난다.

## Directive - Arguments

- 일부 directive는 directive 뒤에 : 으로 표시되는 인자를 사용할 수 있음
- 아래 예시는 href는 HTML a 요소의 href 속성 값을 myUrl 값에 바인딩하도록 하는 v-bind의 인자
    
    ```html
    <a v-bind:href="myUrl">Link</a>
    ```
    
- 아래 예시의 click은 이벤트를 수신할 이벤트 이름을 작성하는 v-on의 인자
    
    ```html
    <button v-on:click="doSomething">Button</button>
    ```
    
- v-bind는 매우 자주 사용되기 때문에 : 앞에 생략할 수 있다.
    
    ```html
    <img :src="imageSrc"/>
    ```
    

## Directive - Modifiers

- .(dot)으로 표시되는 특수 접미사로, directive가 특별한 방식으로 바인딩되어야 함을 나타냄
- 예를 들어 .prevent는 발생한 이벤트에서 event.preventDefault()를 호출하도록 v-on에 지시하는 modifier
    
    ```html
    <form @submit.prevent="onSubmit">...</form>
    ```
