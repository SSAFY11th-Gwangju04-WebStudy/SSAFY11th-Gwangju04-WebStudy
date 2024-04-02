## Fibonacci With Matrix

> 배경

- 보통 피보나치 연산은 memoization 기법을 통해 $O(N)$시간 만에 해결할 수 있지만, N의 크기가 매우 커질수록 실행 시간이 길어진다.
- 피보나치 연산을 행렬로 한다면 $O(log(N))$ 시간 복잡도를 갖는다.
  <br><br>

> 문제 상황

- 매우 큰 N번째의 피보나치 수를 구할 경우
- 나누는 수가 매우 커서 pisano period를 사용할 수 없을 경우
  <br><br>

> 피보나치 수열 정의

$$
\begin{matrix}
F_{n+2} = F_{n+1} + F_n  \newline
\therefore  F_{n+2} = (1 \quad 1) \ {F_{n+1} \choose F_n}
\end{matrix}
$$

<br><br>

> 일반적 성질

$$
\begin{matrix}
F_{n+1} = 1 \cdot F_{n+1} + 0 \cdot F_n 
\newline
\therefore  F_{n+1} = (1 \quad 0) \ {F_{n+1} \choose F_n}
\end{matrix}
$$

<br><br>

> 일반화

- 수열 정의와 일반적 성질을 합치면 아래와 같은 식이 도출된다.

$$
\begin{matrix}
{F_{n+2} \choose F_{n+1}} = \begin{pmatrix} 1 & 1 \\ 1 & 0 \end{pmatrix} {F_{n+1} \choose F_n}
\\
let \quad M_n = {F_{n+1} \choose F_{n}}
\\
\therefore M_{n+1} = \begin{pmatrix} 1 & 1 \\ 1 & 0 \end{pmatrix} M_n \quad(n \ge 0)
\end{matrix}
$$

- 위 식을 n을 넣어 전개해주면 아래와 같은 일반항을 도출할 수 있다.

$$
\begin{matrix}
M_{n} = \begin{pmatrix} 1 & 1 \\ 1 & 0 \end{pmatrix}^n {1 \choose 0}
\end{matrix}
$$

<br><br>

> 구현

```jsx
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FibonacciMatrix {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());

		long[][] origin = {{1, 1}, {1, 0}};

		System.out.println(fibonacciWithMatrix(origin, N - 1));

	}

    // 행렬을 이용하여 피보나치 값을 구하기
	private static long fibonacciWithMatrix(long[][] origin, long N) {

        // matmul을 사용하기 위해 M_0을 2X2 행렬로 잡아주었다.
		long[][] m = {{1, 0}, {1, 0}};

		long[][] mat = divideAndConquerPower(origin, N);

		long result = matmul(mat, m)[1][0];

		return result;
	}

    // 분할정복을 이용한 거듭제곱
	private static long[][] divideAndConquerPower(long[][] origin, long exp) {
		if(exp == 1 || exp == 0) {
			return origin;
		}

		long[][] res = divideAndConquerPower(origin, exp / 2);

		res = matmul(res, res);

		if(exp % 2 == 1) {
			res = matmul(res, origin);
		}

		return res;
	}

    // 행렬의 곱셈 연산
	private static long[][] matmul(long[][] A, long[][] B) {

		long[][] result = new long[2][2];

		result[0][0] = A[0][0] * B[0][0] + A[0][1] * B[1][0];
		result[0][1] = A[0][0] * B[0][1] + A[0][1] * B[1][1];
		result[1][0] = A[1][0] * B[0][0] + A[1][1] * B[1][0];
		result[1][1] = A[1][0] * B[0][1] + A[1][1] * B[1][1];

		return result;
	}

}

```
