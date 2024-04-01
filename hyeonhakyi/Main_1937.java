package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1937 {
    private static int n;
    private static int[][] arr;
    private static int[][] dp;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        dp = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                max = Math.max(max,dfs(i,j));
            }
        }

        System.out.println(max);
    }//main end

    private static int dfs(int x,int y){
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        dp[x][y] = 1;

        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                if(arr[x][y] < arr[nx][ny]){
                    dp[x][y] = Math.max(dp[x][y],dfs(nx,ny)+1);
                    dfs(nx,ny);
                }
            }
        }
        return dp[x][y];
    }//dfs end
}//class end
