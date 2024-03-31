package ex0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12100 {
    private static int n,max;
    private static int[][] arr;
    private static int[][] copy;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int[] direct;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];
        direct = new int[5];

        for(int i = 1; i <= n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);

        System.out.println(max);
    }//main end

    private static void dfs(int count){
        if(count == 5){
            confirm();
        }else{
            for(int i = 0 ; i < 4; i++){
                direct[count] = i;
                dfs(count+1);
            }
        }
    }//dfs end

    private static void confirm(){
        copy = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            copy[i] = arr[i].clone();
        }

        for(int d = 0; d < direct.length; d++){
            visited = new boolean[n+1][n+1];

            if(direct[d] == 0) { // 상
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        move(i,j,direct[d]);
                    }
                }
            }else if(direct[d] == 1){ // 하
                for(int i = n; i >= 1; i--){
                    for(int j = 1; j <= n; j++){
                        move(i,j,direct[d]);
                    }
                }
            }else if(direct[d] == 2){ // 좌
                for(int i = 1; i <= n; i++){
                    for(int j = 1; j <= n; j++){
                        move(j,i,direct[d]);
                    }
                }
            }else{ // 우
                for(int i = n; i >= 1; i--){
                    for(int j = 1; j <= n; j++){
                        move(j,i,direct[d]);
                    }
                }
            }
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                max = Math.max(max,copy[i][j]);
            }
        }
    }//confirm end

    private static void move(int x, int y, int dir){
        if(copy[x][y] == 0){
            return;
        }

        while (true){
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 1 || ny < 1 || nx > n|| ny > n){
                return;
            }

            if(visited[nx][ny]){
                return;
            }

            if(copy[nx][ny] == copy[x][y]){
                visited[nx][ny] = true;
                copy[nx][ny] *= 2;
                copy[x][y] = 0;
                return;
            }else if(copy[nx][ny] != 0){
                return;
            }
            copy[nx][ny] = copy[x][y];
            copy[x][y] = 0;
            x = nx;
            y = ny;
        }
    }//move end
}//class end
