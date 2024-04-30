## 선언 및 할당

- ES5까지는 var로 정의
- ES6부터 let과 const의 추가

1. Scope
    - var의 경우 function scope
    - let과 const의 경우 block scope
2. 재할당 및 재선언
    - var - 재할당 및 재선언 가능
    - let - 재할당 가능, 재선언 불가
    - const - 재할당 및 재선언 불가

## 단축 속성명(Property Shorthand) - ES6 추가
```jsx
const name = 'mike';
const obj = {
    age : 21,
    name,    // 단축속성명
    getName() {return this.name}
}
```

## 구조 분해 할당(DestructuringAssignment)

- 배열 또는 객체의 값을 여러 변수로 분해하여 할당하는 것을 쉽게 할 수 있습니다. 또한 함수의 매개변수로도 사용할 수 있습니다.

1. Array

```jsx
const array = [1, 2, 3];
const [a, b, c] = array;

console.log(a);
console.log(b);
console.log(c);
```

1. Object

```jsx
const object = { name: 'Yoon', age: 33 };
const { name, age } = object;

console.log(name); // Yoon
console.log(age); // 33
```

1. Function Parameter

```jsx
const logData = ({ brand, model }) => {
  console.log(brand, model); // Tesla, 3
};
logData({ brand: 'Tesla', model: 3 });
```

1. Alias

```jsx
const object = { address: 'street-1', zipcode: '123-45' };
const { address: x, zipcode: y } = object;
console.log(x, y);
```

## 전개 구문(SpreadSyntax) - ES6 추가

- 배열 또는 객체의 값을 쉽게 펼칠 수 있다.

1. Array

```jsx
const array1 = [ 1, 2, 3 ];
console.log(...array1); // 1 2 3

const array2 = [ 4, 5 ,6 ];
const combineArray = [ ...array1, ...array2 ];
console.log(combineArray); // [ 1, 2, 3, 4, 5, 6 ]
```

1. Object

```jsx
const { name } = { name: 'Kim', age: 34 };
console.log(name); // Kim

const object1 = { name: 'Yoon', age: 33 };
const object2 = { job: 'student' };
const combinedObject = { ...object1, ...object2 };
console.log(combinedObject); // { name: 'Yoon', age: 33, job: 'student' }
```

## Default Parameter

- 함수에 전달된 파라미터의 값이 undefined이거나 전달된 값이 없을 때, 초기화 설정된 값을 말한다.

```jsx
// ES6 이전
function multiply(a, b) {
  b = (typeof b !== 'undefined') ?  b : 1
  return a*b
}

multiply(5, 2)   // 10
multiply(5)      // 5

// ES6 이후
function multiply(a, b = 1) {
  return a*b
}

multiply(5, 2)          // 10
multiply(5)             // 5
multiply(5, undefined)  // 5
```

## Template String: 백틱(`) - ES6 추가

- 작은 따옴표나 큰 따옴표 대신 백틱으로 감싸줍니다.

1. 표현식 삽입법

```jsx
// ES6 이전
var a = 20;
var b = 8;
var c = "자바스크립트";
var str = "저는 " + (a + b) + "살이고 " + c + "를 좋아합니다.";
console.log(str);   //저는 28살이고 자바스크립트를 좋아합니다.

// ES6 이후
let a = 20;
let b = 8;
let c = "자바스크립트";
let str = `저는 ${a+b}살이고 ${c}를 좋아합니다.`;
console.log(str);   //저는 28살이고 자바스크립트를 좋아합니다.
```

1. Multi-line strings

```jsx
// ES6 이전
console.log("string text line 1\n" + "string text line 2");
//string text line 1
//string text line 2

// ES6 이후
console.log(`string text line 1
string text line 2`);
```

1. Raw strings

```jsx
// ES6 
let s = String.raw`xy\n${1+1}z`;
console.log(s);     //xy\n2z

// 태그 함수를 만들어 원래의 문자열을 반환하려면 첫 번째 인자의 raw 프로퍼티를 사용
let tag = function(strings) {
    return strings.raw[0];
}

let str = tag`Hello\nWorld.`;
console.log(str);       //Hello\nWorld.
```

1. 중첩 템플릿

```jsx
// ES6 이전
var classes = 'header'
classes += (isLargeScreen() ? 
            '' : item.isCollapsed ? 
            ' icon-expander' : ' icon-collapser');

// ES6 이후 중첩 템플릿 미사용
const classes = `header ${ isLargeScreen() ? '' :
                (item.isCollapesd ? 'icon-expander' : 'icon-collapser')}`;

// ES6 이후 중첩 템플릿 사용
const classes = `header ${ isLargeScreen() ? '' :
                `icon-${item.isCollapsed ? 'expander' : 'collapser'}` }`;
```

## 화살표 함수(Arrow Function) - ES6 추가

- 기존 function에 비해 코드가 간결해지고, this 키워드를 자동으로 바인딩한다.

```jsx
// ES5
var sum = function(x, y) {
		return x + y
};

// ES6
const sum = (x, y) => x + y;
```
