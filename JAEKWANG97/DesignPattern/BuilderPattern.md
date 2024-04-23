# Builder 패턴을 사용하는 이유와 장단점

- 한 클래스에 많은 멤버 변수가 있다는 가정
- 근데 상속 받은 클래스는 저 멤버 변수를 다 필요로 하지 않음
- 근데 슈퍼 클래스에서 여러 생성자를 만든다? 이건 쫌..
>점층적 생성자 패턴도 쓸 수는 있지만, 매개 변수 개수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.

- 그래서 사용할 수 있는 자바 빈즈 패턴이 있음.
- 매개변수가 없는 생성자로 객체를 만든 후 세터들로 매개변수 값을 설정하는 방식임
- 하지만 setter를 통한 초기화는 권장되지 않음
> 자바 빈즈 패턴은 일관성이 깨지고 ==불변으로 만들 수 없다.==
> 객체 하나를 만드려면 메서드를 여러 개 호출해야한다.
> 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태이다.


- 점층적 생성자 패턴의 안전성과 자바 빈즈 패턴의 가독성을 겸비한 빌더 패턴의 등장
- 클라이언트는 필수 매개변수만으로 생성자를 호출해 빌더 객체를 얻음
- 빌더 객체가 제공하는 일종의 세터 메서드들로 원하는 선택 매개변수들을 설정
- 매개변수가 없는 build 메서들르 호출, 필요한 객체 획득

### 하지만

- 객체를 만들려면 그에 앞서 빌더 부터 만들어야함
- 빌더 생성 비용이 크지는 않지만 성능에 민감한 상황에서는 문제가 될 수 있음
- 매개변수가 4개 이상은 되어야 값어치를 함 (API는 시간이 지날수록 매개변수가 많아지는 경향이 있음)
- 

```java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // 필수 매개변수
        private final int servingSize;
        private final int servings;

        // 선택 매개변수 - 기본값으로 초기화
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;

        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int val) {
            calories = val;
            return this;
        }

        public Builder fat(int val) {
            fat = val;
            return this;
        }

        public Builder sodium(int val) {
            sodium = val;
            return this;
        }

        public Builder carbohydrate(int val) {
            carbohydrate = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}

```
