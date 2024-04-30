# JS 기본 정리

### JS + HTML

1. js 코드를 html 에서 불러오려면 `<script src="main.js"/>` 로 로드

### 변수

- var   : 변수의 중복선언, 재할당 가능 -> 
    - 함수 스코프 (반복문이나 조건문 내부에서 실행된 것은 밖에서도 사용이 가능함, 함수 블록만 막힘)
    - 선언과 초기화가 동시에, 그 후 할당
- let   : 변수의 중복 선언 불가, 재할당 가능 -> 
    - 블록 스코프
    - 선언, 초기화, 할당 분리되어 실행
- const : 변수의 중복선언, 재할당 둘다 불가능 -> 
    - 블록 스코프
    - 선언, 초기화, 할당이 동시에 실행

**변수의 생성 과정**
1. 선언단계
2. 초기화 단계
3. 할당 단계


### 자료형

**typeof(~) 키워드로 자료형 확인 가능**

- 원시타입 (primitive type)

    1. 숫자형 (number) -> Infinity, NaN 값 존재, 모든 타입 숫자 다 받음 (소수점 연산 근사치 조심!!!)
    2. 문자열형 (string) -> 이스케이프문 = \
    3. 논리형 (boolean) -> true/false
    4. null -> 명시적으로 할당해서 사용자가 임의로 비워놓은 것
    5. undefined -> 초기화 되지 않은 값
    6. Symbol

- 참조 타입

    1. 배열 (array) `[ ]` -> 개수제한, 타입 제한 없음, 접근도 같음
    2. 객체 (object) `{ }` -> key:value 형태로 사용 
        - `객체["key"]` or `객체.key` 로 접근 
        - 객체의 key 도 문자열로 작성이 가능한데 이렇게 하면 띄워쓰기는 가능하다 (.으로는 접근 불가해짐)
    3. 함수 (function) `function() { }` -> 일련의 공통작업을 위한 코드들을 모아놓은 자료형
        - 함수 선언식 `function [함수명]() { ~ }`
        - 함수 표현식 `const [변수명] = function [함수명] () { ~ }`
        - 함수도 JS의 자료형 중 하나이므로 다른 자료형과 똑같이 사용될 수 있다!
        - 주로 const 로 선언된다


### 화살표 함수

ES6 부터 사용 가능한 함수 선언 방식

function, 함수 식별자를 제거 - 이는 함수 표현식 방법으로 식별자를 넣어줌

한 줄일 경우 중괄호와 return 생략이 가능함, 매개변수도 하나일 경우 생략 가능, 매개변수 없다면 ()

내부 객체 반환시는 괄호로 한번 더 감싸야 함!! 내부함수 양식도 있음

```js
const sum1 = (num1, num2) => { return num1 + num2} ;
const sum2 = (num1, num2) => num1 + num2;
const pow = x => x * x;
const pi = () => 3.14;
const object = () => ({name: `Lee`, age = 26});
const outer = (x) => () => x * x;
```
### 템플릿 문자열

백틱을 이용해 값을 넣어줌 `문자열` 방식

이스케이프 안써도 되고 ${변수명 } 으로 변수 대입 가능


### 비교연산자

    ==  : 동등 연산 (10 == '10' 도 같은 값으로 나옴 자료형 일치시켜 비교)
    === : 일치 연산 (10 === '10' 시 false, 자료형도 비교)


### 생성자 함수

```javaScript
function User(name, age) {
    this.name = name;
    this.age = age;
}

let user1 = new User("Kim", 99);
```

첫글자를 대문자로 사용 
    
this 라는 빈 객체를 생성해 그 객체에 값을 세팅한후 그 this 를 반환함!
    
이는 new 예약어를 붙이면 이처럼 동작하게됨
    
이를 화살표 함수로 하면 안됨, 화살표 함수는 this를 가지고 있지 않음 (즉 new 와 같이 사용이 불가능하다!)

### Computed property
(계산된 프로퍼티)
```js
let a = 'age';
const user = { name: 'mike', [a] : 30, ["h" + "i"] : "hello" } // age: 30, hi: "hello"
```

### 객체 기본 메서드

- Object.assign() : 객체 복제 (참조값이 아닌 깊은 복사 가능)
- Object.keys() : 키 배열 반환
- Object.value() : 값 반환
- Object.entries() : 키/값 배열 반환
- Object.fromEntries() : 앞과 반대 상황 -> 배열에 key value 형태면 객체로 변환

```js
// assign()
const newUser = Object.assign({}, user); // 앞의 {}는 초기값 지정 가능 - 객체에 프로퍼티 추가, 같으면 덮어씌워짐
const newInfo = Object.assign(info1, info2, info3) // 1 에 2, 3 값을 순서대로 덮어씌움

// fromEntries()
let arr = [
    ['mon', '월'],
    ['tue', '화']
]
const result = Object.fromEntries(arr); // {mon: "월", tue: "화"}

```

### Symbol

객체의 property key 는 문자형 이므로 `ojb['1']`로 obj {1: "~"} 변수에 접근이 가능함!!

Symbol 은 유일성을 보장! 이는 다른 객체를 사용할때 **중복되지 않은 변수명** 을 사용해 활용하기 위함!!!

Symbol.for() : 전역 심볼
- 하나의 심볼만 보장받을 수 있다 (없으면 만들고 있으면 가져옴)
- Symbol 함수는 매번 다른 Symbol 생성하지만 Symbol.for 메서드는 하나를 생성한 뒤 **키를 이용해 공유!!!**
```js
const id = Symbol('id');
const user = {
    name: "Mike",
    age: 30,
    [id] : 'myId'
} // {name: "Mike", age: 30, Symbol(id): "myId"}
Object.keys(user); // {name, age} -> 심볼 안보여줌
Object.getOwnPropertySymbols(user) // [Symbol(id)]

// 사용 예시 -> user 객체에 Symbol("show name") 으로 함수 등록!!
const showName = Symbol("show name");
user[showName] = function() {
    console.log(this.name);
}
user[showName](); // Mike 출력됨!!

```

