import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486 {
    static int N, B, height[], min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            height = new int[N];
            min = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }
            dfs(0, 0);
            sb.append(min).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void dfs(int depth, int sum) {
        if (depth == N) {
            if (sum >= B) {
                min = Math.min(min, sum - B);
            }
            return;
        }
        //선택
        dfs(depth + 1, sum + height[depth]);
        //비선택
        dfs(depth + 1, sum);
    }
}
