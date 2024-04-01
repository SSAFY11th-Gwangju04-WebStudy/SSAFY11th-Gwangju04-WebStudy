package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2638 {
    private static int n,m;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while(true){
            count++;
            visited = new boolean[n][m];
            outLine(0,0);

            visited = new boolean[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(arr[i][j] == 1){
                        melt(i,j);
                    }
                }
            }
            if(check()){
                break;
            }
        }
        System.out.println(count);
    }//main end

    private static void outLine(int x,int y){
        visited[x][y] = true;
        arr[x][y] = 2;

        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && arr[nx][ny] != 1){
                outLine(nx,ny);
            }
        }
    }//outLine end

    private static void melt(int x,int y){
        int count = 0;
        visited[x][y] = true;
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]){
                if(arr[nx][ny] == 2){
                    count++;
                }else if(arr[nx][ny] == 1){
                    melt(nx,ny);
                }
            }
        }
        if(count >= 2){
            arr[x][y] = 2;
        }
    }

    private static boolean check(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1){
                    return false;
                }
            }
        }
        return true;
    }//check end
}//class end
