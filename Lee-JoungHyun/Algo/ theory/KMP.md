# KMP

= 앞에 지나오면서 얻은 정보를 활용 (유의미한 비교 위치로 이동하자!)

즉 잘못된 시작을 최소화 하자!!!  부분 일치 배열로 패턴 포인터를 앞으로 이동시킴

부분일치 테이블 = 실패함수..?

1. 실패함수, pi 배열, 이라 불리는것을 만들자!
    1. 이는 해당 패턴의 앞과 뒤가 같은(유사 펠린드롬) 길이를 찾아 저장한다
    2. 이를 이용해 text와 비교시 다르면 pattern의 비교 시작 위치를 지정할 수 있다

```java
private static int[] makeP(char[] pattern) {
        int[] pi = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pi.length; i++) {

            while (j != 0 && pattern[j] != pattern[i]) {
                j = pi[j - 1];
            }

            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }
        //System.out.println(Arrays.toString(pi));
        return pi;
    }
```

1. 실패함수를 이용해 문자열의 패턴을 찾는다!
    1. 같으면 j++ 하고 다음 문자 보기 (패턴이 이동하지 않음)
    2. 다르면 실패함수를 이용해 뒤로 이동 (같은것을 찾을 떄 까지)
        1. 같은것을 못찾으면 0부터 비교 시작
        2. 같은것을 찾으면 그것부터 계산
    3. j == pattern.length 가 되면 찾음! 정답 처리하고 뒤로 돌려주기

```java
int j = 0;
        for (int i = 0; i < text.length; i++) {
            while (j != 0 && text[i] != pattern[j]) {
                j = p[j-1];
            }
            if (text[i] == pattern[j]) {
                j++;
            }
            if (j == pattern.length) {
                ans++;
                sb.append(i - j + 1).append(" ");
                j = p[j-1];
            }
            //System.out.println(i + " " + j);
        }

```
