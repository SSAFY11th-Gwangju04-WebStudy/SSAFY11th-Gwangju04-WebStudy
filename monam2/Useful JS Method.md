update 24.04.10

## Object shorthand assignment(객체 초기자, 객체 초기 할당)

[객체 초기자 - JavaScript | MDN](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Object_initializer)

```jsx
let name = "mike";
let age = 20;

let person ={
	name,
	age,
};

//let person = {
	//name : name,
	//age : age,
//}; 와 같은 것을 의미
```

객체에 넣는 요소가 이미 선언된 변수와 동일한 속성을 가질 경우, 객체리터럴 선언시 변수 이름을 통해 키와 값을 선언할 수 있다. 

단, 선언된 변수와 키가 같을 경우만 사용할 수 있다. 만약 변수와 키가 다르다면 사용할 수 없다.

## Destructuring (구조 분해 할당)

[구조 분해 할당 - JavaScript | MDN](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Destructuring_assignment)

구조 분해 할당은 배열과 객체에 모두 적용가능하다.

```jsx
const numbers = [1, 2, 3, 4, 5];

const [a, b, ...rest] = numbers;

console.log(a); // 1
console.log(b); // 2
console.log(rest); // [3, 4, 5]

const person = { name: 'Alice', age: 30, city: 'New York' };

const { name, age, city } = person;

console.log(name); // 'Alice'
console.log(age); // 30
console.log(city); // 'New York'
```

배열엔 적용시엔 []를, 객체에 적용시엔 {}를 사용하며, 구조 분해 할당 내의 변수들은 앞에 선언되는 var, let, const 키워드의 속성을 가진다. (let이면 똑같이 let을 적용받음)

구조 분해 할당은 할당되는 변수명과 객체의 속성 이름이 일치해야 하지만, 할당하려면 변수명을 변경할 수도 있다.

```jsx
const person = { name: 'Bob', age: 25 };

const { name: fullName, age: personAge } = person;

console.log(fullName); // 'Bob'
console.log(personAge); // 25
```

…(spread operator)와 함께 사용시 배열이나 객체에서 필요한 데이터만 추출해 사용할 수 있어 유용하게 사용된다.

## Spread Operator (전개 구문)

[전개 구문 - JavaScript | MDN](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Operators/Spread_syntax)

```jsx
let personconst arr1 = [1, 2, 3];
const arr2 = [4, 5, 6];

const combinedArray = [...arr1, ...arr2];
console.log(combinedArray); // [1, 2, 3, 4, 5, 6]

const obj1 = { foo: 'bar', x: 42 };
const obj2 = { foo: 'baz', y: 13 };

const mergedObject = { ...obj1, ...obj2 };
console.log(mergedObject); // { foo: 'baz', x: 42, y: 13 }
```

배열이나 문자열 같은 `iterable` 한 객체들을 펼쳐서 개별 요소로 분리한다. 이를 통해 배열을 복사하거나 여러 배열을 결합할 수 있다. 객체 리터럴에서도 사용할 수 있다.

## Rest Parameter(나머지 매개변수)

[나머지 매개변수 - JavaScript | MDN](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Functions/rest_parameters)

```jsx
function sum(...theArgs) {
  let total = 0;
  for (const arg of theArgs) {
    total += arg;
  }
  return total;
}

console.log(sum(1, 2, 3));
// Expected output: 6

console.log(sum(1, 2, 3, 4));
// Expected output: 10

```

`…theArgs` 매개변수는 함수에 전달된 모든 인수를 배열로 수집한다. 따라서 `sum` 함수는입력으로 들어오는 수가 몇 개든 그 합을 리턴하게 된다.

나머지 매개변수를 사용시 함수를 정의할 때 명시적으로 정의하지 않은 수의 인수를 처리할 수 있다. 이를 통해 함수의 유연성을 높이고 동적인 개발이 가능하다.
