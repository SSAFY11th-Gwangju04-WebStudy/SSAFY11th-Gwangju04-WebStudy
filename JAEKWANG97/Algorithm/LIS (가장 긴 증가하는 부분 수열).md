
# DP

# 이진 탐색

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        // LIS를 저장할 ArrayList 생성
        List<Integer> sub = new ArrayList<>();

        for (int num : nums) {
            // num의 위치를 이진 탐색으로 찾기
            int pos = Collections.binarySearch(sub, num);

            // 찾은 위치가 음수라면, 실제 삽입 위치는 -(pos + 1)
            if (pos < 0) {
                pos = -(pos + 1);
            }

            // 만약 pos가 sub의 크기와 같다면, 새로운 요소 추가
            if (pos == sub.size()) {
                sub.add(num);
            } else {
                // 기존 위치의 값을 현재 값으로 교체
                sub.set(pos, num);
            }
        }

        // sub의 크기가 LIS의 길이
        return sub.size();
    }

    public static void main(String[] args) {
        int[] nums = {3, 10, 2, 3, 4};
        int length = lengthOfLIS(nums);
        System.out.println("Length of LIS is: " + length);
    }
}
```

### 코드 설명

1. **ArrayList `sub` 초기화**: 이 배열은 부분적으로 완성된 LIS를 저장합니다.
2. **각 요소에 대한 반복**: 입력 배열 `nums`의 각 요소에 대해 이진 탐색을 수행합니다.
3. **이진 탐색**: `Collections.binarySearch(sub, num)`를 호출하여 `num`이 삽입될 위치를 찾습니다. 찾은 위치가 음수인 경우, 삽입할 위치는 `-(pos + 1)`입니다.
4. **요소 삽입 및 교체**: 찾은 위치 `pos`가 `sub`의 크기와 같다면 요소를 추가합니다. 그렇지 않으면 해당 위치의 값을 현재 값으로 교체합니다.
5. **결과 출력**: `sub.size()`는 LIS의 길이를 반환하며, 이 값이 출력됩니다.
