package ex0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502 {
    private static class Virus{
        int x;
        int y;
        public Virus(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    private static int n,m,result;
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
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);

        System.out.println(result);
    }//main end

    private static void dfs(int count){
        if(count == 3){
            result = Math.max(bfs(),result);
            return;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 0 && !visited[i][j]){
                    arr[i][j] = 1;
                    visited[i][j] = true;
                    dfs(count+1);
                    visited[i][j] = false;
                    arr[i][j] = 0;
                }
            }
        }
    }//dfs end

    private static int bfs(){
        Queue<Virus> que = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 2){
                    que.offer(new Virus(i,j));
                }
            }
        }

        int[][] copyArr = copy();

        while(!que.isEmpty()){
            Virus now = que.poll();

            for(int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(check(nx,ny) && copyArr[nx][ny] == 0){
                    que.offer(new Virus(nx,ny));
                    copyArr[nx][ny] = 2;
                }
            }
        }
        return total(copyArr);
    }//bfs end

    private static int total(int[][] arr){
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }//total end

    private static boolean check(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }//check end

    private static int[][] copy(){
        int[][] copy = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                copy[i][j] = arr[i][j];
            }
        }
        return copy;
    }
}//class end
