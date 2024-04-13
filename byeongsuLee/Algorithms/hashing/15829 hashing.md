# 문제

[15829번: Hashing](https://www.acmicpc.net/problem/15829)

---

# 접근 방법

첫번째 방법으로 math.pow()로 했는데 50점나왔다.

math pow 대신  ri 값을 long, r = r*31 하는식으로 바꿨다.

 

---

# 배운 내용

- hasing은 거듭제곱을 이용하기때문에 Math.pow를 이용하면 계산량이 많아지고 실수형태라 정확도와 시간복잡도 측면에서 사용하기 부적절하다.
- sum,ri 는 수의 범위를 넘을수있으니 %m를 해줍시다.

---

# 어려웠던 점

---

# 구현 코드

```jsx
package day0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_hashing {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long r = 1;
		int M = 1234567891;
		
		long sum=0;
		String str= br.readLine();
	
		//수의 범위를 벗어날 수도있기떄문에 모듈러 특성에 따라 
		//(A+B) %M  =  (A%M  + B%M ) %M 같다.
		// (ri*num)%M = (ri %M * num%M) %M
		for(int i = 0 ;i<N;i++) {
			int num =  ((str.charAt(i)-'a')+1)%M;
			if(i==0) r= 1;
			else {
			r = r%M *31%M;}
			sum +=((r%M*num%M )%M);
			
		}
		System.out.println(sum%M);
		
	
	}

}

```

---
