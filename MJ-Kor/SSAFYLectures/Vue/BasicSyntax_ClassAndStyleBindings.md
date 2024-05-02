## Class and Style Bindings

- 클래스와 스타일은 모두 속성이므로 v-bind를 사용하여 다른 속성과 마찬가지로 동적으로 문자열 값을 할당할 수 있음
- 그러나 단순히 문자열 연결을 사용하여 이러한 값을 생성하는 것은 번거롭고 오류가 발생하기가 쉬움
- Vue는 클래스 및 스타일과 함께 v-bind를 사용할 때 객체 또는 배열을 활용한 개선 사항을 제공

⇒ ref에 객체, 배열 가능

## Binding HTML Classes - Binding to Objects

- 객체를 :class에 전달하여 class를 동적으로 전환할 수 있음

```html
const isActive = ref(false)
<div :class="{ active: isActive }"> Text </div>
```

- 객체에 더 많은 필드를 포함하여 여러 클래스를 전환할 수 있음

```html
const isActive = ref(false)
const hasInfo = ref(true)
<div class="static" :class="{ active: isActive . 'text-primary': hasInfo}"> Text </div>
```

- 반드시 inline 방식으로 작성하지 않아도 됨

```html
const isActive = ref(false)
const hasInfo = ref(true)

const classObj = ref({
	active: isActive,
	'text-primary': hasInfo
})

<div class="static" :class="classObj"> Text </div>
```

## Binding HTML Classes - Binding to Arrays

- :class를 배열에 바인딩하여 클래스 목록을 적용할 수 있음

```html
const activeClass = ref('active')
const infoClass = ref('text-primary)
<div :class="[activeClass, infoClass"> Text </div>
```

- 배열 구문 내에서 객체 구문 사용 가능

## Binding Inline Styles - Binding to Object

- :style은 JS 객체 값에 대한 바인딩을 지원

```html
const activeColor = ref('crimson')
const fontSize = ref(50)
<div :class="{ color: activeColor, fontSize: fontSize + 'px' }"> Text </div>
```

- 실제 CSS에서 사용하는 것처럼 :style은 kebab-cased 키 문자열도 지원
- 템플릿을 더 깔끔하게 작성하려면 스타일 객체에 직접 바인딩하는 것을 권장

```html
const styleObj = ref({
	color: activeColor,
	fontSize: fontSize.value + 'px'
})
<div :class="styleObj"> Text </div>
```

## Binding Inline Styles - Binding to Arrays

- 여러 스타일 객체의 배열에 :style을 바인딩할 수 있음
- 작성한 객체는 병합되어 동일한 요소에 적용

```html
const styleObj = ref({
	color: activeColor,
	fontSize: fontSize.value + 'px'
})

const styleObj2 = ref({
	color: blue,
	border: '1px solid black'
})
<div :class="[styleObj, styleObj2]"> Text </div>
```
